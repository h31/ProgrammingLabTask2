package logic;

import jdk.nashorn.internal.runtime.regexp.RegExp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        List<String> output = new ArrayList<>();
        for (String s:text) {
            if (s.contains(word)) {
                output.add(s);
            }
        }
        return output;
    }

    public List<String> grepWithRegex(String regex) {
        List<String> output = new ArrayList<>();
        Pattern p = Pattern.compile(regex);
        for (String s:text) {
            Matcher m = p.matcher(s);
            if (m.find()) {
                output.add(s);
            }
        }
        return output;
    }

    public List<String> grepWithoutRegex(String regex) {
        List<String> output = new ArrayList<>();
        Pattern p = Pattern.compile(regex);
        for (String s:text) {
            Matcher m = p.matcher(s);
            if (!m.find()) {
                output.add(s);
            }
        }
        return output;
    }

    public List<String> grepWithIgnoreRegister(String regex) {
        List<String> output = new ArrayList<>();
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        for (String s:text) {
            Matcher m = p.matcher(s);
            if (m.find()) {
                output.add(s);
            }
        }
        return output;
    }
    }
