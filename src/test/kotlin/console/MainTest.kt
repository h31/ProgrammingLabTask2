package console

import org.junit.Assert.assertEquals
import org.junit.Test
import java.nio.file.Paths

class MainTest {

    @Test
    fun mainTest() {
        assertEquals("You need to specify one file name!", mainTest(arrayOf("code.kt", "code.kt")))
        assertEquals("Main parameters are required (\"File name for finding.\")", mainTest(arrayOf("-d", "code.kt")))
        assertEquals("Given directory doesn't exist!", mainTest(arrayOf("-r", "-d", "code.kt", "code.kt")))
        assertEquals("No file with that file name!", mainTest(arrayOf("code.kt")))

        val testdir = Paths.get("").toAbsolutePath().toString() + "\\src\\test\\testdir"
        assertEquals(testdir + "\\programming\\kotlin\\code.kt" + "\n" +
                testdir + "\\programming\\kotlin\\deeper\\code.kt",
                mainTest(arrayOf("-r", "-d", testdir + "\\programming", "code.kt")))
    }
}