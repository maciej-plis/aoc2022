import kotlin.math.abs
import kotlin.math.sign

class Day9 {

    fun solvePart1(input: String) = solve(input, 1)

    fun solvePart2(input: String) = solve(input, 9)

    fun solve(input: String, knotsCount: Int): Int {
        val visited = hashSetOf(0 to 0)
        val head = Knot(0, 0)
        val knots = Array(knotsCount) { Knot(0, 0) }

        input.lineSequence().forEach {
            val direction = it[0]
            val amount = it.drop(2).toInt()

            repeat(amount) {
                head.move(direction)

                var previous: Knot = head
                for (i in knots.indices) {
                    if (previous touches knots[i]) break

                    val moveX = calculateMove(previous.x, knots[i].x)
                    val moveY = calculateMove(previous.y, knots[i].y)
                    knots[i].move(moveX, moveY)

                    previous = knots[i]
                    if (i == knots.lastIndex) visited.add(knots[i].x to knots[i].y)
                }
            }
        }

        return visited.size
    }

    private data class Knot(var x: Int, var y: Int) {
        fun move(direction: Char) {
            when(direction) {
                'R' -> x++
                'L' -> x--
                'U' -> y++
                'D' -> y--
            }
        }

        fun move(x: Int, y: Int) {
            this.x += x
            this.y += y
        }

        infix fun touches(knot: Knot) = abs(knot.x - x) <= 1 && abs(knot.y - y) <= 1

    }

    private fun calculateMove(a: Int, b: Int) = (a - b).sign
}