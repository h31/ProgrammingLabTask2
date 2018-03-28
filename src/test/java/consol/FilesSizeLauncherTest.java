package consol;

import logic.FilesSize;
import org.junit.Test;

import static org.junit.Assert.*;

public class FilesSizeLauncherTest {
    @Test
    public void filesSizeLauncherTest() throws Exception {
        String[] args1 = {"-c","-h","--si","src/test/java/filesForTest","src/test/java/filesForTest/TestFile1"};
        new FilesSizeLauncher().main(args1);
    }
}