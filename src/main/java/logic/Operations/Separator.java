package logic.Operations;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Separator {
    public static void separateFile(Path fileToSeparatePath, Path outputPath) throws IOException {
        BufferedReader reader = Files.newBufferedReader(fileToSeparatePath);
        List<String> allContent = new ArrayList<>();
        String line;
        ArrayList<Integer> separatorsIndexes = new ArrayList<>();
        ArrayList<String> separatedFileNames = new ArrayList<>();
        int i = 0;
        while ((line = reader.readLine()) != null) {
            if (line.equals("<#####SEPARATOR#####>")) {
                separatorsIndexes.add(i);
            }
            allContent.add(line);
            i++;
        }
        separatorsIndexes.add(allContent.size());

        for (i = 0; i < separatorsIndexes.size() - 1; i++) {
            separatedFileNames.add(allContent.get(separatorsIndexes.get(i) + 1));
        }
        for (int filecount = 0; filecount < separatorsIndexes.size() - 1; filecount++) {
            Files.write(Paths.get(outputPath.toString() + "/" + separatedFileNames.get(filecount)), allContent.subList(separatorsIndexes.get(filecount) + 2, separatorsIndexes.get(filecount + 1)));
        }
    }
}
