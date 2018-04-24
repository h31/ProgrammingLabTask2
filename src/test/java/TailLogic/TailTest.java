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

    private Tail tailStringWithFile = new Tail(Tail.TypeOfInput.File, true, "Files/TestFile");
    private Tail tailCharactersWithFile = new Tail(Tail.TypeOfInput.File, false, "Files/TestFile");
    private List<String> fileNames = Arrays.asList("Files/TestFile", "Files/TestFile2", "Files/TestFile3");

    @Test
    void valueN() {
        try {
            tailStringWithFile.selectText(true, "Files/OutputStrings", 10,
                    true, null);
            List<String> outputStrings = Files.readAllLines
                    (Paths.get("Files/OutputStrings"));
            List<String> outputStringsAssert = Files.readAllLines
                    (Paths.get("Files/OutputStringsAssert"));

            assertEquals(outputStringsAssert, outputStrings);

            for (int i = 0; i < 3; i++) {
                Tail tailStringWithFiles = new Tail(Tail.TypeOfInput.File, true, fileNames.get(i));
                tailStringWithFiles.selectText(true, "Files/OutputStringsManyFiles", 3,
                        false, fileNames.get(i));
            }
            outputStrings = Files.readAllLines
                    (Paths.get("Files/OutputStringsManyFiles"));
            outputStringsAssert = Files.readAllLines
                    (Paths.get("Files/OutputStringsManyFilesAsserts"));

            assertEquals(outputStringsAssert, outputStrings);


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
    void valueC() {
        try {
            tailCharactersWithFile.selectText(false, "Files/OutputCharacters", 30,
                    true, null);
            List<String> outputCharacters = Files.readAllLines
                    (Paths.get("Files/OutputCharacters"));
            List<String> outputCharactersAssert = Files.readAllLines
                    (Paths.get("Files/OutputCharactersAssert"));

            assertEquals(outputCharactersAssert, outputCharacters);

            for (String name : fileNames) {
                Tail tailCharactersWithFiles = new Tail(Tail.TypeOfInput.File, false, name);
                tailCharactersWithFiles.selectText(false, "Files/OutputCharactersManyFiles", 30,
                        false, name);
            }
            outputCharacters = Files.readAllLines
                    (Paths.get("Files/OutputCharactersManyFiles"));
            outputCharactersAssert = Files.readAllLines
                    (Paths.get("Files/OutputCharactersManyFilesAsserts"));

            assertEquals(outputCharactersAssert, outputCharacters);


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
//    @Test
//    void write() throws IOException {
//        tailStringWithFile.writers("Files/Writers", 7);
//    }
}
