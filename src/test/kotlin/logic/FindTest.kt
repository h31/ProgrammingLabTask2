package logic

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.nio.file.Path
import java.nio.file.Paths

class FindTest {
    val testdir = Paths.get("").toRealPath().resolve("src/test/testdir")

    @Test
    fun find() {
        generateTestFiles()

        assertEquals(null, Find().find(false, null, "code.kt"))

        //  -r
        assertEquals(mutableListOf(
                testdir.resolve("anotherProgramming/kotlin/code.kt").toFile(),
                testdir.resolve("programming/kotlin/code.kt").toFile(),
                testdir.resolve("programming/kotlin/deeper/code.kt").toFile()),
                Find().find(true, null, "code.kt"))

        //  -d
        assertEquals(mutableListOf(testdir.resolve("programming/kotlin/code.kt").toFile()),
                Find().find(false, testdir.resolve("programming/kotlin").toFile(), "code.kt"))

        //  -r -d
        assertEquals(mutableListOf(
                testdir.resolve("programming/kotlin/code.kt").toFile(),
                testdir.resolve("programming/kotlin/deeper/code.kt").toFile()),
                Find().find(true, testdir.resolve("programming").toFile(), "code.kt"))

        //  find directories
        assertEquals(mutableListOf(
                testdir.resolve("anotherProgramming/kotlin").toFile(),
                testdir.resolve("programming/kotlin").toFile()),
                Find().find(true, testdir.toFile(), "kotlin"))

        deleteTestFiles()
    }

    fun generateTestFiles() {
        createDirectory(testdir)
        createDirectory(testdir.resolve("programming"))
        createDirectory(testdir.resolve("programming/java"))
        testdir.resolve("programming/java/code.java").toFile().createNewFile()
        createDirectory(testdir.resolve("programming/kotlin"))
        testdir.resolve("programming/kotlin/code.kt").toFile().createNewFile()
        createDirectory(testdir.resolve("programming/kotlin/deeper"))
        testdir.resolve("programming/kotlin/deeper/code.kt").toFile().createNewFile()
        createDirectory(testdir.resolve("anotherProgramming"))
        createDirectory(testdir.resolve("anotherProgramming/kotlin"))
        testdir.resolve("anotherProgramming/kotlin/code.kt").toFile().createNewFile()
    }

    fun deleteTestFiles() {
        testdir.toFile().deleteRecursively()
    }

    fun createDirectory(path: Path) {
        val file = path.toFile()
        if (!file.exists() || !file.isDirectory) {
            file.mkdir()
        }
    }
}