
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crep {
    private CrepReader reader;
    File fileName = new File("input.txt");
    BufferedReader readfile;

    {
        try {
            readfile = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("Файл не был найден!!");
        }
    }

    public List<String> word(String word) throws IOException {
        List<String> output = new ArrayList<>() {
        };
        String line;
        while ((line = readfile.readLine()) != null) {
            if (line.contains(word)) {
                output.add(line);
            }
        }
        return output;
    }

    public List<String> regex(String r) throws IOException {
        List<String> output = new ArrayList<>() {};
        String line;
        Pattern regex = Pattern.compile(r);
        while ((line = readfile.readLine()) != null) {
            Matcher answer = regex.matcher(line);
            if (answer.matches()) output.add(line);
        }
        return output;

    }

    public List<String> withoutRegex(String pattern) throws IOException {
        List<String> output = new ArrayList<>() {};
        String line;
        Pattern regex = Pattern.compile(pattern);
        while ((line = readfile.readLine()) != null) {
            Matcher answer = regex.matcher(line);
            if (!answer.matches()) output.add(line);
        }
        return output;
    }
}

