package console;

import java.io.File;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleTest {

    @Test
    void main() {
        Main path = new Main();

        path.main(new String[]{"-r", "-d", "src", "test1.txt"});
        assertEquals(new File("src/test/test1.txt"), path.file);

        path.main(new String[]{"test2.txt"});
        assertEquals(new File("File does not exist"), path.file);

        path.main(new String[]{"-d", "src", "test2.txt"});
        assertEquals(new File("src/test2.txt"), path.file);

        path.main(new String[]{"-r", "-d", "src", "test3.txt"});
        assertEquals(new File("File does not exist"), path.file);

    }
}