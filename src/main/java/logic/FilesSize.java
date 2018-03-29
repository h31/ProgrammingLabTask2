package logic;

import java.io.File;

public class FilesSize {

    private boolean sum = false;

    private boolean human = false;

    private boolean oneThousand = false;

    private int base = 1024;

    public FilesSize(){}


    public FilesSize(boolean sum, boolean human, boolean oneThousand) {
        this.sum = sum;
        this.human = human;
        this.oneThousand = oneThousand;
    }

    /**
     * Нахождение размера директории с файлами и директориями внутри.
     **/
    private long directorySize(File dir) {
        long size = 0;
        File[] subFiles = dir.listFiles();
        for (File file : subFiles) {
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

    /**
     * Общая функция для нахождения размера файлов с заданными параметрами.
     **/
    public String filesTotalSize(String[] args) {
        String result = "";
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
                fileSize = directorySize(file);
            }
            if (sum) {
                total += fileSize;
            }
            if (human) {
                result += sizePresentedLegibly(fileSize, base) + "\n";
            } else {
                result += fileSize / base + "\n";
            }
        }
        if (sum) {
            if (!human) {
                result += total / base;
            } else {
                result += sizePresentedLegibly(total, base);
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
