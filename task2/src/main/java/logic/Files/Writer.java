package logic.Files;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writer {
    String pathDir;
    String name;

    public Writer() {
    }

    public Writer(String pathDir, String name) {
        this.pathDir = pathDir;
        this.name = name;
    }

    public void writeLines(String pathDir, String name, ArrayList<String> content, boolean rewrite) {
        try {
            FileWriter writer = new FileWriter(pathDir + name, !rewrite);
            StringBuilder lines = new StringBuilder();
            for (String line : content) {
                lines.append(line + "\n");
            }
            lines.delete(lines.length() - 1,lines.length());
            writer.write(lines.toString());
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void writeLines(ArrayList<String> content, boolean rewrite) {
        try {
            FileWriter writer = new FileWriter(pathDir + name, !rewrite);
            StringBuilder lines = new StringBuilder();
            for (String line : content) {
                lines.append(line + "\n");
            }
            lines.delete(lines.length() - 1,lines.length());
            writer.write(lines.toString());
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
