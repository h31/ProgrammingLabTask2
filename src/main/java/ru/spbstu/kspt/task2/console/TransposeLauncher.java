package ru.spbstu.kspt.task2.console;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import ru.spbstu.kspt.task2.logic.Transposition;

public class TransposeLauncher {

    @Argument(required = true)
    private String inputFile;

    @Option(name = "-o")
    private String outputFile;

    @Option(name = "-a")
    private int num;

    @Option(name = "-t")
    private boolean isCut;

    @Option(name = "-r")
    private boolean isRightSide;

    public static void main(String[] args) {
        new TransposeLauncher().launch(args);
    }

    public void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
            return;
        }
        Transposition t = new Transposition(isRightSide, isCut, num, outputFile);
    }
}