package terminalApp;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

class Launcher {

    public static void main(String[] args) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("terminalApp/text/file.txt")));
        System.out.println(content);
        Cut cutter = new Cut();
        Scanner question = new Scanner(System.in);
        String cmd = question.nextLine();
        switch (cmd) {
            case "-c": {
                Scanner values = new Scanner(System.in);
                int n = values.nextInt();
                int k = values.nextInt();
                try (FileWriter writer = new FileWriter("terminalApp/text/fileNew.txt", false)) {
                    String newText = (cutter.cutterC(content, n, k).toString().replaceAll("\\[|\\]", ""));
                    writer.write(newText.replaceAll(", ", "\n"));
                }
                break;
            }
            case "-w": {
                Scanner values = new Scanner(System.in);
                int n = values.nextInt();
                int k = values.nextInt();
                try (FileWriter writer = new FileWriter("terminalApp/text/fileNew.txt", false)) {
                    String newText = (cutter.cutterW(content, n, k).toString().replaceAll("\\[|\\]", ""));
                    writer.write(newText.replaceAll(", ", "\n"));
                }
                break;
            }
        }
    }
}


