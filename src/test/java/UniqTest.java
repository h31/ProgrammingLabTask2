import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class UniqTest {

    private String inputName = "input.txt";
    private String outputName = "output.txt";

    private void generateInputFile(String[] lines) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(inputName))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        }
    }

    @Test
    public void writeUniqTest() throws Exception {
        String[] lines = {"aa", "bb", "bb"};
        String[] expectedLines = {"aa"};
        generateInputFile(lines);
        Uniq u = new Uniq(true, false, 0, false);
        u.writeUniq(inputName, outputName);
        checkOutputFile(expectedLines);
    }

    @Test
    public void writeUniqTest2() throws Exception {
        String[] lines = {"aA", "Aa", "bb"};
        String[] expectedLines = {"2\taa", "1\tbb"};
        generateInputFile(lines);
        Uniq u = new Uniq(false, true, 0, true);
        u.writeUniq(inputName, outputName);
        checkOutputFile(expectedLines);
    }

    @Test
    public void writeUniqTest3() throws Exception {
        String[] lines = {"gspppppp", "qWpppPPp", "aAb", "aab"};
        String[] expectedLines = {"gspppppp", "aab"};
        generateInputFile(lines);
        Uniq u = new Uniq(false, false, 2, true);
        u.writeUniq(inputName, outputName);
        checkOutputFile(expectedLines);
    }

    @Test
    public void writeUniqTest4() throws Exception {
        String[] lines = {"aaa", "aAa", "abb", "abb", "xy", "Xy"};
        String[] expectedLines = {"aaa", "aAa", "abb", "xy", "Xy"};
        generateInputFile(lines);
        Uniq u = new Uniq(false, false, 1, false);
        u.writeUniq(inputName, outputName);
        checkOutputFile(expectedLines);
    }

    @Test
    public void writeUniqTest5() throws Exception {
        String[] lines = {"aaa", "bAA", "aab"};
        String[] expectedLines = {"1\taab"};
        generateInputFile(lines);
        Uniq u = new Uniq(true, true, 1, true);
        u.writeUniq(inputName, outputName);
        checkOutputFile(expectedLines);
    }

    private void checkOutputFile(String[] expectedLines) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(outputName))) {
            String line;
            int i = 0;
            while ((line = bufferedReader.readLine()) != null) {
                assertEquals(line, expectedLines[i++]);
            }
        }
    }
}