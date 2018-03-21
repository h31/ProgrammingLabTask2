import logic.Files.Creator;
import logic.Files.Reader;
import logic.Operation.Collector;
import logic.Operation.Separator;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args){
        File file = new File("/Users/Ferrero/IdeaProjects/ProgrammingLabTask2/task2/src/main/resources/test.txt");
        Collector collector = new Collector();
        File testFile1 = new File("/Users/Ferrero/IdeaProjects/ProgrammingLabTask2/task2/src/main/resources/test1.txt");
        File testFile2 = new File("/Users/Ferrero/IdeaProjects/ProgrammingLabTask2/task2/src/main/resources/test2.txt");
        File testFile3 = new File("/Users/Ferrero/IdeaProjects/ProgrammingLabTask2/task2/src/main/resources/test3.txt");
        List testFiles = Arrays.asList(testFile1, testFile2, testFile3);
        collector.collectFile(testFiles, "testFile.txt");



    }
}
