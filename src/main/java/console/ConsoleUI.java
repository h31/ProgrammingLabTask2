package console;

import logic.Grep;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleUI {

        private String command;
        private boolean isTest;
        public void start() {
            Pattern commandPattern = Pattern.compile("grep\\s+(-r|-i|-v)?\\s+.+\\s+.+\\s*");
            Matcher matcher = commandPattern.matcher(command);
            if (matcher.matches()) {
                GrepParser grepParser = new GrepParser(command);
                Grep grep = new Grep(grepParser.getInputFileName());
                if (command.contains("-i")) grep.setIgnoreCase(true);
                if (command.contains("-r") && (!isTest)) System.out.println(grep.findOnRegex(grepParser.getFindOnRegex()));
                if (command.contains("-v") && (!isTest)) System.out.println(grep.findExceptRegex(grepParser.getFindExceptRegex()));
            } else {
                System.out.println("Неизвестная команда");
            }
        }

        public void setCommandOnConsole() {
            command = new Scanner(System.in).nextLine();
            isTest = false;
        }

        public void setCommandOnTest(String commandForTest) {
            command = commandForTest;
            isTest = true;
        }
    }

