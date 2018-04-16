package console;

import java.io.File;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleTest {

    @Test
    void test1() throws Exception {
        Main path = new Main();
        path.main(new String[]{"-r", "-d", "src", "test1.txt"});
        assertEquals(new File("src/test/test1.txt"), path.file);
    }

    @Test
    void test2() throws Exception {
        Main path = new Main();
        String message = "";
        try {
            path.main(new String[]{"test2.txt"});
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("File does not exist", message);
    }

    @Test
    void test3() throws Exception {
        Main path = new Main();
        path.main(new String[]{"-d", "src", "test2.txt"});
        assertEquals(new File("src/test2.txt"), path.file);
    }

    @Test
    void test4() throws Exception {
        Main path = new Main();
        String message = "";
        try {
            path.main(new String[]{"-r", "-d", "src", "test3.txt"});
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("File does not exist", message);
    }
}