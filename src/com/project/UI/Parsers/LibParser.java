package com.project.UI.Parsers;


import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

public class LibParser {

    public LibParser(String[] command) {
        JCommander.newBuilder().addObject(this).build().parse(command);
    }

    @Parameter
    private List<String> parameters = new ArrayList<>();

    @Parameter(names = "-z", description = "Packing file")
    private boolean packing = false;

    @Parameter(names = "-u", description = "Unpacking file")
    private boolean unpacking = false;

    @Parameter(names = "-out", description = "Output file name")
    private String outputName = "";

    public boolean isPacking() {
        return (packing ^ unpacking) == true ? packing : parameters.get(1).split("\\.")[1].equals("txt");
    }

    public String getOutputName() {
        return outputName.isEmpty() ? parameters.get(1) : outputName;
    }

    public List<String> getParameters() {
        return parameters;
    }
}
