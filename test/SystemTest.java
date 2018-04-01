
import console.ConsoleUI;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class SystemTest {

    @Test
    void Grep() throws IOException {
        ConsoleUI console = new ConsoleUI();
        console.setCommandOnTest("grep -r -i маРтобря test\\FileForRead");
        console.start();
        List<String> result = Files.readAllLines(Paths.get("test\\FileForRead"), StandardCharsets.UTF_8);
        Assert.assertEquals("/Ниоткуда с любовью, надцатого мартобря,", result.get(0));
        console.setCommandOnTest("grep -v -i / test\\FileForRead");
        Assert.assertEquals("ул. Новая, д. 12, кв. 325", result.get(16));
    }
}
