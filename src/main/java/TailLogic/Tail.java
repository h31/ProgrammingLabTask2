package TailLogic;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Tail {
    private List<String> lines;
    private List<Character> characters;

    public enum TypeOfInput {
        Console,
        File
    }

    public Tail(TypeOfInput type, boolean isN, String inputFile) {
        try {
            if (type == TypeOfInput.Console) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                if (isN) {
                    lines = new ArrayList<>();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        lines.add(line);
                    }
                } else {
                    characters = new ArrayList<>();
                    int c;
                    while ((c = reader.read()) != -1) {
                        characters.add((char) c);
                    }
                }
                reader.close();
            }
            if (type == TypeOfInput.File) {
                if (isN) {
                    lines = Files.readAllLines(Paths.get(inputFile));
                } else {
                    BufferedReader reader = new BufferedReader(new FileReader(new File(inputFile)));
                    characters = new ArrayList<>();
                    int c;
                    while ((c = reader.read()) != -1) {
                        characters.add((char) c);
                    }
                    reader.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void selectText(boolean queryValue, String outputFileName, int num, boolean oneFile, String inputFileName) {
        if (outputFileName != null) {
            try {
                FileWriter writer = new FileWriter(outputFileName, true);
                if (queryValue) {
                    if (num > lines.size()) throw new IllegalArgumentException("Num больше количества строк файла");
                    List<String> newLines = lines.subList(lines.size() - num, lines.size());
                    if (!oneFile) newLines.add(0, inputFileName);
                    Files.write(Paths.get(outputFileName), newLines,
                            StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                } else {
                    if (num > characters.size())
                        throw new IllegalArgumentException("Num больше количества символов файла");
                    List<Character> newCharacters = characters.subList(characters.size() - num - 1
                            , characters.size());
                    if (!oneFile) writer.write(inputFileName + '\n');
                    StringBuilder sb = new StringBuilder();
                    newCharacters.forEach(sb::append);
                    writer.write(sb.toString());
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (!oneFile) System.out.println(inputFileName);
            if (queryValue) {
                if (num > lines.size()) throw new IllegalArgumentException("Num больше количества строк файла");
                int numberOfString = lines.size() - num;
                int counter = 1;
                while (counter <= num) {
                    System.out.println(lines.get(numberOfString));
                    numberOfString++;
                    counter++;
                }
            } else {
                if (num > characters.size())
                    throw new IllegalArgumentException("Num больше количества символов файла");
                int numberOfChar = characters.size() - num - 1;
                int counter = 1;
                while (counter <= num) {
                    System.out.print(characters.get(numberOfChar));
                    numberOfChar++;
                    counter++;
                }
            }
        }
    }

//    public void writers(String output, int num) {
//        try {
//            List<String> newLines = lines.subList(lines.size() - num, lines.size());
//            Files.write(Paths.get(output), newLines,
//                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
