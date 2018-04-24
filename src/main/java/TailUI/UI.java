package TailUI;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import TailLogic.Parser;
import TailLogic.Tail;

public class UI {
    public static void start() {
        String command = new Scanner(System.in).nextLine();
        Pattern pattern =
                Pattern.compile("tail\\s+((-c\\s+\\d+)|(-n\\s+\\d+))?(\\s)*(-o\\s+\\w+)?(\\s)*(\\s+\\w+)*(\\s)*");
        Matcher matcher = pattern.matcher(command);
        if (matcher.matches()) {
            Parser parser = new Parser(command);
            if (parser.getInputNames().size() > 0) {
                List<String> names = parser.getInputNames();
                for (String name : names) {
                    boolean oneFile = parser.getInputNames().size() <= 1;
                    Tail tail = new Tail(Tail.TypeOfInput.File, parser.flagIsN(), name);
                    tail.selectText(parser.flagIsN(), parser.getOutputName(), parser.getNum(),
                            oneFile, name);
                }
            }
            if (parser.getInputNames() == null) {
                Tail tail = new Tail(Tail.TypeOfInput.Console, parser.flagIsN(), null);
                tail.selectText(parser.flagIsN(), parser.getOutputName(), parser.getNum(),
                        true, null);
            }
        } else throw new IllegalArgumentException("Введена неправильная команда, попробуйте еще раз");
    }
}

