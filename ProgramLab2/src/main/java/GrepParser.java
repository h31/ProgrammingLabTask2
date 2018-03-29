import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class GrepParser {

    GrepParser(String command) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(command.split("\\s+"));
        } catch (CmdLineException ignored) {
        }
    }

    @Option(name = "-v", required = true)
    private String findExceptRegex;

    @Option(name = "-i", required = true)
    private String ignoreCase;

    @Option(name = "-r", required = true)
    private String findOnRegex;

    @Argument(metaVar = "inputFileName")
    private String[] inputFileName;

    public String getFindExceptRegex() {
        return findExceptRegex;
    }

    public boolean getIgnoreCase() {
        return true;
    }

    public String getFindOnRegex() {
        return findOnRegex;
    }

    public String getInputFileName() {
        return inputFileName[1];
    }
}