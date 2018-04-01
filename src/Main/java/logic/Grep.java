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

    private final boolean regex;

    private final boolean invert;

    private final boolean ignoreCase;

    public Grep(String word, boolean regex, boolean invert, boolean ignoreCase) {
        this.word = word;
        this.regex = regex;
        this.invert = invert;
        this.ignoreCase = ignoreCase;
    }

    public List<String> find(List<String> text) {
        List<String> output = new ArrayList<>();
        if (regex) {
            Pattern p = ignoreCase ? Pattern.compile(word, Pattern.CASE_INSENSITIVE) : Pattern.compile(word);
            for (String line : text) {
                Matcher m = p.matcher(line);
                if ((invert && !m.find()) || (!invert && m.find())) {
                    output.add(line);
                }
            }
        } else {
            for (String line : text) {
                String lineCopy = line;
                String wordCopy = word;
                if (ignoreCase) {
                    wordCopy = wordCopy.toLowerCase();
                    line = line.toLowerCase();
                }
                if ((!invert && line.contains(wordCopy)) || (invert && !line.contains(wordCopy))) {
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