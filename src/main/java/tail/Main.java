package tail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        launcherTail launcher = new launcherTail(args);
        logicTail logic = new logicTail();
        parserTail parser = new parserTail(args);
        List<String> inputText;
        List<String> outputText;
        if (!parser.isInputFile()) inputText = parser.inputText;
        else inputText = launcher.read();
        if (parser.isProcessSymbols()) outputText = logic.flagC(parser.getNumberOfSymbols(), inputText);
        else outputText = logic.flagN(parser.getNumberOfLines(), inputText);
        launcher.write(outputText);
    }
}