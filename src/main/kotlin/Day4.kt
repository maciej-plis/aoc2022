class Day4 {

    fun solvePart1(input: String): Int {
        return input
            .lineSequence()
            .map { parseRanges(it) }
            .count { it.first in it.second || it.second in it.first }
    }

    fun solvePart2(input: String): Int {
        return input
            .lineSequence()
            .map { parseRanges(it) }
            .count { it.first.overlaps(it.second) }
    }

    private operator fun IntRange.contains(range: IntRange) = range.first in this && range.last in this

    private fun IntRange.overlaps(range: IntRange) = range.first in this || range.last in this || this in range

    private fun parseRanges(line: String) = line.split(",").let { rangeOf(it[0]) to rangeOf(it[1]) }

    private fun rangeOf(rangeString: String) = rangeString.split("-").let { it[0].toInt()..it[1].toInt() }
}