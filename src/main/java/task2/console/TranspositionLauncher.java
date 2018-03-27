package task2.console;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import task2.logic.Transposition;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            new TranspositionLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException ex) {
            System.err.println(ex.getMessage());
            System.err.println("java -jar part2.jar -o ofile -file -a width -t cut -r alignRight");
            parser.printUsage(System.err);
            log.log(Level.SEVERE, "Request is invalid", ex);
            return;
        }
        Transposition transposition = new Transposition(width, cut, alignRight);
        try {
            Reader reader = (inputData != null) ? new FileReader(inputData) : new InputStreamReader(System.in);
            Writer writer = (outputData != null) ? new FileWriter(outputData) : new OutputStreamWriter(System.out);
            transposition.transmitMatrix(transposition.getMatrix(reader), writer);
            log.fine("Done");
        } catch (IOException ex) {
            System.err.print(ex.getMessage());
            log.log(Level.SEVERE, "Request is invalid", ex);
        }
    }
}
