package logic

import java.io.File
import java.nio.file.Paths

class Find {

    fun find(r: Boolean, d: File?, filename: String): String? {
        val dir = if (d == null) File(Paths.get("").toAbsolutePath().toString())
        else d

        fun search(dir: File): String {
            var result = ""
            for (file in dir.listFiles()) {
                if (file.name == filename) result += file.absolutePath + "\n"
                if (r && file.isDirectory) {
                    result += search(file)
                }
            }
            return result
        }

        val result = search(dir)
        return if (result.equals("")) null
        else result.trim()
    }
}