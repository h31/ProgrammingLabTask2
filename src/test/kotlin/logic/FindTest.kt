package logic

import org.junit.Assert.assertEquals
import org.junit.Test
import java.nio.file.Paths

class FindTest {
    val testdir = Paths.get("").toRealPath().resolve("src\\test\\testdir")

    @Test
    fun find() {
        assertEquals(null, Find().find(false, null, "code.kt"))

        // -r
        assertEquals(mutableListOf(
                testdir.resolve("anotherProgramming\\kotlin\\code.kt").toFile(),
                testdir.resolve("programming\\kotlin\\code.kt").toFile(),
                testdir.resolve("programming\\kotlin\\deeper\\code.kt").toFile()),
                Find().find(true, null, "code.kt"))

        // -d
        assertEquals(mutableListOf(testdir.resolve("programming\\kotlin\\code.kt").toFile()),
                Find().find(false, testdir.resolve("programming\\kotlin").toFile(), "code.kt"))

        // -r -d
        assertEquals(mutableListOf(
                testdir.resolve("programming\\kotlin\\code.kt").toFile(),
                testdir.resolve("programming\\kotlin\\deeper\\code.kt").toFile()),
                Find().find(true, testdir.resolve("programming").toFile(), "code.kt"))

        //  find directories
        assertEquals(mutableListOf(
                testdir.resolve("anotherProgramming\\kotlin").toFile(),
                testdir.resolve("programming\\kotlin").toFile()),
                Find().find(true, testdir.toFile(), "kotlin"))
    }
}