package TailLogic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


class TailTest {
    TailTest() throws IOException {
    }

    private Tail tailSt = new Tail("/home/vladislav/IdeaProjects/ProgrammingLabTask2/Files/TestFile",
            true);
    private Tail tailCh = new Tail("/home/vladislav/IdeaProjects/ProgrammingLabTask2/Files/TestFile",
            false);
    private ArrayList<String> outputStrings;
    private ArrayList<String> outputStringsAssert;
    private ArrayList<String> outputCharacters;
    private ArrayList<String> outputCharactersAssert;

    @Test
    void flagN() {
        tailSt.flagN(10, "/home/vladislav/IdeaProjects/ProgrammingLabTask2/Files/OutputStrings");
        try {
            outputStrings = (ArrayList<String>) Files.readAllLines
                    (Paths.get("/home/vladislav/IdeaProjects/ProgrammingLabTask2/Files/OutputStrings"));
            outputStringsAssert = (ArrayList<String>) Files.readAllLines
                    (Paths.get("/home/vladislav/IdeaProjects/ProgrammingLabTask2/Files/OutputStringsAssert"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(outputStrings,outputStringsAssert);
    }
    @Test
    void flagC() {
        tailCh.flagC(30, "/home/vladislav/IdeaProjects/ProgrammingLabTask2/Files/OutputCharacters");
        try {
            outputCharacters = (ArrayList<String>) Files.readAllLines
                    (Paths.get("/home/vladislav/IdeaProjects/ProgrammingLabTask2/Files/OutputCharacters"));
            outputCharactersAssert = (ArrayList<String>) Files.readAllLines
                    (Paths.get("/home/vladislav/IdeaProjects/ProgrammingLabTask2/Files/OutputCharactersAssert"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(outputCharacters,outputCharactersAssert);

        tailCh.flagC(30, "/home/vladislav/IdeaProjects/ProgrammingLabTask2/Files/OutputCharacters");
    }
}
