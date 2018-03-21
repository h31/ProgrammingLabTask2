package unit;

import com.project.logic.archiving.Archive;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArchiveTest {

    private String NORMAL_DATA =
            "Захоодит как-то PHP пррррограмммммист в бар и " +
                    "просит баррррмена рррррррррррррррразделить его код (кооооод?) на его талант, " +
            "а бармен ему и говорит NullPPPointerException";
    private String WRONG_DATA =
            "Я тестировщик, да5|. " +
            "Z ntcnbhjdobr? lf/ Ya10| testirovtcshik, da." +
                    " R TecTирОвщик, Da. Съешь ещё этих мягких " +
                    "французских булочек, да выпей чаю.8|. восемь. " +
                    "три = шесть. Нет, я тос|теровщик. Т0|сттЕРРР. " +
                    "format:c . ©ћќ‘&{Ґ“”•–—˜™љ›њќћџ ЎўЈ¦|§Ё©Є«¬®Ї°±эіґµ¶·ё№є»јЅѕїАБВ rm -rf /*";
    private String ANSWER_NORMAL_DATA =
            "Захо2|дит как-то PHP пр4|ограм5|ист в бар и" +
            " просит бар4|мена р16|азделить его код (ко5|д?) на его талант," +
                    " а бармен ему и говорит Nul2|P3|ointerException";
    private String ANSWER_WRONG_DATA =
            "Я тестировщик, да5&|. Z ntcnbhjdobr?" +
            " lf/ Ya10&| testirovtcshik, da. R TecTирОвщик, Da. " +
            "Съешь ещё этих мягких французских булочек, да выпей чаю.8&|. " +
                    "восемь. три = шесть. Нет, я тос|теровщик. Т0&|ст2|ЕР3|. " +
                    "format:c . ©ћќ‘&{Ґ“”•–—˜™љ›њќћџ ЎўЈ¦|§Ё" +
                    "©Є«¬®Ї°±эіґµ¶·ё№є»јЅѕїАБВ rm -rf /*";

    private String PATH_TO_BIN = "test/unit/files/binFile.txt";
    private String ARCHIVE_ANSWER;
    private String BIN_ANSWER;
    private List<String> LINES_BIN = new ArrayList<>();

    @Test
    void packing() {
        Archive.setFile(Collections.singletonList(NORMAL_DATA));
        Archive.start(null, null, true);
        assertEquals(ANSWER_NORMAL_DATA, Archive.getStreamToFile());

        Archive.setFile(Collections.singletonList(WRONG_DATA));
        Archive.start(null, null, true);
        assertEquals(ANSWER_WRONG_DATA, Archive.getStreamToFile());
    }

    @Test
    void unpacking() {
        Archive.setFile(Collections.singletonList(ANSWER_NORMAL_DATA));
        Archive.start(null, null, false);
        assertEquals(NORMAL_DATA, Archive.getStreamToFile());

        Archive.setFile(Collections.singletonList(ANSWER_WRONG_DATA));
        Archive.start(null, null, false);
        assertEquals(WRONG_DATA, Archive.getStreamToFile());
    }

    @Test
    void multiplePackingAndUnpacking() {
        String multiplePacking;
        Archive.setFile(Collections.singletonList(NORMAL_DATA));
        for (int i = 0; i < 10; i++) {
            Archive.start(null, null, true);
            multiplePacking = Archive.getStreamToFile();
            Archive.setFile(Collections.singletonList(multiplePacking));
        }
        multiplePacking = Archive.getStreamToFile();
        String multipleUnpacking = multiplePacking;
        Archive.setFile(Collections.singletonList(multipleUnpacking));
        for (int i = 0; i < 10; i++) {
            Archive.start(null, null, false);
            multipleUnpacking = Archive.getStreamToFile();
            Archive.setFile(Collections.singletonList(multipleUnpacking));
        }
        multipleUnpacking = Archive.getStreamToFile();
        assertEquals(NORMAL_DATA, multipleUnpacking);
    }

    @Test
    void binTest() throws IOException {
        LINES_BIN = Files.readAllLines(Paths.get(PATH_TO_BIN), StandardCharsets.UTF_8);
        BIN_ANSWER = LINES_BIN.get(0);
        Archive.setFile(LINES_BIN);
        Archive.start(null, null, true);
        ARCHIVE_ANSWER = Archive.getStreamToFile();

        assertTrue(ARCHIVE_ANSWER.length() < BIN_ANSWER.length());

        Archive.setFile(Collections.singletonList(ARCHIVE_ANSWER));
        Archive.start(null, null, false);
        ARCHIVE_ANSWER = Archive.getStreamToFile();

        assertEquals(ARCHIVE_ANSWER, LINES_BIN.get(0));

    }

}
