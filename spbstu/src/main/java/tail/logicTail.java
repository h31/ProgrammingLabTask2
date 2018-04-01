package tail;

import java.util.*;

public class logicTail {

    public List<String> c(int amountOfSymbols, List<String> linesFromFile) {
        List<String> result = new ArrayList<>();
        int symbols = 0;
        int i = linesFromFile.size() - 1;
        while (symbols < amountOfSymbols) {
            String Line = linesFromFile.get(i);
            int lengthOfLine = Line.length();
            if (lengthOfLine + symbols <= amountOfSymbols) result.add(0, Line);
            else result.add(0, Line.substring(lengthOfLine - amountOfSymbols + symbols));
            symbols += lengthOfLine;
            i--;
        }
        return result;
    }

    public List<String> n(int amountOfLines, List<String> linesFromFile) {
        List<String> result = new ArrayList<String>();
        for (int i = linesFromFile.size() - amountOfLines; i < linesFromFile.size(); i++)
            result.add(linesFromFile.get(i));
        return result;
    }
}
