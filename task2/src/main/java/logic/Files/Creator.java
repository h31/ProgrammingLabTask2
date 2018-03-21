package logic.Files;

import java.io.File;
import java.io.IOException;

public class Creator {
    String pathDir;
    String name;
    public Creator(){
    }

    public Creator(String pathDir, String name){
        this.pathDir = pathDir;
        this.name = name;
    }

    public void createNewFile(String pathDir, String name){
        try {
            File file = new File(pathDir + name);
            file.createNewFile();
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    public void createNewFile(){
        try {
            File file = new File(pathDir + name);
            file.createNewFile();
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

}
