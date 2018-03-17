package com.project.casing;

import com.project.insides.archiving.Archive;
import com.project.insides.files.Parser;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Receiver {
    private static String receivedCommand;
    private static boolean completed = false;
    private static boolean isTestMode = false;

    public static void create() {
        Pattern commandPattern =
                Pattern.compile("pack-rle\\s+(-z|-u)?\\s*(-out\\s+.+)?\\s*.+\\.(txt|uz)\\s*");
        if (!isTestMode) {
            receivedCommand = new Scanner(System.in).nextLine();
        }
        if (commandPattern.matcher(receivedCommand).matches()) {
            Parser parser = new Parser(receivedCommand);
            Archive.start(parser.getInputName(), parser.getOutputName(), parser.isPacking());
            completed = true;
        } else {
            throw new IllegalArgumentException("Invalid command");
        }
    }

    public static boolean isCompleted() {
        return completed;
    }

    public static void testMode(String command) {
        isTestMode = true;
        receivedCommand = command;
    }

}
