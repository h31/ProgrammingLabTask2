package logic;

import java.io.File;

public class Find {

    private String directoryToSearch;

    private Boolean useSubDirectory;

    public Find(String directoryToSearch, Boolean useSubDirectory) {
        this.directoryToSearch = directoryToSearch;
        this.useSubDirectory = useSubDirectory;
    }


    private String search(String directory, String fileToFind) {
        String pathToTheFile = directory + "/" + fileToFind;
        if (new File(pathToTheFile).isFile()) return pathToTheFile;
        if (useSubDirectory) {
            File ourPath = new File(directory);
            String[] directories = ourPath.list();
            if (directories != null) {
                for (int i = 0; i < directories.length; i++) {
                    if (directories[i].equals(fileToFind)) pathToTheFile = directory;
                    else pathToTheFile = directory + "/" + directories[i];
                    if (new File(pathToTheFile + "/" + fileToFind).isFile())
                        return pathToTheFile + "/" + fileToFind;
                    pathToTheFile = search(pathToTheFile, fileToFind);
                    if (new File(pathToTheFile).isFile()) return pathToTheFile;
                }
            }
        }
        return "File does not exist";
    }


    public File find(String fileToFind) {
        String target = search(directoryToSearch, fileToFind);
        return new File(target);
    }
}