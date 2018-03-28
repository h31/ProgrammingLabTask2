package logic;

import java.io.File;

public class FilesSize {

    private boolean sum = false;

    private boolean human = false;

    private boolean oneThousand = false;

    private int base = 1024;

    FilesSize(){}


    public FilesSize(boolean sum, boolean human, boolean oneThousand) {
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

    private String humanFileSize(double fileSize, int base) {
        int count = 0;
        for (int i = 1; i <= 3; i++) {
            if (fileSize > base) {
                fileSize /= base;
                count++;
            }
        }
        switch (count) {
            case 0:
                return fileSize + " bytes";
            case 1:
                return fileSize + " kb";
            case 2:
                return fileSize + " mb";
            case 3:
                return fileSize + " gb";
        }
        return "";
    }

    public String filesSize(String[] args) {
        String filesSizeTotal = new String();
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
                filesSizeTotal += humanFileSize(fileSize, base) + " ";
            } else {
                filesSizeTotal += fileSize / base + " ";
            }
        }
        if (sum) {
            if (!human) {
                filesSizeTotal += total / base;
            } else {
                filesSizeTotal += humanFileSize(total, base);
            }
        }
        return filesSizeTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilesSize filesSize = (FilesSize) o;

        if (sum != filesSize.sum) return false;
        if (human != filesSize.human) return false;
        if (oneThousand != filesSize.oneThousand) return false;
        return base == filesSize.base;
    }

    @Override
    public int hashCode() {
        int result = (sum ? 1 : 0);
        result = 31 * result + (human ? 1 : 0);
        result = 31 * result + (oneThousand ? 1 : 0);
        result = 31 * result + base;
        return result;
    }
}
