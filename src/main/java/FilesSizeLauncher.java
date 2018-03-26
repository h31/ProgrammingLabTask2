import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

public class FilesSizeLauncher {

    @Option(name="-c",usage="recursively run something")
    private boolean sum = true;

    @Option(name="-h",usage="recursively run something")
    private boolean human = true;

    @Option(name="--si",usage="recursively run something")
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
        file.humanFilesSize(args);

    }
}
