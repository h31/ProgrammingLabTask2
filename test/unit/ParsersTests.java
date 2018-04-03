package unit;

import main.java.project.UI.Parsers.NativeParser;
import main.java.project.UI.Parsers.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParsersTests {

    @Test
    void parseCommand() {

        String command = "pack-rle -u -out outName inName.uz";
        Parser parser = new NativeParser(command);
        assertEquals("inName.uz", parser.getInputFileName());
        assertFalse(parser.isPacking());
        assertEquals("outName.txt", parser.getOutputFileName());

        String command1 = "pack-rle -z -out outName inName.txt";
        Parser parser1 = new NativeParser(command1);
        assertTrue(parser1.isPacking());

        String command2 = "pack-rle -out outName inName.txt";
        Parser parser2 = new NativeParser(command2);
        assertTrue(parser2.isPacking());
    }

}
