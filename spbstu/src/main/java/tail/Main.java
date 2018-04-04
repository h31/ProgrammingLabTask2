package tail;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        /*String text = new String(Files.readAllBytes(Paths.get("/Users/molkos/IdeaProjects/ProgrammingLabTask2/spbstu/src/main/java/Text.txt")));
        List<String> lines = Arrays.asList(text.split("\n"));
        logicTail cutter = new logicTail();
        FileWriter writer = new FileWriter("/Users/molkos/IdeaProjects/ProgrammingLabTask2/spbstu/src/main/java/Output.txt");
        writer.write(cutter.c(20, lines).toString());
        writer.close();
       // System.out.println(cutter.c(20, lines));
       */

        Scanner scanner = new Scanner(System.in);
        String cmdLine = scanner.nextLine();
        interfaceTail launcher = new interfaceTail(cmdLine);
        logicTail logic = new logicTail();
        parserTail parser = new parserTail(cmdLine.split(" "));
        List<String> inputText = launcher.read();
        List<String> outputText;
        if (parser.isWorkWithSymbols()) outputText = logic.c(parser.getNumberOfSymbols(), inputText);
        else outputText = logic.n(parser.getNumberOfLines(), inputText);
        launcher.write(outputText);
    }
}
