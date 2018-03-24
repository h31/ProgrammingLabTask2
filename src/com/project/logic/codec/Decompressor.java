package com.project.logic.codec;

import java.util.Collections;
import java.util.List;

public class Decompressor implements Codec {

    private String outputStringToFile;
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

        final List<String> normalArchiveElements = findArchivePattern(this.fileLines.get(0), true);
        final List<String> deepArchiveElements = findArchivePattern(this.fileLines.get(0), false);

        this.outputStringToFile = unpackLine(normalArchiveElements, deepArchiveElements);
    }

    private String unpackLine(List<String> normalArchiveElements, List<String> deepArchiveElements) {
        String answer = this.fileLines.get(0);

        for (String element : normalArchiveElements) {
            final String symbolForCopies = String.valueOf(element.charAt(0));
            final int quantity = Integer.parseInt(element.substring(1, element.length() - 1));
            answer = answer
                    .replace(element, String.join("", Collections.nCopies(quantity, symbolForCopies)));
        }

        for (String element : deepArchiveElements) {
            String newElement = element.replaceFirst("&", "");
            answer = answer.replace(element, newElement);
        }

        return answer;
    }

    @Override
    public String getOutputStringToFile() {
        return outputStringToFile;
    }

    @Override
    public void setFile(List<String> fileLines) {
        this.fileLines = fileLines;
    }
}
