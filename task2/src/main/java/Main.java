import logic.Files.Reader;

import java.io.File;

public class Main {
    public static void main(String[] args){
        File file = new File("/Users/Ferrero/IdeaProjects/ProgrammingLabTask2/task2/src/main/resources/test.txt");
        System.out.println(file.getAbsolutePath());
    }
}
