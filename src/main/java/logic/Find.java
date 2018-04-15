package logic;

import java.io.File;

public class Find {

    private String directoryToSearch;
    private Boolean useSubDirectory;
    private File flag1;
    private File flag2;
    private File flag3;


    public Find(String directoryToSearch, Boolean useSubDirectory) {
        this.directoryToSearch = directoryToSearch;
        this.useSubDirectory = useSubDirectory;
    }

    private String search(String currentDirectory, String fileToFind) throws Exception {
        String pathToTheFile = currentDirectory + "/" + fileToFind;
        flag1 = new File(pathToTheFile);
        if (flag1.isFile()) return flag1.getPath();
        if (useSubDirectory) {
            File ourPath = new File(currentDirectory);
            String[] directories = ourPath.list();
            if (directories != null) {
                for (int i = 0; i < directories.length; i++) {
                    if (directories[i].equals(fileToFind)) pathToTheFile = currentDirectory;
                    else pathToTheFile = currentDirectory + "/" + directories[i];
                    flag2 = new File(pathToTheFile + "/" + fileToFind);
                    if (flag2.isFile())
                        return flag2.getPath();
                    pathToTheFile = search(pathToTheFile, fileToFind);
                    flag3 = new File(pathToTheFile);
                    if (flag3.isFile()) return flag3.getPath();
                }
            }
        }
        if (currentDirectory.equals(directoryToSearch)){
            throw new Exception("File does not exist");
        }
        return "";
    }

    public File find(String fileToFind) throws Exception {
        String target = search(directoryToSearch, fileToFind);
        if (target.equals(flag1.getPath()))
            return flag1;
        if (target.equals(flag2.getPath()))
            return flag2;
        if (target.equals(flag3.getPath()))
            return flag3;
        return new File("");
    }
}