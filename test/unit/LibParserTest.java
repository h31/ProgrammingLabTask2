package unit;

import com.beust.jcommander.JCommander;
import com.project.UI.Parsers.LibParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LibParserTest {

    @Test
    void parseCommand() {
        LibParser libParser = new LibParser(new String[]{"pack-rle", "-u", "-out", "outName", "inName"});
        assertEquals("pack-rle", libParser.getParameters().get(0));
        assertEquals("inName", libParser.getParameters().get(1));
        assertFalse(libParser.isPacking());
        assertEquals("outName", libParser.getOutputName());

        LibParser libParser2 = new LibParser(new String[]{"pack-rle", "-z", "-out", "outName", "inName"});
        assertTrue(libParser2.isPacking());

        LibParser libParser3 = new LibParser(new String[]{"pack-rle", "-out", "outName", "inName.txt"});
        assertTrue(libParser3.isPacking());
    }

}
