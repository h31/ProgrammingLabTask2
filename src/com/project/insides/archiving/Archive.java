package com.project.insides.archiving;

import com.project.insides.ArchiveObject;
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

        if (packing) {
            packing(outputName);
            double originalSize = new File(inputName).length();
            double compressedSize = new File(outputName + ".uz").length();
            System.out.printf("Original file size = %.0f\n" +
                            "Archive file size = %.0f\n" +
                            "The file is compressed by %.2f percent\n",
                    originalSize, compressedSize, 100 - (compressedSize / originalSize) * 100);
        } else {
            unpacking(outputName);
        }
        System.out.printf("%s end\n\n", packing ? "Packing" : "Unpacking");
    }

    private static void packing(String outputName) {
        try {
            final List<ArchiveObject> buffer = new ArrayList<>();
            for (String line : file) {
                final char[] elementsOfLine = line.toCharArray();
                char last = elementsOfLine[0];
                buffer.add(new ArchiveObject(last));
                for (int index = 1; index < elementsOfLine.length; index++) {
                    final ArchiveObject lastElement = buffer.get(buffer.size() - 1);
                    if (elementsOfLine[index] == last) {
                        lastElement.inc();
                    } else {
                        buffer.add(new ArchiveObject(elementsOfLine[index]));
                        last = elementsOfLine[index];
                    }
                }
            }

            final StringBuilder answerToFile = new StringBuilder();
            buffer.forEach(answerToFile::append);
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

        final Matcher matcher = Pattern.compile(".\\|\\d+").matcher(file.get(0));
        final List<String> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(matcher.group(0));
        }

        final Pattern delimiter = Pattern.compile("\\|");

        String answerToFile = file.get(0);
        for (String element : matches) {
            String[] partElement = delimiter.split(element);
            int quantity = Integer.parseInt(partElement[1]);
            String symbol = partElement[0];
            answerToFile = answerToFile
                    .replace(element, String.join("", Collections.nCopies(quantity, symbol)));
        }

        try {
            Files.write(Paths.get(outputName + ".txt"), Collections.singleton(answerToFile));
        } catch (IOException e) {
            throw new IllegalArgumentException("Unknown error");
        }
    }
}
