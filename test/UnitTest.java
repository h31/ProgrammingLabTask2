import logic.Grep;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnitTest {

    @Test
    void findOnWord() {
        Grep firstCommand = new Grep(false, false, false,
                "мартобря", "test\\FileForRead");
        List<String> firstExpResult = Collections.singletonList("/Ниоткуда с любовью, надцатого мартобря,");
        assertEquals(firstExpResult, firstCommand.findOfFile());
        Grep secondCommand = new Grep(false, false, false,
                "\\d+", "test\\FileForRead"); //в другую сторону
        List<String> secondExpResult = Collections.singletonList("\\d+");
        assertEquals(secondExpResult, secondCommand.findOfFile());
        Grep thirdCommand = new Grep(false, false, true,
                "МАртОБря", "test\\FileForRead");
        List<String> thirdExpResult = Collections.singletonList("/Ниоткуда с любовью, надцатого мартобря,");
        assertEquals(thirdExpResult, thirdCommand.findOfFile());
    }

    @Test
    void findOnRegex() {
        Grep firstCommand = new Grep(true, false, false,
                "ул\\.\\s+(.+),\\s+д\\.\\s+(\\d+),\\s+кв\\.\\s+(\\d+)", "test\\FileForRead");
        List<String> firstExpResult = Collections.singletonList("ул. Новая, д. 12, кв. 325");
        assertEquals(firstExpResult, firstCommand.findOfFile());
        Grep secondCommand = new Grep(true, false, true,
                "Ул\\.\\s+(.+),\\s+Д\\.\\s+(\\d+),\\s+Кв\\.\\s+(\\d+)", "test\\FileForRead");
        List<String> secondExpResult = Collections.singletonList("ул. Новая, д. 12, кв. 325");
        assertEquals(secondExpResult, secondCommand.findOfFile());

    }

    @Test
    void findExceptRegex() {
        Grep firstCommand = new Grep(false, true, false,
                "/", "test\\FileForRead");
        List<String> firstExpResult = Arrays.asList("ул. Новая, д. 12, кв. 325", "\\d+");
        assertEquals(firstExpResult, firstCommand.findOfFile());
        Grep secondCommand = new Grep(false, true, true,
                "И", "test\\FileForRead");
        List<String> secondExpResult = Arrays.asList("/как безумное зеркало повторяя.",
                "ул. Новая, д. 12, кв. 325", "\\d+");
        assertEquals(secondExpResult, secondCommand.findOfFile());
    }
}