import logic.Files.Deleter;
import logic.Files.Reader;
import logic.Operations.Collector;
import logic.Operations.Separator;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class OperationsTests {
    private static Logger log = Logger.getLogger(OperationsTests.class.getName());
    Deleter deleter = new Deleter();
    private final String projectResourcesPath = "/Users/Ferrero/IdeaProjects/ProgrammingLabTask2/task2/src/test/java/resources/";

    private String actualCollectedFilePathPattern = projectResourcesPath + "Collect/fileToCollect";
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
    private final String actualSplitFilePathPattern = projectResourcesPath + "Separate/Actual/testSplitFile";
    private final String expectedSplitFilePathPattern = projectResourcesPath + "Separate/Expected/splitFile";
    private File fileToSeparate = new File(projectResourcesPath + "Separate/fileToSeparate.txt");
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
    private List<File> testSplitFiles = new ArrayList<>(Arrays.asList(testSplitFile1, testSplitFile2, testSplitFile3,
            testSplitFile4, testSplitFile5));


    @Test
    public void collectFile() {
        Collector collector = new Collector();
        log.info("Collector инициализирован!");
        collector.collectFile(filesToCollect, "testCollectedFile.txt", projectResourcesPath + "Collect/Actual/");
        log.info("Файлы " + filesToCollect.toString() + " успешно собраны в файл " + projectResourcesPath + "Collect/Actual/testCollectedFile.txt");
        List<String> testCollectedLines = reader.getLines(testCollectedFile);
        deleter.deleteFile(new File(projectResourcesPath + "Collect/Actual/testCollectedFile.txt"));
        log.info("Файл " + projectResourcesPath + "Collect/Actual/testCollectedFile.txt" + " удален");

        assertEquals(correctlyCollectedLines, testCollectedLines);
    }

    @Test
    public void separateFile() {
        Separator separator = new Separator();
        log.info("Separator инициализирован!");
        separator.separateFile(fileToSeparate, projectResourcesPath + "Separate/Actual/", "testSplitFile");
        log.info("Файл " + fileToSeparate.toString() + " успешно поделен на файлы " + testSplitFiles);
        List<String> testFileLines1 = reader.getLines(testSplitFile1);
        List<String> testFileLines2 = reader.getLines(testSplitFile2);
        List<String> testFileLines3 = reader.getLines(testSplitFile3);
        List<String> testFileLines4 = reader.getLines(testSplitFile4);
        List<String> testFileLines5 = reader.getLines(testSplitFile5);
        deleter.deleteFiles(testSplitFiles);
        log.info("Файлы " + testSplitFiles + " удалены");
        List<List<String>> testFilesList = new ArrayList<>(Arrays.asList(testFileLines1, testFileLines2,
                testFileLines3, testFileLines4, testFileLines5));

        assertEquals(actualFilesList, testFilesList);
    }

}
