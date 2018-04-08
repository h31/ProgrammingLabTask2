package logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {
    private final GrepOption option;

    public Grep(GrepOption option) {
        this.option = option;
    }

    public List<String> find(List<String> text) {
        List<String> output = new ArrayList<>();
        Pattern pattern;
        if (option.isRegex) {
             pattern = option.isIgnoreCase ? Pattern.compile(option.word, Pattern.CASE_INSENSITIVE) : Pattern.compile(option.word);
        } else {
             pattern = option.isIgnoreCase ? Pattern.compile(option.word,
                    Pattern.CASE_INSENSITIVE | Pattern.LITERAL) : Pattern.compile(option.word, Pattern.LITERAL);
        }
            for (String line : text) {
                Matcher matcher = pattern.matcher(line);
                if ((option.isInvert && !matcher.find()) || (!option.isInvert && matcher.find())) {
                    output.add(line);
                }
            }
        return output;
    }

    public List<String> find(Path inputFile) {
        try {
            List<String> text = Files.readAllLines(inputFile);
            return find(text);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}