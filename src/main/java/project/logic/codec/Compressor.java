package main.java.project.logic.codec;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Compressor implements Codec {

    private List<String> outputToFile;
    private List<String> fileLines;
    private char escapingCharacter = '&';

    public Compressor(List<String> fileLines) {
        this.fileLines = fileLines;
    }

    @Override
    public void start() {
        List<String> answer = new ArrayList<>();
        Pair<List<String>, List<String>> codecPattern = findArchivePattern(fileLines);
        int indexElement = 0;
        String lastLine = fileLines.get(fileLines.size() - 1);
        for (String line : fileLines) {
            final List<CodecElementStorage> buffer = new ArrayList<>();
            List<String> packingElements = codecPattern.getKey();
            boolean addNewLineSymbol = !line.equals(lastLine);
            if (packingElements.size() == 0) {
                packingElements = codecPattern.getValue();
            }
            for (;indexElement < packingElements.size(); indexElement++) {
                String element = packingElements.get(indexElement);
                line = line.replace(element, element.replaceFirst("\\|", "&|"));
            }
            if (addNewLineSymbol) line += ("\n");
            packOneLine(line, buffer);
            StringBuilder bufferString = new StringBuilder();
            buffer.forEach(bufferString::append);
            answer.add(bufferString.toString());
        }
        this.outputToFile = answer;
    }

    private void packOneLine(String line, List<CodecElementStorage> buffer) {
        char lastChar = line.charAt(0);
        buffer.add(new CodecElementStorage(lastChar));
        for (int index = 1; index < line.length(); index++) {
            final CodecElementStorage lastCodecElementStorage = buffer.get(buffer.size() - 1);
            char symbolOnLine = line.charAt(index);
            if (symbolOnLine == lastChar && symbolOnLine != escapingCharacter) {
                lastCodecElementStorage.inc();
            } else {
                buffer.add(new CodecElementStorage(symbolOnLine));
                lastChar = symbolOnLine;
            }
        }
    }

    @Override
    public List<String> getOutputToFile() {
        return outputToFile;
    }

    @Override
    public void setFile(List<String> fileLines) {
        this.fileLines = fileLines;
    }
}
