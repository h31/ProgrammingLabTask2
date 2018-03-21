package logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Grep {

    private  List<String> text;

    public Grep(String inputDirectory) {
        try {
            text = Files.readAllLines(Paths.get(inputDirectory));
        } catch (IOException e) {
            throw new IllegalArgumentException("Can't read file" + e.getMessage());
        }
    }

}
