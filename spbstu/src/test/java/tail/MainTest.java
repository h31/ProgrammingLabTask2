package tail;

import org.junit.Test;
import org.junit.Assert;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class MainTest {

    private void assertFileContent(String actualOutputName, String expectedOutputName) throws FileNotFoundException {
        FileReader file1 = new FileReader(actualOutputName);
        FileReader file2 = new FileReader(expectedOutputName);
        Scanner scanner1 = new Scanner(file1);
        Scanner scanner2 = new Scanner(file2);

        while (file1.)
    }

    @Test
    public void main() {
    }
}