package logic;

import java.io.File;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FindTest {

    private Throwable exception;

    @Test
    void test1() throws Exception {
        Find path = new Find("src", true);
        assertEquals(new File("src/test/test1.txt"),
                path.find("test1.txt"));
    }

    @Test
    void test2() throws Exception {
        Find path = new Find("src/test", false);
        assertEquals(new File("src/test/test1.txt"),
                path.find("test1.txt"));
    }

    @Test
    void test3() {
        Find path = new Find("src", false);
        exception = assertThrows(Exception.class, () -> path.find("test1.txt"));
        assertEquals("File does not exist", exception.getMessage());
    }

    @Test
    void test4() throws Exception {
        Find path = new Find("src", false);
        assertEquals(new File("src/test2.txt"),
                path.find("test2.txt"));
    }

    @Test
    void test5() {
        Find path = new Find("src/test", true);
        exception = assertThrows(Exception.class, () -> path.find("test3.txt"));
        assertEquals("File does not exist", exception.getMessage());
    }

}