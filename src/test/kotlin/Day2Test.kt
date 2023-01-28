import org.junit.jupiter.api.Test
import kotlin.io.path.readText
import kotlin.test.assertEquals

internal class Day2Test {

    val day2 = Day2()

    @Test
    fun part1_testInput() {
        val input = readResourceFile("advent-of-code-2022/day-2-test").readText()

        val output: Int = day2.solvePart1(input)

        assertEquals(15, output)
    }

    @Test
    fun part1_fullInput() {
        val input = readResourceFile("advent-of-code-2022/day-2").readText()

        val output: Int = day2.solvePart1(input)

        assertEquals(11_449, output)
    }

    @Test
    fun part2_testInput() {
        val input = readResourceFile("advent-of-code-2022/day-2-test").readText()

        val output: Int = day2.solvePart2(input)

        assertEquals(12, output)
    }

    @Test
    fun part2_fullInput() {
        val input = readResourceFile("advent-of-code-2022/day-2").readText()

        val output: Int = day2.solvePart2(input)

        assertEquals(13_187, output)
    }
}