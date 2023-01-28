import Day7.Command.Companion.parseCommand
import kotlin.io.path.Path
import kotlin.io.path.name

class Day7 {

    fun solvePart1(input: String): Int {
        val fileSystem = recreateFileSystem(input)
        return fileSystem.lookupTree { it is Directory && it.size <= 100_000 }.sumOf { it.size }
    }

    fun solvePart2(input: String): Int {
        val fileSystem = recreateFileSystem(input)

        val missingSpace = 30_000_000 - (70_000_000 - fileSystem.root.size)
        return fileSystem.lookupTree { it is Directory && it.size >= missingSpace }.minBy { it.size }.size
    }

    private fun recreateFileSystem(input: String): FileSystem {
        val fileSystem = FileSystem()

        input.split("\n$").drop(1)
            .map { it.trim().split("\n") }
            .forEach {
                val command = parseCommand(it.first())
                if (command is Ls) command.output.addAll(it.drop(1))
                fileSystem.applyCommand(command)
            }

        return fileSystem
    }

    class FileSystem {

        var root = Directory("/")

        var currentDir = root
        var currentPath = Path("/")

        fun applyCommand(command: Command) {
            when (command) {
                is Ls -> applyLs(command)
                is Cd -> applyCd(command)
            }
        }

        fun lookupTree(lambda: (FileSystemResource) -> Boolean): List<FileSystemResource> {
            val output = mutableListOf<FileSystemResource>()

            val stack = ArrayDeque<FileSystemResource>(listOf(root))
            while (stack.isNotEmpty()) {
                val resource = stack.removeFirst()
                if (resource is Directory && resource.children != null) stack.addAll(resource.children!!)
                if (lambda(resource)) output.add(resource)
            }

            return output
        }

        private fun applyLs(ls: Ls) {
            currentDir.children = ls.output.map {
                val parts = it.split(" ")
                return@map if (parts[0] == "dir") Directory(parts[1], currentDir) else File(parts[1], parts[0].toInt())
            }
        }

        private fun applyCd(cd: Cd) {
            if (cd.path == "..") {
                currentPath = currentPath.parent
                currentDir = currentDir.parent!!
                return
            }
            currentPath = currentPath.resolve(cd.path)
            val fileSystemResource = currentDir.children?.first { it.name == currentPath.name }
            if (fileSystemResource == null || fileSystemResource !is Directory) throw IllegalStateException("Cannot access given path: ${cd.path}")
            currentDir = fileSystemResource
        }
    }

    sealed class Command {
        companion object {
            fun parseCommand(command: String): Command {
                command.removePrefix("$ ").split(" ").let {
                    if (it[0] == "ls") return Ls()
                    else if (it[0] == "cd") return Cd(it[1])
                    else throw IllegalArgumentException("Unknown command '$command'")
                }
            }
        }
    }

    data class Ls(val output: MutableList<String> = mutableListOf()) : Command()

    data class Cd(val path: String) : Command()

    sealed class FileSystemResource(val name: String) {
        abstract val size: Int
    }

    class File(name: String, override val size: Int) : FileSystemResource(name)

    class Directory(name: String, val parent: Directory? = null, var children: List<FileSystemResource>? = null) : FileSystemResource(name) {
        override val size by lazy { children?.map { it.size }?.sum() ?: 0 }
    }
}