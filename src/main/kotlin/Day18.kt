class Day18 {

    fun solvePart1(input: String): Int {

        val cubes = HashSet<Coordinates>()
        var openSides = 0

        input.lineSequence()
            .map { it.toIntList(",") }
            .map { Coordinates(it[0], it[1], it[2]) }
            .forEach {
                it.adjacent.forEach { if (it in cubes) openSides -= 2 }
                cubes += it
                openSides += 6
            }

        return openSides
    }

    fun solvePart2(input: String): Int {

        val cubes = input.lineSequence()
            .map { it.toIntList(",") }
            .map { Coordinates(it[0], it[1], it[2]) }
            .toHashSet()

        val xAxis = -1..cubes.maxOf { it.x } + 1
        val yAxis = -1..cubes.maxOf { it.y } + 1
        val zAxis = -1..cubes.maxOf { it.z } + 1

        val visited = HashSet<Coordinates>()
        var openSides = 0

        DeepRecursiveFunction<Coordinates, Unit> {
            if (it.x !in xAxis || it.y !in yAxis || it.z !in zAxis) return@DeepRecursiveFunction
            if (it in visited) return@DeepRecursiveFunction
            visited += it
            it.adjacent.forEach { if (it in cubes) openSides++ else callRecursive(it) }
        }(Coordinates(-1, -1, -1))

        return openSides
    }

    private data class Coordinates(val x: Int, val y: Int, val z: Int) {
        val adjacent: List<Coordinates>
            get() = listOf(
                Coordinates(x - 1, y, z),
                Coordinates(x + 1, y, z),
                Coordinates(x, y - 1, z),
                Coordinates(x, y + 1, z),
                Coordinates(x, y, z - 1),
                Coordinates(x, y, z + 1)
            )
    }
}

fun String.toIntList(delimiter: String = ", ") = split(delimiter).map { it.toInt() }