package com.project.insides.archiving;

import com.project.insides.ArchiveObject;
import com.project.insides.files.Reader;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class Archive {

    private static List<String> file;

    public static void start(String inputName, String outputName, boolean packing) {
        Reader reader = new Reader(inputName);
        file = reader.getAnswer();
        System.out.println(reader.getAnswer());
        if (packing) {
            packing(outputName);
        } else {
            unpacking(outputName);
        }
    }

    private static void packing(String outputName) {
        try {
            List<ArchiveObject> buffer = new ArrayList<>();
            for (String line : file) {
                char[] bytesLine = line.toCharArray();
                char lastByte = bytesLine[0];
                buffer.add(new ArchiveObject(lastByte));
                for (int byteIndex = 1; byteIndex < bytesLine.length; byteIndex++) {
                    ArchiveObject lastElement = buffer.get(buffer.size() - 1);
                    if (bytesLine[byteIndex] == lastByte) {
                        lastElement.inc();
                    } else {
                        buffer.add(new ArchiveObject(bytesLine[byteIndex]));
                        lastByte = bytesLine[byteIndex];
                    }
                }
            }
            String answerToFile = buffer.toString().replaceAll("([\\[\\],])+", "");
            Files.write(Paths.get(outputName + ".uz"), Collections.singleton(answerToFile));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("An error occurred while reading the file, invalid encoding");
        } catch (IOException e) {
            throw new IllegalArgumentException("File already exists or the name is not correct");
        }

    }

    private static void unpacking(String outputName) {
        if (file.size() != 0 && (file.size() > 1 || file.get(0).matches("(\\d(\\|\\d+)?\\s?)+"))) {
            throw new IllegalArgumentException("The file is damaged");
        }
        Pattern pattern = Pattern.compile(".\\|\\d+");
        String[] partsFile = file.get(0).split(" ");
        StringBuilder buffer = new StringBuilder();
        for (String part : partsFile) {
            if (part.isEmpty()) {
                buffer.append(" ");
            } else if (pattern.matcher(part).matches()) {
                int numberOfRepeats = Integer.parseInt(part.split("\\|")[1]);
                char symbolForRepeat = part.charAt(0);
                while (numberOfRepeats > 0) {
                    buffer.append(symbolForRepeat);
                    numberOfRepeats--;
                }
            } else {
                buffer.append(part);
            }
        }
        String answerToFile = buffer.toString().replaceAll("\\s+", " ");
        try {
            Files.write(Paths.get(outputName + ".txt"), Collections.singleton(answerToFile));
        } catch (IOException e) {
            throw new IllegalArgumentException("Something"); // TODO ERROR
        }
    }

}
