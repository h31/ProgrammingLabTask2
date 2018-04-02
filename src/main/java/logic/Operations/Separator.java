package logic.Operations;

import logic.Files.Creator;
import logic.Files.Reader;
import logic.Files.Writer;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class Separator {
    String fileNamePattern = "";
    String path = "";


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
        String[] lines = reader.listOfLines;
        ArrayList<String> linesToFile = new ArrayList<>();
        int length = reader.listOfLines.length;
        if (length > 4) {
            int quarter = length / 4;
            for (int fileCounter = 1; fileCounter <= 5; fileCounter++) {
                linesToFile.add("<file" + fileCounter + ">");
                for (int i = (fileCounter - 1) * quarter; i < fileCounter * quarter; i++) {
                    if (i >= length) break;
                    linesToFile.add(lines[i]);
                }
                String newFileName = path + fileCounter + "." + extension;
                creator.createNewFile(path, newFileName);
                Writer writer = new Writer(path, newFileName);
                writer.writeLines(linesToFile, true, true);
            }
        } else {
            throw new IllegalArgumentException("Размер файла слишком мал для применения tar");
        }
    }

    public void separateFile(File file, String newFilesPath, String newFilesNamesPattern) {
        Creator creator = new Creator();
        Reader reader = new Reader();
        Map<String, String> pathFragments = reader.getPathFragments(file);
        String path = pathFragments.get("path");
        String name = pathFragments.get("name");
        String extension = pathFragments.get("extension");
        String nameWithExtension = name + "." + extension;
        reader = new Reader(path, nameWithExtension);
        reader.readLines();
        String[] lines = reader.listOfLines;
        int length = reader.listOfLines.length;
        if (length > 4) {
            int quarter = length / 4;
            for (int fileCounter = 1; fileCounter <= 5; fileCounter++) {
                ArrayList<String> linesToFile = new ArrayList<>();
                for (int i = (fileCounter - 1) * quarter; i < fileCounter * quarter; i++) {
                    if (i >= length) break;
                    linesToFile.add(lines[i]);
                }
                String newFileName = newFilesNamesPattern + fileCounter + "." + extension;
                creator.createNewFile(newFilesPath, newFileName);
                Writer writer = new Writer(newFilesPath, newFileName);
                writer.writeLines(linesToFile, true, true);
            }

        } else {
            throw new IllegalArgumentException("Размер файла слишком мал для применения tar");
        }
    }

    public void setFileNamePattern(String name){
        fileNamePattern = name;
    }
    public void setPath(String newPath){
        path = newPath;
    }
}
