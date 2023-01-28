import org.junit.jupiter.api.Test
import kotlin.io.path.readText
import kotlin.test.assertEquals

internal class Day3Test {

    val day3 = Day3()

    @Test
    fun part1_testInput() {
        val input = readResourceFile("advent-of-code-2022/day-3-test").readText()

        val output: Int = day3.solvePart1(input)

        assertEquals(157, output)
    }

    @Test
    fun part1_fullInput() {
        val input = readResourceFile("advent-of-code-2022/day-3").readText()

        val output: Int = day3.solvePart1(input)

        assertEquals(7_742, output)
    }

    @Test
    fun part2_testInput() {
        val input = readResourceFile("advent-of-code-2022/day-3-test").readText()

        val output: Int = day3.solvePart2(input)

        assertEquals(70, output)
    }

    @Test
    fun part2_fullInput() {
        val input = readResourceFile("advent-of-code-2022/day-3").readText()

        val output: Int = day3.solvePart2(input)

        assertEquals(2_276, output)
    }
}