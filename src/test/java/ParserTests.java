import UI.Parsers.Args;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTests {
    File testCollectedFile = new File("/Users/Ferrero/IdeaProjects/ProgrammingLabTask2/task2/src/main/resources/collectionFile.txt");
    List<File> filesToCollectList = Arrays.asList(new File("/Users/Ferrero/IdeaProjects/ProgrammingLabTask2/task2/src/main/resources/fileToCollect1.txt"),
            new File("/Users/Ferrero/IdeaProjects/ProgrammingLabTask2/task2/src/main/resources/fileToCollect2.txt"),
            new File("/Users/Ferrero/IdeaProjects/ProgrammingLabTask2/task2/src/main/resources/fileToCollect3.txt"),
            new File("/Users/Ferrero/IdeaProjects/ProgrammingLabTask2/task2/src/main/resources/fileToCollect4.txt"),
            new File("/Users/Ferrero/IdeaProjects/ProgrammingLabTask2/task2/src/main/resources/fileToCollect5.txt"));
    File testFileToSeparate = new File("/Users/Ferrero/IdeaProjects/ProgrammingLabTask2/task2/src/main/resources/fileToSeparate.txt");

    @Test
    public void parseCollectTaskTest() {
        String arguments = "/Users/Ferrero/IdeaProjects/ProgrammingLabTask2/task2/src/main/resources/fileToCollect1.txt\n" +
                "/Users/Ferrero/IdeaProjects/ProgrammingLabTask2/task2/src/main/resources/fileToCollect2.txt\n" +
                "/Users/Ferrero/IdeaProjects/ProgrammingLabTask2/task2/src/main/resources/fileToCollect3.txt\n" +
                "/Users/Ferrero/IdeaProjects/ProgrammingLabTask2/task2/src/main/resources/fileToCollect4.txt\n" +
                "/Users/Ferrero/IdeaProjects/ProgrammingLabTask2/task2/src/main/resources/fileToCollect5.txt\n" +
                "-out\n" +
                "/Users/Ferrero/IdeaProjects/ProgrammingLabTask2/task2/src/main/resources/collectionFile.txt";
        String[] args = arguments.split(" |\n");
        Args avhs = new Args(args);
        avhs.getTask();
        assertTrue(avhs.taskFlag());
        assertEquals(avhs.getCollectionFile(), testCollectedFile);
        assertEquals(avhs.getFilesToCollect(), filesToCollectList);
        String arguments_ex = "/ 23 /232i f d";
        args = arguments_ex.split(" |\n");
        Args avhs_exception = new Args(args);
        assertThrows(IndexOutOfBoundsException.class, () -> avhs_exception.getTask());
    }

    @Test
    public void parseSeparateTaskTest(){
        String arguments = "-u /Users/Ferrero/IdeaProjects/ProgrammingLabTask2/task2/src/main/resources/fileToSeparate.txt";
        String[] args = arguments.split(" |\n");
        Args avhs = new Args(args);
        avhs.getTask();
        assertFalse(avhs.taskFlag());
        assertEquals(avhs.getFileToSeparate(), testFileToSeparate);
        String arguments_ex = "-  /cxzask2/task2/src/main/resources/fileToSeparate.txt";
        args = arguments_ex.split(" |\n");
        Args avhs_exception = new Args(args);
        assertThrows(IndexOutOfBoundsException.class, () -> avhs_exception.getTask());
    }
}
