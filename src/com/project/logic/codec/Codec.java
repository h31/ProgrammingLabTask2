package com.project.logic.codec;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Codec {

    void start();
    String getOutputStringToFile();

    default List<String> findArchivePattern(String strForFind, boolean notShielding) {
        final Matcher matcher;
        if (!notShielding) {
            matcher = Pattern.compile(".\\d+&+\\|").matcher(strForFind);
        } else {
            matcher = Pattern.compile(".\\d+\\|").matcher(strForFind);
        }
        final List<String> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(matcher.group(0));
        }
        return matches;
    }

    void setFile(List<String> fileLines);
}
