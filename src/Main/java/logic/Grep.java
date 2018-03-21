package logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Grep {

    private List<String> text = new ArrayList<String>();

    public Grep(String inputDirectory) {
        try {
            text = Files.readAllLines(Paths.get(inputDirectory));
        } catch (IOException e) {
            throw new IllegalArgumentException("Can't read file" + e.getMessage());
        }
    }

    public List<String> grepWithWord(String word) {
        List<String> output = new ArrayList<String>();
        for (String s:text) {
            if (s.contains(word)) {
                output.add(s);
            }
        }
        return output;
    }

    public List<String> grepWithRegex(String pattern) {
        List<String> output = new ArrayList<String>();
        for (String s:text) {
            if (s.matches(pattern)) {
                output.add(s);
            }
        }
        return output;
    }

    public List<String> grepWithoutRegex(String pattern) {
        List<String> output = new ArrayList<String>();
        for (String s:text) {
            if (!s.matches(pattern)) {
                output.add(s);
            }
        }
        return output;
    }

    public List<String> grepWithIgnoreRegister(String pattern) {
        List<String> output = new ArrayList<String>();
        for (String s:text) {
            if (s.matches("?" + pattern)) {
                output.add(s);
            }
        }
        return output;
    }
}
