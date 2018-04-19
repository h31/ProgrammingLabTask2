import ls.java.LsLogic;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogicTest {

    private File test = new File("C:\\Users\\hp\\IdeaProjects\\ProgrammingLabTask2\\task\\src\\test\\java\\resources\\testFolder");
    private LsLogic lsLogic = new LsLogic();

    private static List<String> testList(){
        List<String> testList = new ArrayList<String>();
        testList.add("NewText");
        testList.add("output");
        testList.add("SampleFile");
        testList.add("Text");
        return testList;
    }

    private static List<String> testList0(){
        List<String> testList = new ArrayList<String>();
        testList.add("Text");
        testList.add("SampleFile");
        testList.add("output");
        testList.add("NewText");
        return testList;
    }

    private static List<String> testList1() {
        List<String> testList = new ArrayList<String>();
        testList.add("true true true NewText 1523399712039 47");
        testList.add("true true true output 1523397900652 150");
        testList.add("true true true SampleFile 1522858880210 2236");
        testList.add("true true true Text 1523396761372 150");
        return testList;
    }

    private static List<String> testList2() {
        List<String> testList = new ArrayList<String>();
        testList.add("true true true Text 1523396761372 150");
        testList.add("true true true SampleFile 1522858880210 2236");
        testList.add("true true true output 1523397900652 150");
        testList.add("true true true NewText 1523399712039 47");
        return testList;
    }

    private static List<String> testList3() {
        List<String> testList = new ArrayList<String>();
        testList.add("rwx NewText Wed Apr 11 01:35:12 MSK 2018 47 byte");
        testList.add("rwx output Wed Apr 11 01:05:00 MSK 2018 150 byte");
        testList.add("rwx SampleFile Wed Apr 04 19:21:20 MSK 2018 2,18 Kb");
        testList.add("rwx Text Wed Apr 11 00:46:01 MSK 2018 150 byte");
        return testList;
    }

    private static List<String> testList4() {
        List<String> testList = new ArrayList<String>();
        testList.add("rwx Text Wed Apr 11 00:46:01 MSK 2018 150 byte");
        testList.add("rwx SampleFile Wed Apr 04 19:21:20 MSK 2018 2,18 Kb");
        testList.add("rwx output Wed Apr 11 01:05:00 MSK 2018 150 byte");
        testList.add("rwx NewText Wed Apr 11 01:35:12 MSK 2018 47 byte");
        return testList;
    }

    @Test
    public void ls() throws IOException{
        assertEquals(lsLogic.ls(test.getPath()), testList());
    }

    @Test
    public void fladLong() throws IOException {
        assertEquals(lsLogic.flagLong(test.getPath()), testList1());
    }

    @Test
    public void flagReverse() throws IOException {
        assertEquals(lsLogic.flagReverse(testList1()), testList2());
        assertEquals(lsLogic.flagReverse(testList3()), testList4());
        assertEquals(lsLogic.flagReverse(testList()), testList0());
    }

    @Test
    public void flagHumanReadable() throws IOException {
        assertEquals(lsLogic.flagHumanReadable(test.getPath()), testList3());
    }
}
