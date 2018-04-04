package tail;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class interfaceTail {
    private parserTail parser;

    interfaceTail(String cmdLine) {
        String[] args = cmdLine.split(" ");
        this.parser = new parserTail(args);
    }

    public List<String> read() throws FileNotFoundException {
        List<String> listOfInputs = parser.getInputFileName();
        FileReader reader;
        Scanner scanner;
        List<String> text = new ArrayList<>();
        for (String listOfInput : listOfInputs) {
            reader = new FileReader(listOfInput);
            scanner = new Scanner(reader);
            while (scanner.hasNextLine())
                text.add(scanner.nextLine());
        }
        return text;
    }

    public void write(List<String> text) throws IOException {
        FileWriter writer = new FileWriter(parser.getOutputFileName());
        for (String aText : text)
            writer.write(aText);
    }
}
