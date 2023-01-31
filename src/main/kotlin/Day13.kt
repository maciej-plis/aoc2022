import kotlin.math.min

class Day13 {

    fun solvePart1(input: String) = parseInputToPacketPairs(input)
        .map { it[0].compareTo(it[1]) }
        .foldIndexed(0) { index, acc, result -> if (result > 0) acc + index + 1 else acc }

    fun solvePart2(input: String): Int {
        val packets = parseInputToPacketPairs(input).flatten().toMutableList()
        val dividerPacket1 = parsePacket("[[2]]").also { packets.add(it) }
        val dividerPacket2 = parsePacket("[[6]]").also { packets.add(it) }

        packets.sortDescending()

        return (packets.indexOf(dividerPacket1) + 1) * (packets.indexOf(dividerPacket2) + 1)
    }

    private sealed interface Packet : Comparable<Packet>

    private class ValuePacket(val value: Int) : Packet {

        fun asPacketList() = PacketList(mutableListOf(this))

        override fun compareTo(other: Packet) = when (other) {
            is PacketList -> asPacketList().compareTo(other)
            is ValuePacket -> compareValues(other)
        }

        override fun toString() = "$value"

        private fun compareValues(other: ValuePacket) = other.value.compareTo(this.value)
    }

    private class PacketList(val subPackets: MutableList<Packet> = mutableListOf()) : Packet {

        fun addPacket(packet: Packet) {
            subPackets.add(packet)
        }

        override fun compareTo(other: Packet) = when (other) {
            is ValuePacket -> compareLists(other.asPacketList())
            is PacketList -> compareLists(other)
        }

        override fun toString() = "$subPackets"

        private fun compareLists(other: PacketList): Int {
            for (i in 0..min(this.subPackets.lastIndex, other.subPackets.lastIndex)) {
                val first = this.subPackets[i]
                val second = other.subPackets[i]

                val result = first.compareTo(second)
                if (result != 0) return result
            }

            return other.subPackets.size.compareTo(this.subPackets.size)
        }
    }

    private fun parseInputToPacketPairs(input: String) = input
        .split("\n\n")
        .map { it.split("\n") }
        .map { it.map { parsePacket(it) } }

    private fun parsePacket(line: String): PacketList {
        val stack = ArrayDeque<PacketList>()
        val sb = StringBuilder()
        for (char in line) {
            if (char == '[') PacketList().let { packet -> stack.addLast(packet) }
            if (char.isDigit()) sb.append(char)
            if (char == ',' || char == ']') sb.toIntAndClear()?.let { stack.last().addPacket(ValuePacket(it)) }
            if (char == ']') stack.removeLast().let {
                if (stack.isEmpty()) return it
                stack.last().addPacket(it)
            }
        }
        return PacketList()
    }

    private fun StringBuilder.toIntAndClear() = toString().toIntOrNull().also { clear() }
}
