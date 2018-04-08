package console;

import logic.Grep;
import logic.GrepOption;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.nio.file.Paths;
import java.util.List;

public class GrepLauncher {

    @Option(name = "-r", usage = "Instead of word it find regular expression")
    private boolean isRegex;

    @Option(name = "-v", usage = "Inverts filtering condition")
    private boolean isInvert;

    @Option(name = "-i", usage = "Ignore case")
    private boolean isIgnoreCase;

    @Argument(required = true, usage = "Input file name", metaVar = "InputName", index = 0)
    private String inputFileName;

    @Argument(required = true, usage = "Specifies a word to search for", metaVar = "word", index = 1)
    private String word;

    public static void main(String[] args) {
        new GrepLauncher().launch(args);
    }

    public List<String> output;

    public void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
            return;
        }
        GrepOption option = new GrepOption(word, isRegex, isInvert, isIgnoreCase);
        Grep grep = new Grep(option);
        output = grep.find(Paths.get(inputFileName));
        for (String s : output) System.out.println(s);
    }
}