import ls.java.LsLogic;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class LogicTest {

    File test = new File("C:\\Users\\hp\\IdeaProjects\\ProgrammingLabTask2\\task\\src\\test\\java\\resources\\testFolder");
    LsLogic tt = new LsLogic();

    private static List<String> testList() {
        List<String> testList = new ArrayList<String>();
        testList.add("true true true NewText 1522858844767 42");
        testList.add("true true true SampleFile 1522858880210 2236");
        testList.add("true true true Text 1522858844873 26");
        return testList;
    }

    private static List<String> testList1() {
        List<String> testList = new ArrayList<String>();
        testList.add("true true true Text 1522858844873 26");
        testList.add("true true true SampleFile 1522858880210 2236");
        testList.add("true true true NewText 1522858844767 42");
        return testList;
    }

    private static List<String> testList2() {
        List<String> testList = new ArrayList<String>();
        testList.add("rwx NewText Wed Apr 04 19:20:44 MSK 2018 42 byte");
        testList.add("rwx SampleFile Wed Apr 04 19:21:20 MSK 2018 2,18 Kb");
        testList.add("rwx Text Wed Apr 04 19:20:44 MSK 2018 26 byte");
        return testList;
    }

    private static List<String> testList3() {
        List<String> testList = new ArrayList<String>();
        testList.add("rwx Text Wed Apr 04 19:20:44 MSK 2018 26 byte");
        testList.add("rwx SampleFile Wed Apr 04 19:21:20 MSK 2018 2,18 Kb");
        testList.add("rwx NewText Wed Apr 04 19:20:44 MSK 2018 42 byte");
        return testList;
    }

    @Test
    public void fladLong() throws IOException {
        assertEquals(tt.flagLong(test.getPath()), testList());
    }

    @Test
    public void flagReverse() throws IOException {
        assertEquals(tt.flagReverse(testList()), testList1());
        assertEquals(tt.flagReverse(testList2()), testList3());
    }

    @Test
    public void flagHumanReadable() throws IOException {
        assertEquals(tt.flagHumanReadable(test.getPath()), testList2());
    }
}
