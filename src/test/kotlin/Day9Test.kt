import org.junit.jupiter.api.Test
import kotlin.io.path.readText
import kotlin.test.assertEquals

internal class Day9Test {

    val day9 = Day9()

    @Test
    fun part1_testInput() {
        val input = readResourceFile("advent-of-code-2022/day-9-test").readText()

        val output: Int = day9.solvePart1(input)

        assertEquals(13, output)
    }

    @Test
    fun part1_fullInput() {
        val input = readResourceFile("advent-of-code-2022/day-9").readText()

        val output: Int = day9.solvePart1(input)

        assertEquals(5695, output)
    }

    @Test
    fun part2_testInput() {
        val input = readResourceFile("advent-of-code-2022/day-9-test").readText()

        val output: Int = day9.solvePart2(input)

        assertEquals(1, output)
    }

    @Test
    fun part2_fullInput() {
        val input = readResourceFile("advent-of-code-2022/day-9").readText()

        val output: Int = day9.solvePart2(input)

        assertEquals(2434, output)
    }
}