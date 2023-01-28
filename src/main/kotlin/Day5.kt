class Day5 {

    fun solvePart1(input: String): String {
        val lines = input.lines()

        val numberOfStacks = getNumberOfStacks(lines)
        val stacks = getStacks(lines, numberOfStacks)
        val commands = getCommands(lines)

        commands.forEach { command ->
            val crates = stacks[command.from].removeLast(command.amount).reversed()
            stacks[command.to].addLast(crates)
        }

        return stacks.map { it.last() }.joinToString("")
    }

    fun solvePart2(input: String): String {
        val lines = input.lines()

        val numberOfStacks = getNumberOfStacks(lines)
        val stacks = getStacks(lines, numberOfStacks)
        val commands = getCommands(lines)

        commands.forEach { command ->
            val crates = stacks[command.from].removeLast(command.amount)
            stacks[command.to].addLast(crates)
        }

        return stacks.map { it.last() }.joinToString("")
    }

    private fun getNumberOfStacks(lines: List<String>): Int {
        return lines.first { it.startsWith(" 1 ") }.trim().split("\\s+".toRegex()).size
    }

    private fun getStacks(lines: List<String>, numberOfStacks: Int): List<ArrayDeque<Char>> {
        val stacks = List(numberOfStacks) { ArrayDeque<Char>() }

        lines.takeWhile { it.trimStart().startsWith("[") }
            .forEach { line ->
                repeat(numberOfStacks) { stackIndex ->
                    val item = line[1 + (4 * stackIndex)]
                    if (item != ' ') stacks[stackIndex].addFirst(item)
                }
            }

        return stacks
    }

    private fun getCommands(lines: List<String>): List<MoveCommand> {
        return lines.takeLastWhile { it.startsWith("move") }
            .map { it.split(" ") }
            .map { MoveCommand(it[1].toInt(), it[3].toInt() - 1, it[5].toInt() - 1) }
    }

    private fun <T> ArrayDeque<T>.removeLast(n: Int): List<T> {
        val removed = ArrayList<T>(n)
        for (i in 0 until n) {
            removed += this.removeLast()
        }
        return removed.reversed()
    }

    private fun <T> ArrayDeque<T>.addLast(items: List<T>) = items.forEach { this.addLast(it) }

    private data class MoveCommand(val amount: Int, val from: Int, val to: Int)
}