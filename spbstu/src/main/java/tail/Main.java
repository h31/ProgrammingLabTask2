package tail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String text = new String(Files.readAllBytes(Paths.get("/Users/molkos/IdeaProjects/ProgrammingLabTask2/spbstu/src/main/java/Text.txt")));
        List<String> lines = Arrays.asList(text.split("\n"));
        logicTail cutter = new logicTail();
        System.out.println(cutter.c(20, lines));
    }
}
