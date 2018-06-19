package logic;

import java.io.File;

public class Find {

    private String directoryToSearch;
    private Boolean useSubDirectory;

    public Find(String directoryToSearch, Boolean useSubDirectory) {
        this.directoryToSearch = directoryToSearch;
        this.useSubDirectory = useSubDirectory;
    }

    private File search(String currentDirectory, String fileToFind) throws Exception {
        String pathToTheFile = currentDirectory + "/" + fileToFind;
        if (new File(pathToTheFile).isFile()) return new File(pathToTheFile);
        if (useSubDirectory) {
            File ourPath = new File(currentDirectory);
            String[] directories = ourPath.list();
            if (directories != null) {
                for (int i = 0; i < directories.length; i++) {
                    if (directories[i].equals(fileToFind)) pathToTheFile = currentDirectory;
                    else pathToTheFile = currentDirectory + "/" + directories[i];
                    if (new File(pathToTheFile + "/" + fileToFind).isFile())
                        return new File(pathToTheFile + "/" + fileToFind);
                    if (search(pathToTheFile, fileToFind) != null) {
                        pathToTheFile = search(pathToTheFile, fileToFind).toString();
                        if (new File(pathToTheFile).isFile()) return new File(pathToTheFile);
                    }
                }
            }
        }
        if (currentDirectory.equals(directoryToSearch)) {
            throw new Exception("File does not exist");
        }
        return null;
    }

    public File find(String fileToFind) throws Exception {
        File target = search(directoryToSearch, fileToFind);
        return target;
    }
}