package task2.console;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import task2.logic.Transposition;

import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TranspositionLauncher {
    private static Logger log = Logger.getLogger(TranspositionLauncher.class.getName());

    @Argument(usage = "Input from this file")
    private String inputData;

    @Option(name = "-o", usage = "Output to this file", metaVar = "outputFile")
    private String outputData;

    @Option(name = "-a", usage = "Each word is num symbols long in the output text", metaVar = "width")
    private int width;

    @Option(name = "-t", usage = "Cut the word to the width required", metaVar = "cut")
    private boolean cut;

    @Option(name = "-r", usage = "Align the text to the right", metaVar = "alignRight")
    private boolean isRightAligned;

    public static void main(String[] args) throws CmdLineException{
            new TranspositionLauncher().launch(args);
    }

    public void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
            if (width < 0) {
                throw new CmdLineException(parser, "invalid request");
            }
        } catch (CmdLineException ex) {
            System.err.println(ex.getMessage());
            System.err.println("java -jar part2.jar -o ofile -file -a width -t cut -r alignRight");
            parser.printUsage(System.err);
            log.log(Level.SEVERE, "Arguments are inappropriate", ex);
            return;
        }
        Transposition transposition = new Transposition(width, cut, isRightAligned);
        try {
            Reader reader = (inputData != null) ? new FileReader(inputData) : new InputStreamReader(System.in);
            Writer writer = (outputData != null) ? new FileWriter(outputData) : new OutputStreamWriter(System.out);
            List<List<String>> listFetched = transposition.getMatrix(reader);
            transposition.writeMatrix(listFetched, writer);
            log.fine("Done");
        } catch (IOException ex) {
            System.err.print(ex.getMessage());
            log.log(Level.SEVERE, "Request is invalid", ex);
        }
    }
}
