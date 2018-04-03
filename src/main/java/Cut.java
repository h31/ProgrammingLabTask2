import java.util.ArrayList;
import java.util.List;

public class Cut {

    public List<String> cutterC(String text, int n, int k) {
        List<String> newText = new ArrayList<>();
        StringBuilder newLine = new StringBuilder();
        for (String lines : text.split("\n")) {
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
            newLine.delete(0, newLine.length());
        }
        return newText;
    }

    public List<String> cutterW(String text, int n, int k) {
        List<String> newText = new ArrayList<>();
        StringBuilder newLine = new StringBuilder();
        String textWithoutSpaces = text.replaceAll(" ", "");
        for (String lines : textWithoutSpaces.split("\n")) {
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
            newLine.delete(0, newLine.length());
        }
        return newText;
    }
}