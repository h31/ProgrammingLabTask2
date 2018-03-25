package console

import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File
import java.nio.file.Paths

class MainTest {

    @Test
    fun mainTest() {
        assertEquals("You need to specify one file name!", mainTest(arrayOf("code.kt", "code.kt")))
        assertEquals("Main parameters are required (\"File name for finding.\")", mainTest(arrayOf("-d", "code.kt")))
        assertEquals("Given directory doesn't exist!", mainTest(arrayOf("-r", "-d", "code.kt", "code.kt")))
        assertEquals("No file with that file name!", mainTest(arrayOf("code.kt")))

        val s = File.separator
        val testdir = Paths.get("").toRealPath().toString() + "${s}src${s}test${s}testdir"
        assertEquals(testdir + "${s}programming${s}kotlin${s}code.kt" + "\n" +
                testdir + "${s}programming${s}kotlin${s}deeper${s}code.kt",
                mainTest(arrayOf("-r", "-d", testdir + "${s}programming", "code.kt")))
    }
}