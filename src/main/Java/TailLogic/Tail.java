package TailLogic;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Tail {
    private List<String> strings;

    public Tail(String path) {
        try {
            this.strings = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void flagN(int num, String outputFile) {
        if (num > strings.size()) throw new IllegalArgumentException("Num больше строк файла");
        try (FileWriter writer = new FileWriter(outputFile)) {
            int numberOfSting = strings.size() - num;
            int counter = 1;
            while (counter <= num) {
                writer.write(strings.get(numberOfSting) + '\n');
                numberOfSting++;
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void flagN(int num) {
        if (num > strings.size()) throw new IllegalArgumentException("Num больше строк файла");
            int numberOfSting = strings.size() - num;
            int counter = 1;
            while (counter <= num) {
                System.out.println(strings.get(numberOfSting));
                numberOfSting++;
                counter++;
        }
    }

    public void flagN(String outputFile) {
        int num = 10;
        if (num > strings.size()) throw new IllegalArgumentException("Num больше строк файла");
        try (FileWriter writer = new FileWriter(outputFile)) {
            int numberOfSting = strings.size() - num;
            int counter = 1;
            while (counter <= num) {
                writer.write(strings.get(numberOfSting) + '\n');
                numberOfSting++;
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void flagN() {
        int num = 10;
        if (num > strings.size()) throw new IllegalArgumentException("Num больше строк файла");
            int numberOfSting = strings.size() - num;
            int counter = 1;
            while (counter <= num) {
                System.out.println(strings.get(numberOfSting));
                numberOfSting++;
                counter++;
            }
    }

    private List<Character> readSymbols(String inputFile) {
        List<Character> chars = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File(inputFile)));
            int c;
            while ((c = reader.read()) != -1) {
                chars.add((char) c);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return chars;
    }

    public void flagC(int num, String outputFile, String inputFile) {
        List<Character> chars = readSymbols(inputFile);
        int characters = chars.size();
        if (num > chars.size()) throw new IllegalArgumentException("Num больше количества символов файла");
        try (FileWriter writer = new FileWriter(outputFile)) {
            int numberOfChar = characters - num;
            int counter = 1;
            while (counter <= num) {
                writer.write(chars.get(numberOfChar));
                numberOfChar++;
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void flagC(int num, String inputFile) {
        List<Character> chars = readSymbols(inputFile);
        int characters = chars.size();
        if (num > chars.size()) throw new IllegalArgumentException("Num больше количества символов файла");
        int numberOfChar = characters - num;
            int counter = 1;
            while (counter <= num) {
                System.out.print(chars.get(numberOfChar));
                numberOfChar++;
                counter++;
            }
    }
}
