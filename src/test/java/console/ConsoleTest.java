package console;

import java.io.File;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleTest {

    private Throwable exception;

    @Test
    void test1() throws Exception {
        Main path = new Main();
        path.main(new String[]{"-r", "-d", "src", "test1.txt"});
        assertEquals(new File("src/test/test1.txt"), path.file);
    }

    @Test
    void test2() {
        Main path = new Main();
        exception = assertThrows(Exception.class, () -> path.main(new String[]{"test2.txt"}));
        assertEquals("File does not exist", exception.getMessage());
    }

    @Test
    void test3() throws Exception {
        Main path = new Main();
        path.main(new String[]{"-d", "src", "test2.txt"});
        assertEquals(new File("src/test2.txt"), path.file);
    }

    @Test
    void test4() {
        Main path = new Main();
        exception = assertThrows(Exception.class, () -> path.main(new String[]{"-r", "-d", "src", "test3.txt"}));
        assertEquals("File does not exist", exception.getMessage());
    }
}