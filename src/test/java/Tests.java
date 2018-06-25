
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class Tests {

    Cut cutter = new Cut();
    String text = new String(Files.readAllBytes(Paths.get("src/test/files/Input/Input.txt")));
    String expected0 = new String(Files.readAllBytes(Paths.get("src/test/files/Expected/Expected0.txt")));
    String expected1 = new String(Files.readAllBytes(Paths.get("src/test/files/Expected/Expected1.txt")));
    String expected2 = new String(Files.readAllBytes(Paths.get("src/test/files/Expected/Expected2.txt")));
    String expected3 = new String(Files.readAllBytes(Paths.get("src/test/files/Expected/Expected3.txt")));
    String expected4 = new String(Files.readAllBytes(Paths.get("src/test/files/Expected/Expected4.txt")));
    String expected5 = new String(Files.readAllBytes(Paths.get("src/test/files/Expected/Expected5.txt")));
    String expected6 = new String(Files.readAllBytes(Paths.get("src/test/files/Expected/Expected6.txt")));
    String expected7 = new String(Files.readAllBytes(Paths.get("src/test/files/Expected/Expected7.txt")));

    public Tests() throws IOException {
    }

    @Test
    public void testCutter() {

        String newText0 = cutter.cutter(text, 0, 100, true);
        assertEquals(expected0, newText0);

        String newText1 = cutter.cutter(text, 4, 9, true);
        assertEquals(expected1, newText1);

        String newText2 = cutter.cutter(text, 0, 14, true);
        assertEquals(expected2, newText2);

        String newText3 = cutter.cutter(text, 10, 100, true);
        assertEquals(expected3, newText3);

        String newText4 = cutter.cutter(text, 0, 100, false);
        assertEquals(expected4, newText4);

        String newText5 = cutter.cutter(text, 4, 9, false);
        assertEquals(expected5, newText5);

        String newText6 = cutter.cutter(text, 0, 14, false);
        assertEquals(expected6, newText6);

        String newText7 = cutter.cutter(text, 10, 100, false);
        assertEquals(expected7, newText7);
    }

    @Test
    public void test() throws IOException {
        String[] args0 = {"-c", "-o", "src/file.txt", "src/newFile.txt", "n-", "4", "-k", "9"};
        Main.main(args0);
        String output0 = new String(Files.readAllBytes(Paths.get("src/newFile.txt")));
        assertEquals(expected1, output0);

        String[] args1 = {"-w", "-o", "src/file.txt", "src/newFile.txt", "n-", "4", "-k", "9"};
        Main.main(args1);
        String output1 = new String(Files.readAllBytes(Paths.get("src/newFile.txt")));
        assertEquals(expected5, output1);
    }
}