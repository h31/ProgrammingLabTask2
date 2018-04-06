package task2.logic;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Transposition {
    private static Logger log = Logger.getLogger(Transposition.class.getName());

    private final int width;
    private final boolean cut;
    private final boolean isRightAligned;

    public Transposition(int width, boolean cut, boolean isRightAligned) {
        try {
            this.width = width;
            this.cut = cut;
            this.isRightAligned = isRightAligned;
        } catch (IllegalArgumentException ex) {
            log.log(Level.SEVERE, "Request is invalid.", ex);
            throw ex;
        }
        log.fine("Parameters are assigned");
    }

    private String formatting(String element, String alignmentRight, String widthString, Boolean cut) {
        String formattedString;
        formattedString = String.format("%" + alignmentRight + widthString + "s", element);
        if (cut) {
            formattedString = formattedString.substring(0, width);
        }
        log.fine("String has been formatted");
        return formattedString;
    }


    public List<List<String>> getMatrix(Reader in) throws IOException {
        BufferedReader reader = new BufferedReader(in);
        List<List<String>> allLines = new ArrayList<>();
        Boolean isWidthSet = this.width > 0;
        String alignmentRight = ((this.isRightAligned) || !isWidthSet) ? "" : "-";
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            String widthString = isWidthSet ? Integer.toString(width) : "";
            int count = 0;
            for (String element : currentLine.split("[ ]+")) {
                element = formatting(element, alignmentRight, widthString, cut);
                while (allLines.size() <= count) {
                    allLines.add(new ArrayList<>());
                }
                allLines.get(count).add(element);
                count++;
            }
        }
        log.fine("File has been read");
        return allLines;
    }

    private String transpose(List<List<String>> linesFetched, Integer count) {
        List<String> newLine = new LinkedList<>();
        String message = "";
        for (String element : linesFetched.get(count)) {
            newLine.add(element);
            message = String.join(" ", newLine);
        }
        log.fine("Matrix has been transposed");
        return message;
    }

    public void writeMatrix(List<List<String>> linesFetched, Writer out) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(out);
        for (int i = 0; i < linesFetched.size(); i++) {
            bufferedWriter.write(transpose(linesFetched, i));
            log.fine("New line has been written into the file");
            if (i < linesFetched.size()) {
                bufferedWriter.write("\n");
            }
        }
        log.fine("Matrix has been transmitted to the file");
        bufferedWriter.close();
        out.close();
    }
}


