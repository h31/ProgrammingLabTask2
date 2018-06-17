import org.kohsuke.args4j.*;

import java.io.IOException;

public class UniqLauncher {
    @Option(name = "-i", usage = "Not case-sensitive")
    private boolean caseSensitive = false;

    @Option(name = "-u", usage = "Uniq lines only")
    private boolean uniqLines = false;

    @Option(name = "-c", usage = "Number of equal strings")
    private boolean countLines = false;

    @Option(name = "-o", metaVar = "outputFile", usage = "Output file name")
    private String outputFileName = null;

    @Option(name = "-s", usage = "Ignore first N characters")
    private int ignoreSymbols = 0;

    @Argument(required = true, metaVar = "InputName", usage = "Input file name")
    private String inputFileName = null;

    public static void main(String[] args) {
        new UniqLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
            return;
        }

        Uniq uniq = new Uniq(uniqLines, countLines, ignoreSymbols, caseSensitive);

        try {
            uniq.writeUniq(inputFileName, outputFileName);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
