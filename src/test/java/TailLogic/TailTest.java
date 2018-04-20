package TailLogic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


class TailTest {
    // Тест парсера
    TailTest() throws IOException {
    }
    private Tail tailStringWithFile = new Tail(Tail.TypeOfInput.File, true, "Files/TestFile");
    private Tail tailCharactersWithFile = new Tail(Tail.TypeOfInput.File, false, "Files/TestFile");
    private List<String> outputStrings;
    private List<String> outputStringsAssert;
    private List<String> outputCharacters;
    private List<String> outputCharactersAssert;

    @Test
    void valueN() throws IOException {
        tailStringWithFile.selectText(true,"Files/OutputStrings", 10,
                true, null);
        try {
            outputStrings = Files.readAllLines
                    (Paths.get("Files/OutputStrings"));
            outputStringsAssert = Files.readAllLines
                    (Paths.get("Files/OutputStringsAssert"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(outputStrings,outputStringsAssert);
    }
    @Test
    void valueC() {
        tailCharactersWithFile.selectText(false, "Files/OutputCharacters", 30,
                true, null);
        try {
            outputCharacters = Files.readAllLines
                    (Paths.get("Files/OutputCharacters"));
            outputCharactersAssert = Files.readAllLines
                    (Paths.get("Files/OutputCharactersAssert"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(outputCharacters,outputCharactersAssert);
    }
}
