package logic;

import org.junit.Test;

import static org.junit.Assert.*;

public class FilesSizeTest {

    @Test
    public void filesSize() throws Exception {
        String[] args = {"src/test/java/filesForTest/TestFile1","src/test/java/filesForTest/TestFile2",
                "src/test/java/filesForTest/TestFile3"};
        // -h
        assertEquals("7.912109375 kb\n" + "6.0 bytes\n" + "4.232421875 kb\n",
                new FilesSize(false,true,false).filesSize(args));
        // -c
        assertEquals("7.912109375\n" + "0.005859375\n" + "4.232421875\n" + "12.150390625",
                new FilesSize(true,false,false).filesSize(args));
        // --si
        assertEquals("8.102\n" + "0.006\n" + "4.334\n",
                new FilesSize(false,false,true).filesSize(args));
        // -c -h
        assertEquals("7.912109375 kb\n" + "6.0 bytes\n" + "4.232421875 kb\n" + "12.150390625 kb",
                new FilesSize(true,true,false).filesSize(args));
        // -c -h --si
        assertEquals("8.102 kb\n" + "6.0 bytes\n" + "4.334 kb\n" + "12.442 kb",
                new FilesSize(true,true,true).filesSize(args));
        // -c --si
        assertEquals("8.102\n" + "0.006\n" + "4.334\n" + "12.442",
                new FilesSize(true,false,true).filesSize(args));
        // -h --si
        assertEquals("8.102 kb\n" + "6.0 bytes\n" + "4.334 kb\n",
                new FilesSize(false,true,true).filesSize(args));
    }
}
