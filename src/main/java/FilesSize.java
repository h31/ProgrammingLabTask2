import java.io.File;

class FilesSize {

    private boolean sum;

    private boolean human;

    private boolean oneThousand;

    private int base = 1024;

    FilesSize(boolean sum, boolean human, boolean oneThousand) {
        this.sum = sum;
        this.human = human;
        this.oneThousand = oneThousand;
    }

    private long getDirectorySize(File dir) {
        long size = 0;
        File[] subFiles = dir.listFiles();
        for (File file : subFiles) {
            if (file.isFile()) {
                size += file.length();
            } else {
                size += getDirectorySize(file);
            }
        }
        return size;
    }

    private void humanFileSize(double fileSize, boolean oneThousand) {
        if (oneThousand) {
            base = 1000;
        }
        int count = 0;
        for (int i = 1; i <= 3; i++) {
            if (fileSize > base) {
                fileSize /= base;
                count++;
            }
        }
        switch (count) {
            case 0:
                System.out.println(fileSize + " bytes");
                break;
            case 1:
                System.out.println(fileSize + " kb");
                break;
            case 2:
                System.out.println(fileSize + " mb");
                break;
            case 3:
                System.out.println(fileSize + " gb");
                break;
        }
    }

    void humanFilesSize(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Нет файла");
        }
        double total = 0;
        if (oneThousand) {
            base = 1000;
        }
        for (String arg : args) {
            File file = new File(arg);
            if (!file.isFile() && !file.isDirectory()) {
                throw new IllegalArgumentException("Это не файл или директория");
            }
            double fileSize;
            if (file.isFile()) {
                fileSize = file.length();
            } else {
                fileSize = getDirectorySize(file);
            }
            if (sum) {
                total += fileSize;
            }
            if (human) {
                humanFileSize(fileSize, oneThousand);
            } else {
                System.out.println(fileSize / base);
                if (sum) {
                    total += fileSize / base;
                }
            }
        }
        if (sum && !human) {
            System.out.println(total);
        }
        if (sum && human) {
            humanFileSize(total, oneThousand);
        }
    }
}
