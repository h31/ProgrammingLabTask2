import org.junit.jupiter.api.Test;
import ru.spbstu.kspt.task2.logic.Transpose;


class GeneralTest {
    @Test
    void readerTest() {
        Transpose t = new Transpose(true, true, 4, "src/test/java/outputFile.txt");
        System.out.println(t.reader("src/test/java/inputFile.txt"));
    }

    @Test
    void allLogicTest() {
        Transpose t = new Transpose(true, true, 4, "src/test/java/outputFile.txt");
        System.out.println(t.allLogic("src/test/java/inputFile.txt"));
    }

    @Test
    void writerTest() {
        Transpose t = new Transpose(true, true, 4, "src/test/java/outputFile.txt");
        t.writer("src/test/java/inputFile.txt");
    }
}