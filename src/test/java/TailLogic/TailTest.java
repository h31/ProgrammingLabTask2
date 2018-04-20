package TailLogic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;
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
    private List<String> fileNames = Arrays.asList("Files/TestFile","Files/TestFile2","Files/TestFile3");

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
        assertEquals(outputStringsAssert,outputStrings);

        for (int i = 0; i < 3; i++) {
            Tail tailStringWithFiles = new Tail(Tail.TypeOfInput.File, true, fileNames.get(i));
            tailStringWithFiles.selectText(true,"Files/OutputStringsManyFiles", 3,
                    false, fileNames.get(i));
        }
        try {
            outputStrings = Files.readAllLines
                    (Paths.get("Files/OutputStringsManyFiles"));
            outputStringsAssert = Files.readAllLines
                    (Paths.get("Files/OutputStringsManyFilesAsserts"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(outputStringsAssert,outputStrings);


        try {
            FileWriter strings = new FileWriter("Files/OutputStrings");
            FileWriter stringsOfManyFiles = new FileWriter("Files/OutputStringsManyFiles");
            strings.write("");
            strings.close();
            stringsOfManyFiles.write("");
            stringsOfManyFiles.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    void valueC() throws IOException {
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
        assertEquals(outputCharactersAssert,outputCharacters);

        for (int i = 0; i < 3; i++) {
            Tail tailCharactersWithFiles = new Tail(Tail.TypeOfInput.File, false, fileNames.get(i));
            tailCharactersWithFiles.selectText(false,"Files/OutputCharactersManyFiles", 30,
                    false, fileNames.get(i));
        }
        try {
            outputCharacters = Files.readAllLines
                    (Paths.get("Files/OutputCharactersManyFiles"));
            outputCharactersAssert = Files.readAllLines
                    (Paths.get("Files/OutputCharactersManyFilesAsserts"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(outputCharactersAssert,outputCharacters);


        try {
            FileWriter characters = new FileWriter("Files/OutputCharacters");
            FileWriter charactersOfManyFiles = new FileWriter("Files/OutputCharactersManyFiles");
            characters.write("");
            characters.close();
            charactersOfManyFiles.write("");
            charactersOfManyFiles.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
