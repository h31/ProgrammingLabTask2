import java.io.*;

class Uniq {
    private final boolean caseSensitive;
    private final boolean uniqLines;
    private final boolean countLines;
    private final int ignoreSymbols;

    Uniq(boolean uniqLines, boolean countLines, int ignoreSymbols, boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
        this.uniqLines = uniqLines;
        this.countLines = countLines;
        this.ignoreSymbols = ignoreSymbols;
    }

    void writeUniq(String inputFile, String outputFile) throws IOException {
        try (
                InputStream inputStream =  new FileInputStream(inputFile);
                OutputStream outputStream = new FileOutputStream(outputFile)
        ) {
                writeUniq(inputStream, outputStream);
        }
    }

    private void writeUniq(InputStream inputStream, OutputStream outputStream) throws IOException {
        String pastLine = null;
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream))
        ) {
            int count = 0;
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                if (areEqual(currentLine, pastLine)) {
                    count++;
                } else {
                    printCountedLine(pastLine, count, bw);
                    pastLine = currentLine;
                    count = 1;
                }
            }
            printCountedLine(pastLine, count, bw);
        }
    }


    private boolean areEqual(String currentLine, String pastLine) {
        if (pastLine == null) return false;
        currentLine = currentLine.substring(ignoreSymbols);
        pastLine = pastLine.substring(ignoreSymbols);
        if (currentLine.equals(pastLine))
            return true;
        if (caseSensitive){
            if (currentLine.equalsIgnoreCase(pastLine))
                return true;
        }
        return false;
    }

    private void printCountedLine(String str, int count, BufferedWriter bw) throws IOException {
        if (str != null) {
            if (uniqLines && count > 1) return;
            if (countLines)
                bw.write("" + count + "\t");
            if (caseSensitive) {
                str = str.toLowerCase();
            }
            bw.write(str);
            bw.newLine();
            bw.flush();
        }
    }
}
