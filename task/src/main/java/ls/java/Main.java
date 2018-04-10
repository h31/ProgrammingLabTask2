package ls.java;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        LsLogic test = new LsLogic();
        File test1 = new File("C:\\Users\\hp\\IdeaProjects\\ProgrammingLabTask2\\task\\src\\test\\java\\resources\\testFolder");

        List<String> testList = new ArrayList<String>();
        testList.add("rwx NewText Wed Apr 04 19:20:44 MSK 2018 42 byte");
        testList.add("rwx SampleFile Wed Apr 04 19:21:20 MSK 2018 2,18 Kb");
        testList.add("rwx Text Wed Apr 04 19:20:44 MSK 2018 26 byte");

        System.out.println(test.flagLong(test1.getPath()));
        System.out.println(test.flagReverse(testList));
        System.out.println(test.flagHumanReadable("C:\\Users\\hp\\IdeaProjects\\ProgrammingLabTask2\\task\\src\\test\\java\\resources\\testFolder"));
        System.out.println(test.ls("C:\\Users\\hp\\IdeaProjects\\ProgrammingLabTask2\\task\\src\\test\\java\\resources\\testFolder"));
    }
}
