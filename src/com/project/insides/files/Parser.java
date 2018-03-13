package com.project.insides.files;

public class Parser {

    private boolean packing;
    private boolean newNameForOutputFile = false;
    private String outputName;
    private String inputName;


    public Parser(String value) {
        String[] values = value.trim().split("\\s+");
        switch (values.length) {
            case 2: {
                String[] partsFileName = values[1].split("\\.");
                packing = partsFileName[1].equals("txt");
                outputName = partsFileName[0];
                inputName = values[1];
                break;
            }
            case 3: {
                inputName = values[2];
                packing = values[1].contains("-z");
                outputName = inputName.split("\\.")[0];
                break;
            }
            case 4: {
                inputName = values[3];
                String[] partsFileName = values[3].split("\\.");
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
