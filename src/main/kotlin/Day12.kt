class Day12 {

    fun solvePart1(input: String): Int {
        val heightMap = HeightMap(input.lines())
        val startingCoordinates = heightMap.findCoordinates('E')
        val destination = Land('S')

        return heightMap.shortestPath(startingCoordinates, destination)
    }

    fun solvePart2(input: String): Int {
        val heightMap = HeightMap(input.lines())
        val startingPos = heightMap.findCoordinates('E')
        val destination = Land('a')

        return heightMap.shortestPath(startingPos, destination)
    }
}

private val directions = listOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)

data class QueueNode(val coordinates: Coordinates, val distance: Int)

@JvmInline
value class HeightMap(private val heightMap: List<String>) {

    operator fun contains(coordinates: Coordinates) =
        coordinates.x in heightMap.indices && coordinates.y in heightMap.first().indices

    operator fun get(coordinates: Coordinates) = Land(heightMap[coordinates.x][coordinates.y])

    fun findCoordinates(searched: Char): Coordinates {
        val x = heightMap.indexOfFirst { it.contains(searched) }
        val y = heightMap[x].indexOf(searched)
        return Coordinates(x, y)
    }

    fun shortestPath(startingPos: Coordinates, destination: Land): Int {
        val visitedCoordinates = hashSetOf(startingPos)
        val queue = ArrayDeque(listOf(QueueNode(startingPos, 0)))

        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            if (this[node.coordinates] == destination) return node.distance

            neighbours(node.coordinates)
                .filter { it !in visitedCoordinates }
                .filter { get(it).isReachable(get(node.coordinates)) }
                .forEach {
                    queue.addLast(QueueNode(it, node.distance + 1))
                    visitedCoordinates.add(it)
                }
        }

        return -1
    }

    private fun neighbours(coordinates: Coordinates) = directions
        .map { Coordinates(coordinates.x + it.first, coordinates.y + it.second) }
        .filter { contains(it) }
}

@JvmInline
value class Coordinates(private val coordinates: Pair<Int, Int>) {

    val x: Int
        get() = coordinates.first

    val y: Int
        get() = coordinates.second

    constructor(x: Int, y: Int) : this(x to y)

    override fun toString() = "[X = $x, Y = $y]"
}

@JvmInline
value class Land(private val land: Char) {

    private val height: Int
        get() {
            if(land == 'S') return 0
            if(land == 'E') return 'z' - 'a'
            return land - 'a'
        }

    fun isReachable(land: Land) = land.height - height <= 1
}