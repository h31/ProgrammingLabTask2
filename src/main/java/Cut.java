import java.util.ArrayList;
import java.util.List;

public class Cut {

    public String cutter(String text, int n, int k, boolean spaces) {
        List<String> newText = new ArrayList<>();
        String textWithOrWithoutSpaces;
        String result;
        String[] line;
        if (spaces) {
            textWithOrWithoutSpaces = text;
        } else {
            textWithOrWithoutSpaces = text.replaceAll(" ", "");
        }
        line = textWithOrWithoutSpaces.split("\n");
        for (int i = 0; i < line.length; i++) {
            if (k > line[i].length()) {
                k = line[i].length();
            }
            String newLine = line[i].substring(n, k);
            newText.add(newLine);
        }
        result = newText.toString().replaceAll("\\[|\\]", "");
        return result;
    }
}