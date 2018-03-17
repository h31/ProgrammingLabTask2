package com.project.insides.archiving;

import com.project.insides.files.Reader;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Archive {

    private static List<String> file;

    public static void start(String inputName, String outputName, boolean packing) {
        final Reader reader = new Reader(inputName);
        file = reader.getAnswer();

        assert file != null && !file.isEmpty() : "Error reading file";

        System.out.printf("Start %s file %s\n", packing ? "packing" : "unpacking", inputName);
        System.out.printf("New file name = %s\n", outputName);

        double originalSize = (double) new File(inputName).length();
        double nowSize = (double) new File(outputName + (packing ? ".uz" : ".txt")).length();

        if (packing) {
            packing(outputName);
            System.out.printf("Original file size = %.0f\n" +
                            "Archive file size = %.0f\n" +
                            "The file is compressed by %.2f percent\n",
                    originalSize, nowSize, 100 - (nowSize / originalSize) * 100);
        } else {
            unpacking(outputName);
            System.out.printf("Archive file size = %.0f\n" +
                            "Unpacking file size = %.0f\n" +
                            "The unpacked file is more by %.2f percent\n",
                    nowSize, originalSize, 100 - (originalSize / nowSize) * 100);
        }
        System.out.printf("%s end\n\n", packing ? "Packing" : "Unpacking");
    }

    private static void packing(String outputName) {
        final List<ArchiveElement> buffer = new ArrayList<>();
        for (String line : file) {
            List<String> packingElements = findArchivePattern(line, true);
            if (packingElements.size() != 0) {
                for (String element : packingElements) {
                    line = line.replace(element,  element.replace("|", "|&"));
                }
            }
            packingOneLine(line, buffer);
        }

        StringBuilder answerToFile = new StringBuilder();
        buffer.forEach(answerToFile::append);

        try {
            Files.write(Paths.get(outputName + ".uz"), Collections.singleton(answerToFile));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("An error occurred while reading the file, invalid encoding");
        } catch (IOException e) {
            throw new IllegalArgumentException("File already exists or the name is not correct");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("The file is empty or of unknown characters");
        }
    }

    private static void packingOneLine(String line, List<ArchiveElement> buffer) {
        char[] charsOfLine = line.toCharArray();
        if (charsOfLine.length != 0) {
            char lastChar = charsOfLine[0];
            buffer.add(new ArchiveElement(lastChar));
            for (int index = 1; index < charsOfLine.length; index++) {
                final ArchiveElement lastArchiveElement = buffer.get(buffer.size() - 1);
                if (charsOfLine[index] == lastChar) {
                    lastArchiveElement.inc();
                } else {
                    buffer.add(new ArchiveElement(charsOfLine[index]));
                    lastChar = charsOfLine[index];
                }
            }
        }
    }



    private static void unpacking(String outputName) {
        assert file.size() == 1 : "The file is damaged";

        final List<String> normalArchiveElements = findArchivePattern(file.get(0), false);
        final List<String> deepArchiveElements = findArchivePattern(file.get(0), true);

        final String answerToFile = unpackingLine(normalArchiveElements, deepArchiveElements);

        try {
            Files.write(Paths.get(outputName + (deepArchiveElements.size() == 0 ? ".txt" : ".uz")),
                    Collections.singleton(answerToFile));
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private static String unpackingLine(List<String> normalArchiveElements, List<String> deepArchiveElements) {
        String answer = file.get(0);
        final Pattern delimiter = Pattern.compile("\\|");
        for (String element : normalArchiveElements) {
            final String[] partElement = delimiter.split(element);
            final int quantity = Integer.parseInt(partElement[1]);
            String symbolForCopies = partElement[0];
            answer = answer
                    .replace(element, String.join("", Collections.nCopies(quantity, symbolForCopies)));
        }

        for (String element : deepArchiveElements) {
            String newElement = element.replaceFirst("&", "");
            answer = answer.replace(element, newElement);
        }
        return answer;
    }

    private static List<String> findArchivePattern(String strForFind, boolean packing) {
        final Matcher matcher;
        if (packing) {
            matcher = Pattern.compile(".\\|&+\\d+").matcher(strForFind);
        } else {
            matcher = Pattern.compile(".\\|\\d+").matcher(strForFind);
        }
        final List<String> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(matcher.group(0));
        }
        return matches;
    }

}
