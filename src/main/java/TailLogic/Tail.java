package TailLogic;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Tail {
    private List<String> lines;
    private List<Character> characters;
    public enum TypeOfInput {
        Console,
        File
    }

    public Tail(TypeOfInput type, boolean isN, String inputFile) throws IOException {
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
            BufferedReader reader =  new BufferedReader(new FileReader(new File(inputFile)));
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
    }

    public void selectText(boolean queryValue, String outputFile, int num, boolean oneFile, String inputFile) {
        if (!(outputFile == null)) {
            try {
                FileWriter writer = new FileWriter(outputFile, true);
                if (!oneFile) writer.write(inputFile + '\n');
                if (queryValue) {
                    if (num > lines.size()) throw new IllegalArgumentException("Num больше количества строк файла");
                    int numberOfString = lines.size() - num;
                    int counter = 1;
                    while (counter <= num) {
                        writer.write(lines.get(numberOfString) + '\n');
                        numberOfString++;
                        counter++;
                    }
                }
                else {
                    if (num > characters.size())
                        throw new IllegalArgumentException("Num больше количества символов файла");
                    int numberOfChar = characters.size() - num - 1;
                    int counter = 1;
                    while (counter <= num) {
                        writer.write(characters.get(numberOfChar));
                        numberOfChar++;
                        counter++;
                    }
                    writer.write('\n');
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (!oneFile) System.out.println(inputFile);
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
}
