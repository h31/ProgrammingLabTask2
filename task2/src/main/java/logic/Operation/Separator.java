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
        Map<String, String> fragmentsOfThePath = reader.getPathFragments(file);
        String path = fragmentsOfThePath.get("path");
        String name = fragmentsOfThePath.get("name");
        String extension = fragmentsOfThePath.get("extension");
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
                StringBuilder toFile = new StringBuilder();
                for (int i = (fileCounter - 1) * quarter; i < fileCounter * quarter; i++) {
                    toFile.append(lines[i]).append("\n");
                }
                String newFileName = name + fileCounter + "." + extension;
                creator.createNewFile(path, newFileName);
                Writer writer = new Writer(path, newFileName);
                writer.writeStringToFile(toFile.toString(), true);
            }
        }
    }
}
