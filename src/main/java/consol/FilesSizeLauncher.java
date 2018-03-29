package consol;

import logic.FilesSize;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

public class FilesSizeLauncher {

    @Option(name="-c",usage="total size of files")
    private boolean sum;

    @Option(name="-h",usage="the file size is given in human readable format")
    private boolean human;

    @Option(name="--si",usage="base is 1000")
    private boolean oneThousand;

    @Argument(usage = "files for which need to find the size")
    private String[] files;

    public static void main(String[] args) {
        new FilesSizeLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("Неверно введены данные. Пример правильного: -c -h --si file1 file2");
            parser.printUsage(System.err);
            return;
        }

        FilesSize file = new FilesSize(sum, human, oneThousand);
        try {
            String result = file.filesSize(files);
            System.out.println(result);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
