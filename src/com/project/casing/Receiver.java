package com.project.casing;

import com.project.insides.archiving.Archive;
import com.project.insides.files.Parser;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Receiver {
    private static String receivedCommand;
    private static String answer;
    private static boolean testMode = false;

    public static void create() {
        Pattern commandPattern =
                Pattern.compile("pack-rle\\s+(-z|-u)?\\s*(-out\\s+.+)?\\s*.+\\.(txt|uz)\\s*");
        if (!testMode) {
            commandListener();
        }
        if (commandPattern.matcher(receivedCommand).matches()) {
            Parser parser = new Parser(receivedCommand);
            Archive.start(parser.getInputName(), parser.getOutputName(), parser.isPacking());
            answer = "completed";
        } else {
            throw new IllegalArgumentException("Invalid command");
        }
    }

    private static void commandListener() {
        final Scanner inputCommand = new Scanner(System.in);
        receivedCommand = inputCommand.nextLine();
    }

    public static String getAnswer() {
        return answer;
    }

    public static void testMode(String command) {
        testMode = true;
        receivedCommand = command;
    }

}
