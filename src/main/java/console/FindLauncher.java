package console;

import logic.Find;

import java.io.File;

public class FindLauncher {

    private String[] consoleInput;

    FindLauncher(String[] input) {
        consoleInput = input;
    }

    public File commandLine() throws Exception {
        if (consoleInput.length == 0)
            throw new Exception("Not enough arguments on the command line");
        boolean searchInSubDirectories = false;
        String fileDirectory = new File("").getAbsolutePath();
        String nameOfTheFile = consoleInput[consoleInput.length - 1];
        for (int i = 0; i < consoleInput.length - 1; i++) {
            if (consoleInput[i].equals("-r"))
                searchInSubDirectories = true;
            if ((consoleInput[i].equals("-d")) && (consoleInput.length == 2)) {
                throw new Exception("Incorrect input");
            } else if (consoleInput[i].equals("-d"))
                fileDirectory = consoleInput[i + 1];
        }
        Find find = new Find(fileDirectory, searchInSubDirectories);
        return find.find(nameOfTheFile);
    }
}
