package logic;

import java.io.File;

public class FilesSize {

    private boolean sum = false;

    private boolean isReadable = false;

    private boolean oneThousand = false;

    public FilesSize(){}

    public FilesSize(boolean sum, boolean isReadable, boolean oneThousand) {
        this.sum = sum;
        this.isReadable = isReadable;
        this.oneThousand = oneThousand;
    }

    /**
     * Нахождение размера директории с файлами и директориями внутри.
     **/
    private long directorySize(File directory) {
        long size = 0;
        for (File file : directory.listFiles()) {
            if (file.isFile()) {
                size += file.length();
            } else {
                size += directorySize(file);
            }
        }
        return size;
    }

    /**
     * Нахождение размера файла в человеко-читаемом формате
    **/
    private String sizePresentedLegibly(double fileSize, int base) {
        int count = 0;
        while (count < 3 && base < fileSize){
                fileSize /= base;
                count++;
        }
        switch (count) {
            case 0:
                return fileSize + " bytes";
            case 1:
                return fileSize + " Kb";
            case 2:
                return fileSize + " Mb";
            case 3:
                return fileSize + " Gb";
        }
        return "";
    }

    /**
     * Общая функция для нахождения размера файлов с заданными параметрами.
     **/
    public String filesTotalSize(String[] args) {
        int base = 1024;
        String result = "";
        double totalSize = 0;
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
                fileSize = directorySize(file);
            }
            if (sum) {
                totalSize += fileSize;
            }
            if (isReadable) {
                result += sizePresentedLegibly(fileSize, base) + "\n";
            } else {
                result += fileSize / base + "\n";
            }
        }
        if (sum) {
            if (!isReadable) {
                result += totalSize / base;
            } else {
                result += sizePresentedLegibly(totalSize, base);
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilesSize filesSize = (FilesSize) o;

        if (sum != filesSize.sum) return false;
        if (isReadable != filesSize.isReadable) return false;
        if (oneThousand != filesSize.oneThousand) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (sum ? 1 : 0);
        result = 31 * result + (isReadable ? 1 : 0);
        result = 31 * result + (oneThousand ? 1 : 0);
        return result;
    }
}
