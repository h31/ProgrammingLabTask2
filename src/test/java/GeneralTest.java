import org.junit.jupiter.api.Test;
import ru.spbstu.kspt.task2.logic.Transposition;


class GeneralTest {
    @Test
    void readerTest() {
        Transposition t = new Transposition(true, true, 4, "src/test/java/outputFile.txt");
        System.out.println(t.reader("src/test/java/inputFile.txt"));
    }

    @Test
    void allLogicTest() {
        Transposition t = new Transposition(true, true, 4, "src/test/java/outputFile.txt");
        System.out.println(t.allLogic("src/test/java/inputFile.txt"));
    }

    @Test
    void writerTest() {
        Transposition t = new Transposition(true, true, 4, "src/test/java/outputFile.txt");
        t.writer("src/test/java/inputFile.txt");
    }

    @Test
    void exceptionTest() {
        Transposition t = new Transposition(true, false, 0, "src/test/java/outputFile.txt");
        t.writer("src/test/java/inputFile.txt");
    }
}