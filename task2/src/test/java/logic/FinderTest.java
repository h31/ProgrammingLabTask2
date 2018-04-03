package logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class FinderTest {
    @Test
    void mainTest() {
        Finder finder = new Finder();
        Assertions.assertEquals(new File("C:\\file.txt"),
                finder.find(true, new File("C:"), "file.txt"));
    }
}
