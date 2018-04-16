package console

import com.beust.jcommander.JCommander
import logic.Find
import java.io.File

fun main(args: Array<String>) {
    try {
        print(start(args))
    } catch (e: Exception) {
        System.err.print(e.message)
    }
}

fun start(args: Array<String>): String {
    val arguments = Args()

    JCommander(arguments).parse(*args)
    checkArguments(arguments)

    val dir = if (arguments.directory == null) null
    else File(arguments.directory)

    val result = Find().find(arguments.checkSubdirectories, dir, arguments.filename[0])
    return result?.joinToString(separator = "\n") { it.toString() } ?: throw Exception("No file with that file name!")
}

private fun checkArguments(args: Args) {
    if (args.directory != null) {
        val dir = File(args.directory)
        if (!dir.exists() || !dir.isDirectory) throw IllegalArgumentException("Given directory doesn't exist!")
    }
    if (args.filename.size != 1) throw IllegalArgumentException("You need to specify one file name!")
}
