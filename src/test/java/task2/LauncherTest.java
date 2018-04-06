package task2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import task2.console.TranspositionLauncher;

import java.io.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class LauncherTest {
    private static File textFile = new File("src/test/java/task2/text.txt");

    private static File outFile = new File("src/test/java/task2/out.txt");

    private static FileReader textReader;

    private static FileWriter outWriter;

    @Before
    public void before() throws IOException {
        textReader = new FileReader(textFile);
        outWriter = new FileWriter(outFile);
    }

    @After
    public void after() throws IOException {
        outFile.delete();
    }

    private void assertFileContent(String expectedContent, File file) throws FileNotFoundException {
        String reader = new BufferedReader(new FileReader(file)).lines().collect(Collectors.joining("\n"));
        assertEquals(expectedContent, reader);
    }

    @Test
    public void transpositionLauncherTest() throws IOException {
        textReader = new FileReader(textFile);
        String[] firstArgs = {"src/test/java/task2/text.txt", "-o", "src/test/java/task2/out.txt", "-a", "3"};
        TranspositionLauncher firstLauncher = new TranspositionLauncher();
        firstLauncher.launch(firstArgs);
        File currentFile = new File("src/test/java/task2/out.txt");
        assertFileContent("aaa bbb ccc\n" +
                "aaa bbb ccc\n" + "aaa bbb ccc", currentFile);
    }
}
