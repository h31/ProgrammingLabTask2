package logic.Operations;

import logic.Files.Creator;
import logic.Files.Reader;
import logic.Files.Writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Collector {

    public static void collectFiles(List<Path> filesForCollect, Path outputFileName){
        ArrayList<String> linesToWrite = new ArrayList<>();
        try {
            for (Path currentPath : filesForCollect) {
                List<String> readLines = Files.readAllLines(currentPath);
                linesToWrite.add("<#####SEPARATOR#####>");
                linesToWrite.addAll(readLines);
            }
            Files.write(outputFileName, linesToWrite);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
