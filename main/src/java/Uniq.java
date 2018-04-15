import java.io.*;

public class Uniq {

    private boolean uniqueLines;
    private boolean countLines;
    private int ignoreSymbols;
    private boolean register;

    public Uniq(boolean uniqueLines, boolean countLines, int ignoreSymbols, boolean register){
        this.uniqueLines = uniqueLines;
        this.countLines = countLines;
        this.ignoreSymbols = ignoreSymbols;
        this.register = register;
    }

    public void writeUniq(String inputFile, String outputFile) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputFile)) {
            try (FileOutputStream outputStream = new FileOutputStream(outputFile, true)) {
                writeUniq(inputStream, outputStream);
            }
        }
    }

    public void writeUniq(InputStream inputStream, OutputStream outputStream) throws IOException {
        String pastLine = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream))) {
              int count = 0;
              String currentLine;
              while ((currentLine = br.readLine()) != null) {
                  if (areEqual(currentLine, pastLine)) {
                      count++;
                  }
                  else {
                      add(pastLine, count, bw);
                      pastLine = currentLine;
                      count = 1;
                  }
              }
              add(pastLine, count, bw);
            }
        }
    }

    private boolean areEqual(String currentLine, String pastLine){
        boolean equal = false;
        if (currentLine.equals(pastLine))
            equal = true;
        if (register){
            if (currentLine.equalsIgnoreCase(pastLine))
                equal = true;
        }
        /*if (ignoreSymbols > 0){
            if (currentLine.length() == pastLine.length())
                if (currentLine.regionMatches(ignoreSymbols, pastLine, ignoreSymbols, currentLine.length() - ignoreSymbols))
                    equal = true;
        }*/
        return equal;
    }

    private void add(String str, int count, BufferedWriter bw) throws IOException {
        if (str != null) {
            if ((uniqueLines) && count > 0)
                return;
            if (register)
                str = str.toLowerCase();
            if (countLines)
                bw.write("" + count + "\t");
            if (ignoreSymbols > 0 && count > 1 && str.length() > ignoreSymbols) {
                int endIndex = Math.min(str.length(), ignoreSymbols);
                str = str.substring(0, endIndex).replaceAll(".", "*") + str.substring(endIndex);
                bw.write(str);
                bw.newLine();
                bw.flush();
            }
        }
    }
}
