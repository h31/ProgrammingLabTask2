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

    private List<String> findInLines(String regex, String key) {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        for (String line : lines) {
            if ((key.equals("-r") && pattern.matcher(line).find()) || (key.equals("word") && line.contains(regex))
                || key.equals("-v") && !pattern.matcher(line).find()) {
                result.add(line);
            }
        }
        return result;
    }

    public List<String> findOnWord(String word) {
        return findInLines(word, "word");
    }

    public List<String> findOnRegex(String regex) {
        return findInLines(regex, "-r");
    }

    public List<String> findExceptRegex(String regex) {
        return findInLines(regex, "-v");
    }
}

