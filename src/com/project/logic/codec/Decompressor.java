package com.project.logic.codec;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Decompressor implements Codec {

    private List<String> outputStringToFile;
    private List<String> fileLines;

    public Decompressor(List<String> fileLines) {
        this.fileLines = fileLines;
    }

    @Override
    public void start() {
        final int maxSize = 1;
        if (this.fileLines.size() != maxSize) {
            throw new IllegalArgumentException("The file is damaged");
        }

        Pair<List<String>, List<String>> codecPattern = findArchivePattern(this.fileLines);
        final List<String> normalArchiveElements = codecPattern.getKey();
        final List<String> deepArchiveElements = codecPattern.getValue();

        this.outputStringToFile = unpackLine(normalArchiveElements, deepArchiveElements);
    }

    private List<String> unpackLine(List<String> normalArchiveElements, List<String> deepArchiveElements) {
        List<String> answer = new ArrayList<>();
        for (String line : fileLines) {
            for (String element : normalArchiveElements) {
                final String symbolForCopies = String.valueOf(element.charAt(0));
                final int quantity = Integer.parseInt(element.substring(1, element.length() - 1));
                line = line
                        .replace(element, String.join("", Collections.nCopies(quantity, symbolForCopies)));
            }

            for (String element : deepArchiveElements) {
                String newElement = element.replaceFirst("&", "");
                line = line.replace(element, newElement);
            }
            answer.add(line);
        }

        return answer;
    }

    @Override
    public List<String> getOutputToFile() {
        return outputStringToFile;
    }

    @Override
    public void setFile(List<String> fileLines) {
        this.fileLines = fileLines;
    }
}
