
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
    public void testC() {
        List<String> newText0 = new ArrayList<>(cutter.cutterC(text, 0, 100));
        assertEquals("[" + expected0.replaceAll("\n", ", ") + "]", newText0.toString());

        List<String> newText1 = new ArrayList<>(cutter.cutterC(text, 4, 9));
        assertEquals("[" + expected1.replaceAll("\n", ", ") + "]", newText1.toString());

        List<String> newText2 = new ArrayList<>(cutter.cutterC(text, 0, 14));
        assertEquals("[" + expected2.replaceAll("\n", ", ") + "]", newText2.toString());

        List<String> newText3 = new ArrayList<>(cutter.cutterC(text, 10, 100));
        assertEquals("[" + expected3.replaceAll("\n", ", ") + "]", newText3.toString());
    }

    @Test
    public void testW() {
        List<String> newText4 = new ArrayList<>(cutter.cutterW(text, 0, 100));
        assertEquals("[" + expected4.replaceAll("\n", ", ") + "]", newText4.toString());

        List<String> newText5 = new ArrayList<>(cutter.cutterW(text, 4, 9));
        assertEquals("[" + expected5.replaceAll("\n", ", ") + "]", newText5.toString());

        List<String> newText6 = new ArrayList<>(cutter.cutterW(text, 0, 14));
        assertEquals("[" + expected6.replaceAll("\n", ", ") + "]", newText6.toString());

        List<String> newText7 = new ArrayList<>(cutter.cutterW(text, 10, 100));
        assertEquals("[" + expected7.replaceAll("\n", ", ") + "]", newText7.toString());
    }
}