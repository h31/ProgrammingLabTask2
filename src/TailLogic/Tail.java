package TailLogic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Tail {
    private List<String> strings;

    public Tail(String path) {
        try {
            this.strings = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
