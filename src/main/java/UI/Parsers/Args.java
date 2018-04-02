package UI.Parsers;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Args {
    private List<String> arguments = new ArrayList<>();
    boolean pack;
    List<File> filesToCollect = new ArrayList<>();
    File collectionFile;

    public File fileToSeparate;

    public Args(String[] args) {
        this.arguments = Arrays.asList(args);
    }

    public void getTask() {
        try {
            if (arguments.get(0).equals("-u")) {
                if (arguments.get(1).startsWith("/")) {
                    fileToSeparate = new File(arguments.get(1));
                } else {
                    File home = new File("");
                    fileToSeparate = new File(home.getAbsolutePath() + "/" + arguments.get(1));
                }
                pack = false;

            } else {
                int i = 0;
                while (!(arguments.get(i).equals("-out"))) {
                    if (arguments.get(i).startsWith("/")) {
                        filesToCollect.add(new File(arguments.get(i)));
                        i++;
                    } else {
                        File home = new File("");
                        filesToCollect.add(new File(home.getAbsolutePath() + "/" + arguments.get(i)));
                        i++;
                    }
                }
                if (arguments.get(i + 1).startsWith("/")) {
                    collectionFile = new File(arguments.get(i + 1));
                } else {
                    File home = new File("");
                    collectionFile = new File(home.getAbsolutePath() + "/" + arguments.get(i + 1));
                }
                pack = true;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw ex;
        }
    }

    public boolean taskFlag() {
        return pack;
    }

    public File getCollectionFile() {
        return collectionFile;
    }

    public List<File> getFilesToCollect() {
        return filesToCollect;
    }

    public File getFileToSeparate() {
        return fileToSeparate;
    }
}
