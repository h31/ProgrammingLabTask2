package TailLogic;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private int num;
    private ArrayList<String> inputName;
    private String outputName;
    private boolean isC;

    public Parser(String command) {
        String[] commandToParts = command.trim().split("\\s+");
        Pattern patternOfFlag = Pattern.compile("(-c\\s+\\d+)|(-n\\s+\\d+)");
        Pattern patternOfOutputName = Pattern.compile("-o\\s+\\w+");
        Pattern patternOfInputName = Pattern.compile("\\s+\\w+");
        Matcher matcherOfFlag = patternOfFlag.matcher(command);
        Matcher matcherOfOutputName = patternOfOutputName.matcher(command);
        Matcher matcherOfInputName = patternOfInputName.matcher(command);

        if (matcherOfFlag.matches()) {
            isC = commandToParts[1].contains("-c");
            num = Integer.parseInt(commandToParts[2]);
            if (matcherOfOutputName.matches()) {
                outputName = commandToParts[4];
                if (matcherOfInputName.matches()) {
                    for (int i = 5; i < commandToParts.length; i++) {
                        inputName.add(commandToParts[i]);
                    }
                } else inputName.add("Отсутствует");
            } else {
                outputName = "Отсутствует";
                if (matcherOfInputName.matches()) {
                    for (int i = 3; i < commandToParts.length; i++) {
                        inputName.add(commandToParts[i]);
                    }
                } else inputName.add("Отсутствует");
            }
        } else {
            isC = false;
            num = 10;
            if (matcherOfOutputName.matches()) {
                outputName = commandToParts[2];
                if (matcherOfInputName.matches()) {
                    for (int i = 3; i < commandToParts.length; i++) {
                        inputName.add(commandToParts[i]);
                    }
                } else inputName.add("Отсутствует");
            } else {
                outputName = "Отсутствует";
                if (matcherOfInputName.matches()) {
                    for (int i = 1; i < commandToParts.length; i++) {
                        inputName.add(commandToParts[i]);
                    }
                } else inputName.add("Отсутствует");
            }
        }
    }

    public boolean flagIsC() {
        return isC;
    }

    public int getNum() {
        return num;
    }

    public String getOutputName() {
        return outputName;
    }

    public ArrayList<String> getInputName() {
        return inputName;
    }

}

