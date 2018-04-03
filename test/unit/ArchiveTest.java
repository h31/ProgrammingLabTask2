package unit;

import main.java.project.logic.codec.Codec;
import main.java.project.logic.codec.Compressor;
import main.java.project.logic.codec.Decompressor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArchiveTest {

    private List<String> NORMAL_DATA = new ArrayList<>(Collections.singletonList(
            "Захоодит как-то PHP пррррограмммммист в бар и " +
                    "просит баррррмена рррррррррррррррразделить его код (кооооод?) на его талант, " +
                    "а бармен ему и говорит NullPPPointerException"));
    private List<String> WRONG_DATA = new ArrayList<>(Collections.singletonList(
            "Я тестировщик, да5|. " +
                    "Z ntcnbhjdobr? lf/ Ya10| testirovtcshik, da." +
                    " R TecTирОвщик, Da. Съешь ещё этих мягких " +
                    "французских булочек, да выпей чаю.8|. восемь. " +
                    "три = шесть. Нет, я тос|теровщик. Т0|сттЕРРР. " +
                    "format:c . ©ћќ‘&{Ґ“”•–—˜™љ›њќћџ ЎўЈ¦|§Ё©Є«¬®Ї°±эіґµ¶·ё№є»јЅѕїАБВ rm -rf /*"));
    private List<String> ANSWER_NORMAL_DATA = new ArrayList<>(Collections.singletonList(
            "Захо2|дит как-то PHP пр4|ограм5|ист в бар и" +
                    " просит бар4|мена р16|азделить его код (ко5|д?) на его талант," +
                    " а бармен ему и говорит Nul2|P3|ointerException"));
    private List<String> ANSWER_WRONG_DATA = new ArrayList<>(Collections.singletonList(
            "Я тестировщик, да5&|. Z ntcnbhjdobr?" +
                    " lf/ Ya10&| testirovtcshik, da. R TecTирОвщик, Da. " +
                    "Съешь ещё этих мягких французских булочек, да выпей чаю.8&|. " +
                    "восемь. три = шесть. Нет, я тос|теровщик. Т0&|ст2|ЕР3|. " +
                    "format:c . ©ћќ‘&{Ґ“”•–—˜™љ›њќћџ ЎўЈ¦|§Ё" +
                    "©Є«¬®Ї°±эіґµ¶·ё№є»јЅѕїАБВ rm -rf /*"));

    private String PATH_TO_BIN = "test/unit/files/PIC_FOR_TEST.bmp";

    @Test
    void packing() {
        Codec compressor = new Compressor(Collections.emptyList());

        compressor.setFile(NORMAL_DATA);
        compressor.start();
        assertEquals(ANSWER_NORMAL_DATA, compressor.getOutputToFile());

        compressor.setFile(WRONG_DATA);
        compressor.start();
        assertEquals(ANSWER_WRONG_DATA, compressor.getOutputToFile());
    }

    @Test
    void unpacking() {
        Codec decompressor = new Decompressor(Collections.emptyList());
        decompressor.setFile(ANSWER_NORMAL_DATA);
        decompressor.start();
        assertEquals(NORMAL_DATA, decompressor.getOutputToFile());

        decompressor.setFile(ANSWER_WRONG_DATA);
        decompressor.start();
        assertEquals(WRONG_DATA, decompressor.getOutputToFile());
    }

    @Test
    void multiplePackingAndUnpacking() {
        Codec compressor = new Compressor(Collections.emptyList());

        List<String> multiplePacking;
        compressor.setFile(NORMAL_DATA);
        for (int i = 0; i < 10; i++) {
            compressor.start();
            multiplePacking = compressor.getOutputToFile();
            compressor.setFile(multiplePacking);
        }
        multiplePacking = compressor.getOutputToFile();
        List<String> multipleUnpacking = multiplePacking;
        Codec decompressor = new Decompressor(Collections.emptyList());
        decompressor.setFile(multipleUnpacking);
        for (int i = 0; i < 10; i++) {
            decompressor.start();
            multipleUnpacking = decompressor.getOutputToFile();
            decompressor.setFile(multipleUnpacking);
        }
        multipleUnpacking = decompressor.getOutputToFile();
        assertEquals(NORMAL_DATA, multipleUnpacking);
    }

    private List<String> binToText() throws IOException {
        List<String> resultEncode = new ArrayList<>();
        byte[] bytes = Files.readAllBytes(Paths.get(PATH_TO_BIN));
        char[] chars = new char[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            chars[i] = (char) bytes[i];
        }
        resultEncode.add(String.valueOf(chars));
        return resultEncode;
    }

    @Test
    void binTest() throws IOException {
        List<String> originalBinText = binToText();
        Codec codec = new Compressor(originalBinText);
        codec.start();
        List<String> compressResult = codec.getOutputToFile();
        codec = new Decompressor(compressResult);
        codec.start();
        assertEquals(codec.getOutputToFile(), originalBinText);

        List<String> compressorText = codec.getOutputToFile();

    }

}
