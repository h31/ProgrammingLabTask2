package com.project.UI;

import com.project.logic.archiving.Archive;
import com.project.logic.files.Reader;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ConsoleUI {
    private static String receivedCommand;
    private static boolean completed = false;
    private static boolean isTestMode = false;

    public static void create() {
        Pattern commandPattern =
                Pattern.compile("pack-rle\\s+(-z|-u)?\\s*(-out\\s+.+)?\\s*.+\\.(txt|uz)\\s*");
        if (!isTestMode) {
            receivedCommand = new Scanner(System.in).nextLine();
        }
        assert commandPattern.matcher(receivedCommand).matches() : "Invalid command";

        Parser parser = new Parser(receivedCommand);
        Archive.setFile(new Reader(parser.getInputName()).getAnswer());
        Archive.start(parser.getInputName(), parser.getOutputName(), parser.isPacking());
        Reader.write(Archive.getFileName(), Archive.getStreamToFile());
        completed = true;

    }

    public static boolean isCompleted() {
        return completed;
    }

    public static void testMode(String command) {
        isTestMode = true;
        receivedCommand = command;
    }

}
