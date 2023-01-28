import org.junit.jupiter.api.Test
import kotlin.io.path.readText
import kotlin.test.assertEquals

internal class Day5Test {

    val day5 = Day5()

    @Test
    fun part1_testInput() {
        val input = readResourceFile("advent-of-code-2022/day-5-test").readText()

        val output: String = day5.solvePart1(input)

        assertEquals("CMZ", output)
    }

    @Test
    fun part1_fullInput() {
        val input = readResourceFile("advent-of-code-2022/day-5").readText()

        val output: String = day5.solvePart1(input)

        assertEquals("SBPQRSCDF", output)
    }

    @Test
    fun part2_testInput() {
        val input = readResourceFile("advent-of-code-2022/day-5-test").readText()

        val output: String = day5.solvePart2(input)

        assertEquals("MCD", output)
    }

    @Test
    fun part2_fullInput() {
        val input = readResourceFile("advent-of-code-2022/day-5").readText()

        val output: String = day5.solvePart2(input)

        assertEquals("RGLVRCQSB", output)
    }
}