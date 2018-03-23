package task2;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.kohsuke.args4j.OptionHandlerFilter.ALL;

public class TranspositionLauncher {
    private static Logger log = Logger.getLogger(TranspositionLauncher.class.getName());

    @Option(name = "-file", usage = "Input from this file", metaVar = "inputFile")
    private File inputData;

    @Option(name = "-o", usage = "Output to this file", metaVar = "outputFile")
    private File outputData;

    @Option(name = "-a", usage = "Each word is num symbols long in the output text", metaVar = "width")
    private int width;

    @Option(name = "-t", usage = "Cut the word to the width required", metaVar = "cut")
    private boolean cut;

    @Option(name = "-r", usage = "Align the text to the right", metaVar = "alignRight")
    private boolean alignRight;

    public static void main(String[] args) throws CmdLineException{
            new TranspositionLauncher().cmdLaunch(args);
    }

    public void cmdLaunch(String[] args) throws CmdLineException {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
            if (width == 0 && (cut || alignRight))
                width = 15;
        } catch (CmdLineException ex) {
            System.err.println(ex.getMessage());
            System.err.println("java -jar part2.jar -o ofile -file -a width -t cut -r alignRight");
            parser.printUsage(System.err);
            System.err.println();
            System.err.println("Example: java TranspositionLauncher" + parser.printExample(ALL));
            log.log(Level.SEVERE, "Request is invalid", ex);
            return;
        }
        Transposition transposition = new Transposition(width, cut,  alignRight);
        try {
            Reader reader = (inputData == null) ? new InputStreamReader(System.in) : new FileReader(inputData);
            Writer writer = (outputData == null) ? new OutputStreamWriter(System.out) : new FileWriter(outputData);
            transposition.transmitMatrix(transposition.getMatrix(reader), writer);
            log.fine("Done");
        } catch (IOException ex) {
            System.err.print(ex.getMessage());
            log.log(Level.SEVERE, "Request is invalid", ex);
        }
    }
}
