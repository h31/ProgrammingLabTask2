import java.io.File;

public class Find {

    private String fileName;

    private Boolean subDirectory;

    public static void main(String[] args) {

        boolean subDirectory = false;
        String fileDirectory = new File("").getAbsolutePath();
        String nameOfTheFile = args[args.length-1];

        for (int i = 0; i < args.length - 1; i++){
            if (args[i].equals("-r"))
                subDirectory = true;
            if (args[i].equals("-d"))
                fileDirectory = args[i+1];
        }

        Find find = new Find(fileDirectory, subDirectory);
        System.out.println(find.find(nameOfTheFile));

    }



    public Find(String fileName, Boolean subDirectory) {
        this.fileName = fileName;
        this.subDirectory = subDirectory;
    }


    private String search(String directory, String nameOfTheFile) {
        String fullNameOfTheFile = directory + "/" + nameOfTheFile;
        if (new File(fullNameOfTheFile).isFile()) return fullNameOfTheFile;
        return "nothing";
    }


    private String subSearch(String directory, String nameOfTheFile) {
        File ourFile = new File(directory);
        String[] directories = ourFile.list();
        if (directories != null)
            for (int i = 0; i < directories.length; i++) {
                String fullNameOfTheFile;
                if (directories[i].equals(nameOfTheFile)) fullNameOfTheFile = directory;
                else fullNameOfTheFile = directory + "/" + directories[i];
                if (new File(fullNameOfTheFile + "/" + nameOfTheFile).isFile())
                    return fullNameOfTheFile + "/" + nameOfTheFile;
                fullNameOfTheFile = subSearch(fullNameOfTheFile, nameOfTheFile);
                if (new File(fullNameOfTheFile).isFile()) return fullNameOfTheFile;
            }
        return "nothing";
    }


    public File find(String nameOfTheFile) {
        String target;
        if (subDirectory) target = subSearch(fileName, nameOfTheFile);
        else target = search(fileName, nameOfTheFile);
        return new File(target);
    }
}