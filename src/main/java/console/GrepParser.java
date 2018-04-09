package console;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class GrepParser {

    GrepParser(String[] args){
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
        }
    }

    @Argument(metaVar = "grep")
    private String grep;

    @Option(name = "-v")
    private boolean findExceptRegex;

    @Option(name = "-i")
    private boolean ignoreCase;

    @Option(name = "-r")
    private boolean findOnRegex;

    @Argument(metaVar = "word", index = 1)
    private String word;

    @Argument(metaVar = "inputFileName", index = 2)
    private String inputFileName;

    public boolean isFindExceptRegex() {
        return findExceptRegex;
    }

    public boolean isFindOnRegex() {
        return findOnRegex;
    }

    public String getWord() {
        return word;
    }

    public String getInputFileName() {
        return inputFileName;
    }

    public boolean isIgnoreCase() {
        return ignoreCase;
    }
}






