import kotlin.math.abs
import kotlin.math.max

class Day15 {

    private val numberRegex = "-?\\d+".toRegex()

    fun solvePart1(input: String, line: Int): Int {
        val sensors = parseSensors(input)
        val beacons = sensors.map { it.beacon }.toSet()

        val beaconsAtLine = beacons.count { it.y == line }

        return sensors.mapNotNull { it.getCoverageOnLine(line) }.merge().sumOf { it.size } - beaconsAtLine
    }

    fun solvePart2(input: String, max: Int): Long {
        val sensors = parseSensors(input)

        for (line in 0..max) {
            val coverage = sensors.mapNotNull { it.getCoverageOnLine(line) }.merge()
            if (!coverage.any { it.contains(0..max) }) return (coverage.first().last+1) * 4_000_000L + line
        }

        return -1
    }

    private fun List<IntRange>.merge(): List<IntRange> {
        val ranges = this.sortedBy { it.first }
        var start = ranges.first().first
        var end = ranges.first().last

        val result = mutableListOf<IntRange>()
        for (range in ranges) {
            if (range.first <= end + 1) {
                end = max(range.last, end)
            } else {
                result.add(start..end)
                start = range.first
                end = range.last
            }
        }
        result.add(start..end)

        return result
    }

    private operator fun IntRange.contains(range: IntRange) = range.first in this && range.last in this

    private val IntRange.size
        get() = abs(last - first) + 1

    private fun parseSensors(input: String) = input.lines()
        .map { numberRegex.findAll(it).map { it.value.toInt() }.toList() }
        .map { Sensor(Coordinates(it[0], it[1]), Coordinates(it[2], it[3])) }

    data class Sensor(val position: Coordinates, val beacon: Coordinates) {

        val coveredRange = calculateDistance(position, beacon)

        fun getCoverageOnLine(y: Int): IntRange? {
            val pointOnLine = Coordinates(position.x, y)
            val distanceToPoint = calculateDistance(position, Coordinates(position.x, y))
            val span = coveredRange - distanceToPoint
            if (span < 0) return null

            return pointOnLine.x - span..pointOnLine.x + span
        }

        fun isCovered(coordinates: Coordinates) = calculateDistance(position, coordinates) <= coveredRange

        private fun calculateDistance(a: Coordinates, b: Coordinates) = abs(a.x - b.x) + abs(a.y - b.y);
    }
}