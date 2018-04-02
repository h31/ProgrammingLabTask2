package logic.Operations;

import logic.Files.Creator;
import logic.Files.Reader;
import logic.Files.Writer;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Collector {

    public void collectFile(List<File> filesForCollect, String outputFileName) {
        Reader reader = new Reader();
        Map<String, String> pathFragments = reader.getPathFragments(filesForCollect.get(0));
        Creator creator = new Creator(pathFragments.get("path"), outputFileName);
        creator.createNewFile();
        Writer writer = new Writer(pathFragments.get("path"), outputFileName);
        ArrayList<String> atributes = new ArrayList<>();
        atributes.add("FILE COUNT: " + filesForCollect.size());
        for (int i = 1; i < filesForCollect.size(); i++){
            atributes.add(i + ": " + filesForCollect.get(i).getPath());
        }
        writer.writeLines(atributes, false, false);
        int i = 0;
        for (File file : filesForCollect) {
            i++;
            pathFragments = reader.getPathFragments(file);
            String path = pathFragments.get("path");
            String name = pathFragments.get("name");
            String extension = pathFragments.get("extension");
            String nameWithExtension = name + "." + extension;
            Reader currentFileReader = new Reader(path, nameWithExtension);
            ArrayList<String> content = new ArrayList<>();
            content.add("<file" + i + ">");
            content.addAll(currentFileReader.getLines());
            content.add("</file" + i + ">");
            writer.writeLines(content, false, false);
        }


    }

    public void collectFile(List<File> filesForCollect, String outputFileName, String pathOutputFiles) {
        Creator creator = new Creator(pathOutputFiles, outputFileName);
        creator.createNewFile();
        Writer writer = new Writer(pathOutputFiles, outputFileName);
        Reader reader = new Reader();
        ArrayList<String> atributes = new ArrayList<>();
        atributes.add("FILE COUNT: " + filesForCollect.size());
        for (int i = 1; i < filesForCollect.size(); i++){
            atributes.add(i + ": " + filesForCollect.get(i).getPath());
        }
        int i = 0;
        writer.writeLines(atributes, false, false);
        for (File file : filesForCollect) {
            i++;
            Map<String, String> pathFragments = reader.getPathFragments(file);
            String path = pathFragments.get("path");
            String name = pathFragments.get("name");
            String extension = pathFragments.get("extension");
            String nameWithExtension = name + "." + extension;
            Reader currentFileReader = new Reader(path, nameWithExtension);
            ArrayList<String> content = new ArrayList<>();
            content.add("<file" + i + ">");
            content.addAll(currentFileReader.getLines());
            content.add("</file" + i + ">");
            writer.writeLines(content, false, false);
        }
    }
}
