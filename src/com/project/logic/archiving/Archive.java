package com.project.logic.archiving;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Archive {

    private static List<String> fileLines;
    private static String streamToFile;
    private static String fileName;
    private static char shieldingElement = '&';

    public static void start(String outputName, boolean packing) {

        if (fileLines != null && !fileLines.isEmpty()) {
            throw new IllegalArgumentException("Error reading file");
        }

        if (packing) {
            packing(outputName);
        } else {
            unpacking(outputName);
        }
    }

    private static void packing(String outputName) {
        final List<ArchiveElement> buffer = new ArrayList<>();
        for (String line : fileLines) {
            List<String> packingElements = findArchivePattern(line, true);
            if (packingElements.size() == 0) packingElements = findArchivePattern(line, false);
            for (String element : packingElements) {
                line = line.replace(element, element.replaceFirst("\\|", "&|"));
            }

            packingOneLine(line, buffer);
        }

        StringBuilder answer = new StringBuilder();
        buffer.forEach(answer::append);

        fileName = outputName + ".uz";
        streamToFile = answer.toString();
    }

    private static void packingOneLine(String line, List<ArchiveElement> buffer) {
        char[] charsOfLine = line.toCharArray();
        if (charsOfLine.length != 0) {
            char lastChar = charsOfLine[0];
            buffer.add(new ArchiveElement(lastChar));
            for (int index = 1; index < charsOfLine.length; index++) {
                final ArchiveElement lastArchiveElement = buffer.get(buffer.size() - 1);
                if (charsOfLine[index] == lastChar && charsOfLine[index] != shieldingElement) {
                    lastArchiveElement.inc();
                } else {
                    buffer.add(new ArchiveElement(charsOfLine[index]));
                    lastChar = charsOfLine[index];
                }
            }
        }
    }


    private static void unpacking(String outputName) {
        assert fileLines.size() == 1 : "The file is damaged";

        final List<String> normalArchiveElements = findArchivePattern(fileLines.get(0), true);
        final List<String> deepArchiveElements = findArchivePattern(fileLines.get(0), false);
        final boolean onceCompress = Pattern.compile("(.\\d+&\\|)").matcher(fileLines.get(0)).find();
        final String answer = unpackingLine(normalArchiveElements, deepArchiveElements);

        fileName = outputName + (onceCompress ? ".txt" : ".uz");
        streamToFile = answer;
    }

    private static String unpackingLine(List<String> normalArchiveElements, List<String> deepArchiveElements) {
        String answer = fileLines.get(0);

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

    private static List<String> findArchivePattern(String strForFind, boolean notShielding) {
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

    public static void setFile(List<String> file) {
        Archive.fileLines = file;
    }

    public static String getFileName() {
        return fileName;
    }

    public static String getStreamToFile() {
        return streamToFile;
    }
}
