package TailUI;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import TailLogic.Parser;
import TailLogic.Tail;

public class UI {
    public static void start() {
        String command = new Scanner(System.in).nextLine();
        Pattern pattern = Pattern.compile("tail\\s+((-c\\s+\\d+)|(-n\\s+\\d+))?(-o\\s+\\w+)?(\\s+\\w+)*");
        Matcher matcher = pattern.matcher(command);
        if (matcher.matches()) {
            Parser parser = new Parser(command);
            if (parser.getInputName() != null) { //Входной файл один
                if (!parser.getOutputName().equals("Нет") && parser.flagIsC()) { //Есть выходной файл и флаг С
                    Tail tail = new Tail(parser.getInputName());
                    tail.flagC(parser.getNum(), parser.getOutputName(), parser.getInputName(), true);
                }
                if (!parser.getOutputName().equals("Нет") && !parser.flagIsC()) { //Есть выходной файл и флаг N
                    Tail tail = new Tail(parser.getInputName());
                    tail.flagN(parser.getNum(), parser.getOutputName());
                }
                if (parser.getOutputName().equals("Нет") && parser.flagIsC()) { //Нет выходного файла и флаг С
                    Tail tail = new Tail(parser.getInputName());
                    tail.flagC(parser.getNum(), parser.getInputName(), true);
                }
                if (parser.getOutputName().equals("Нет") && !parser.flagIsC()) { //Нет выходного файла и флаг N
                    Tail tail = new Tail(parser.getInputName());
                    tail.flagN(parser.getNum());
                }
            }

            if (parser.getInputName() == null && !parser.getInputNames().get(0).equals("Нет")) { // Входной файл не один
                for (int i = 0; i < parser.getInputNames().size(); i++) {
                    if (!parser.getOutputName().equals("Нет") && !parser.flagIsC()) { //Есть выходной файл и флаг N
                        Tail tail = new Tail(parser.getInputNames().get(i));
                        tail.flagN(parser.getNum(), parser.getOutputName());
                    }
                    if (parser.getOutputName().equals("Нет") && !parser.flagIsC()) { //Нет выходного файла и флаг N
                        Tail tail = new Tail(parser.getInputNames().get(i));
                        tail.flagN(parser.getNum());
                    }
                    if (!parser.getOutputName().equals("Нет") && parser.flagIsC()) { //Есть выходной файл и флаг С
                        Tail tail = new Tail(parser.getInputNames().get(i));
                        tail.flagC(parser.getNum(), parser.getOutputName(), parser.getInputNames().get(i), false);
                    }
                    if (!parser.getOutputName().equals("Нет") && parser.flagIsC()) { //Нет выходного файла и флаг С
                        Tail tail = new Tail(parser.getInputNames().get(i));
                        tail.flagC(parser.getNum(), parser.getInputNames().get(i), false);
                    }
                }
            }
        }
    }
}

