package TailLogic;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Tail {
    private List<String> lines;
    private List<Character> characters;

    public Tail(Reader input, boolean isN) throws IOException {
        lines = new ArrayList<>();
        characters = new ArrayList<>();
        BufferedReader reader =  new BufferedReader(input);
            if (isN) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
                reader.close();
            } else {
                int c;
                while ((c = reader.read()) != -1) {
                    characters.add((char) c);
                }
                reader.close();
            }
    }

    public Tail(boolean isN) throws IOException {
        lines = new ArrayList<>();
        characters = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            if (isN) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
                reader.close();
            } else {
                int c;
                while ((c = reader.read()) != -1) {
                    characters.add((char) c);
                }
                reader.close();
            }
    }

    public void flagN(int num, String outputFile) {
        if (num > lines.size()) throw new IllegalArgumentException("Num больше строк файла");
        try (FileWriter writer = new FileWriter(outputFile)) {
            int numberOfSting = lines.size() - num;
            int counter = 1;
            while (counter <= num) {
                writer.write(lines.get(numberOfSting) + '\n');
                numberOfSting++;
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void flagN(int num) {
        if (num > lines.size()) throw new IllegalArgumentException("Num больше строк файла");
        int numberOfSting = lines.size() - num;
        int counter = 1;
        while (counter <= num) {
            System.out.println(lines.get(numberOfSting));
            numberOfSting++;
            counter++;
        }
    }

    public void flagN(int num, String outputFile, String inputFile, boolean inputFileIsOne) {
        if (num > lines.size()) throw new IllegalArgumentException("Num больше строк файла");
        int numberOfSting = lines.size() - num;
        int counter = 1;
        try (FileWriter writer = new FileWriter(outputFile)) {
            if (!inputFileIsOne) writer.write('\n' + inputFile);
            while (counter <= num) {
                writer.write(lines.get(numberOfSting) + '\n');
                numberOfSting++;
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void flagN(int num, String inputFile, boolean inputFileIsOne) {
        if (num > lines.size()) throw new IllegalArgumentException("Num больше строк файла");
        int numberOfString = lines.size() - num;
        int counter = 1;
        if (!inputFileIsOne) System.out.println(inputFile);
        while (counter <= num) {
            System.out.println(lines.get(numberOfString));
            numberOfString++;
            counter++;
        }
    }

    public void flagC(int num, String outputFile, String inputFile, boolean inputFileIsOne) {
        List<Character> chars = characters;
        int characters = chars.size();
        if (num > chars.size()) throw new IllegalArgumentException("Num больше количества символов файла " +
                inputFile);
        try (FileWriter writer = new FileWriter(outputFile)) {
            if (!inputFileIsOne) writer.write('\n' + inputFile);
            int numberOfChar = characters - num - 1;
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

    public void flagC(int num, String inputFile, boolean inputFileIsOne) {
        List<Character> chars = characters;
        int characters = chars.size();
        if (num > chars.size()) throw new IllegalArgumentException("Num больше количества символов файла " +
                inputFile);
        if (!inputFileIsOne) System.out.println('\n' + inputFile);
        int numberOfChar = characters - num - 1;
        int counter = 1;
        while (counter <= num) {
            System.out.print(chars.get(numberOfChar));
            numberOfChar++;
            counter++;
        }
    }

    public void flagC(int num, String outputFile) {
        List<Character> chars = characters;
        int characters = chars.size();
        try (FileWriter writer = new FileWriter(outputFile)) {
            int numberOfChar = characters - num - 1;
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

    public void flagC(int num) {
        List<Character> chars = characters;
        int characters = chars.size();
        int numberOfChar = characters - num - 1;
        int counter = 1;
        while (counter <= num) {
            System.out.print(chars.get(numberOfChar));
            numberOfChar++;
            counter++;
        }
    }
}
