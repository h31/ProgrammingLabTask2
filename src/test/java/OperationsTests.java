import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static logic.Operations.Collector.collectFiles;
import static logic.Operations.Separator.separateFile;
import static org.junit.Assert.assertEquals;

public class OperationsTests {

    private final String projectResourcesPath = "src/test/java/resources";

    // For collector
    private Path expectedCollectledFile = Paths.get(projectResourcesPath + "/Collect/Expected/correctlyCollectedFile.txt");
    private String actualCollectedFilePathPattern = projectResourcesPath + "/Collect/fileToCollect";
    private List<Path> filesToCollect = Arrays.asList(Paths.get(actualCollectedFilePathPattern + "1.txt"), Paths.get(actualCollectedFilePathPattern + "2.txt"), Paths.get(actualCollectedFilePathPattern + "3.txt"),
            Paths.get(actualCollectedFilePathPattern + "4.txt"), Paths.get(actualCollectedFilePathPattern + "5.txt"));

    // For separator
    private String expectedFilesPathPattern = projectResourcesPath + "/Separate/Expected/splitFile";
    private List<Path> expectedSeparatedFiles = Arrays.asList(Paths.get(expectedFilesPathPattern + "1.txt"), Paths.get(expectedFilesPathPattern + "2.txt"), Paths.get(expectedFilesPathPattern + "3.txt"),
            Paths.get(expectedFilesPathPattern + "4.txt"), Paths.get(expectedFilesPathPattern + "5.txt"));
    private Path fileToSeparate = Paths.get(projectResourcesPath + "/Separate/fileToSeparate.txt");
    private String actualFilesPathPattern = projectResourcesPath + "/Separate/Actual/separatedFile";
    private List<Path> actualSeparatedFiles = Arrays.asList(Paths.get(actualFilesPathPattern + "1.txt"), Paths.get(actualFilesPathPattern + "2.txt"), Paths.get(actualFilesPathPattern + "3.txt"),
            Paths.get(actualFilesPathPattern + "4.txt"), Paths.get(actualFilesPathPattern + "5.txt"));


    @Test
    public void collectFilesTest() throws IOException {
        collectFiles(filesToCollect, Paths.get("src/test/java/resources/Collect/Actual/testCollectedFile.txt"));
        List<String> expectedLines = Files.readAllLines(expectedCollectledFile);
        List<String> actualLines = Files.readAllLines(Paths.get("src/test/java/resources/Collect/Actual/testCollectedFile.txt"));
        assertEquals(expectedLines, actualLines);
    }

    @Test
    public void separateFileTest() throws IOException {
        separateFile(fileToSeparate, Paths.get(projectResourcesPath + "/Separate/Actual/"));
        List<String> expectedLinesForCompare = new ArrayList<>();
        for(Path path : expectedSeparatedFiles){
            expectedLinesForCompare.addAll(Files.readAllLines(path));
        }

        List<String> actualLinesForCompare = new ArrayList<>();
        for (Path path : actualSeparatedFiles){
            actualLinesForCompare.addAll(Files.readAllLines(path));
        }
        System.out.println(actualLinesForCompare);
        assertEquals(expectedLinesForCompare, actualLinesForCompare);

    }
}
