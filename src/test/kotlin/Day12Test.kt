import org.junit.jupiter.api.Test
import kotlin.io.path.readText
import kotlin.test.assertEquals

internal class Day12Test {

    val day12 = Day12()

    @Test
    fun part1_testInput() {
        val input = readResourceFile("advent-of-code-2022/day-12-test").readText()

        val output: Int = day12.solvePart1(input)

        assertEquals(31, output)
    }

    @Test
    fun part1_fullInput() {
        val input = readResourceFile("advent-of-code-2022/day-12").readText()

        val output: Int = day12.solvePart1(input)

        assertEquals(520, output)
    }

    @Test
    fun part2_testInput() {
        val input = readResourceFile("advent-of-code-2022/day-12-test").readText()

        val output: Int = day12.solvePart2(input)

        assertEquals(29, output)
    }

    @Test
    fun part2_fullInput() {
        val input = readResourceFile("advent-of-code-2022/day-12").readText()

        val output: Int = day12.solvePart2(input)

        assertEquals(508, output)
    }
}