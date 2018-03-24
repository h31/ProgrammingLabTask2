package unit;

import com.project.logic.codec.Codec;
import com.project.logic.codec.Compressor;
import com.project.logic.codec.Decompressor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
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
        Codec compressor = new Compressor(Collections.emptyList());

        compressor.setFile(Collections.singletonList(NORMAL_DATA));
        compressor.start();
        assertEquals(ANSWER_NORMAL_DATA, compressor.getOutputStringToFile());

        compressor.setFile(Collections.singletonList(WRONG_DATA));
        compressor.start();
        assertEquals(ANSWER_WRONG_DATA, compressor.getOutputStringToFile());
    }

    @Test
    void unpacking() {
        Codec decompressor = new Decompressor(Collections.emptyList());
        decompressor.setFile(Collections.singletonList(ANSWER_NORMAL_DATA));
        decompressor.start();
        assertEquals(NORMAL_DATA, decompressor.getOutputStringToFile());

        decompressor.setFile(Collections.singletonList(ANSWER_WRONG_DATA));
        decompressor.start();
        assertEquals(WRONG_DATA, decompressor.getOutputStringToFile());
    }

    @Test
    void multiplePackingAndUnpacking() {
        Codec compressor = new Compressor(Collections.emptyList());

        String multiplePacking;
        compressor.setFile(Collections.singletonList(NORMAL_DATA));
        for (int i = 0; i < 10; i++) {
            compressor.start();
            multiplePacking = compressor.getOutputStringToFile();
            compressor.setFile(Collections.singletonList(multiplePacking));
        }
        multiplePacking = compressor.getOutputStringToFile();
        String multipleUnpacking = multiplePacking;
        Codec decompressor = new Decompressor(Collections.emptyList());
        decompressor.setFile(Collections.singletonList(multipleUnpacking));
        for (int i = 0; i < 10; i++) {
            decompressor.start();
            multipleUnpacking = decompressor.getOutputStringToFile();
            decompressor.setFile(Collections.singletonList(multipleUnpacking));
        }
        multipleUnpacking = decompressor.getOutputStringToFile();
        assertEquals(NORMAL_DATA, multipleUnpacking);
    }

    @Test
    void binTest() throws IOException {
        LINES_BIN = Files.readAllLines(Paths.get(PATH_TO_BIN), StandardCharsets.UTF_8);
        BIN_ANSWER = LINES_BIN.get(0);

        Codec compressor = new Compressor(Collections.emptyList());

        compressor.setFile(LINES_BIN);
        compressor.start();
        ARCHIVE_ANSWER = compressor.getOutputStringToFile();

        assertTrue(ARCHIVE_ANSWER.length() < BIN_ANSWER.length());

        Codec decompressor = new Decompressor(Collections.emptyList());
        decompressor.setFile(Collections.singletonList(ARCHIVE_ANSWER));
        decompressor.start();
        ARCHIVE_ANSWER = decompressor.getOutputStringToFile();

        assertEquals(ARCHIVE_ANSWER, LINES_BIN.get(0));

    }

}
