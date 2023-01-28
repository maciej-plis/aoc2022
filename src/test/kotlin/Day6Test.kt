import org.junit.jupiter.api.Test
import kotlin.io.path.readText
import kotlin.test.assertEquals

internal class Day6Test {

    val day6 = Day6()

    @Test
    fun part1_testInput() {
        val input = readResourceFile("advent-of-code-2022/day-6-test").readText()

        val output: Int = day6.solvePart1(input)

        assertEquals(7, output)
    }

    @Test
    fun part1_fullInput() {
        val input = readResourceFile("advent-of-code-2022/day-6").readText()

        val output: Int = day6.solvePart1(input)

        assertEquals(1848, output)
    }

    @Test
    fun part2_testInput() {
        val input = readResourceFile("advent-of-code-2022/day-6-test").readText()

        val output: Int = day6.solvePart2(input)

        assertEquals(19, output)
    }

    @Test
    fun part2_fullInput() {
        val input = readResourceFile("advent-of-code-2022/day-6").readText()

        val output: Int = day6.solvePart2(input)

        assertEquals(2308, output)
    }
}