import console.GrepLauncher;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SystemTest {
    @Test
    void systemTests() {
        String inputFileName = "test\\FileForRead";

        String[] args1 = {"grep", "мартобря", inputFileName};
        GrepLauncher launcher1 = new GrepLauncher();
        launcher1.launch(args1);
        List<String> expResult1 = Collections.singletonList("/Ниоткуда с любовью, надцатого мартобря,");
        assertEquals(expResult1, launcher1.getResult());

        String[] args2 = {"grep", "-r", "ул\\.\\s+(.+),\\s+д\\.\\s+(\\d+),\\s+кв\\.\\s+(\\d+)", inputFileName};
        GrepLauncher launcher2 = new GrepLauncher();
        launcher2.launch(args2);
        List<String> expResult2 = Collections.singletonList("ул. Новая, д. 12, кв. 325");
        assertEquals(expResult2, launcher2.getResult());

        String[] args3 = {"grep", "-r", "-i", "УЛ\\.\\s+(.+),\\s+д\\.\\s+(\\d+),\\s+КВ\\.\\s+(\\d+)", inputFileName};
        GrepLauncher launcher3 = new GrepLauncher();
        launcher3.launch(args3);
        List<String> expResult3 = Collections.singletonList("ул. Новая, д. 12, кв. 325");
        assertEquals(expResult3, launcher3.getResult());

        String[] args4 = {"grep", "-v", "/", inputFileName};
        GrepLauncher launcher4 = new GrepLauncher();
        launcher4.launch(args4);
        List<String> expResult4 = Arrays.asList("ул. Новая, д. 12, кв. 325", "\\d+");
        assertEquals(expResult4, launcher4.getResult());

        String[] args5 = {"grep", "-v", "-i", "И", inputFileName};
        GrepLauncher launcher5 = new GrepLauncher();
        launcher5.launch(args5);
        List<String> expResult5 = Arrays.asList("/как безумное зеркало повторяя.",
                "ул. Новая, д. 12, кв. 325", "\\d+");
        assertEquals(expResult5, launcher5.getResult());
    }
}
