package unit;

import com.project.UI.Parsers.LibParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibParserTest {

    @Test
    void parseCommand() {
        LibParser libParser = new LibParser("pack-rle -u -out outName inName.uz");
        assertEquals("inName.uz", libParser.getInputFileName());
        assertFalse(libParser.isPacking());
        assertEquals("outName", libParser.getOutputFileName());

        LibParser libParser2 = new LibParser("pack-rle -z -out outName inName.txt");
        assertTrue(libParser2.isPacking());

        LibParser libParser3 = new LibParser("pack-rle -out outName inName.txt");
        assertTrue(libParser3.isPacking());
    }

}
