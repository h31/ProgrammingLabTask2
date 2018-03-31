import UI.Parsers.Args;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    }

    @Test
    public void parseSeparateTaskTest(){
        String arguments = "-u /Users/Ferrero/IdeaProjects/ProgrammingLabTask2/task2/src/main/resources/fileToSeparate.txt";
        String[] args = arguments.split(" |\n");
        Args avhs = new Args(args);
        avhs.getTask();
        assertFalse(avhs.taskFlag());
        assertEquals(avhs.getFileToSeparate(), testFileToSeparate);
    }
}
