package tail;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class launcherTail {
    private parserTail parser;

    launcherTail(String[] args) {
        this.parser = new parserTail(args);
    }

    public List<String> read() throws IOException {
        Scanner scanner = null;
        List<String> text = new ArrayList<>();
        List<String> listOfInputs = parser.getInputFileName();
        FileReader reader = null;
        for (String listOfInput : listOfInputs) {
            reader = new FileReader(listOfInput);
            scanner = new Scanner(reader);
            while (scanner.hasNextLine())
                text.add(scanner.nextLine());
        }
        reader.close();
        scanner.close();
        return text;
    }

    public void write(List<String> text) throws IOException {
        if (parser.isOutputFile()) {
            FileWriter writer = new FileWriter(parser.getOutputFileName());
            for (String aText : text)
                writer.write(aText + "\n");
            writer.close();
        } else {
            for (String aText : text)
                System.out.println(aText + "\n");
        }
    }
}
