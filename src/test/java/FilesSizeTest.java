import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class FilesSizeTest {

    @Test
    public void humanFilesSize() throws Exception {
        String arguments = "/Games/cyclone-17.1.0.590.qdz\n" +
                "/Games\n";
        String[] args = arguments.split(" |\n");

        assertEquals("", new FilesSize().filesSize(args));
    }
}
