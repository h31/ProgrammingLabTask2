package task2;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.util.ArrayList;
import java.util.List;

class FileSizeLauncher {
    @Option(name = "-h", usage = "readable format for a person")
    private boolean readable;

    @Option(name = "-c", usage = "total size")
    private boolean totalSize;

    @Option(name = "--si", usage = "base = 1000")
    private boolean baseThousand;

    @Argument(usage = "Input file names")
    private List<String> fileN;

    static void main(List<String> args) {
        new FileSizeLauncher().launch(args);
    }

    static List list = new ArrayList();

    private void launch(List<String> args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("Incorrect entered data. Example: -c -h --si file1 file2");
            parser.printUsage(System.err);
            return;
        }
        FileSize du = new FileSize(readable, totalSize, baseThousand);
        list = du.getSize(fileN);
        for (Object i : list) {
            System.out.println(i);
        }
    }
}
