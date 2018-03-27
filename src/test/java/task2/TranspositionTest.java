package task2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import task2.logic.Transposition;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;


public class TranspositionTest {
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
    public void transpositionTest() throws IOException {
        Transposition matrix = new Transposition(3, false, false);
        List<List<String>> matrixContent = matrix.getMatrix(textReader);
        matrix.transmitMatrix(matrixContent, outWriter);
        assertFileContent("aaa bbb ccc\n" +
                "aaa bbb ccc\n" + "aaa bbb ccc", outFile);
    }

    @Test
    public void widthTest() throws IOException {
        Transposition matrix = new Transposition(5, false, false);
        List<List<String>> matrixContent = matrix.getMatrix(andAnotherTextReader);
        matrix.transmitMatrix(matrixContent, outWriter);
        assertFileContent("word  hey   the  \n" + "another hey   third\n"
                + "word  hey   line ", outFile);
    }

    @Test
    public void cutTest() throws IOException {
        Transposition matrix = new Transposition(2, true, false);
        List<List<String>> matrixContent = matrix.getMatrix(anotherTextReader);
        matrix.transmitMatrix(matrixContent, outWriter);
        assertFileContent("aa aa aa\n" +
                "aa aa aa\n" + "aa aa aa", outFile);
    }

    @Test
    public void alignRightTest() throws IOException {
        Transposition matrix = new Transposition(7, false, true);
        List<List<String>> matrixContent = matrix.getMatrix(oneAnotherTextReader);
        matrix.transmitMatrix(matrixContent, outWriter);
        assertFileContent("   line    line    just     was    line\n" +
                " number  number kidding     the actually\n" +
                "    one   three      it  second       !", outFile);
    }
}

