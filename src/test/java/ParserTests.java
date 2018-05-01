import ui.Parsers.Args;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTests {
    File home = new File("");
    String defaultPathPattern = "/Users/Ferrero/IdeaProjects/ProgrammingLabTask2/task2/src/main/resources/";
    File testCollectedFile = new File(defaultPathPattern + "collectionFile.txt");
    List<File> filesToCollectList = Arrays.asList(
            new File( defaultPathPattern + "fileToCollect1.txt"),
            new File( defaultPathPattern + "fileToCollect2.txt"),
            new File( defaultPathPattern + "fileToCollect3.txt"),
            new File( defaultPathPattern + "fileToCollect4.txt"),
            new File( defaultPathPattern + "fileToCollect5.txt"));
    File testFileToSeparate = new File("/Users/Ferrero/IdeaProjects/ProgrammingLabTask2/task2/src/main/resources/fileToSeparate.txt");
    File short_testFileToSeparate = new File(home.getAbsolutePath() + "/fileToSeparate.txt");
    File short_testCollectedFile = new File(home.getAbsolutePath() + "/collectionFile.txt");
    List<File> short_filesToCollectList = Arrays.asList(new File(home.getAbsolutePath() + "/fileToCollect1.txt"),
            new File(home.getAbsolutePath() + "/fileToCollect2.txt"),
            new File(home.getAbsolutePath() + "/fileToCollect3.txt"),
            new File(home.getAbsolutePath() + "/fileToCollect4.txt"),
            new File(home.getAbsolutePath() + "/fileToCollect5.txt"));

    @Test
    public void parseCollectTaskTest() {
        String arguments = defaultPathPattern + "fileToCollect1.txt\n" +
                defaultPathPattern + "fileToCollect2.txt\n" +
                defaultPathPattern + "fileToCollect3.txt\n" +
                defaultPathPattern + "fileToCollect4.txt\n" +
                defaultPathPattern + "fileToCollect5.txt\n" +
                "-out\n" +
                defaultPathPattern + "collectionFile.txt";
        String[] args = arguments.split(" |\n");
        Args avhs = new Args(args);
        avhs.getTask();
        assertTrue(avhs.getTaskFlag());
        assertEquals(avhs.getCollectionFile(), testCollectedFile);
        assertEquals(avhs.getFilesToCollect(), filesToCollectList);
        String arguments_ex = "/ 23 /232i f d";
        args = arguments_ex.split(" |\n");
        Args avhs_exception = new Args(args);
        assertThrows(IndexOutOfBoundsException.class, () -> avhs_exception.getTask());

        String short_arguments = "fileToCollect1.txt fileToCollect2.txt fileToCollect3.txt fileToCollect4.txt fileToCollect5.txt -out collectionFile.txt";
        args = short_arguments.split(" |\n");
        Args avhs_short = new Args(args);
        avhs_short.getTask();
        assertTrue(avhs_short.getTaskFlag());
        assertEquals(avhs_short.getCollectionFile(), short_testCollectedFile);
        assertEquals(avhs_short.getFilesToCollect(), short_filesToCollectList);
    }

    @Test
    public void parseSeparateTaskTest(){
        String arguments = "-u " + defaultPathPattern + "fileToSeparate.txt";
        String[] args = arguments.split(" |\n");
        Args avhs = new Args(args);
        avhs.getTask();
        assertFalse(avhs.getTaskFlag());
        assertEquals(avhs.getFileToSeparate(), testFileToSeparate);

        String arguments_short = "-u fileToSeparate.txt";
        args = arguments_short.split(" |\n");
        Args avhs_short = new Args(args);
        avhs_short.getTask();
        assertFalse(avhs_short.getTaskFlag());
        assertEquals(avhs_short.getFileToSeparate(), short_testFileToSeparate);

        String arguments_ex = "-  /cxzask2/task2/src/main/resources/fileToSeparate.txt";
        args = arguments_ex.split(" |\n");
        Args avhs_exception = new Args(args);
        assertThrows(IndexOutOfBoundsException.class, () -> avhs_exception.getTask());
    }
}
