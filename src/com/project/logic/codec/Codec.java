package com.project.logic.codec;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Codec {

    void start();

    void setFile(List<String> fileLines);

    List<String> getOutputToFile();

    default Pair<List<String>, List<String>> findArchivePattern(List<String> linesForFind) {

        final List<String> deepMatches = new ArrayList<>();
        final List<String> normalMatches = new ArrayList<>();
        Matcher deepMatcher;
        Matcher normalMatcher;
        for (String line : linesForFind) {
            deepMatcher = Pattern.compile(".\\d+&+\\|").matcher(line);
            normalMatcher = Pattern.compile(".\\d+\\|").matcher(line);

            while (normalMatcher.find()) {
                normalMatches.add(normalMatcher.group(0));
            }
            while (deepMatcher.find()) {
                deepMatches.add(deepMatcher.group(0));
            }
        }
        return new Pair<>(normalMatches, deepMatches);
    }
}
