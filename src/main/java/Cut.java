import java.util.ArrayList;
import java.util.List;

public class Cut {

    public List<String> cutter(String text, int n, int k, boolean spaces) {
        List<String> newText = new ArrayList<>();
        String textWithOrWithoutSpaces = "";
        if (spaces) {
            textWithOrWithoutSpaces = text;
        } else {
            textWithOrWithoutSpaces = text.replaceAll(" ", "");
        }
        for (String lines : textWithOrWithoutSpaces.split("\n")) {
            if (k > lines.length()) {
                k = lines.length();
            }
            String newLine = lines.substring(n, k);
            newText.add(newLine);
        }
        return newText;
    }
}