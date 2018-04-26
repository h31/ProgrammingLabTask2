package tail;

import org.junit.Test;
import org.junit.Assert;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainTest {
    private Scanner input;

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
    public void testC() throws IOException {
        String[] args = {"tail", "-c", "50", "-o", "/Users/molkos/IdeaProjects/ProgrammingLabTask2/spbstu/src/main/java/Output.txt",
                "/Users/molkos/IdeaProjects/ProgrammingLabTask2/spbstu/src/main/java/Text1.txt"};
        Main.main(args, input);
        assertFileContent("/Users/molkos/IdeaProjects/ProgrammingLabTask2/spbstu/src/main/java/Output.txt",
                "/Users/molkos/IdeaProjects/ProgrammingLabTask2/spbstu/src/main/java/Expected1.txt");
    }

    @Test
    public void testN() throws IOException {
        String[] args = {"tail", "-n", "10", "-o", "/Users/molkos/IdeaProjects/ProgrammingLabTask2/spbstu/src/main/java/Output.txt",
                "/Users/molkos/IdeaProjects/ProgrammingLabTask2/spbstu/src/main/java/Text2.txt"};
        Main.main(args, input);
        assertFileContent("/Users/molkos/IdeaProjects/ProgrammingLabTask2/spbstu/src/main/java/Output.txt",
                "/Users/molkos/IdeaProjects/ProgrammingLabTask2/spbstu/src/main/java/Expected2.txt");
    }

    @Test
    public void testFromCmdToFile() throws IOException {
        String[] args = {"tail", "-c", "36", "-o", "/Users/molkos/IdeaProjects/ProgrammingLabTask2/spbstu/src/main/java/Output.txt"};
        input = new Scanner("Я б навеки забыл кабаки\n" +
                "И стихи бы писать забросил.\n" +
                "Только б тонко касаться руки\n" +
                "И волос твоих цветом в осень.");
        Main.main(args, input);
        assertFileContent("/Users/molkos/IdeaProjects/ProgrammingLabTask2/spbstu/src/main/java/Output.txt",
                "/Users/molkos/IdeaProjects/ProgrammingLabTask2/spbstu/src/main/java/Expected3.txt");
    }
}