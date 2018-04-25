package test2;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import task2.console.CmdCheck;
import task2.logic.FileCoder;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Tag("Checking Cmd")
    @Test
    void checkCmd() {
        String str = "";
        String args[] = str.split("\\s+");
        assertThrows(IllegalArgumentException.class, () -> new CmdCheck().readCmd(args));
    }

    @Tag("Checking -c/-d and key")
    @Test
    void checkKey(){
        String str = "abcd in.txt";
        String args[] = str.split("\\s+");
        assertThrows(IllegalArgumentException.class, () -> new CmdCheck().readCmd(args));
        str = "-p abcd in.txt";
        String args1[] = str.split("\\s+");
        assertThrows(IllegalArgumentException.class, () -> new CmdCheck().readCmd(args1));
    }

    @Tag("Checking inputName")
    @Test
    void checkInputName() {
        String str = "-c abcd";
        String args[] = str.split("\\s+");
        assertThrows(IllegalArgumentException.class, () -> new CmdCheck().readCmd(args));
        str = "-d abcd";
        String args1[] = str.split("\\s+");
        assertThrows(IllegalArgumentException.class, () -> new CmdCheck().readCmd(args1));
    }

    @Tag("Checking -o and outputName")
    @Test
    void checkOutputName() {
        String str = "-c abcd in.txt -o";
        String args[] = str.split("\\s+");
        assertThrows(IllegalArgumentException.class, () -> new CmdCheck().readCmd(args));
        str = "-c abcd in.txt -p out.txt";
        String args1[] = str.split("\\s+");
        assertThrows(IllegalArgumentException.class, () -> new CmdCheck().readCmd(args1));
        str = "-c abcd in.txt out.txt";
        String args2[] = str.split("\\s+");
        assertThrows(IllegalArgumentException.class, () -> new CmdCheck().readCmd(args2));
    }

    @Tag("Checking Cipher")
    @Test
    void checkCipher() throws IOException {
        FileCoder code1 = new FileCoder("abcd");
        code1.transform("src/test/java/test2/in.txt", "src/test/java/test2/out.txt");
        FileCoder code2 = new FileCoder("abcd");
        code2.transform("src/test/java/test2/out.txt", "src/test/java/test2/change");
        FileInputStream file1 = new FileInputStream("src/test/java/test2/in.txt");
        FileInputStream file2 = new FileInputStream("src/test/java/test2/change");
        int b1, b2;
        while ((b1 = file1.read()) != -1) {
            b2 = file2.read();
            assertEquals(b1, b2);
        }
        file1.close();
        file2.close();
    }


}
