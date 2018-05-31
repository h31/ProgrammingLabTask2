package task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


class FileSize {

    private boolean readable;
    private boolean totalSize;
    private boolean baseThousand;

    FileSize(boolean readable, boolean totalSize, boolean baseThousand) {
        this.readable = readable;
        this.totalSize = totalSize;
        this.baseThousand = baseThousand;
    }

    private long fileSize(File path) {
        long sum = 0;
        if (path.isFile()) return path.length();
        else {
            File[] list = path.listFiles();
            if (list == null) return path.length();
            for (File i : list) {
                if (i.isDirectory()) sum += fileSize(i);
                else sum += i.length();
            }
            return sum;
        }
    }

    List getSize(List<String> args) {
        long fullSize = 0;
        String[] sizeList = {"B", "KB", "MB", "GB"};
        List<String> res = new ArrayList<>();
        for (String i : args) {
            int sizeType = 0;
            File file = new File(String.valueOf(i));
            if (!file.exists()) throw new IllegalArgumentException("no such file or directory");
            if (totalSize) fullSize += fileSize(file);
            else {
                int base = 1024;
                if (baseThousand) base = 1000;
                long size = fileSize(file);
                if (readable) {
                    if (size / base > 0) {
                        size /= base;
                        sizeType++;
                    }
                    res.add("Size of" + " " + i + " " + "equals" + " " + size + " " + sizeList[sizeType]);
                } else {
                    size /= base;
                    res.add("Size of" + " " + i + " " + "equals" + " " + size);
                }
            }
        }
        if (totalSize) {
            int base = 1024;
            if (baseThousand) base = 1000;
            if (readable) {
                int sizeType = 0;
                if (fullSize / base > 0) {
                    fullSize /= base;
                    sizeType++;
                }
                res.add("Full size of files equals" + " " + fullSize + " " + sizeList[sizeType]);
            } else {
                fullSize /= base;
                res.add("Full size of files equals" + " " + fullSize);
            }
        }
        return res;
    }
}