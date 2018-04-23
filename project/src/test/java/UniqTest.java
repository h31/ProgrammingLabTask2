

import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;


public class UniqTest {

    public String outputStreamToString(ByteArrayOutputStream outputStream) {
        String[] real = outputStream.toString().split("\n");
        String result = "";
        for (int i = 0; i < real.length; i++) {
            result += real[i].trim() + "\n";
        }
        return result.substring(0, result.length() - 1);
    }

    UniqProcess process = UniqProcess.getInstance();
    final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private String[] argument;
    private String real;

    @Test
    public void fromFileToConsole() throws Exception {
        System.setOut(new PrintStream(outputStream));

        // without -o ,-u
        argument = new String[]{"-i", "-s", "4", "-c", "input/file.txt"};
        String expect = "5 Monday\n2 happy\n1 Finish\n3 Whom have a try";
        process.run(argument);
        real = outputStreamToString(outputStream);
        assertEquals(expect, real);
        outputStream.reset();

        //only -c
        argument = new String[]{"-c", "input/file.txt"};
        expect = "1 you  have a try\n1 WhoM have a try\n3 Monday\n1 Friday\n2 happy\n1 friday\n1 Finish\n1 Whom have a try";
        process.run(argument);
        real = outputStreamToString(outputStream);
        assertEquals(expect, real);
        outputStream.reset();


        //without -o
        argument = new String[]{"-i", "-u", "-s", "4", "-c", "input/file.txt"};
        expect = "1 Finish";
        process.run(argument);
        real = outputStreamToString(outputStream);
        assertEquals(expect, real);
        outputStream.reset();

        //without -i , -o , -u
        argument = new String[]{"-s", "4", "-c", "input/file.txt"};
        expect = "5 Monday\n2 happy\n1 Finish\n3 Whom have a try";
        process.run(argument);
        real = outputStreamToString(outputStream);
        assertEquals(expect, real);
        outputStream.reset();

    }

    @Test
    public void fromFileToFile() throws Exception {
        System.setOut(new PrintStream(outputStream));

        //All commands
        argument = new String[]{"-i", "-u", "-s", "2", "-c", "-o", "outputFile.txt", "input/file.txt"};
        String[] expectingResults = new String[]{"1 you  have a try"};
        process.run(argument);
        process.delete();
        Path path = Paths.get("outputFile.txt");
        File testFile = path.toFile();

        try (BufferedReader br = new BufferedReader(new FileReader(testFile))) {
            for (String anExpectResult : expectingResults) {
                assertEquals(anExpectResult, br.readLine().trim());
            }
        } finally {
            Files.delete(path);
        }

        //without -u
        argument = new String[]{"-i", "-s", "3", "-c", "-o", "outputFile.txt", "input/file.txt"};
        expectingResults = new String[]{"1 you  have a try", "5 Monday", "2 happy", "1 Finish", "2 Whom have a try"};
        process.run(argument);
        process.delete();
        path = Paths.get("outputFile.txt");
        testFile = path.toFile();

        try (BufferedReader br = new BufferedReader(new FileReader(testFile))) {
            for (String anExpectResult : expectingResults) {
                assertEquals(anExpectResult, br.readLine().trim());
            }
        } finally {
            Files.delete(path);
        }
    }

    String testInput;
    ByteArrayInputStream inputStream;

    @Test
    public void fromConsoleToFile() throws Exception {
        System.setOut(new PrintStream(outputStream));

        //ignore 2 chars
        testInput = "Monday\nFriday\nMonday\nfriday\nhappy\nMonday\nhappy\nend\n";
        argument = new String[]{"-i", "-s", "2", "-c", "-o", "outputFile.txt"};
        String[] expectedResults = "3 Monday\n2 happy\n2 friday\n1 end".split("\n");

        inputStream = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(inputStream);
        process.run(argument);
        process.delete();
        Path path = Paths.get("outputFile.txt");
        File testFile = path.toFile();

        try (BufferedReader br = new BufferedReader(new FileReader(testFile))) {
            for (String anExpectResult : expectedResults) {
                assertEquals(anExpectResult, br.readLine().trim());
            }
        } finally {
            Files.delete(path);
        }
        inputStream.reset();
        outputStream.reset();

        // ignore 3 chars
        testInput = "Monday\nFriday\nMonday\nfriday\nhappy\nMonday\nhappy\nend\n";
        argument = new String[]{"-i", "-s", "3", "-c", "-o", "outputFile.txt"};
        expectedResults = "5 Monday\n2 happy\n1 end".split("\n");

        inputStream = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(inputStream);
        process.run(argument);
        process.delete();
        path = Paths.get("outputFile.txt");
        testFile = path.toFile();

        try (BufferedReader br = new BufferedReader(new FileReader(testFile))) {
            for (String anExpectResult : expectedResults) {
                assertEquals(anExpectResult, br.readLine().trim());
            }
        } finally {
            Files.delete(path);
        }
        inputStream.reset();
        outputStream.reset();
    }

    @Test
    public void fromConsoleToConsole() throws Exception {
        System.setOut(new PrintStream(outputStream));
        testInput = "Monday\nFriday\nMonday\nfriday\nhappy\nMonday\nhappy\nend\n";
        argument = new String[]{"-i", "-u","-s", "4", "-c"};
        String expectedResults = "1 end";
        inputStream = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(inputStream);
        process.run(argument);

        String realResults = outputStreamToString(outputStream);
        assertEquals(expectedResults,realResults);
        inputStream.reset();
        outputStream.reset();

        //2
        testInput = "Monday\nFriday\nMonday\nfriday\nhappy\nMonday\nhappy\nend\n";
        argument = new String[]{"-i", "-c"};
        expectedResults = "3 Monday\n2 happy\n2 friday\n1 end";
        inputStream = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(inputStream);
        process.run(argument);

        realResults = outputStreamToString(outputStream);
        assertEquals(expectedResults,realResults);
        inputStream.reset();
        outputStream.reset();
    }
}

