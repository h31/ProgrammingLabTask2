package com.project.insides.files;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Reader {

    private List<String> answer;

    public Reader(String pathToFile) {
        try {
            answer = Files.readAllLines(Paths.get(pathToFile), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public List<String> getAnswer() {
        return answer;
    }
}
