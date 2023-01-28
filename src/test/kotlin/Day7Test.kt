import org.junit.jupiter.api.Test
import kotlin.io.path.readText
import kotlin.test.assertEquals

internal class Day7Test {

    val day7 = Day7()

    @Test
    fun part1_testInput() {
        val input = readResourceFile("advent-of-code-2022/day-7-test").readText()

        val output: Int = day7.solvePart1(input)

        assertEquals(95437, output)
    }

    @Test
    fun part1_fullInput() {
        val input = readResourceFile("advent-of-code-2022/day-7").readText()

        val output: Int = day7.solvePart1(input)

        assertEquals(1581595, output)
    }

    @Test
    fun part2_testInput() {
        val input = readResourceFile("advent-of-code-2022/day-7-test").readText()

        val output: Int = day7.solvePart2(input)

        assertEquals(24933642, output)
    }

    @Test
    fun part2_fullInput() {
        val input = readResourceFile("advent-of-code-2022/day-7").readText()

        val output: Int = day7.solvePart2(input)

        assertEquals(1544176, output)
    }
}