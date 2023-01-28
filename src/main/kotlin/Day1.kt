class Day1 {

    fun solvePart1(input: String): Int {
        return input
            .splitToSequence("\n\n")
            .map { it.lines().sumOf(String::toInt) }
            .maxOf { it }
    }

    fun solvePart2(input: String): Int {
        return input
            .splitToSequence("\n\n")
            .map { it.lines().sumOf(String::toInt) }
            .sortedDescending()
            .take(3)
            .sum()
    }
}