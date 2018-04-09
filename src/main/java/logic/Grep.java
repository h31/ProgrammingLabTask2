package logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {
    private boolean regex;
    private boolean exceptRegex;
    private boolean ignoreCase;
    private String word;
    private List<String> lines;

    public Grep(boolean regex, boolean exceptRegex, boolean ignoreCase, String word, String path) {
        this.regex = regex;
        this.exceptRegex = exceptRegex;
        this.ignoreCase = ignoreCase;
        this.word = word;
        try {
            this.lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public List<String> findOfFile() {
        List<String> result = new ArrayList<>();
        for (String line : this.lines) {
            String lineBuf = this.ignoreCase ? line.toLowerCase() : line;
            String wordBuf = this.ignoreCase ? word.toLowerCase() : word;
            if (regex) {
                Matcher matcher = Pattern.compile(wordBuf).matcher(lineBuf);
                if (matcher.find()) {
                    result.add(line);
                }
            }
            if (exceptRegex) {
                if (!lineBuf.contains(wordBuf)) {
                    result.add(line);
                }
            } else {
                if (lineBuf.contains(wordBuf)) {
                    result.add(line);
                }
            }
        }
        return result;
    }
}

