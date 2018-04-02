package logic;

import java.io.File;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FindTest {

    @Test
    void main() {
        Find path = new Find("src", true);
        assertEquals(new File("src/test/test1.txt"),
                path.find("test1.txt"));

        path = new Find("src/test", false);
        assertEquals(new File("src/test/test1.txt"),
                path.find("test1.txt"));

        path = new Find("src", false);
        assertEquals(new File("File does not exist"),
                path.find("test1.txt"));

        path = new Find("src", false);
        assertEquals(new File("src/test2.txt"),
                path.find("test2.txt"));

        path = new Find("src/test", true);
        assertEquals(new File("File does not exist"),
                path.find("test3.txt"));

    }
}