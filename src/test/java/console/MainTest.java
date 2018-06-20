package console;

import org.junit.Test;

import static console.Main.fileIsDetected;
import static console.Main.main;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MainTest {

    @Test
    public void exceptionTest() {

        try {
            main("-r", "-d", "src/test/testDir", "testFile");
            assertTrue(fileIsDetected);
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        try {
            main("-r", "-d", "src/test/testDir");
            assertFalse(fileIsDetected);
        } catch (IllegalArgumentException e) {
            assertEquals("please, enter a file name!", e.getMessage());
        }
        try {
            main("-r", "-d", "some dir", "testFile");
            assertFalse(fileIsDetected);
        } catch (IllegalArgumentException e) {
            assertEquals("this directory doesn't exist", e.getMessage());
        }
        try {
            main("-r", "-d", "src/test/testDir", "testFile", "testFile");
            assertFalse(fileIsDetected);
        } catch (IllegalArgumentException e) {
            assertEquals("please, enter a ONE file name!", e.getMessage());
        }
        try {
            main("-d", "src/test/testDir/testDir2", "testFile");
            assertFalse(fileIsDetected);
        } catch (IllegalArgumentException e) {
            assertEquals("no results for: testFile", e.getMessage());
        }
    }

}



