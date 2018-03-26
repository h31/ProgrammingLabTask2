package task2;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Transposition {
    private static Logger log = Logger.getLogger(Transposition.class.getName());

    private final int width;
    private final boolean cut;
    private final boolean alignRight;

    Transposition(int width, boolean cut, boolean alignRight) {
        try {
            this.width = width;
            this.cut = cut;
            this.alignRight = alignRight;
        } catch (IllegalArgumentException ex) {
            log.log(Level.SEVERE, "Request is invalid.", ex);
            throw ex;
        }
        log.fine("Parameters are assigned");
    }

    public List<List<String>> getMatrix(Reader in) throws IOException {
        BufferedReader reader = new BufferedReader(in);
            List<List<String>> allLines = new ArrayList<>();
            String alignmentRight = ((this.alignRight) || (width == 0)) ? "" : "-";
            String currentLine = "";
            do {
                currentLine = reader.readLine();
                List<String> currentLineElements = new ArrayList<>();
                Collections.addAll(currentLineElements, currentLine.split("[ ]+"));
                String widthString = width != 0 ? Integer.toString(width) : "";
                int count = 0;
                for (String element : currentLineElements) {
                    element = String.format("%" + alignmentRight + widthString + "s", element);
                    if (cut) {
                        element = element.substring(0, width);
                    }
                    if (allLines.size() <= count) {
                        allLines.add(new ArrayList<>());
                    }
                    allLines.get(count).add(element);
                    count++;
                }
                currentLine = reader.readLine();
            } while (currentLine != null);
            log.fine("File has been read");
            return allLines;

    }

    public void transmitMatrix(List<List<String>> linesGotten, Writer out) throws IOException {
        for (int i = 0; i < linesGotten.size() - 1; i++) {
            StringBuilder newLine = new StringBuilder();
            for (String element : linesGotten.get(i)) {
                newLine.append(element);
                if (element != linesGotten.get(i).get(linesGotten.get(i).size() - 1)) {
                    newLine.append(" ");
                }
            }
            out.write(newLine.toString());
        }
        StringBuilder newLine = new StringBuilder();
        for (String element : linesGotten.get(linesGotten.size() - 1)) {
            newLine.append(element);
        }
        out.write(newLine.toString());
        out.close();
    }

}


