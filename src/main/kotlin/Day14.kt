import Day14.LineType.HORIZONTAL
import Day14.LineType.VERTICAL
import kotlin.math.max
import kotlin.math.min

class Day14 {

    fun solvePart1(input: String): Int {
        val rockFormations = input.lines()
            .map { it.split(" -> ") }
            .map { it.map { parsePoint(it) } }
            .map { createLines(it) }
            .flatten()

        val caveHeight = rockFormations.maxBy { max(it.start.y, it.end.y) }.let { max(it.start.y, it.end.y) } + 1
        val cave = Array(caveHeight) { BooleanArray(200) }
        rockFormations.forEach { it.forEach { cave.mark(it) } }

        printCave(cave)

        var count = 0
        var finish = false
        DeepRecursiveFunction { tile: Point ->
            if (tile.y >= caveHeight) finish = true
            if (finish || cave[tile.y][tile.x]) return@DeepRecursiveFunction

            callRecursive(Point(tile.x, tile.y + 1))
            callRecursive(Point(tile.x - 1, tile.y + 1))
            callRecursive(Point(tile.x + 1, tile.y + 1))

            if (!cave[tile.y][tile.x] && !finish) {
                cave[tile.y][tile.x] = true
                count++
            }

        }(Point(100, 0))

        printCave(cave)

        return count
    }

    fun solvePart2(input: String): Int {
        return 0
    }

    private fun printCave(cave: Array<BooleanArray>) {
        for (row in cave) {
            for (tile in row) {
                if (tile) print('#') else print('.')
            }
            println()
        }
    }

    data class RockPath(val lines: List<Line>)
    data class Point(val x: Int, val y: Int)
    enum class LineType { HORIZONTAL, VERTICAL }
    data class Line(val start: Point, val end: Point) : Iterable<Point> {
        val type = if (start.y == end.y) HORIZONTAL else VERTICAL
        override fun iterator() = object : Iterator<Point> {
            private var x = min(start.x, end.x)
            private var y = min(start.y, end.y)
            override fun hasNext() = when (type) {
                HORIZONTAL -> x <= max(start.x, end.x)
                VERTICAL -> y <= max(start.y, end.y)
            }

            override fun next() = when (type) {
                HORIZONTAL -> Point(x++, y)
                VERTICAL -> Point(x, y++)
            }
        }
    }

    private fun parsePoint(point: String) = point.split(",").let { return@let Point(it[0].toInt() - 400, it[1].toInt()) }
    private fun createLines(points: List<Point>): List<Line> {
        val lines = mutableListOf<Line>()
        var prevPoint = points.first()
        for (i in 1..points.lastIndex) {
            lines.add(Line(prevPoint, points[i]))
            prevPoint = points[i]
        }
        return lines
    }

    private fun Array<BooleanArray>.mark(point: Point) {
        this[point.y][point.x] = true
    }
}