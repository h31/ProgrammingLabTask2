package tail;

import org.junit.Test;
import org.junit.Assert;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainTest {

    private boolean assertFileContent(String actualOutputName, String expectedOutputName) throws FileNotFoundException {
        FileReader file1 = new FileReader(actualOutputName);
        FileReader file2 = new FileReader(expectedOutputName);
        Scanner scanner1 = new Scanner(file1);
        Scanner scanner2 = new Scanner(file2);
        List<String> text1 = new ArrayList<>();
        List<String> text2 = new ArrayList<>();

        while (scanner1.hasNextLine()) {
            text1.add(scanner1.nextLine());
        }
        while (scanner2.hasNextLine()) {
            text2.add(scanner2.nextLine());
        }
        return text1.equals(text2);
    }

    @Test
    public void main() throws FileNotFoundException {
        assertFileContent("/Users/molkos/IdeaProjects/ProgrammingLabTask2/spbstu/src/main/java/Output.txt",
                "/Users/molkos/IdeaProjects/ProgrammingLabTask2/spbstu/src/main/java/Expected.txt");

    }
}