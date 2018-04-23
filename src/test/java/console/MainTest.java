package console;

import org.junit.Test;

import static console.Main.main;
import static org.junit.Assert.assertEquals;

public class MainTest {

    @Test
    public void exceptionTest() {
        try {
            main("-r", "-d", "testDir");
        } catch (IllegalArgumentException e) {
            assertEquals("please, enter a file name!", e.getMessage());
        }
        try {
            main("-r", "-d", "some dir", "testFile.txt");
        } catch (IllegalArgumentException e) {
            assertEquals("this directory doesn't exist", e.getMessage());
        }
        try {
            main("-r", "-d", "testDir", "testFile.txt", "testFile.txt");
        } catch (IllegalArgumentException e) {
            assertEquals("please, enter a ONE file name!", e.getMessage());
        }
        try {
            main("-d", "testDir/testDir2", "testFile.txt");
        } catch (IllegalArgumentException e) {
            assertEquals("no results for: testFile.txt", e.getMessage());
        }
    }

}



