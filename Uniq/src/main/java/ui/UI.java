package ui;

import logic.Uniq;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class UI {
    public static void main(String[] args) throws IOException {
        start(args);
    }

    public static void start(String... args) throws IOException {
        Parser parser = new Parser(args);

        List<String> allLines = new ArrayList<>();
        if (parser.inputFileName() != null) {
            allLines = Files.readAllLines(Paths.get(parser.inputFileName()));
        } else {
            System.out.println("Press Enter if u end write lines");

            String line;
            do {
                line = new Scanner(System.in).nextLine();
            } while (!line.isEmpty() && allLines.add(line));
        }

        Uniq uniq = new Uniq(allLines);
        Set<String> uniqStrings = uniq.getUniqStrings(parser.registerIgnored(), parser.onlyUniqueStrings(),
                parser.countOfString(), parser.countIgnoreSymbols());

        System.out.println(parser.outputFileName());

        if (parser.outputFileName() != null) {
            Files.write(Paths.get(parser.outputFileName()), uniqStrings);
        } else {
            for (String string : uniqStrings) {
                System.out.println(string);
            }
        }
    }
}
