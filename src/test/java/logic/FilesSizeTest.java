package logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class FilesSizeTest {
    @Test
    void filesTotalSize() {
        String[] args = {"src/test/java/filesForTest/TestFile1","src/test/java/filesForTest/TestFile2",
                "src/test/java/filesForTest/TestFile3"};
        String[] argsForError = {"src/test/java/filesForTesting"};

        assertEquals("7.912109375\n" + "0.005859375\n" + "4.232421875\n",
                new FilesSize().filesTotalSize(args));
        // -h
        assertEquals("7.912109375 kb\n" + "6.0 bytes\n" + "4.232421875 kb\n",
                new FilesSize(false,true,false).filesTotalSize(args));
        // -c
        assertEquals("7.912109375\n" + "0.005859375\n" + "4.232421875\n" + "12.150390625",
                new FilesSize(true,false,false).filesTotalSize(args));
        // --si
        assertEquals("8.102\n" + "0.006\n" + "4.334\n",
                new FilesSize(false,false,true).filesTotalSize(args));
        // -c -h
        assertEquals("7.912109375 kb\n" + "6.0 bytes\n" + "4.232421875 kb\n" + "12.150390625 kb",
                new FilesSize(true,true,false).filesTotalSize(args));
        // -c -h --si
        assertEquals("8.102 kb\n" + "6.0 bytes\n" + "4.334 kb\n" + "12.442 kb",
                new FilesSize(true,true,true).filesTotalSize(args));
        // -c --si
        assertEquals("8.102\n" + "0.006\n" + "4.334\n" + "12.442",
                new FilesSize(true,false,true).filesTotalSize(args));
        // -h --si
        assertEquals("8.102 kb\n" + "6.0 bytes\n" + "4.334 kb\n",
                new FilesSize(false,true,true).filesTotalSize(args));
        // test for error detection
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new FilesSize(false,true,true).filesTotalSize(argsForError);
        });
    }
}