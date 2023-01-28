import org.junit.jupiter.api.Test
import kotlin.io.path.readText
import kotlin.test.assertEquals

internal class Day4Test {

    val day4 = Day4()

    @Test
    fun part1_testInput() {
        val input = readResourceFile("advent-of-code-2022/day-4-test").readText()

        val output: Int = day4.solvePart1(input)

        assertEquals(2, output)
    }

    @Test
    fun part1_fullInput() {
        val input = readResourceFile("advent-of-code-2022/day-4").readText()

        val output: Int = day4.solvePart1(input)

        assertEquals(532, output)
    }

    @Test
    fun part2_testInput() {
        val input = readResourceFile("advent-of-code-2022/day-4-test").readText()

        val output: Int = day4.solvePart2(input)

        assertEquals(4, output)
    }

    @Test
    fun part2_fullInput() {
        val input = readResourceFile("advent-of-code-2022/day-4").readText()

        val output: Int = day4.solvePart2(input)

        assertEquals(854, output)
    }
}