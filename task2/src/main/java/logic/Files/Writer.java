package logic.Files;

import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    String pathDir;
    String name;

    public Writer(){
    }
    public Writer(String pathDir, String name){
        this.pathDir = pathDir;
        this.name = name;
    }
    public void writeStringToFile(String pathDir, String name, String content, boolean rewrite){
        try {
            FileWriter writer = new FileWriter(pathDir + name, !rewrite);
            writer.write(content);
            writer.close();
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    public void writeStringToFile(String content, boolean rewrite){
        try {
            FileWriter writer = new FileWriter(pathDir + name, !rewrite);
            writer.write(content);
            writer.close();
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
