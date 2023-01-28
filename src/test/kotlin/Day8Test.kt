import org.junit.jupiter.api.Test
import kotlin.io.path.readText
import kotlin.test.assertEquals

internal class Day8Test {

    val day8 = Day8()

    @Test
    fun part1_testInput() {
        val input = readResourceFile("advent-of-code-2022/day-8-test").readText()

        val output: Int = day8.solvePart1(input)

        assertEquals(21, output)
    }

    @Test
    fun part1_fullInput() {
        val input = readResourceFile("advent-of-code-2022/day-8").readText()

        val output: Int = day8.solvePart1(input)

        assertEquals(1829, output)
    }

    @Test
    fun part2_testInput() {
        val input = readResourceFile("advent-of-code-2022/day-8-test").readText()

        val output: Int = day8.solvePart2(input)

        assertEquals(8, output)
    }

    @Test
    fun part2_fullInput() {
        val input = readResourceFile("advent-of-code-2022/day-8").readText()

        val output: Int = day8.solvePart2(input)

        assertEquals(291840, output)
    }
}