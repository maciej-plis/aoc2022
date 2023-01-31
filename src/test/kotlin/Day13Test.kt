import kotlin.io.path.readText
import kotlin.test.Test
import kotlin.test.assertEquals

internal class Day13Test {

    val day13 = Day13()

    @Test
    fun part1_testInput() {
        val input = readResourceFile("advent-of-code-2022/day-13-test").readText()

        val output: Int = day13.solvePart1(input)

        assertEquals(13, output)
    }

    @Test
    fun part1_fullInput() {
        val input = readResourceFile("advent-of-code-2022/day-13").readText()

        val output: Int = day13.solvePart1(input)

        assertEquals(6656, output)
    }

    @Test
    fun part2_testInput() {
        val input = readResourceFile("advent-of-code-2022/day-13-test").readText()

        val output: Int = day13.solvePart2(input)

        assertEquals(-1, output)
    }

    @Test
    fun part2_fullInput() {
        val input = readResourceFile("advent-of-code-2022/day-13").readText()

        val output: Int = day13.solvePart2(input)

        assertEquals(-1, output)
    }
}