import org.junit.jupiter.api.Test
import kotlin.io.path.readText
import kotlin.test.assertEquals

internal class Day11Test {

    val day11 = Day11()

    @Test
    fun part1_testInput() {
        val input = readResourceFile("advent-of-code-2022/day-11-test").readText()

        val output: Long = day11.solvePart1(input)

        assertEquals(10_605, output)
    }

    @Test
    fun part1_fullInput() {
        val input = readResourceFile("advent-of-code-2022/day-11").readText()

        val output: Long = day11.solvePart1(input)

        assertEquals(121_450, output)
    }

    @Test
    fun part2_testInput() {
        val input = readResourceFile("advent-of-code-2022/day-11-test").readText()

        val output: Long = day11.solvePart2(input)

        assertEquals(2_713_310_158, output)
    }

    @Test
    fun part2_fullInput() {
        val input = readResourceFile("advent-of-code-2022/day-11").readText()

        val output: Long = day11.solvePart2(input)

        assertEquals(28_244_037_010, output)
    }
}