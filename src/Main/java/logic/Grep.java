package logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {

    private List<String> text = new ArrayList<>();

    public Grep(String inputDirectory) {
        try {
            text = Files.readAllLines(Paths.get(inputDirectory));
        } catch (IOException e) {
            throw new IllegalArgumentException("Can't read file" + e.getMessage());
        }
    }

    public List<String> find(String w, boolean isItRegex, boolean invert, boolean ignoreCase) {
        List<String> output = new ArrayList<>();
        if (isItRegex) {
            Pattern p = ignoreCase ? Pattern.compile(w, Pattern.CASE_INSENSITIVE) : Pattern.compile(w);
            for (String s : text) {
                Matcher m = p.matcher(s);
                if ((invert && !m.find()) || (!invert && m.find())) {
                    output.add(s);
                }
            }
        } else {
            for (String s : text) {
                String s1 = s;
                if (ignoreCase) {
                    w = w.toLowerCase();
                    s = s.toLowerCase();
                }
                if ((!invert && s.contains(w)) || (invert && !s.contains(w))) {
                    output.add(s1);
                }
            }
        }
        return output;
    }
}