package tail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args, Scanner input) throws IOException {
        launcherTail launcher = new launcherTail(args);
        logicTail logic = new logicTail();
        parserTail parser = new parserTail(args);
        List<String> inputText = new ArrayList<>();
        List<String> outputText;
        if (!parser.isInputFile())
            while (input.hasNextLine())
                inputText.add(input.nextLine());
        else inputText = launcher.read();
        if (parser.isWorkWithSymbols()) outputText = logic.c(parser.getNumberOfSymbols(), inputText);
        else outputText = logic.n(parser.getNumberOfLines(), inputText);
        launcher.write(outputText);
    }
}
