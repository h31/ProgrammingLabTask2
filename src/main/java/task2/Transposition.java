package task2;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Transposition {
    private static Logger log = Logger.getLogger(Transposition.class.getName());

    private int width;
    private boolean cut;
    private boolean alignRight;

    public Transposition(int width, boolean cut, boolean alignRight) {
        try {
            this.width = width;
            this.cut = cut;
            this.alignRight = alignRight;
            log.log(Level.INFO, "Transposition adjustments have been assigned: {0}", this);
        } catch (IllegalArgumentException ex) {
            log.log(Level.SEVERE, "Request is invalid.", ex);
            throw ex;
        }
        log.fine("Parameters are assigned");
    }

    public List<List<String>> getMatrix(InputStream dataReceived) throws IOException {
        try (Scanner scanner = new Scanner(dataReceived)) {
            List<List<String>> allLines = new ArrayList<>();
            String alignmentRight = ((this.alignRight) || (width == 0)) ? "" : "-";
            while (scanner.hasNextLine()) {
                List<String> currentLine = new ArrayList<>();
                Collections.addAll(currentLine, scanner.nextLine().split("[ ]+"));
                String widthString = width != 0 ? Integer.toString(width) : "";
                int count = 0;
                for (String element : currentLine) {
                    element = String.format("%" + alignmentRight + widthString + "s", element);
                    if (cut) {
                        element = element.substring(0, width);
                    }
                    allLines.get(count).add(element);
                    count++;
                }
            }
            log.fine("File has been read");
            return allLines;
        }
    }

    public void transmitMatrix(List<List<String>> linesGotten, OutputStream dataTransmitted) throws IOException {
        PrintWriter output = new PrintWriter(dataTransmitted);
        for (int i = 0; i < linesGotten.size() - 1; i++) {
            StringBuilder newLine = new StringBuilder();
            for (String element : linesGotten.get(i)) {
                newLine.append(element);
                if (element != linesGotten.get(i).get(linesGotten.get(i).size() - 1)) {
                    newLine.append(" ");
                }
            }
            output.print(newLine.toString());
            output.println();
        }
        StringBuilder newLine = new StringBuilder();
        for (String element : linesGotten.get(linesGotten.size() - 1)) {

            newLine.append(element);
        }
        output.print(newLine);
        output.close();
        log.fine("New matrix has been written to the file");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transposition)) return false;

        Transposition that = (Transposition) o;

        if (width != that.width) return false;
        if (cut != that.cut) return false;
        return alignRight == that.alignRight;
    }

    @Override
    public int hashCode() {
        int result = width;
        result = 31 * result + (cut ? 1 : 0);
        result = 31 * result + (alignRight ? 1 : 0);
        return result;
    }
}


