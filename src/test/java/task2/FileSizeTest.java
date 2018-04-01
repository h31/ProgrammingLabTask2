package task2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FileSizeTest {

    @Test
    public void test() {
        String[] args1 = {"src/test/java/task2/files/blaBla.txt"};
        List list1 = new ArrayList();
        list1.add("Size of src/test/java/task2/files/blaBla.txt equals 1");
        FileSizeLauncher.main(args1);
        assertEquals(FileSizeLauncher.list, list1);

        String[] args2 = {"-c", "src/test/java/task2/files/papka", "src/test/java/task2/files/nya.txt",
                "src/test/java/task2/files/blaBla.txt"};
        List list2 = new ArrayList();
        list2.add("Full size of files equals 6");
        FileSizeLauncher.main(args2);
        assertEquals(FileSizeLauncher.list, list2);

        String[] args3 = {"-c", "-h", "src/test/java/task2/files/papka", "src/test/java/task2/files/god.jpg",
                "src/test/java/task2/files/nya.txt"};
        List list3 = new ArrayList();
        list3.add("Full size of files equals 499 KB");
        FileSizeLauncher.main(args3);
        assertEquals(FileSizeLauncher.list, list3);

        String[] args4 = {"-h", "--si", "src/test/java/task2/files/god.jpg", "src/test/java/task2/files/blaBla.txt"};
        List list4 = new ArrayList();
        list4.add("Size of src/test/java/task2/files/god.jpg equals 56 MB");
        list4.add("Size of src/test/java/task2/files/blaBla.txt equals ~~");
        FileSizeLauncher.main(args4);
        assertEquals(FileSizeLauncher.list, list4);
    }
}