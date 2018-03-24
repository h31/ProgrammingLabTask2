package logic

import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File
import java.nio.file.Paths

class FindTest {
    val testdir = Paths.get("").toAbsolutePath().toString() + "\\src\\test\\testdir"

    @Test
    fun find() {
        assertEquals(null, Find().find(false, null, "code.kt"))

        // -r
        assertEquals(testdir + "\\anotherProgramming\\kotlin\\code.kt" + "\n" +
                testdir + "\\programming\\kotlin\\code.kt" + "\n" +
                testdir + "\\programming\\kotlin\\deeper\\code.kt",
                Find().find(true, null, "code.kt").toString())

        // -d
        assertEquals(testdir + "\\programming\\kotlin\\code.kt",
                Find().find(false, File(testdir + "\\programming\\kotlin"), "code.kt").toString())

        // -r -d
        assertEquals(testdir + "\\programming\\kotlin\\code.kt" + "\n" +
                testdir + "\\programming\\kotlin\\deeper\\code.kt",
                Find().find(true, File(testdir + "\\programming"), "code.kt").toString())

        //  find directories
        assertEquals(testdir + "\\anotherProgramming\\kotlin" + "\n" +
                testdir + "\\programming\\kotlin",
                Find().find(true, File(testdir), "kotlin").toString())
    }
}