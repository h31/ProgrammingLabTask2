package console;

import logic.Find;

import java.io.File;

public class FindLauncher {

    static String[] console;

    FindLauncher(String[] input) {
        console = input;
    }

    static File work() {

        if (console.length == 0)
            return new File("No input data available");

        boolean useSubDirectory = false;
        String fileDirectory = new File("").getAbsolutePath();
        String nameOfTheFile = console[console.length - 1];

        for (int i = 0; i < console.length - 1; i++) {
            if (console[i].equals("-r"))
                useSubDirectory = true;
            if ((console[i].equals("-d")) && (console.length == 2)) {
                return new File("Incorrect input");

            } else if (console[i].equals("-d"))
                fileDirectory = console[i + 1];
        }

        Find find = new Find(fileDirectory, useSubDirectory);

        return new File(find.find(nameOfTheFile).toString());
    }
}
