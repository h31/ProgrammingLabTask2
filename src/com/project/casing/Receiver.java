package com.project.casing;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Receiver {

    private static String receivedCommand;
    private static String answer;

    public static void create() {
        Pattern commandPattern =
                Pattern.compile("pack-rle\\s+(-z|-u)?\\s*(-out\\s+.+\\.(txt|uz))?\\s*.+\\.(txt|uz)\\s*");
        listen();
        if (commandPattern.matcher(receivedCommand).matches()) {

        } else {
            answer = null;
        }
    }

    private static void listen() {
        Scanner inputCommand = new Scanner(System.in);
        receivedCommand = inputCommand.nextLine();
    }

    public static String getAnswer() {
        return answer;
    }
}
