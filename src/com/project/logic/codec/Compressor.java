package com.project.logic.codec;

import java.util.ArrayList;
import java.util.List;

public class Compressor implements Codec {

    private String outputStringToFile;
    private List<String> fileLines;
    private char escapingCharacter = '&';

    public Compressor(List<String> fileLines) {
        this.fileLines = fileLines;
    }

    @Override
    public void start() {
        final List<CodecElement> buffer = new ArrayList<>();
        for (String line : fileLines) {
            List<String> packingElements = findArchivePattern(line, true);
            if (packingElements.size() == 0) packingElements = findArchivePattern(line, false);
            for (String element : packingElements) {
                line = line.replace(element, element.replaceFirst("\\|", "&|"));
            }

            packOneLine(line, buffer);
        }

        StringBuilder answer = new StringBuilder();
        buffer.forEach(answer::append);

        this.outputStringToFile = answer.toString();
    }

    private void packOneLine(String line, List<CodecElement> buffer) {
        char lastChar = line.charAt(0);
        buffer.add(new CodecElement(lastChar));
        for (int index = 1; index < line.length(); index++) {
            final CodecElement lastCodecElement = buffer.get(buffer.size() - 1);
            char symbolOnLine = line.charAt(index);
            if (symbolOnLine == lastChar && symbolOnLine != escapingCharacter) {
                lastCodecElement.inc();
            } else {
                buffer.add(new CodecElement(symbolOnLine));
                lastChar = symbolOnLine;
            }
        }
    }

    @Override
    public String getOutputStringToFile() {
        return outputStringToFile;
    }

    @Override
    public void setFile(List<String> fileLines) {
        this.fileLines = fileLines;
    }
}
