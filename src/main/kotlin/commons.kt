import kotlin.io.path.toPath

fun readResourceFile(path: String) = object {}.javaClass.classLoader.getResource(path)?.toURI()?.toPath() ?: throw IllegalArgumentException("Resource file not found: $path")