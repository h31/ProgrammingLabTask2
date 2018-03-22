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
    private File outData;

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
        parser.setUsageWidth(15);
        try {
            parser.parseArgument(args);
            if (width == 0 && (cut || alignRight))
                throw new CmdLineException(parser, "No argument is given");
        } catch (CmdLineException ex) {
            System.err.println(ex.getMessage());
            System.err.println("java -jar part2.jar -a width -t cut -r alignRight -o ofile -file");
            parser.printUsage(System.err);
            System.err.println();
            System.err.println("Example: java TranspositionLauncher" + parser.printExample(ALL));
            log.log(Level.SEVERE, "Request is invalid", ex);
            return;
        }
        Transposition transposition = new Transposition(width, cut,  alignRight);
        try {
            InputStream input = inputData != null ? new FileInputStream(inputData) : new BufferedInputStream(System.in);
            OutputStream output = outData != null ? new FileOutputStream(outData) : new BufferedOutputStream(System.out);
            transposition.transmitMatrix(transposition.getMatrix(input), output);
            log.fine("Done");
        } catch (IOException ex) {
            System.err.print(ex.getMessage());
            log.log(Level.SEVERE, "Request is invalid", ex);
        }
    }
}
