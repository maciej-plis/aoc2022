import java.lang.IllegalArgumentException

class Day11 {

    fun solvePart1(input: String): Int {
        val monkeys = input.lines().chunked(7).map(Monkey::parse)

        repeat(20) {
            monkeys.forEach { it.inspectAllItems(monkeys) }
        }

        return monkeys.map(Monkey::inspections).sorted().takeLast(2).reduce { acc, i -> acc * i }
    }

    fun solvePart2(input: String): Int {
        return 0
    }

    private data class Monkey(
        val items: ArrayDeque<Int>,
        val inspectItem: (worry: Int) -> Int,
        val whereToThrow: (worry: Int) -> Int,
        var inspections: Int = 0
    ) {

        fun inspectAllItems(monkeys: List<Monkey>) {
            while(items.isNotEmpty()) {
                inspections++
                var item = items.removeFirst()
                item = inspectItem(item)
                item /= 3
                monkeys[whereToThrow(item)].items.addLast(item)
            }
        }

        companion object {
            fun parse(lines: List<String>): Monkey {
                val items = lines[1].drop(18).split(",").map { it.trim().toInt() }
                val inspectItem = lines[2].removePrefix("  Operation: new = old ").let { getEquation(it[0], it.drop(2)) }
                val whereToThrow = { worry: Int -> if(worry % lastInt(lines[3]) == 0) lastInt(lines[4]) else lastInt(lines[5])}
                return Monkey(ArrayDeque(items), inspectItem, whereToThrow)
            }

            fun getEquation(operation: Char, value: String) = when (operation) {
                '+' -> { worry: Int -> worry + parseValue(value, worry) }
                '*' -> { worry: Int -> worry * parseValue(value, worry) }
                else -> throw IllegalArgumentException("Unknown operation: $operation")
            }

            fun parseValue(value: String, worry: Int): Int = if(value == "old") worry else value.toInt()

            fun lastInt(line: String) = line.substringAfterLast(" ").toInt()
        }
    }
}