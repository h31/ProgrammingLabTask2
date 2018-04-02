package terminalApp;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

class Launcher {

    public static void main(String[] args) throws IOException {
        Scanner question = new Scanner(System.in);
        String cmd = question.next();
        String oldFileName = question.next();
        String newFileName = question.next();
        int n = question.nextInt();
        int k = question.nextInt();
        String content = new String(Files.readAllBytes(Paths.get("terminalApp/text/" + oldFileName + ".txt")));
        Cut cutter = new Cut();
        switch(cmd) {
            case "-c" : {
                try (FileWriter writer = new FileWriter("terminalApp/text/" + newFileName + ".txt", false)) {
                    String newText = (cutter.cutterC(content, n, k).toString().replaceAll("\\[|\\]", ""));
                    writer.write(newText.replaceAll(", ", "\n"));
                }
                break;
            }
            case "-w": {
                try (FileWriter writer = new FileWriter("terminalApp/text/" + newFileName + ".txt", false)) {
                    String newText = (cutter.cutterW(content, n, k).toString().replaceAll("\\[|\\]", ""));
                    writer.write(newText.replaceAll(", ", "\n"));
                }
                break;
            }
        }
    }
}


