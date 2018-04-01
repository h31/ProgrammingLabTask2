package task2;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.util.ArrayList;
import java.util.List;

class FileSizeLauncher {
    @Option(name = "-h", usage = "readable format for a person")
    private boolean h;

    @Option(name = "-c", usage = "total size")
    private boolean c;

    @Option(name = "--si", usage = "base = 1000")
    private boolean si;

    @Argument(usage = "Input file names")
    private String[] fileN;

    static void main(String[] args) {
        new FileSizeLauncher().launch(args);
    }

    static List list = new ArrayList();

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("Incorrect entered data. Example: -c -h --si file1 file2");
            parser.printUsage(System.err);
            return;
        }
        FileSize du = new FileSize(h, c, si);
        for (Object i : du.print(fileN)) System.out.println(i);
        list = du.print(fileN);
    }
}