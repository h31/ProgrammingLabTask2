import org.junit.jupiter.api.Test;
import ru.spbstu.kspt.task2.logic.Transpose;


class readerTest {
    @Test
    void WriterTest() {
        Transpose t = new Transpose(true, true, 4, "");
        System.out.println(t.reader("src/test/java/inputFile.txt"));
    }

    @Test
    void ReaderTest() {
        Transpose t = new Transpose(true, true, 4, "");
        t
        System.out.println(writer("src/test/java/outputFile.txt"));
    }
}