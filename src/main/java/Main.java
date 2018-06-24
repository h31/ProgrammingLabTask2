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


        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-c": {
                    spaces = true;
                    break;
                }
                case "-o": {
                    oldFileName = args[++i];
                    break;
                }
                case "n-": {
                    try {
                        n = Integer.parseInt(args[++i]);
                        break;
                    }
                    catch (IllegalArgumentException e) {
                        System.out.println("Некорректный ввод");
                    }
                }
                case "-k": {
                    try {
                        k = Integer.parseInt(args[++i]);
                        break;
                    }
                    catch (IllegalArgumentException e) {
                        System.out.println("Некорректный ввод");
                    }
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
        String content = new String(Files.readAllBytes(Paths.get(oldFileName)));
        try (FileWriter writer = new FileWriter(newFileName, false)) {
            String newText = cutter.cutter(content, n, k, spaces);
            writer.write(newText.replaceAll(", ", "\n"));
        }
    }
}


