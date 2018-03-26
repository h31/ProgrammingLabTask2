import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleUI {
    public static void start() {
        String command = new Scanner(System.in).nextLine();
        Pattern commandPattern = Pattern.compile("grep\\s+(-r|-i|-v)?\\s+.+\\s+\\w+\\.txt\\s*");
        Matcher matcher = commandPattern.matcher(command);
        if (matcher.matches()) {
            GrepParser grepParser = new GrepParser(command);
            Grep grep = new Grep(grepParser.getInputFileName());
            if (command.contains("-r")) System.out.println(grep.findOnRegex(grepParser.getFindOnRegex()));
            if (command.contains("-i")) System.out.println(grep.ignoreCase(grepParser.getIgnoreCase()));
            if (command.contains("-v")) System.out.println(grep.findExceptRegex(grepParser.getFindExceptRegex()));
        }
        else System.out.println("Неизвестная команда");
    }
}
