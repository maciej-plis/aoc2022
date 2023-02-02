import org.junit.jupiter.api.Test
import kotlin.io.path.readText
import kotlin.test.assertEquals

internal class Day15Test {

    val day15 = Day15()

    @Test
    fun part1_testInput() {
        val input = readResourceFile("advent-of-code-2022/day-15-test").readText()

        val output: Int = day15.solvePart1(input, 10)

        assertEquals(26, output)
    }

    @Test
    fun part1_fullInput() {
        val input = readResourceFile("advent-of-code-2022/day-15").readText()

        val output: Int = day15.solvePart1(input, 2_000_000)

        assertEquals(5607466, output)
    }

    @Test
    fun part2_testInput() {
        val input = readResourceFile("advent-of-code-2022/day-15-test").readText()

        val output: Long = day15.solvePart2(input, 20)

        assertEquals(56_000_011, output)
    }

    @Test
    fun part2_fullInput() {
        val input = readResourceFile("advent-of-code-2022/day-15").readText()

        val output: Long = day15.solvePart2(input, 4_000_000)

        assertEquals(12_543_202_766_584, output)
    }
}