package unit;

import com.project.logic.archiving.Archive;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ArchiveTest {

    private String normalData =
            "Захоодит как-то PHP пррррограмммммист в бар и " +
                    "просит баррррмена рррррррррррррррразделить его код (кооооод?) на его талант, " +
            "а бармен ему и говорит NullPPPointerException";
    private String wrongData =
            "Я тестировщик, да5|. " +
            "Z ntcnbhjdobr? lf/ Ya10| testirovtcshik, da." +
                    " R TecTирОвщик, Da. Съешь ещё этих мягких " +
                    "французских булочек, да выпей чаю.8|. восемь. " +
                    "три = шесть. Нет, я тос|теровщик. Т0|сттЕРРР. " +
                    "format:c . ©ћќ‘&{Ґ“”•–—˜™љ›њќћџ ЎўЈ¦|§Ё©Є«¬®Ї°±эіґµ¶·ё№є»јЅѕїАБВ rm -rf /*";
    private String answerForNormalData =
            "Захо2|дит как-то PHP пр4|ограм5|ист в бар и" +
            " просит бар4|мена р16|азделить его код (ко5|д?) на его талант," +
                    " а бармен ему и говорит Nul2|P3|ointerException";
    private String answerForWrongData =
            "Я тестировщик, да5&|. Z ntcnbhjdobr?" +
            " lf/ Ya10&| testirovtcshik, da. R TecTирОвщик, Da. " +
            "Съешь ещё этих мягких французских булочек, да выпей чаю.8&|. " +
                    "восемь. три = шесть. Нет, я тос|теровщик. Т0&|ст2|ЕР3|. " +
                    "format:c . ©ћќ‘&{Ґ“”•–—˜™љ›њќћџ ЎўЈ¦|§Ё" +
                    "©Є«¬®Ї°±эіґµ¶·ё№є»јЅѕїАБВ rm -rf /*";

    @Test
    void packing() {
        Archive.setFile(Collections.singletonList(normalData));
        Archive.start(null, null, true);
        assertEquals(answerForNormalData, Archive.getStreamToFile());

        Archive.setFile(Collections.singletonList(wrongData));
        Archive.start(null, null, true);
        assertEquals(answerForWrongData, Archive.getStreamToFile());
    }

    @Test
    void unpacking() {
        Archive.setFile(Collections.singletonList(answerForNormalData));
        Archive.start(null, null, false);
        assertEquals(normalData, Archive.getStreamToFile());

        Archive.setFile(Collections.singletonList(answerForWrongData));
        Archive.start(null, null, false);
        assertEquals(wrongData, Archive.getStreamToFile());
    }

    @Test
    void multiplePackingAndUnpacking() {
        String multiplePacking;
        Archive.setFile(Collections.singletonList(normalData));
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
        assertEquals(normalData, multipleUnpacking);
    }

}
