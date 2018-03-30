package com.project.UI.Parsers;

import java.util.regex.Pattern;

public class NativeParser implements Parser {
    private boolean packing;
    private String inputName;
    private String outputName;

    public NativeParser(String command) {
        String[] commandParts = command.trim().split("\\s+");
        Pattern dotPattern = Pattern.compile("\\.");
        switch (commandParts.length) {
            case 2: {
                inputName = commandParts[1];
                String[] partsFileName = dotPattern.split(inputName);
                outputName = partsFileName[0] + "-copy";
                packing = !partsFileName[1].equals("uz");
                break;
            }
            case 3: {
                inputName = commandParts[2];
                outputName = dotPattern.split(inputName)[0];
                packing = commandParts[1].contains("-z");
                break;
            }
            case 4: {
                inputName = commandParts[3];
                outputName = commandParts[2];
                String[] partsFileName = dotPattern.split(inputName);
                packing = !partsFileName[1].equals("uz");
                break;
            }
            case 5: {
                packing = commandParts[1].contains("-z");
                inputName = commandParts[4];
                outputName = commandParts[3];
                break;
            }
            default: throw new IllegalArgumentException("Unknown command");
        }
    }

    @Override
    public boolean isPacking() {
        return packing;
    }

    @Override
    public String getInputFileName() {
        return inputName;
    }

    @Override
    public String getOutputFileName() {
        return outputName + (packing ? ".uz" : ".txt");
    }
}
