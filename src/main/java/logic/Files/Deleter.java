package logic.Files;

import java.io.File;
import java.util.List;

public class Deleter {
    public void deleteFiles(List<File> files) {
        for (File file : files) {
            file.delete();
        }
    }

    public void deleteFile(File file){
        file.delete();
    }
}
