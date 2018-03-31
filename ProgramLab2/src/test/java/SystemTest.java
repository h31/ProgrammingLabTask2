import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SystemTest {

    @Test
    void Grep() {
        String[] argument = {"grep","-r", "мартобря", "", "src\\test\\FileForRead"};
        ConsoleUI console = new ConsoleUI();
        console.testForTest("grep -r мартобря src\\test\\FileForRead");
        console.start();
        List<String> result = new ArrayList<>();
        try {
            result = Files.readAllLines(Paths.get("src\\test\\FileForRead"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals("Ниоткуда с любовью, надцатого мартобря,", result.get(0));

        //testGrep("grep","-r", "мартобря",   "", "src\\test\\FileForRead");
        //testGrep("grep","-v", "мартобря",   "", "src\\test\\FileForRead");
        //testGrep("grep","-r", "Милая", "-i", "src\\test\\FileForRead");
    }
}
