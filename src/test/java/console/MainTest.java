package console;

import org.junit.Test;

import static console.Main.main;
import static org.junit.Assert.assertEquals;

public class MainTest {

    @Test
    public void exceptionTest() {
        try {
            main("-r", "-d", "C:\\users");
        } catch (IllegalArgumentException e) {
            assertEquals("please, enter a file name!", e.getMessage());
        }
        try {
            main("-r", "-d", "C:\\users\\gggfffg", "file.txt");
        } catch (IllegalArgumentException e) {
            assertEquals("this directory doesn't exist", e.getMessage());
        }
        try {
            main("-r", "-d", "C:\\users", "file.txt", "file.txt");
        } catch (IllegalArgumentException e) {
            assertEquals("please, enter a ONE file name!", e.getMessage());
        }
        try {
            main("-d", "C:\\users", "file.txt");
        } catch (IllegalArgumentException e) {
            assertEquals("no results for: file.txt", e.getMessage());
        }
    }

}



