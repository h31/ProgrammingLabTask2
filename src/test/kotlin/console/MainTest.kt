package console

import com.beust.jcommander.ParameterException
import logic.FindTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.file.Paths

class MainTest {

    @Test
    fun mainTest() {
        FindTest().generateTestFiles()

        assertThrows(IllegalArgumentException::class.java, { start(arrayOf("code.kt", "code.kt")) }, "You need to specify one file name!")
        assertThrows(ParameterException::class.java, { start(arrayOf("-d", "code.kt")) }, "Main parameters are required (\"File name for finding\")")
        assertThrows(IllegalArgumentException::class.java, { start(arrayOf("-r", "-d", "code.kt", "code.kt")) }, "Given directory doesn't exist!")
        assertThrows(Exception::class.java, { start(arrayOf("code.kt")) }, "No file with that file name!")

        val s = File.separator
        val testdir = Paths.get("").toRealPath().toString() + "${s}src${s}test${s}testdir"
        assertEquals(testdir + "${s}programming${s}kotlin${s}code.kt" + "\n" +
                testdir + "${s}programming${s}kotlin${s}deeper${s}code.kt",
                start(arrayOf("-r", "-d", testdir + "${s}programming", "code.kt")))

        FindTest().deleteTestFiles()
    }
}