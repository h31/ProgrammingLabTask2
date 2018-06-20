package tail;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class parserTail {
    private List<String> inputFileName = new ArrayList<>();
    private String outputFileName;
    private boolean processSymbols = false;
    private int numberOfSymbols = 0;
    private boolean processLines = false;
    private int numberOfLines = 0;
    private boolean outputFile = false;
    private boolean inputFile = false;
    List<String> inputText = new ArrayList<>();

    parserTail(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-c":
                    processSymbols = true;
                    try {
                        numberOfSymbols = Integer.parseInt(args[++i]);
                    }
                    catch (IndexOutOfBoundsException e) {
                        System.out.println("Incorrect command format");
                    }
                    break;
                case "-n":
                    processLines = true;
                    try {
                        numberOfLines = Integer.parseInt(args[++i]);
                    }
                    catch (IndexOutOfBoundsException e) {
                        System.out.println("Incorrect command format");
                    }
                    break;
                case "-o":
                    try {
                        outputFileName = args[++i];
                        outputFile = true;
                    }
                    catch (IndexOutOfBoundsException e) {
                        System.out.println("Incorrect command format");
                    }
                    break;
                default:
                    if (args[i].matches("^*.*")) {
                            inputFileName.add(args[i]);
                            inputFile = true;
                    }
                    else inputText.add(args[i]);


            }
        }
        if (processLines && processSymbols) throw new IllegalArgumentException("Incorrect command format");
    }

    public boolean isProcessLines() {
        return processLines;
    }

    public int getNumberOfLines() {
        return numberOfLines;
    }

    public boolean isProcessSymbols() {
        return processSymbols;
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
            if (!inputFile) throw new FileNotFoundException("Input file is not found");
        return inputFileName;
    }

    public String getOutputFileName() throws FileNotFoundException {
        if (!outputFile) throw new FileNotFoundException("Output file is not found");
        return outputFileName;
    }
}
