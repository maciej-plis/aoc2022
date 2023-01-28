import Day2.RpsResult.*

class Day2 {

    fun solvePart1(input: String): Int {
        return input
            .lineSequence()
            .map { RpsShape.ofSymbol(it[0]) to RpsShape.ofSymbol(it[2]) }
            .sumOf { computeBattleScore(it.first, it.second) }

    }

    fun solvePart2(input: String): Int {
        return input
            .lineSequence()
            .map { RpsShape.ofSymbol(it[0]) to RpsResult.ofSymbol(it[2]) }
            .sumOf { computeBattleScore(it.first, it.second) }

    }

    private enum class RpsShape(val score: Int, val win: String, val lose: String) {
        ROCK(1, "SCISSORS", "PAPER"),
        PAPER(2, "ROCK", "SCISSORS"),
        SCISSORS(3, "PAPER", "ROCK");

        val winAgainst
            get() = valueOf(win)

        val loseAgainst
            get() = valueOf(lose)

        fun battle(shape: RpsShape): RpsResult {
            if (this == shape) return DRAW
            return if (shape == winAgainst) WIN else LOSE
        }

        companion object {
            fun ofSymbol(symbol: Char): RpsShape = when (symbol) {
                'A', 'X' -> ROCK
                'B', 'Y' -> PAPER
                'C', 'Z' -> SCISSORS
                else -> throw IllegalArgumentException("Unknown symbol")
            }
        }
    }

    private enum class RpsResult(val score: Int) {
        WIN(6),
        DRAW(3),
        LOSE(0);

        companion object {
            fun ofSymbol(symbol: Char): RpsResult = when (symbol) {
                'X' -> LOSE
                'Y' -> DRAW
                'Z' -> WIN
                else -> throw IllegalArgumentException("Unknown symbol")
            }
        }
    }

    private fun computeBattleScore(enemyShape: RpsShape, result: RpsResult): Int {
        return computeBattleScore(enemyShape, computeRequiredShape(enemyShape, result))
    }

    private fun computeRequiredShape(enemyShape: RpsShape, requiredResult: RpsResult): RpsShape {
        return when (requiredResult) {
            DRAW -> enemyShape
            WIN -> enemyShape.loseAgainst
            LOSE -> enemyShape.winAgainst
        }
    }

    private fun computeBattleScore(enemyShape: RpsShape, myShape: RpsShape): Int {
        return myShape.score + myShape.battle(enemyShape).score
    }
}