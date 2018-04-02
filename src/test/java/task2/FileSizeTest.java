package task2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FileSizeTest {

    @Test
    public void test1() {
        String[] args1 = {"src/test/java/task2/files/blaBla.txt"};
        List<String> list1 = new LinkedList<>();
        list1.add("Size of src/test/java/task2/files/blaBla.txt equals 1");
        FileSizeLauncher.main(args1);
        assertEquals(FileSizeLauncher.list, list1); 
    }

    @Test
    public void test2() {
        String[] args2 = {"-c", "src/test/java/task2/files/papka", "src/test/java/task2/files/nya.txt",
                "src/test/java/task2/files/blaBla.txt"};
        List<String> list2 = new LinkedList<>();
        list2.add("Full size of files equals 6");
        FileSizeLauncher.main(args2);
        assertEquals(FileSizeLauncher.list, list2);
    }

    @Test
    public void test3() {
        String[] args3 = {"-c", "-h", "src/test/java/task2/files/papka", "src/test/java/task2/files/god.jpg",
                "src/test/java/task2/files/nya.txt"};
        List<String> list3 = new LinkedList<>();
        list3.add("Full size of files equals 499 KB");
        FileSizeLauncher.main(args3);
        assertEquals(FileSizeLauncher.list, list3);
    }

     @Test
    public void test4() {
        String[] args4 = {"--si", "-h", "src/test/java/task2/files/blaBla.txt", "src/test/java/task2/files/blaBla.txt"};
        List<String> list4 = new LinkedList<>();
        list4.add("Size of src/test/java/task2/files/blaBla.txt equals 2 KB");
        list4.add("Size of src/test/java/task2/files/blaBla.txt equals 2 KB");
        FileSizeLauncher.main(args4);
        assertEquals(FileSizeLauncher.list, list4);
    }
}