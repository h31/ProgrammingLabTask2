package logic.Operations;

import logic.Files.Creator;
import logic.Files.Reader;
import logic.Files.Writer;

import java.io.File;
import java.util.List;
import java.util.Map;

public class Collector {

     public void collectFile(List<File> filesForCollect, String outputFileName) {
        Reader reader = new Reader();
        Map<String, String> pathFragments = reader.getPathFragments(filesForCollect.get(0));
        Creator creator = new Creator(pathFragments.get("path"), outputFileName);
        creator.createNewFile();
        Writer writer = new Writer(pathFragments.get("path"), outputFileName);

        for (File file : filesForCollect) {
            pathFragments = reader.getPathFragments(file);
            String path = pathFragments.get("path");
            String name = pathFragments.get("name");
            String extension = pathFragments.get("extension");
            String nameWithExtension = name + "." + extension;
            Reader currentFileReader = new Reader(path, nameWithExtension);
            writer.writeLines(currentFileReader.getLines(), false, false);
        }


    }
}
