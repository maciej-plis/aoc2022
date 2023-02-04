import org.junit.jupiter.api.Test
import kotlin.io.path.readText
import kotlin.test.assertEquals

internal class Day18Test {

    val day18 = Day18()

    @Test
    fun part1_testInput() {
        val input = readResourceFile("advent-of-code-2022/day-18-test").readText()

        val output: Int = day18.solvePart1(input)

        assertEquals(64, output)
    }

    @Test
    fun part1_fullInput() {
        val input = readResourceFile("advent-of-code-2022/day-18").readText()

        val output: Int = day18.solvePart1(input)

        assertEquals(4302, output)
    }

    @Test
    fun part2_testInput() {
        val input = readResourceFile("advent-of-code-2022/day-18-test").readText()

        val output: Int = day18.solvePart2(input)

        assertEquals(58, output)
    }

    @Test
    fun part2_fullInput() {
        val input = readResourceFile("advent-of-code-2022/day-18").readText()

        val output: Int = day18.solvePart2(input)

        assertEquals(2492, output)
    }
}