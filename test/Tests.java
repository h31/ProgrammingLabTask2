import com.project.casing.Receiver;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class Tests {

    private static final String PATH_TO_JUST = "test/files/justFile.txt";
    private static final String PATH_TO_ARCHIVE = "test/files/archiveFile.uz";
    private static final String JUST_NAME = "test/files/justFile";
    private static final String ARCHIVE_NAME = "test/files/archiveFile";
    private static final String ARCHIVE_FILE = "Мама|3 мы|4ла ра|7му." +
            "Серьезно?" +
            "Да мне|3 так Сере|6га сказал|3" +
            ".Аа|3 почему|3 мы говорим как Ма|4сквичи?" +
            "Просто мы|3 осо|4бен|6ые.";
    private static final String JUST_FILE = "Мамааа мыыыыла раааааааму.Серьезно?" +
            "Да мнеее так Сереееееега сказаллл." +
            "Аааа почемууу мы говорим как Маааасквичи?" +
            "Просто мыыы осооообенннннные.";

    @Test
    void packing() {
        justAction(ARCHIVE_NAME, PATH_TO_JUST, PATH_TO_ARCHIVE, ARCHIVE_FILE, "z");
    }

    @Test
    void unpacking() {
        justAction(JUST_NAME, PATH_TO_ARCHIVE, PATH_TO_JUST, JUST_FILE, "u");
    }

    private void justAction(String fileName, String firstPath, String secondPath, String textOfFile, String key) {
        Receiver.testMode(String.format("pack-rle -%s -out %s %s", fileName, key, firstPath));
        Receiver.create();
        assertEquals("completed", Receiver.getAnswer());

        List<String> result;
        try {
            result = Files.readAllLines(Paths.get(secondPath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalArgumentException("Something went wrong");
        }
        assertEquals(textOfFile, result.get(0));
    }

    @Test
    void easyPacking() {
        easyAction(PATH_TO_JUST, JUST_NAME, ARCHIVE_FILE, ".uz");
    }

    @Test
    void easyUnpacking() {
        easyAction(PATH_TO_ARCHIVE, ARCHIVE_NAME, JUST_FILE, ".txt");
    }

    private void easyAction(String path, String firstName, String secondName, String expansion) {
        Receiver.testMode(String.format("pack-rle %s", path));
        Receiver.create();
        assertEquals("completed", Receiver.getAnswer());

        List<String> result;
        try {
            result = Files.readAllLines(Paths.get(firstName + expansion), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalArgumentException("Something went wrong");
        }

        assertEquals(secondName, result.get(0));
    }

    @Test
    void wrongCommand() {
        List<String> wrongCommands = Arrays.asList("", "pack-rle", "pack-rle dsfsd", "pack-rle -out dsfdf");

        for (String command : wrongCommands) {
            assertThrows(IllegalArgumentException.class, () -> {
                Receiver.testMode(command);
                Receiver.create();
            });
        }
    }
}
