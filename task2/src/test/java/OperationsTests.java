import logic.Files.Reader;
import logic.Operations.Collector;
import logic.Operations.Separator;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OperationsTests {
    private final String projectResourcesPath = "/Users/Ferrero/IdeaProjects/ProgrammingLabTask2/task2/src/test/java/resources/";

    private String actualCollectedFilePathPattern = projectResourcesPath + "Collect/Actual/fileToCollect";
    private File correctlyCollectledFile = new File(projectResourcesPath + "Collect/Expected/correctlyCollectedFile.txt");
    private File fileToCollect1 = new File(actualCollectedFilePathPattern + "1.txt");
    private File fileToCollect2 = new File(actualCollectedFilePathPattern + "2.txt");
    private File fileToCollect3 = new File(actualCollectedFilePathPattern + "3.txt");
    private File fileToCollect4 = new File(actualCollectedFilePathPattern + "4.txt");
    private File fileToCollect5 = new File(actualCollectedFilePathPattern + "5.txt");
    private List<File> filesToCollect = Arrays.asList(fileToCollect1, fileToCollect2, fileToCollect3, fileToCollect4, fileToCollect5);


    private Reader reader = new Reader();
    private List<String> correctlyCollectedLines = reader.getLines(correctlyCollectledFile);
    private File testCollectedFile = new File(projectResourcesPath + "Collect/Actual/testCollectedFile.txt");
    List<String> testCollectedLines = reader.getLines(testCollectedFile);
    private final String actualSplitFilePathPattern = projectResourcesPath + "Separate/Actual/testSplitFile";
    private final String expectedSplitFilePathPattern = projectResourcesPath + "Separate/Expected/splitFile";
    private File fileToSeparate = new File(projectResourcesPath + "Separate/Actual/fileToSeparate.txt");
    private File splitFile1 = new File(expectedSplitFilePathPattern + "1.txt");
    private File splitFile2 = new File(expectedSplitFilePathPattern + "2.txt");
    private File splitFile3 = new File(expectedSplitFilePathPattern + "3.txt");
    private File splitFile4 = new File(expectedSplitFilePathPattern + "4.txt");
    private File splitFile5 = new File(expectedSplitFilePathPattern + "5.txt");

    private List<String> actualFileLines1 = reader.getLines(splitFile1);
    private List<String> actualFileLines2 = reader.getLines(splitFile2);
    private List<String> actualFileLines3 = reader.getLines(splitFile3);
    private List<String> actualFileLines4 = reader.getLines(splitFile4);
    private List<String> actualFileLines5 = reader.getLines(splitFile5);
    private List<List<String>> actualFilesList = new ArrayList<>(Arrays.asList(actualFileLines1, actualFileLines2,
            actualFileLines3, actualFileLines4, actualFileLines5));

    private File testSplitFile1 = new File(actualSplitFilePathPattern + "1.txt");
    private File testSplitFile2 = new File(actualSplitFilePathPattern + "2.txt");
    private File testSplitFile3 = new File(actualSplitFilePathPattern + "3.txt");
    private File testSplitFile4 = new File(actualSplitFilePathPattern + "4.txt");
    private File testSplitFile5 = new File(actualSplitFilePathPattern + "5.txt");


    @Test
    public void collectFile() {
        Collector collector = new Collector();
        collector.collectFile(filesToCollect, "testCollectedFile.txt");
        assertEquals(correctlyCollectedLines, testCollectedLines);
    }

    @Test
    public void separateFile() {
        Separator separator = new Separator();
        separator.separateFile(fileToSeparate, projectResourcesPath + "Separate/Expected", "testSplitFile");
        List<String> testFileLines1 = reader.getLines(testSplitFile1);
        List<String> testFileLines2 = reader.getLines(testSplitFile2);
        List<String> testFileLines3 = reader.getLines(testSplitFile3);
        List<String> testFileLines4 = reader.getLines(testSplitFile4);
        List<String> testFileLines5 = reader.getLines(testSplitFile5);

        List<List<String>> testFilesList = new ArrayList<>(Arrays.asList(testFileLines1, testFileLines2,
                testFileLines3, testFileLines4, testFileLines5));

        assertEquals(actualFilesList, testFilesList);
    }

}
