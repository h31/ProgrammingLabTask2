package com.project.insides.files;


import java.util.regex.Pattern;

public class Parser {

    private boolean packing;
    private boolean newNameForOutputFile = false;
    private String outputName;
    private String inputName;


    public Parser(String value) {
        String[] values = value.trim().split("\\s+");
        Pattern dotPattern = Pattern.compile("\\.");
        switch (values.length) {
            case 2: {
                String[] partsFileName = dotPattern.split(values[1]);
                packing = partsFileName[1].equals("txt");
                outputName = partsFileName[0];
                inputName = values[1];
                break;
            }
            case 3: {
                inputName = values[2];
                packing = values[1].contains("-z");
                outputName = dotPattern.split(inputName)[0];
                break;
            }
            case 4: {
                inputName = values[3];
                String[] partsFileName = dotPattern.split(values[3]);
                newNameForOutputFile = true;
                packing = partsFileName[1].equals("txt");
                outputName = values[2];
                break;
            }
            case 5: {
                newNameForOutputFile = true;
                packing = values[1].contains("-z");
                inputName = values[4];
                outputName = values[3];
                break;
            }
            default: throw new IllegalArgumentException("Unknown command");
        }
    }

    public boolean isPacking() {
        return packing;
    }

    public boolean isNewNameForOutputFile() {
        return newNameForOutputFile;
    }

    public String getInputName() {
        return inputName;
    }

    public String getOutputName() {
        return outputName;
    }
}
