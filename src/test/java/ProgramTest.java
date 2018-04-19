import org.codehaus.plexus.util.FileUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

public class ProgramTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void crypt() throws IOException {
        File fl = folder.newFile("input");
        FileUtils.fileWrite(fl, "MamaWashRamaTest");
        Program program = new Program("monkey", fl.getAbsolutePath());
        Assert.assertEquals(" \u000E\u0003\n2\u0018\u001E\u0007<\n\b\u00189\n\u001D\u001F", program.work());
    }

    @Test
    public void decrypt() throws IOException {
        File fl = folder.newFile("input");
        File flo = folder.newFile("output");
        FileUtils.fileWrite(fl, " \u000E\u0003\n2\u0018\u001E\u0007<\n\b\u00189\n\u001D\u001F");
        Program program = new Program("monkey", fl.getAbsolutePath());
        Assert.assertEquals("MamaWashRamaTest", program.work());
    }
}