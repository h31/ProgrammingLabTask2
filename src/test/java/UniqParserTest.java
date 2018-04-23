import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class UniqParserTest {
    Parser parser = new Parser();
    String[] args = new String[]{};

    @Test
    public void test() throws FileNotFoundException {
        parser = new Parser();
        args = new String[]{"-i", "-u", "-c", "-s", "2", "-o", "outputFile.txt", "file.txt"};

        parser.analyzeArgs(args);

        assertTrue(parser.isCaseInsensetive());
        assertTrue(parser.isUnique());
        assertTrue(parser.isCount());
        assertEquals(parser.getIgnoreChar(), 2);
        assertEquals(parser.getInputFileName(), "file.txt");
        assertEquals(parser.getOutputFileName(), "outputFile.txt");

    }

    @Test
    public void hasNoFileName() throws Exception {
        parser = new Parser();
        args = new String[]{"-i", "-u", "-c", "-s", "2"};
        parser.analyzeArgs(args);

        assertTrue(parser.isCaseInsensetive());
        assertTrue(parser.isUnique());
        assertTrue(parser.isCount());
        assertEquals(parser.getIgnoreChar(), 2);
        assertEquals(parser.getInputFileName(), "");
        assertEquals(parser.getOutputFileName(), "");

    }

    @Test
    public void onlyHasOutputFileName() throws FileNotFoundException {
        parser = new Parser();
        args = new String[]{"-i", "-u", "-c", "-o", "output.txt"};

        parser.analyzeArgs(args);

        assertTrue(parser.isCaseInsensetive());
        assertTrue(parser.isUnique());
        assertTrue(parser.isCount());
        assertEquals(parser.getIgnoreChar(), 0);
        assertEquals(parser.getInputFileName(), "");
        assertEquals(parser.getOutputFileName(), "output.txt");
    }

    @Test
    public void test4() throws FileNotFoundException {
        parser = new Parser();
        args = new String[]{"-u", "-c", "-o", "output.txt", "file.txt"};

        parser.analyzeArgs(args);

        assertFalse(parser.isCaseInsensetive());
        assertTrue(parser.isUnique());
        assertTrue(parser.isCount());
        assertEquals(parser.getIgnoreChar(), 0);
        assertEquals(parser.getInputFileName(), "file.txt");
        assertEquals(parser.getOutputFileName(), "output.txt");
    }

}
