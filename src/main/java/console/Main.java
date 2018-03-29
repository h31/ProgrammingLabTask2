package console;

import logic.Find;

import java.io.File;

public class Main {

    public static File main(String[] args) {

        boolean subDirectory = false;
        String fileDirectory = new File("").getAbsolutePath();
        String nameOfTheFile = args[args.length - 1];

        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals("-r"))
                subDirectory = true;
            if (args[i].equals("-d"))
                fileDirectory = args[i + 1];
        }

        Find find = new Find(fileDirectory, subDirectory);

        return find.find(nameOfTheFile);
    }
}
