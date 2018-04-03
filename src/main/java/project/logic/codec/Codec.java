package main.java.project.logic.codec;

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

        final List<String> escapingMatches = new ArrayList<>();
        final List<String> normalMatches = new ArrayList<>();
        Matcher escapingMatcher;
        Matcher normalMatcher;
        for (String line : linesForFind) {
            escapingMatcher = Pattern.compile(".\\d+&+\\|").matcher(line);
            normalMatcher = Pattern.compile(".\\d+\\|").matcher(line);

            while (normalMatcher.find()) {
                normalMatches.add(normalMatcher.group(0));
            }
            while (escapingMatcher.find()) {
                escapingMatches.add(escapingMatcher.group(0));
            }
        }
        return new Pair<>(normalMatches, escapingMatches);
    }
}
