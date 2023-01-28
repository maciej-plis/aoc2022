import org.junit.jupiter.api.Test
import kotlin.io.path.readText
import kotlin.test.assertEquals

internal class Day1Test {

    val day1 = Day1()

    @Test
    fun part1_testInput() {
        val input = readResourceFile("advent-of-code-2022/day-1-test").readText()

        val output: Int = day1.solvePart1(input)

        assertEquals(24_000, output)
    }

    @Test
    fun part1_fullInput() {
        val input = readResourceFile("advent-of-code-2022/day-1").readText()

        val output: Int = day1.solvePart1(input)

        assertEquals(67_658, output)
    }

    @Test
    fun part2_testInput() {
        val input = readResourceFile("advent-of-code-2022/day-1-test").readText()

        val output: Int = day1.solvePart2(input)

        assertEquals(45_000, output)
    }

    @Test
    fun part2_fullInput() {
        val input = readResourceFile("advent-of-code-2022/day-1").readText()

        val output: Int = day1.solvePart2(input)

        assertEquals(200_158, output)
    }
}