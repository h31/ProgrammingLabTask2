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

        assert file != null : "Error reading file";

        System.out.printf("Start %s file %s\n", packing ? "packing" : "unpacking", inputName);
        System.out.printf("New file name = %s\n", outputName);

        double originalSize = new File(inputName).length();
        double nowSize = new File(outputName + (packing ? ".uz" : ".txt")).length();

        if (packing) {
            packing(outputName);
            System.out.printf("Original file size = %.0f\n" +
                            "Archive file size = %.0f\n" +
                            "The file is compressed by %.2f percent\n",
                    originalSize, nowSize, 100 - (nowSize / originalSize) * 100);
        } else {
            System.out.printf("Archive file size = %.0f\n" +
                            "Unpacking file size = %.0f\n" +
                            "The unpacked file is more by %.2f percent\n",
                    nowSize, originalSize, 100 - (originalSize / nowSize) * 100);
            unpacking(outputName);
        }
        System.out.printf("%s end\n\n", packing ? "Packing" : "Unpacking");
    }

    private static void packing(String outputName) {

        final List<ArchiveElement> buffer = new ArrayList<>();
        for (String line : file) {
            List<String> archiveElements = findArchivePattern(line, true);
            if (archiveElements.size() != 0) {
                for (String element : archiveElements) {
                    line = line.replace(element,  element.replace("|", "|&"));
                }
            }
            char[] elementsOfLine = line.toCharArray();
            if (elementsOfLine.length != 0) {
                char last = elementsOfLine[0];
                buffer.add(new ArchiveElement(last));
                for (int index = 1; index < elementsOfLine.length; index++) {
                    final ArchiveElement lastElement = buffer.get(buffer.size() - 1);
                    if (elementsOfLine[index] == last) {
                        lastElement.inc();
                    } else {
                        buffer.add(new ArchiveElement(elementsOfLine[index]));
                        last = elementsOfLine[index];
                    }
                }
            }
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

    private static void unpacking(String outputName) {

        assert file.size() != 0 && file.size() <= 1 : "The file is damaged";

        final List<String> normalArchiveElements = findArchivePattern(file.get(0), false);
        final List<String> deepArchiveElements = findArchivePattern(file.get(0), true);
        final Pattern delimiter = Pattern.compile("\\|");

        String answerToFile = file.get(0);
        for (String element : normalArchiveElements) {
            String[] partElement = delimiter.split(element);
            int quantity = Integer.parseInt(partElement[1]);
            String symbol = partElement[0];
            answerToFile = answerToFile
                    .replace(element, String.join("", Collections.nCopies(quantity, symbol)));
        }

        for (String element : deepArchiveElements) {
            String newElement = element.replaceFirst("&", "");
            answerToFile = answerToFile.replace(element, newElement);
        }

        try {
            Files.write(Paths.get(outputName + (deepArchiveElements.size() == 0 ? ".txt" : ".uz")),
                    Collections.singleton(answerToFile));
        } catch (IOException e) {
            throw new IllegalArgumentException("Unknown error");
        }
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
