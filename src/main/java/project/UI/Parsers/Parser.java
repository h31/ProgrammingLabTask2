package main.java.project.UI.Parsers;

import java.io.File;

public interface Parser {

    boolean isPacking();

    String getOutputFileName();

    String getInputFileName();

    default String createOutputFileName(String outputName, String inputFileName) {
        String extension = isPacking() ? "uz" : "txt";
        if (outputName != null) return createNewFileName(outputName, -1, extension);

        int count = 1;
        String[] inputFileNamePart = inputFileName.split("\\.");
        System.err.println(inputFileName);

        File outputFile = new File(createNewFileName(inputFileNamePart[0], count, extension));
        while (outputFile.exists()) {
            count++;
            outputFile = new File(createNewFileName(inputFileNamePart[0], count, extension));
        }
        System.err.println(outputFile.getPath());
        return outputFile.getPath();
    }

    default String createNewFileName(String foundName, int count, String extension) {
        return new StringBuilder(foundName)
                .append(count != -1 ? count : "")
                .append(".")
                .append(extension)
                .toString();
    }

}
