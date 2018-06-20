import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

class Launcher {

    public static void main(String[] args) throws IOException {
        Scanner question = new Scanner(System.in);
        String cmd = question.next();
        String oldFileName = question.next();
        String newFileName = question.next();
        int n = question.nextInt();
        int k = question.nextInt();
        String content = new String(Files.readAllBytes(Paths.get("src/" + oldFileName + ".txt")));
        Cut cutter = new Cut();
        boolean spaces = false;
        switch (cmd) {
            case "-c": {
                spaces = true;
                break;
            }
        }
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

