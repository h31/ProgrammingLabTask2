import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Grep {
    private boolean ignoreCase;
    private List<String> lines;

    Grep(String path) {
        try {
            this.lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public List<String> getLines() {
        return lines;
    }

    private List<String> findInLines(String regex, int key) {
        List<String> result = new ArrayList<>();
        regex = ignoreCase ? regex.toLowerCase() : regex;
        Pattern pattern = Pattern.compile(regex);
        for (String line : lines) {
            String lineBuf = line;
            if (ignoreCase)  {
                lineBuf = line.toLowerCase();
            }
            if ((key == 1 && pattern.matcher(lineBuf).find()) ||
                    key == 2 && !pattern.matcher(lineBuf).find()) {
                result.add(line);
            }
        }
        return result;
    }

    public List<String> findOnRegex(String regex) {
        return findInLines(regex, 1);
    }

    public List<String> findExceptRegex(String regex) {
        return findInLines(regex, 2);
    }


    public void setIgnoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }
}