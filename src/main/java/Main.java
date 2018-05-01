import ui.Parsers.Args;
import logic.Operations.Collector;
import logic.Operations.Separator;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        /*
        Args arguments = new Args(args);
        arguments.getTask();
        if (arguments.getTaskFlag()) {
            Collector collector = new Collector();
            collector.collectFiles(arguments.getFilesToCollect(), arguments.getCollectionFile().getName());
        } else {
            Separator separator = new Separator();
            separator.separateFile(arguments.getFileToSeparate());
        }
        */
File file = new File(".");
        System.out.println(file.getAbsolutePath());
        Collector collector = new Collector();
        List<Path> files = new ArrayList<>();
        files.add(Paths.get("src/main/resources/fileToCollect1.txt"));
        files.add(Paths.get("src/main/resources/fileToCollect2.txt"));
        files.add(Paths.get("src/main/resources/fileToCollect3.txt"));
        collector.collectFiles(files, Paths.get("collectionFile.txt"));

    }
}
