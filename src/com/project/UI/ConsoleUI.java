package com.project.UI;

import com.project.UI.Parsers.DefaultParser;
import com.project.UI.Parsers.LibParser;
import com.project.logic.archiving.Archive;
import com.project.logic.files.Reader;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ConsoleUI {
    private static String receivedCommand;
    private static boolean completed = false;
    private static boolean isTestMode = false;
    private static boolean defaultParser = true;

    public static void create() {
        Pattern commandPattern =
                Pattern.compile("pack-rle\\s+(-z|-u)?\\s*(-out\\s+.+)?\\s*.+\\.(txt|uz)\\s*");
        if (!isTestMode) receivedCommand = new Scanner(System.in).nextLine();

        assert commandPattern.matcher(receivedCommand).matches() : "Invalid command";

        ArgsParser parser = new ArgsParser();
        if (defaultParser) {
            DefaultParser defaultParser = new DefaultParser(receivedCommand);
            parser.setArgs(defaultParser.isPacking(), defaultParser.getInputName(), defaultParser.getOutputName());
        } else {
            LibParser libParser = new LibParser(receivedCommand.split("\\s+"));
            parser.setArgs(libParser.isPacking(), libParser.getParameters().get(1), libParser.getOutputName());
        }

        Archive.setFile(new Reader(parser.getInputName()).getAnswer());
        Archive.start(parser.getInputName(), parser.getOutputName(), parser.isPacking());

        Reader.write(Archive.getFileName(), Archive.getStreamToFile());

        completed = true;
    }

    public static boolean isCompleted() {
        return completed;
    }

    public static void setDefaultParser(boolean isDefaultParser) {
        defaultParser = isDefaultParser;
    }

    public static void testMode(String command) {
        isTestMode = true;
        receivedCommand = command;
    }
}
