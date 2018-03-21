import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Grep {

    private List<String> lines = new ArrayList<>();

    public List<String> getLines() {
        return lines;
    }

    public Grep(String path) {
        try {
            lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> findInLines(String regex, int key) {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        for (String line : lines) {
            if ((key == 0 && line.contains(regex)) || (key == 1 && pattern.matcher(line).find())
                    || key == 2 && !pattern.matcher(line).find()) {
                result.add(line);
            }
        }
        return result;
    }

    public List<String> findOnWord(String word) {
        return findInLines(word, 0);
    }

    public List<String> findOnRegex(String regex) {
        return findInLines(regex, 1);
    }

    public List<String> findExceptRegex(String regex) {
        return findInLines(regex, 2);
    }

    public List<String> ignoreCase(String word) {
        List<String> result = new ArrayList<>();
        word = word.toLowerCase();
        for (String line: lines) {
            if (line.toLowerCase().contains(word)) {
                result.add(line);
            }
        }
        return result;
    }
}