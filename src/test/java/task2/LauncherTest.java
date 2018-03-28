package task2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import task2.console.TranspositionLauncher;

import java.io.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class LauncherTest {
    private static File textFile;
    private static File anotherTextFile;
    private static File oneAnotherTextFile;
    private static File andAnotherTextFile;

    private static File outFile;

    private static FileReader textReader;
    private static FileReader anotherTextReader;
    private static FileReader oneAnotherTextReader;
    private static FileReader andAnotherTextReader;

    private static FileWriter outWriter;
    @Before
    public void Before() throws IOException {
        textFile = new File("src/test/java/task2/text.txt");
        anotherTextFile = new File("src/test/java/task2/anotherText.txt");
        oneAnotherTextFile = new File("src/test/java/task2/oneAnotherText.txt");
        andAnotherTextFile = new File("src/test/java/task2/andAnotherText.txt");

        textReader = new FileReader(textFile);
        anotherTextReader = new FileReader(anotherTextFile);
        oneAnotherTextReader = new FileReader(oneAnotherTextFile);
        andAnotherTextReader = new FileReader(andAnotherTextFile);

        outFile = new File("src/test/java/task2/out.txt");
        outWriter = new FileWriter(outFile);
    }

    @After
    public void After() throws IOException {
        System.gc();
        outFile.delete();
    }

    private void assertFileContent(String expectedContent, File file) throws FileNotFoundException {
        String reader = new BufferedReader(new FileReader(file)).lines().collect(Collectors.joining("\n"));
        assertEquals(expectedContent, reader);
    }

    @Test
    public void transpositionLauncherTest() throws IOException {
        String[] firstArgs = {"src/test/java/task2/text.txt", "-o", "src/test/java/task2/out.txt", "-a", "3"};
        TranspositionLauncher firstLauncher = new TranspositionLauncher();
        firstLauncher.launch(firstArgs);
        File currentFile = new File("src/test/java/task2/out.txt");
        assertFileContent("aaa bbb ccc\n" +
                "aaa bbb ccc\n" + "aaa bbb ccc", currentFile);
    }
}
