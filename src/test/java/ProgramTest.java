import org.codehaus.plexus.util.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ProgramTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void work() throws IOException {
        File fl = folder.newFile("input");
        FileUtils.fileWrite(fl, "МамаМылаРамуTest");
        System.out.println("tst");
        Program program = new Program("Обезьяна", new BufferedReader(new FileReader(fl.getAbsolutePath()), 8));
        assertEquals("ZYL9re1WD8TsZntikxaMI8XmXk7hBKE0waZrvg==", program.work());
    }
}