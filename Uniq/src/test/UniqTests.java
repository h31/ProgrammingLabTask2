

import org.junit.jupiter.api.Test;
import ui.UI;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UniqTests {

    private String[][] normalCommand = new String[][] {
            {"uniq", "-i", "-u", "-c", "-s", "15", "-o", "/Users/User/IdeaProjects/ProgrammingLabTask2/Uniq/src/test/testFile1.txt",
                    "/Users/User/IdeaProjects/ProgrammingLabTask2/Uniq/src/test/file.txt"},
            {"uniq", "-i", "-c", "-s", "15", "-o", "/Users/User/IdeaProjects/ProgrammingLabTask2/Uniq/src/test/testFile2.txt",
                    "/Users/User/IdeaProjects/ProgrammingLabTask2/Uniq/src/test/file.txt"},
            {"uniq", "-u", "-c", "-s", "15", "-o", "Users/User/IdeaProjects/ProgrammingLabTask2/Uniq/src/test/testFile3.txt",
                    "/Users/User/IdeaProjects/ProgrammingLabTask2/Uniq/src/test/file.txt"},
            {"uniq", "-i", "-u", "-c", "-o", "Users/User/IdeaProjects/ProgrammingLabTask2/Uniq/src/test/testFile4.txt",
                    "/Users/User/IdeaProjects/ProgrammingLabTask2/Uniq/src/test/file.txt"},
            {"uniq", "-o", "Users/User/IdeaProjects/ProgrammingLabTask2/Uniq/src/test/testFile5.txt",
                    "/Users/User/IdeaProjects/ProgrammingLabTask2/Uniq/src/test/file.txt"}
    };

    @Test
    void normalCommandTest() throws IOException {
        for (int i = 0; i < normalCommand.length; i++) {
            String[] command = normalCommand[i];
            UI.start(command);
        }
    }

}
