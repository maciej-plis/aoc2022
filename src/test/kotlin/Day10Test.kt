import org.junit.jupiter.api.Test
import kotlin.io.path.readText
import kotlin.test.assertEquals

internal class Day10Test {

    val day10 = Day10()

    @Test
    fun part1_testInput() {
        val input = readResourceFile("advent-of-code-2022/day-10-test").readText()

        val output: Int = day10.solvePart1(input)

        assertEquals(13140, output)
    }

    @Test
    fun part1_fullInput() {
        val input = readResourceFile("advent-of-code-2022/day-10").readText()

        val output: Int = day10.solvePart1(input)

        assertEquals(12540, output)
    }

    @Test
    fun part2_testInput() {
        val input = readResourceFile("advent-of-code-2022/day-10-test").readText()

        val output: Int = day10.solvePart2(input)

        assertEquals(-1, output)
    }

    @Test
    fun part2_fullInput() {
        val input = readResourceFile("advent-of-code-2022/day-10").readText()

        val output: Int = day10.solvePart2(input)

        assertEquals(-1, output)
    }
}