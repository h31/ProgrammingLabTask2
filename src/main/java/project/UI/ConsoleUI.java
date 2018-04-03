package main.java.project.UI;

import main.java.project.UI.Parsers.LibParser;
import main.java.project.UI.Parsers.NativeParser;
import main.java.project.UI.Parsers.Parser;
import main.java.project.logic.codec.Codec;
import main.java.project.logic.codec.Compressor;
import main.java.project.logic.codec.Decompressor;
import main.java.project.logic.files.FileIOHelper;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ConsoleUI {
    private static String receivedCommand;
    private static boolean completed = false;
    private static boolean isTestMode = false;
    private static boolean useNativeParser = true;
    private static Pattern commandPattern =
            Pattern.compile("pack-rle\\s+(-z|-u)?\\s*(-out\\s+.+)?\\s*.+\\..+\\s*");

    public static void create() {

        if (!isTestMode) receivedCommand = new Scanner(System.in).nextLine();

        if (!commandPattern.matcher(receivedCommand).matches()) {
            throw new IllegalArgumentException("Invalid command");
        }

        Parser parser;
        if (useNativeParser) {
            parser = new NativeParser(receivedCommand);
        } else {
            parser = new LibParser(receivedCommand);
        }

        System.out.printf("Start %s file %s\n", parser.isPacking() ? "packing" : "unpacking",
                parser.getInputFileName());
        System.out.printf("New file name = %s\n", parser.getOutputFileName());

        List<String> readFile = new FileIOHelper().read(parser.getInputFileName());
        Codec codec;
        if (parser.isPacking()) {
            codec = new Compressor(readFile);
        } else {
            codec = new Decompressor(readFile);
        }
        codec.start();
        new FileIOHelper().write(parser.getOutputFileName(), codec.getOutputToFile());

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
