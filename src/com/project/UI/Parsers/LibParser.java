package com.project.UI.Parsers;


import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

public class LibParser implements Parser {

    public LibParser(String command) {
        String[] commandParts = command.split("\\s+");
        JCommander.newBuilder().addObject(this).build().parse(commandParts);
    }

    @Parameter
    private List<String> parameters = new ArrayList<>();

    @Parameter(names = "-z", description = "Packing file")
    private boolean packing = false;

    @Parameter(names = "-u", description = "Unpacking file")
    private boolean unpacking = false;

    @Parameter(names = "-out", description = "Output file name")
    private String outputName = "";

    @Override
    public boolean isPacking() {
        boolean argumentSpecified = packing ^ unpacking;
        if (!argumentSpecified) {
            return !getInputFileName().split("\\.")[1].equals("uz");
        } else {
            return packing;
        }
    }

    @Override
    public String getOutputFileName() {
        if (outputName == null || outputName.isEmpty()) {
            outputName = createOutputFileName(outputName, parameters.get(1));
        }
        return outputName;
    }

    @Override
    public String getInputFileName() {
        return parameters.get(1);
    }
}
