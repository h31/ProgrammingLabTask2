package TailUI;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UI {
    public static void start() {
        String command = new Scanner(System.in).nextLine();
        Pattern pattern = Pattern.compile("tail\\s+((-c\\s+\\d+)|(-n\\s+\\d+))?(-o\\s+\\w+)?(\\s+\\w+)*");
        Matcher matcher = pattern.matcher(command);
    }
}

