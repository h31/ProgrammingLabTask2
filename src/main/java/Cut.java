import java.util.ArrayList;
import java.util.List;

public class Cut {

    public List<String> cutter(String text, int n, int k, boolean spaces) {
        List<String> newText = new ArrayList<>();
        String textWithOrWithoutSpaces;
        if (spaces) {
            textWithOrWithoutSpaces = text;
        } else {
            textWithOrWithoutSpaces = text.replaceAll(" ", "");
        }
        for (String lines : textWithOrWithoutSpaces.split("\n")) {
            StringBuilder newLine = new StringBuilder();
            int length = lines.length();
            if (n != 0) {
                if (k >= length) {
                    int begin = n;
                    length -= n;
                    while (length != 0) {
                        newLine.append(lines.charAt(begin));
                        begin++;
                        length--;
                    }
                } else {
                    length = k - n;
                    int begin = n;
                    while (length != 0) {
                        newLine.append(lines.charAt(begin));
                        begin++;
                        length--;
                    }
                }
            } else {
                if (k >= length) {
                    newLine.append(lines);
                } else {
                    length = k;
                    int begin = n;
                    while (length != 0) {
                        newLine.append(lines.charAt(begin));
                        begin++;
                        length--;
                    }
                }
            }
            newText.add(newLine.toString());
        }
        return newText;
    }
}