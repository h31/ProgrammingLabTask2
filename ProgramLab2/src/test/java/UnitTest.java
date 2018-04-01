import logic.Grep;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnitTest {
    Grep grep = new Grep("src\\test\\FileForRead");


    @Test
    void findOnRegex() {
        List<String> expResult = Collections.singletonList(grep.getLines().get(16));
        assertEquals(expResult, grep.findOnRegex("ул\\.\\s+(.+),\\s+д\\.\\s+(\\d+),\\s+кв\\.\\s+(\\d+)"));
        List<String> exp = Collections.singletonList(grep.getLines().get(0));
        assertEquals(exp, grep.findOnRegex("мартобря"));
        List<String> exp2 = Collections.singletonList(grep.getLines().get(0));
        grep.setIgnoreCase(true);
        assertEquals(exp2, grep.findOnRegex("МАРТобря"));

    }

    @Test
    void findExceptRegex() {
        List<String> expResult = Arrays.asList(grep.getLines().get(16));
        assertEquals(expResult, grep.findExceptRegex("/"));
    }
}

