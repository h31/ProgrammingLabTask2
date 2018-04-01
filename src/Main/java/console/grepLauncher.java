package console;

import logic.Grep;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.util.List;

public class grepLauncher {

    @Option(name = "-r", usage = "Instead of word it find regular expression")
    private boolean regex;

    @Option(name = "-v", usage = "Inverts filtering condition")
    private boolean invert;

    @Option(name = "-i", usage = "Ignore case")
    private boolean ignoreCase;

    @Argument(required = true, usage = "Input file name", metaVar = "InputName", index = 0)
    private String inputFileName;

    @Argument(required = true, usage = "Specifies a word to search for", metaVar = "word", index = 1)
    private String word;

    public static void main(String[] args) {
        new grepLauncher().launch(args);
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

        Grep grep = new Grep(word, regex, invert, ignoreCase);
        output = grep.find(inputFileName);
        for (String s : output) System.out.println(s);
    }
}