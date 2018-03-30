package com.project.logic.files;

import java.io.File;
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

    public void write(String outputName, List<String> answerToFile) {
        try {
            File newFile = new File(outputName);
            Files.write(newFile.toPath(), answerToFile);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("The Character Encoding is not supported.");
        } catch (IOException e) {
            throw new IllegalArgumentException("File already exists or the name is not correct");
        } catch (NullPointerException ignored) {
        }
    }
}
