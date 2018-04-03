package consol;

import logic.FilesSize;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FilesSizeLauncherTest {
    @Test
    void main() {
        String[] args1 = {"-c","-h","--si","src/test/java/filesForTest","src/test/java/filesForTest/TestFile1"};
        new FilesSizeLauncher().main(args1);
        String[] files = {"src/test/java/filesForTest","src/test/java/filesForTest/TestFile1"};
        assertEquals("12.442 kb\n" + "8.102 kb\n" + "20.544 kb",
                new FilesSize(true,true,true).filesTotalSize(files));

        String[] args2 = {"src/test/java/filesForTest","src/test/java/filesForTest/TestFile1"};
        new FilesSizeLauncher().main(args2);
        assertEquals("12.150390625\n" + "7.912109375\n", new FilesSize().filesTotalSize(files));

        String[] args3 = {"-h","src/test/java/filesForTest","src/test/java/filesForTest/TestFile1"};
        new FilesSizeLauncher().main(args3);
        assertEquals("12.150390625 kb\n" + "7.912109375 kb\n",
                new FilesSize(false,true,false).filesTotalSize(files));

        String[] argsForError = {"wdwdwd"};
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new FilesSize(false,true,true).filesTotalSize(argsForError);
        });
    }
}