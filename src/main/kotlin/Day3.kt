class Day3 {

    fun solvePart1(input: String): Int {
        return input
            .lineSequence()
            .map { it.splitOnHalf() }
            .map { findCommonsBetween(it.first, it.second) }
            .flatten()
            .sumOf { calculatePriority(it) }
    }

    fun solvePart2(input: String): Int {
        return input
            .lineSequence()
            .withIndex()
            .groupBy { it.index / 3 }
            .map { it.value.map { it.value } }
            .map { computeBadge(it[0], it[1], it[2]) }
            .sumOf { calculatePriority(it) }
    }

    private fun findCommonsBetween(a: String, b: String): List<Char> {
        val aSet = a.toCharArray().toHashSet()
        return b.toCharArray().filter(aSet::contains).distinct()
    }

    private fun calculatePriority(c: Char): Int {
        return if (c.isLowerCase()) c.code - 96 else c.code - 64 + 26
    }

    private fun computeBadge(a: String, b: String, c: String): Char {
        val aSet = a.toCharArray().toHashSet()
        val bSet = b.toCharArray().toHashSet()
        return c.toCharArray().first { aSet.contains(it) && bSet.contains(it) }
    }

    private fun String.splitOnHalf(): Pair<String, String> {
        if (this.length % 2 != 0) throw IllegalStateException("Cannot split string on half when it's not even")
        chunked(this.length / 2).let { return it[0] to it[1] }
    }
}