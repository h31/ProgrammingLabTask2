package console;

import logic.Find;

import java.io.File;

public class FindLauncher {

    static String[] consoleInput;

    FindLauncher(String[] input) {
        consoleInput = input;
    }

    static File isInputCorrect() throws Exception {
        if (consoleInput.length == 0)
            throw new Exception("No input data available");
        boolean useSubDirectoryForSearching = false;
        String fileDirectory = new File("").getAbsolutePath();
        String nameOfTheFile = consoleInput[consoleInput.length - 1];
        for (int i = 0; i < consoleInput.length - 1; i++) {
            if (consoleInput[i].equals("-r"))
                useSubDirectoryForSearching = true;
            if ((consoleInput[i].equals("-d")) && (consoleInput.length == 2)) {
                throw new Exception("Incorrect input");
            } else if (consoleInput[i].equals("-d"))
                fileDirectory = consoleInput[i + 1];
        }
        Find find = new Find(fileDirectory, useSubDirectoryForSearching);
        return new File(find.find(nameOfTheFile).toString());
    }
}
