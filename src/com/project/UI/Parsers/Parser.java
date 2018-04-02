package com.project.UI.Parsers;

import java.io.File;

public interface Parser {

    boolean isPacking();

    String getOutputFileName();

    String getInputFileName();

    default String createNewFileName(String outputName) {
        if (!outputName.isEmpty()) return outputName;

        int count = 1;
        String[] inputFileNamePart = getInputFileName().split("\\s+");
        String extension = isPacking() ? ".uz" : "txt";

        File outputFile = new File(getInputFileName());
        while (!outputFile.exists()) {
            outputFile = new File(inputFileNamePart[0] + count + extension);
            count++;
        }
        return outputFile.getName();
    }

}
