package task2;

import org.junit.Before;
import org.junit.Test;
import task2.logic.Transposition;

import java.io.*;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class TranspositionTest {
    private static File textFile = new File("src/test/java/task2/text.txt");
    private static File anotherTextFile = new File("src/test/java/task2/anotherText.txt");
    private static File oneAnotherTextFile = new File("src/test/java/task2/oneAnotherText.txt");
    private static File andAnotherTextFile = new File("src/test/java/task2/andAnotherText.txt");

    private static File outFile = new File("src/test/java/task2/out.txt");

    private static FileReader textReader;
    private static FileReader anotherTextReader;
    private static FileReader oneAnotherTextReader;
    private static FileReader andAnotherTextReader;
    private static FileReader outputReader;

    private static FileWriter outWriter;

    @Before
    public void before() throws IOException {
        textReader = new FileReader(textFile);
        anotherTextReader = new FileReader(anotherTextFile);
        oneAnotherTextReader = new FileReader(oneAnotherTextFile);
        andAnotherTextReader = new FileReader(andAnotherTextFile);
        outWriter = new FileWriter(outFile);
        outputReader = new FileReader(outFile);
    }

    public void after() throws IOException {
        outFile.delete();
    }

    @Test
    public void transpositionTest() throws IOException {
        StringWriter outputWriter = new StringWriter();
        Transposition matrix = new Transposition(3, false, false);
        List<List<String>> matrixContent = matrix.getMatrix(textReader);
        matrix.writeMatrix(matrixContent, outputWriter);
        assertEquals("aaa bbb ccc\n" +
                "aaa bbb ccc\n" + "aaa bbb ccc\n", outputWriter.toString());
    }

    @Test
    public void widthTest() throws IOException {
        StringWriter outputWriter = new StringWriter();
        Transposition matrix = new Transposition(5, false, false);
        List<List<String>> matrixContent = matrix.getMatrix(andAnotherTextReader);
        matrix.writeMatrix(matrixContent, outputWriter);
        assertEquals("word  hey   the  \n" + "another hey   third\n"
                + "word  hey   line \n", outputWriter.toString());
    }

    @Test
    public void cutTest() throws IOException {
        StringWriter outputWriter = new StringWriter();
        Transposition matrix = new Transposition(2, true, false);
        List<List<String>> matrixContent = matrix.getMatrix(anotherTextReader);
        matrix.writeMatrix(matrixContent, outputWriter);
        assertEquals("aa aa aa\n" +
                "aa aa aa\n" + "aa aa aa\n", outputWriter.toString());
    }

    @Test
    public void alignRightTest() throws IOException {
        StringWriter outputWriter = new StringWriter();
        Transposition matrix = new Transposition(7, false, true);
        List<List<String>> matrixContent = matrix.getMatrix(oneAnotherTextReader);
        matrix.writeMatrix(matrixContent, outputWriter);
        assertEquals("   line    line    just     was    line\n" +
                " number  number kidding     the actually\n" +
                "    one   three      it  second       !\n", outputWriter.toString());
    }

    @Test
    public void doubleTranspositionTest() throws IOException {
        Transposition matrix = new Transposition(3, false, false);
        List<List<String>> matrixContent = matrix.getMatrix(textReader);
        matrix.writeMatrix(matrixContent, outWriter);
        Transposition secondMatrix = new Transposition(3, false, false);
        List<List<String>> secondMatrixContent = secondMatrix.getMatrix(outputReader);
        StringWriter outputWriter = new StringWriter();
        secondMatrix.writeMatrix(secondMatrixContent, outputWriter);
        assertEquals("aaa aaa aaa\n" +
                "bbb bbb bbb\n" + "ccc ccc ccc\n", outputWriter.toString());
    }
}

