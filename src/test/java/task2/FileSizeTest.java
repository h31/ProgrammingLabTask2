package task2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FileSizeTest {

    @Test
    public void test1() {
        List<String> args1 = Arrays.asList("src/test/java/task2/files/blaBla.txt");
        List<String> list1 = new ArrayList<>();
        list1.add("Size of src/test/java/task2/files/blaBla.txt equals 1");
        FileSizeLauncher.main(args1);
        assertEquals(FileSizeLauncher.list, list1);
    }

    @Test
    public void test2() {
        List<String> args2 = Arrays.asList("-c", "src/test/java/task2/files/papka", "src/test/java/task2/files/nya.txt",
                "src/test/java/task2/files/blaBla.txt");
        List<String> list2 = new ArrayList<>();
        list2.add("Full size of files equals 6");
        FileSizeLauncher.main(args2);
        assertEquals(FileSizeLauncher.list, list2);
    }

    @Test
    public void test3() {
        List<String> args3 = Arrays.asList("-c", "-h", "src/test/java/task2/files/papka", "src/test/java/task2/files/god.jpg",
                "src/test/java/task2/files/nya.txt");
        List<String> list3 = new ArrayList<>();
        list3.add("Full size of files equals 498 KB");
        FileSizeLauncher.main(args3);
        assertEquals(FileSizeLauncher.list, list3);
    }

     @Test
    public void test4() {
        List<String> args4 = Arrays.asList("--si", "-h", "src/test/java/task2/files/blaBla.txt", "src/test/java/task2/files/blaBla.txt");
        List<String> list4 = new ArrayList<>();
        list4.add("Size of src/test/java/task2/files/blaBla.txt equals 2 KB");
        list4.add("Size of src/test/java/task2/files/blaBla.txt equals 2 KB");
        FileSizeLauncher.main(args4);
        assertEquals(FileSizeLauncher.list, list4);
    }
}