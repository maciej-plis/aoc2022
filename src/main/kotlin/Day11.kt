import java.lang.IllegalArgumentException

class Day11 {

    fun solvePart1(input: String): Long {
        val monkeys = input.lines().chunked(7).map(Monkey::parse)
        return solve(monkeys, 20) { it / 3 }
    }

    fun solvePart2(input: String): Long {
        val monkeys = input.lines().chunked(7).map(Monkey::parse)
        val superMod = monkeys.fold(1L) { acc, monkey -> acc * monkey.divider }
        return solve(monkeys, 10_000) { it % superMod }
    }

    private fun solve(monkeys: List<Monkey>, iterations: Int, reliefMethod: (Long) -> Long): Long {
        repeat(iterations) {
            monkeys.forEach { it.performInspections(monkeys, reliefMethod) }
        }

        return monkeys.topBy(2, Monkey::inspections).product()
    }

    private data class Monkey(
        val items: ArrayDeque<Long>,
        val inspection: (worry: Long) -> Long,
        val whereToThrow: (worry: Long) -> Int,
        val divider: Int,
        var inspections: Long = 0
    ) {

        fun performInspections(monkeys: List<Monkey>, relief: (Long) -> Long) {
            while (items.isNotEmpty()) {
                var itemWorry = items.removeFirst()
                itemWorry = inspection(itemWorry)
                itemWorry = relief(itemWorry)
                monkeys[whereToThrow(itemWorry)].items.addLast(itemWorry)
                inspections++
            }
        }

        companion object {
            fun parse(lines: List<String>): Monkey {
                val items = lines[1].drop(18).split(",").map { it.trim().toLong() }
                val inspectItem = lines[2].drop(23).let { createInspection(it[0], it.drop(2)) }
                val divider = lines[3].lastInt()
                val whereToThrow = { worry: Long -> if (worry % divider == 0L) lines[4].lastInt() else lines[5].lastInt() }
                return Monkey(ArrayDeque(items), inspectItem, whereToThrow, divider)
            }

            private fun createInspection(operation: Char, value: String) = when (operation) {
                '+' -> { worry: Long -> worry + (value.toLongOrNull() ?: worry) }
                '*' -> { worry: Long -> worry * (value.toLongOrNull() ?: worry) }
                else -> throw IllegalArgumentException("Unknown operation: $operation")
            }

            private fun String.lastInt() = substringAfterLast(' ').toInt()
        }
    }
    private fun Iterable<Long>.product() = this.fold(1L) { acc, el -> acc * el }
    private fun <T> Iterable<T>.topBy(n: Int, transform: (T) -> Long) = this.map(transform).sortedDescending().take(n)
}
