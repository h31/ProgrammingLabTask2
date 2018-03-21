package com.project.UI;

import java.util.regex.Pattern;

public class ArgsParser {

    private boolean packing;
    private String inputName;
    private String outputName;

    public boolean isPacking() {
        return packing;
    }

    public String getInputName() {
        return inputName;
    }

    public String getOutputName() {
        return outputName;
    }

    public void setArgs(boolean packing, String inputName, String outputName) {
        this.packing = packing;
        this.inputName = inputName;
        this.outputName = outputName;
    }
}
