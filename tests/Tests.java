import com.project.casing.Receiver;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Tests {

    private static final String PATH_TO_JUST = "tests/files/justFile.txt";
    private static final String PATH_TO_ARCHIVE = "tests/files/archiveFile.uz";
    private static final String JUST_NAME = "tests/files/justFile";
    private static final String ARCHIVE_NAME = "tests/files/archiveFile";
    private static final String ARCHIVE_FILE = "М а м а|3   м ы|4 л а   р а|7 м у ." +
            " С е р ь е з н о ? Д а   м н е|3   т а к   С е р е|6 г а   с к а з а л|3 ." +
            " А а|3   п о ч е м у|3   м ы   г о в о р и м   к а к   М а|4 с к в и ч и ?" +
            " П р о с т о   м ы|3   о с о|4 б е н|6 ы е .";
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

    private void justAction(String justName, String pathToArchive, String pathToJust, String justFile, String key) {
        Receiver.testMode(String.format("pack-rle -%s -out %s %s", justName, key, pathToArchive));
        Receiver.create();
        assertEquals("completed", Receiver.getAnswer());

        List<String> result;
        try {
            result = Files.readAllLines(Paths.get(pathToJust), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalArgumentException("Something went wrong");
        }
        assertEquals(justFile, result.get(0));
    }

    @Test
    void easyPacking() {
        easyAction(PATH_TO_JUST, JUST_NAME, ARCHIVE_FILE, ".uz");
    }

    @Test
    void easyUnpacking() {
        easyAction(PATH_TO_ARCHIVE, ARCHIVE_NAME, JUST_FILE, ".txt");
    }

    private void easyAction(String pathToArchive, String archiveName, String justFile, String expansion) {
        Receiver.testMode(String.format("pack-rle %s", pathToArchive));
        Receiver.create();
        assertEquals("completed", Receiver.getAnswer());

        List<String> result;
        try {
            result = Files.readAllLines(Paths.get(archiveName + expansion), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalArgumentException("Something went wrong");
        }

        assertEquals(justFile, result.get(0));
    }
}
