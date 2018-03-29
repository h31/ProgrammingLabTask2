package console;

import java.io.File;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void main() {
        Main mainHelp = new Main();

        assertEquals(new File("src/test/test1.txt"),
                mainHelp.main(new String[]{"-r", "-d", "src", "test1.txt"}));

        assertEquals(new File("nothing"),
                mainHelp.main(new String[]{"test2.txt"}));

        assertEquals(new File("src/test2.txt"),
                mainHelp.main(new String[]{"-d", "src", "test2.txt"}));

        assertEquals(new File("nothing"),
                mainHelp.main(new String[]{"-r", "-d", "src", "test3.txt"}));
    }
}