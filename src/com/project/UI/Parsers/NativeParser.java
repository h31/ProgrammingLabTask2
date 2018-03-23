package com.project.UI.Parsers;

import java.util.regex.Pattern;

public class NativeParser implements Parser {
    private boolean packing;
    private String inputName;
    private String outputName;

    public NativeParser(String value) {
        String[] values = value.trim().split("\\s+");
        Pattern dotPattern = Pattern.compile("\\.");
        switch (values.length) {
            case 2: {
                inputName = values[1];
                String[] partsFileName = dotPattern.split(inputName);
                outputName = partsFileName[0] + "-copy";
                packing = partsFileName[1].equals("txt");
                break;
            }
            case 3: {
                inputName = values[2];
                outputName = dotPattern.split(inputName)[0];
                packing = values[1].contains("-z");
                break;
            }
            case 4: {
                inputName = values[3];
                outputName = values[2];
                String[] partsFileName = dotPattern.split(inputName);
                packing = partsFileName[1].equals("txt");
                break;
            }
            case 5: {
                packing = values[1].contains("-z");
                inputName = values[4];
                outputName = values[3];
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
        return outputName;
    }
}
