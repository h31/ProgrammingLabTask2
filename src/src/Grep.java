import java.io.*;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {
    private static void exists(String fileName) throws FileNotFoundException { // проверка существования файла
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException(file.getName());
        }
    }

    public String word(String[] args) throws IOException {
        String fileName = args[args.length - 1];
        File file = new File(fileName);
        exists(fileName);
        StringBuilder Lines = new StringBuilder();
        String word = args[args.length - 2];
        String pattern = "";

        GrepReader crepChecker = new GrepReader(args);
        if (crepChecker.getI()) {// игнорирование регистра слов
            word = word.toLowerCase();
        }
        if (crepChecker.getR()) { // вместо слова задается регулярное выражение для поиска
            pattern = word;
        }

        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = in.readLine()) != null) {
                if (crepChecker.getI()) { // игнорирование регистра слов
                    line = line.toLowerCase();
                }
                if (!crepChecker.getR()) { // (regex)
                    if (line.contains(word) != crepChecker.getV()) {
                        Lines.append(line).append("\n");
                    }
                }
            }
        }
        return Lines.toString();
    }

}