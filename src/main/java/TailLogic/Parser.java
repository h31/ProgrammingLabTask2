package TailLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// использовать args4, jcommander
public class Parser {
    private int num;
    private List<String> inputNames;
    private String outputName;
    private boolean isN; // Имя переменной

    public Parser(String command) {
        inputNames = new ArrayList<>();
        String[] commandToParts = command.trim().split("\\s+"); //Один паттерн
        Pattern patternOfFlag = Pattern.compile("(-c\\s+\\d+)|(-n\\s+\\d+)");
        Pattern patternOfOutputName = Pattern.compile("-o\\s+\\w+");
        Pattern patternOfInputName = Pattern.compile("\\s+\\w+");
        Matcher matcherOfFlag = patternOfFlag.matcher(command);
        Matcher matcherOfOutputName = patternOfOutputName.matcher(command);
        Matcher matcherOfInputName = patternOfInputName.matcher(command);


        if (matcherOfFlag.matches()) {
            isN = commandToParts[1].contains("-n");
            num = Integer.parseInt(commandToParts[2]);
            if (matcherOfOutputName.matches()) {
                outputName = commandToParts[4];
                if (matcherOfInputName.matches()) {
                    for (int i = 5; i < commandToParts.length; i++) {
                        inputNames.add(commandToParts[i]);
                    }
                } else inputNames = null;
            } else {
                outputName = null;
                if (matcherOfInputName.matches()) {
                    for (int i = 3; i < commandToParts.length; i++) {
                        inputNames.add(commandToParts[i]);
                    }
                } else inputNames = null;
            }
        } else {
            isN = false;
            num = 10;
            if (matcherOfOutputName.matches()) {
                outputName = commandToParts[2];
                if (matcherOfInputName.matches()) {
                    for (int i = 3; i < commandToParts.length; i++) {
                        inputNames.add(commandToParts[i]);
                    }
                } else inputNames = null;
            } else {
                outputName = null;
                if (matcherOfInputName.matches()) {
                    for (int i = 1; i < commandToParts.length; i++) {
                        inputNames.add(commandToParts[i]);
                    }
                } else inputNames = null;
            }
        }
    }

    public boolean flagIsN() {
        return isN;
    }

    public int getNum() {
        return num;
    }

    public String getOutputName() {
        return outputName;
    }

    public List<String> getInputNames() {
        return inputNames;
    }

}

