package logic.Operation;

import logic.Files.Creator;
import logic.Files.Reader;
import logic.Files.Writer;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class Separator {

    public void separateFile(File file) {
        Creator creator = new Creator();
        Reader reader = new Reader();
        Map<String, String> pathFragments = reader.getPathFragments(file);
        String path = pathFragments.get("path");
        String name = pathFragments.get("name");
        String extension = pathFragments.get("extension");
        String nameWithExtension = name + "." + extension;
        reader = new Reader(path, nameWithExtension);
        reader.readLines();
        String[] lines = new String[0];
        lines = reader.listOfLines;



        int length = reader.listOfLines.length;
        if (length > 4) {
            int quarter = length / 4;
            int remainder = length % 4;
            for (int fileCounter = 1; fileCounter < 4; fileCounter++) {
                ArrayList<String> linesToFile = new ArrayList<>();
                for (int i = (fileCounter - 1) * quarter; i < fileCounter * quarter; i++) {
                    linesToFile.add(lines[i]);
                }
                String newFileName = name + fileCounter + "." + extension;
                creator.createNewFile(path, newFileName);
                Writer writer = new Writer(path, newFileName);
                writer.writeLines(linesToFile, true);
            }
        }
    }
}
