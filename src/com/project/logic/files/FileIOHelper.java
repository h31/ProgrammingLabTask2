package com.project.logic.files;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class FileIOHelper {

    public List<String> read(String pathToFile) {
        try {
            return Files.readAllLines(Paths.get(pathToFile), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error reading file = " + e.getMessage());
        }
    }

    public void write(String outputName, String answerToFile) {
        try {
            Files.write(Paths.get(outputName), Collections.singleton(answerToFile));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("An error occurred while reading the file, invalid encoding");
        } catch (IOException e) {
            throw new IllegalArgumentException("File already exists or the name is not correct");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("The file is empty or unknown error");
        }
    }
}
