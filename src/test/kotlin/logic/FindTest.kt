package logic

import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File
import java.nio.file.Paths

class FindTest {
    val s = File.separator
    val testdir = Paths.get("").toRealPath().toString() + "${s}src${s}test${s}testdir"

    @Test
    fun find() {
        assertEquals(null, Find().find(false, null, "code.kt"))

        // -r
        assertEquals(testdir + "${s}anotherProgramming${s}kotlin${s}code.kt" + "\n" +
                testdir + "${s}programming${s}kotlin${s}code.kt" + "\n" +
                testdir + "${s}programming${s}kotlin${s}deeper${s}code.kt",
                Find().find(true, null, "code.kt").toString())

        // -d
        assertEquals(testdir + "${s}programming${s}kotlin${s}code.kt",
                Find().find(false, File(testdir + "${s}programming${s}kotlin"), "code.kt").toString())

        // -r -d
        assertEquals(testdir + "${s}programming${s}kotlin${s}code.kt" + "\n" +
                testdir + "${s}programming${s}kotlin${s}deeper${s}code.kt",
                Find().find(true, File(testdir + "${s}programming"), "code.kt").toString())

        //  find directories
        assertEquals(testdir + "${s}anotherProgramming${s}kotlin" + "\n" +
                testdir + "${s}programming${s}kotlin",
                Find().find(true, File(testdir), "kotlin").toString())
    }
}