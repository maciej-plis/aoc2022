class Day6 {

    fun solvePart1(input: String) = solve(input, 4)

    fun solvePart2(input: String) = solve(input, 14)

    private fun solve(input: String, sequenceSize: Int) = input.windowedSequence(sequenceSize).indexOfFirst { it.allUnique() } + sequenceSize

    private fun CharSequence.allUnique() = toSet().count() == length
}