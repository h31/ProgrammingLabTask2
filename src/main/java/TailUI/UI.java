package TailUI;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import TailLogic.Parser;
import TailLogic.Tail;

public class UI {
    public static void start() throws IOException {
        String command = new Scanner(System.in).nextLine();
        Pattern pattern = Pattern.compile("tail\\s+((-c\\s+\\d+)|(-n\\s+\\d+))?(-o\\s+\\w+)?(\\s+\\w+)*");
        Matcher matcher = pattern.matcher(command);
        if (matcher.matches()) {
            Parser parser = new Parser(command);
            if (parser.getInputNames() != null) {
                for (int i = 0; i < parser.getInputNames().size(); i++) {
                    boolean oneFile = parser.getInputNames().size() <= 1;
                    Tail tail = new Tail(Tail.TypeOfInput.File, parser.flagIsN(), parser.getInputNames().get(i));
                    tail.selectText(parser.flagIsN(), parser.getOutputName(), parser.getNum(),
                            oneFile, parser.getInputNames().get(i));
                }
            }
            if (parser.getInputNames() == null) {
                Tail tail = new Tail(Tail.TypeOfInput.Console, parser.flagIsN(), null);
                tail.selectText(parser.flagIsN(), parser.getOutputName(), parser.getNum(),
                        true,null);
            }
        }
    }
}

