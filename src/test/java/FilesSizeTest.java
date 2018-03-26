import org.junit.Test;

import java.util.logging.LogManager;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class FilesSizeTest {

    @Test
    public void humanFilesSize() throws Exception {
        String arguments = "/Games/cyclone-17.1.0.590.qdz\n" +
                "/Games\n";
        String[] args = arguments.split(" |\n");

        FilesSize file1 = new FilesSize(true,true,true);
        file1.humanFilesSize(args);

        FilesSize file2 = new FilesSize(false,false,false);
        file2.humanFilesSize(args);
    }

}