package logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {

    private final String word;

    private final boolean isRegex;

    private final boolean isInvert;

    private final boolean isIgnoreCase;

    public Grep(String word, boolean regex, boolean invert, boolean ignoreCase) {
        this.word = word;
        this.isRegex = regex;
        this.isInvert = invert;
        this.isIgnoreCase = ignoreCase;
    }

    public List<String> find(List<String> text) {
        List<String> output = new ArrayList<>();
        if (isRegex) {
            Pattern p = isIgnoreCase ? Pattern.compile(word, Pattern.CASE_INSENSITIVE) : Pattern.compile(word);
            for (String line : text) {
                Matcher m = p.matcher(line);
                if ((isInvert && !m.find()) || (!isInvert && m.find())) {
                    output.add(line);
                }
            }
        } else {
            for (String line : text) {
                String lineCopy = line;
                String wordCopy = word;
                if (isIgnoreCase) {
                    wordCopy = wordCopy.toLowerCase();
                    line = line.toLowerCase();
                }
                if ((!isInvert && line.contains(wordCopy)) || (isInvert && !line.contains(wordCopy))) {
                    output.add(lineCopy);
                }
            }
        }
        return output;
    }

    public List<String> find(String inputDirectory) {
        try {
            List<String> text = Files.readAllLines(Paths.get(inputDirectory));
            return find(text);
        } catch (IOException e) {
            throw new IllegalArgumentException("Something went wrong" + e.getMessage());
        }
    }
}