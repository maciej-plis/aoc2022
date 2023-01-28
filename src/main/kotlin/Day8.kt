class Day8 {

    fun solvePart1(input: String): Int {
        val lines = input.lines()

        val visibilityGrid = Array(lines.size) { BooleanArray(lines.size) }
        var visibleTrees = 0

        for (i in lines.indices) {
            repeat(2) {
                var forwardIndex = 0
                var backwardIndex = lines.lastIndex

                var forwardBlocker = -1
                var backwardBlocker = -1

                var direction = true

                var row = i
                var col = forwardIndex

                val swapDirections = it == 1
                if (swapDirections) col = row.apply { row = col }

                while (forwardIndex <= backwardIndex) {
                    val treeHeight = lines[row][col].digitToInt()

                    if (direction) {
                        if (treeHeight > forwardBlocker) {
                            if (!visibilityGrid[row][col]) visibilityGrid[row][col] = true.also { visibleTrees++ }
                            forwardBlocker = treeHeight
                            if (forwardBlocker > backwardBlocker) {
                                direction = !direction
                            }
                        }
                        forwardIndex++
                    } else {
                        if (treeHeight > backwardBlocker) {
                            if (!visibilityGrid[row][col]) visibilityGrid[row][col] = true.also { visibleTrees++ }
                            backwardBlocker = treeHeight
                            if (backwardBlocker > forwardBlocker) {
                                direction = !direction
                            }
                        }
                        backwardIndex--
                    }
                    if (swapDirections) {
                        row = if (direction) forwardIndex else backwardIndex
                    } else {
                        col = if (direction) forwardIndex else backwardIndex
                    }
                }
            }
        }

        return visibleTrees
    }

    fun solvePart2(input: String): Int {
        val lines = input.lines()

        var bestScenicScore: Int = 0

        for (i in lines.indices) {
            for (j in lines[i].indices) {
                val scenicScore = calculateScenicScore(lines, i, j)
                if (scenicScore > bestScenicScore) bestScenicScore = scenicScore
            }
        }

        return bestScenicScore
    }

    private fun calculateScenicScore(lines: List<String>, line: Int, pos: Int): Int {
        val treeHeight = lines[line][pos].digitToInt()
        return scanVisibleTress(treeHeight, lines.topIterator(line, pos)) *
               scanVisibleTress(treeHeight, lines[line].leftIterator(pos)) *
               scanVisibleTress(treeHeight, lines[line].rightIterator(pos)) *
               scanVisibleTress(treeHeight, lines.bottomIterator(line, pos))
    }

    private fun scanVisibleTress(treeHeight: Int, treesIterator: Iterator<Char>): Int {
        var visibleTrees = 0
        var blocker = -1

        while (treesIterator.hasNext()) {
            visibleTrees++
            val tree = treesIterator.next().digitToInt()
            if (tree >= treeHeight) break
        }

        return visibleTrees
    }

    private fun String.leftIterator(pos: Int) = object : CharIterator() {
        private var index = pos
        override fun hasNext() = index > 0
        override fun nextChar() = get(--index)
    }

    private fun String.rightIterator(pos: Int) = object : CharIterator() {
        private var index = pos
        override fun hasNext() = index < lastIndex
        override fun nextChar() = get(++index)
    }

    private fun List<String>.topIterator(line: Int, pos: Int) = object : CharIterator() {
        private var index = line
        override fun hasNext() = index > 0
        override fun nextChar() = get(--index)[pos]
    }

    private fun List<String>.bottomIterator(line: Int, pos: Int) = object : CharIterator() {
        private var index = line
        override fun hasNext() = index < lastIndex
        override fun nextChar() = get(++index)[pos]
    }
}