import org.junit.jupiter.api.Test;
import ru.spbstu.kspt.task2.logic.Transpose;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;


class launcherTest {
    @Test
    public void Test1() {
        Path inputFile = Paths.get("src/test/java/inputFile.txt");
        List<List<String>> l = new ArrayList<>();
        Transpose matrix = new Transpose(true, true, 4, "");
        List<String> expected1 = Arrays.asList("Win1", " PSP", "3Tas", "  2P");
        List<String> expected2 = Arrays.asList("Winn", " Fly", "Team");
        List<String> expected3 = Arrays.asList("Tabl", "Prog");
        List<String> expected4 = Arrays.asList("Boom");
        List<String> expected5 = Arrays.asList("Zomb");
        l = Arrays.asList(expected1, expected2, expected3, expected4, expected5);
        assertEquals(l, matrix.allLogic(inputFile));
    }
}