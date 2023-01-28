class Day10 {

    fun solvePart1(input: String) = cycleSequence(input.lines())
        .filter { it.first % 40 - 20 == 0 }
        .sumOf { it.first * it.second }

    fun solvePart2(input: String): Int {
        return 0
    }

    private fun cycleSequence(instructions: List<String>) = sequence {
        var cycle = 2
        var value = 1
        instructions.forEach {
            if (it != "noop") {
                yield(cycle to value)
                cycle++
                value += it.removePrefix("addx ").toInt()
            }
            yield(cycle to value)
            cycle++
        }
    }
}