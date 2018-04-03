package com.project.UI.Parsers;

import java.util.regex.Pattern;

public class NativeParser implements Parser {
    private boolean packing;
    private String inputFileName;
    private String outputName;

    public NativeParser(String command) {
        String[] commandParts = command.trim().split("\\s+");
        Pattern dotPattern = Pattern.compile("\\.");
        switch (commandParts.length) {
            case 2: {
                inputFileName = commandParts[1];
                String[] partsFileName = dotPattern.split(inputFileName);
                packing = !partsFileName[1].equals("uz");
                break;
            }
            case 3: {
                inputFileName = commandParts[2];
                outputName = dotPattern.split(inputFileName)[0];
                packing = commandParts[1].contains("-z");
                break;
            }
            case 4: {
                inputFileName = commandParts[3];
                outputName = commandParts[2];
                String[] partsFileName = dotPattern.split(inputFileName);
                packing = !partsFileName[1].equals("uz");
                break;
            }
            case 5: {
                packing = commandParts[1].contains("-z");
                inputFileName = commandParts[4];
                outputName = commandParts[3];
                break;
            }
            default:
                throw new IllegalArgumentException("Unknown command");
        }
    }

    @Override
    public boolean isPacking() {
        return packing;
    }

    @Override
    public String getInputFileName() {
        return inputFileName;
    }

    @Override
    public String getOutputFileName() {
        return createOutputFileName(outputName, inputFileName);
    }
}
