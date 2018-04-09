package console;

import logic.Grep;

import java.util.List;
import java.util.Scanner;

public class GrepLauncher {

    List<String> result;

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String[] argsConsole = console.nextLine().split("\\s+");
        new GrepLauncher().launch(argsConsole);
    }

    public void launch(String[] args) {

        GrepParser grepParser = new GrepParser(args);
        Grep grep = new Grep(grepParser.isFindOnRegex(), grepParser.isFindExceptRegex(),
                grepParser.isIgnoreCase(), grepParser.getWord(), grepParser.getInputFileName());

        result = grep.findOfFile();
        System.out.println(result);
    }
    public List<String> getResult() {
        return result;
    }
}
