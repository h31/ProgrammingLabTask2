import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Tests {
    Grep grep = new Grep("C:\\Users\\Dwohco\\ProgrammingLabTask2\\ProgramLab2\\src\\test\\FileForRead");

    @Test
    void findOnWord() {
        List<String> expResult = Arrays.asList(grep.getLines().get(0));
        assertEquals(expResult, grep.findOnWord("рядом"));
    }

    @Test
    void findOnRegex() {
        List<String> expResult = Arrays.asList(grep.getLines().get(2));
        assertEquals(expResult, grep.findOnRegex("ул\\.\\s+(.+),\\s+д\\.\\s+(\\d+),\\s+кв\\.\\s+(\\d+)"));
    }

    @Test
    void findExceptRegex() {
        List<String> expResult = Arrays.asList(grep.getLines().get(0), grep.getLines().get(1));
        assertEquals(expResult, grep.findExceptRegex("ул\\.\\s+(.+),\\s+д\\.\\s+(\\d+),\\s+кв\\.\\s+(\\d+)"));
    }
}

