package logic

import java.io.File
import java.nio.file.Paths

class Find {

    fun find(recursive: Boolean, directory: File?, filename: String): List<File>? {
        val dir = if (directory == null) File(Paths.get("").toRealPath().toString())
        else directory

        fun search(dir: File): List<File> {
            val result = mutableListOf<File>()
            for (file in dir.listFiles()) {
                if (file.name == filename) result.add(file)
                if (recursive && file.isDirectory) {
                    result.addAll(search(file))
                }
            }
            return result
        }

        val result = search(dir)
        return if (result.isEmpty()) null
        else result
    }
}