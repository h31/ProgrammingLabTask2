package tail;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class parserTail {
    private List<String> inputFileName = new ArrayList<>();
    private String outputFileName;
    private boolean workWithSymbols = false;
    private int numberOfSymbols = 0;
    private boolean workWithLines = false;
    private int numberOfLines = 0;
    private boolean outputFile = false;
    private boolean inputFile = false;

    parserTail(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-c":
                    workWithSymbols = true;
                    numberOfSymbols = Integer.parseInt(args[++i]);
                    break;
                case "-n":
                    workWithLines = true;
                    numberOfLines = Integer.parseInt(args[++i]);
                    break;
                case "-o":
                    outputFileName = args[++i];
                    outputFile = true;
                    break;
                case "tail":
                    break;
                default:
                    inputFileName.add(args[i]);
                    inputFile = true;
            }
        }
        if (workWithLines && workWithSymbols) throw new IllegalArgumentException("Incorrect command format");
    }

    public boolean isWorkWithLines() {
        return workWithLines;
    }

    public int getNumberOfLines() {
        return numberOfLines;
    }

    public boolean isWorkWithSymbols() {
        return workWithSymbols;
    }

    public int getNumberOfSymbols() {
        return numberOfSymbols;
    }

    public boolean isInputFile() {
        return inputFile;
    }

    public boolean isOutputFile() {
        return outputFile;
    }

    public List<String> getInputFileName() throws FileNotFoundException {
        for (String anInputFileName : inputFileName)
            if (!anInputFileName.matches("^*.txt$") && !inputFile)
                throw new FileNotFoundException("Incorrect name of input file");
        return inputFileName;
    }

    public String getOutputFileName() throws FileNotFoundException {
        if (!outputFileName.matches("^*.txt$") && !outputFile)
            throw new FileNotFoundException("Incorrect name of output file");
        return outputFileName;
    }
}
