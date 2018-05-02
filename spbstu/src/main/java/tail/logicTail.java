package tail;

import java.util.*;

public class logicTail {
    private List<String> result = new ArrayList<>();

    logicTail() {
    }

    public List<String> c(int amountOfSymbols, List<String> linesFromFile) {

        int symbols = 0;
        int i = linesFromFile.size() - 1;
        String Line;
        while (symbols < amountOfSymbols && i >= 0) {
            Line = linesFromFile.get(i);
            int lengthOfLine = Line.length();
            if (lengthOfLine + symbols <= amountOfSymbols) result.add(0, Line);
            else result.add(0, Line.substring(lengthOfLine - amountOfSymbols + symbols));
            symbols += lengthOfLine;
            i--;
        }
        return result;
    }

    public List<String> n(int amountOfLines, List<String> linesFromFile) {
        for (int i = linesFromFile.size() - amountOfLines; i < linesFromFile.size(); i++)
            result.add(linesFromFile.get(i));
        return result;
    }
}
