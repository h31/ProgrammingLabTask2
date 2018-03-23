package com.project.UI;

import com.project.UI.Parsers.LibParser;
import com.project.UI.Parsers.NativeParser;
import com.project.UI.Parsers.Parser;
import com.project.logic.archiving.Archive;
import com.project.logic.files.Reader;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ConsoleUI {
    private static String receivedCommand;
    private static boolean completed = false;
    private static boolean isTestMode = false;
    private static boolean useNativeParser = true;
    private static Pattern commandPattern =
            Pattern.compile("pack-rle\\s+(-z|-u)?\\s*(-out\\s+.+)?\\s*.+\\.(txt|uz)\\s*");

    public static void create() {

        if (!isTestMode) receivedCommand = new Scanner(System.in).nextLine();

        if (!commandPattern.matcher(receivedCommand).matches()) {
            throw new IllegalArgumentException("Invalid command");
        }

        Parser parser;
        if (useNativeParser) {
            parser = new NativeParser(receivedCommand);
        } else {
            parser = new LibParser(receivedCommand.split("\\s+"));
        }

        System.out.printf("Start %s file %s\n", parser.isPacking() ? "packing" : "unpacking",
                parser.getInputFileName());
        System.out.printf("New file name = %s\n", parser.getOutputFileName());

        Archive.setFile(new Reader(parser.getInputFileName()).getAnswer());
        Archive.start(parser.getOutputFileName(), parser.isPacking());

        Reader.write(Archive.getFileName(), Archive.getStreamToFile());

        System.out.printf("%s end\n\n", parser.isPacking() ? "Packing" : "Unpacking");

        completed = true;
    }

    public static boolean isCompleted() {
        return completed;
    }

    public static void setUseNativeParser(boolean use) {
        useNativeParser = use;
    }

    public static void testMode(String command) {
        isTestMode = true;
        receivedCommand = command;
    }
}
