import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

class Main {

    public static void main(String[] args) throws IOException {

        Cut cutter = new Cut();
        boolean spaces = false;
        String oldFileName = "";
        String newFileName = "";
        int n = -1;
        int k = 0;
        Scanner scanner = new Scanner(System.in);


        for (String arg : args) {
            int i = 0;
            switch (arg) {
                case "-c": {
                    spaces = true;
                    break;
                }
                case "-o": {
                    oldFileName = arg;
                    break;
                }
                case "n-": {
                    n = Integer.parseInt(arg);
                    break;
                }
                case "-k": {
                    k = Integer.parseInt(arg);
                    break;
                }
                case "n-k": {
                    String[] argument = arg.split("-");
                    n = Integer.parseInt(argument[0]);
                    k = Integer.parseInt(argument[1]);
                    break;
                }
            }
        }
        if (Objects.equals(oldFileName, "")){
            oldFileName = scanner.nextLine();
            newFileName = scanner.nextLine();
        }
        if (n == -1) {
            n = scanner.nextInt();
        }
        if (k == 0) {
            k = scanner.nextInt();
        }
        String content = new String(Files.readAllBytes(Paths.get("src/" + oldFileName + ".txt")));
        try (FileWriter writer = new FileWriter("src/" + newFileName + ".txt", false)) {
            List<String> newText = cutter.cutter(content, n, k, spaces);
            String readyText = "";
            for (String element : newText) {
                readyText.join(element);
            }
            writer.write(readyText.replaceAll(", ", "\n"));
        }
    }
}


