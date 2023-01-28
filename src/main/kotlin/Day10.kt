import kotlin.math.abs

class Day10 {

    fun solvePart1(input: String) = cycleSequence(input.lines())
        .filter { it.first % 40 - 20 == 0 }
        .sumOf { it.first * it.second }

    fun solvePart2(input: String) = buildString {
        cycleSequence(input.lines(), 0).forEach {
            if (it.first % 40 == 0) append('\n')
            val isSprite = abs(it.second - (it.first % 40)) <= 1
            if (isSprite) append('#') else append('.')
        }
    }

    private fun cycleSequence(instructions: List<String>, startingCycle: Int = 1) = sequence {
        var cycle = startingCycle
        var value = 1
        instructions.forEach {
            yield(cycle++ to value)
            if (it == "noop") return@forEach
            yield(cycle++ to value)
            value += it.removePrefix("addx ").toInt()
        }
    }
}