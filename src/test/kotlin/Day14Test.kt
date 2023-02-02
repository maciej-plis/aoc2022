import org.junit.jupiter.api.Test
import kotlin.io.path.readText
import kotlin.test.assertEquals

internal class Day14Test {

    val day14 = Day14()

    @Test
    fun part1_testInput() {
        val input = readResourceFile("advent-of-code-2022/day-14-test").readText()

        val output: Int = day14.solvePart1(input)

        assertEquals(24, output)
    }

    @Test
    fun part1_fullInput() {
        val input = readResourceFile("advent-of-code-2022/day-14").readText()

        val output: Int = day14.solvePart1(input)

        assertEquals(825, output)
    }

    @Test
    fun part2_testInput() {
        val input = readResourceFile("advent-of-code-2022/day-14-test").readText()

        val output: Int = day14.solvePart2(input)

        assertEquals(93, output)
    }

    @Test
    fun part2_fullInput() {
        val input = readResourceFile("advent-of-code-2022/day-14").readText()

        val output: Int = day14.solvePart2(input)

        assertEquals(26729, output)
    }
}