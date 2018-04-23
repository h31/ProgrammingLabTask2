import java.io.FileNotFoundException;

public class Parser {

    private String inputFileName="";
    private String outputFileName="";

    private boolean readFromFile=false;
    private boolean outputToFile=false;
    private boolean count=false;
    private boolean caseInsensetive=false;
    private boolean unique=false;
    private int ignoreChar=0;

    public void analyzeArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-i":
                    caseInsensetive = true;
                    break;
                case "-u":
                    unique = true;
                    break;
                case "-c":
                    count = true;
                    break;
                case "-s":
                    ignoreChar = Integer.parseInt(args[++i]);
                    break;
                case "-o":
                    outputToFile = true;
                    outputFileName = args[++i];
                    break;
                default:
                    inputFileName = args[i];
                    readFromFile = inputFileName.length() > 0;
                    break;
            }
        }
    }


    String getInputFileName() throws FileNotFoundException {
        if (inputFileName.matches("^*.txt$")) throw new FileNotFoundException("Incorrect file format. Enter *.txt file.");
        return inputFileName;
    }

    String getOutputFileName() throws FileNotFoundException {
        if (outputFileName.matches("^*.txt$"))
            throw new FileNotFoundException("Incorrect file format. Enter *.txt file.");
        return outputFileName;
    }

    int getIgnoreChar() {
        return ignoreChar;
    }

    boolean isOutputToFile() {
        return outputToFile;
    }

    boolean isReadFile() {
        return readFromFile;
    }

    boolean isCount() {
        return count;
    }

    boolean isCaseInsensetive() {
        return caseInsensetive;
    }

    boolean isUnique() {
        return unique;
    }


}
