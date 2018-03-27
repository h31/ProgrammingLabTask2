import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

public class FilesSizeLauncher {

    @Option(name="-c",usage="total size of files")
    private boolean sum = true;

    @Option(name="-h",usage="the file size is given in human readable format")
    private boolean human = true;

    @Option(name="--si",usage="base is 1000")
    private boolean oneThousand = true;

    public static void main(String[] args) {
        new FilesSizeLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("Неверно введены данные.");
            parser.printUsage(System.err);
            return;
        }

        FilesSize file = new FilesSize(sum, human, oneThousand);
        file.filesSize(args);
    }
}
