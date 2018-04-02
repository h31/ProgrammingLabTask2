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
                fileToSeparate = new File(arguments.get(1));
                pack = false;
            } else {
                int i = 0;
                while (!(arguments.get(i).equals("-out"))) {
                    filesToCollect.add(new File(arguments.get(i)));
                    i++;
                }
                collectionFile = new File(arguments.get(i + 1));
                pack = true;
            }
        } catch (ArrayIndexOutOfBoundsException ex){
            throw ex;
        }
    }
    public boolean taskFlag(){
        return pack;
    }

    public File getCollectionFile(){
        return collectionFile;
    }

    public List<File> getFilesToCollect() {
        return filesToCollect;
    }

    public File getFileToSeparate() {
        return fileToSeparate;
    }
}
