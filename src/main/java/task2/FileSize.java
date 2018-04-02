package task2;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


class FileSize {

    private boolean h;
    private boolean c;
    private boolean si;
    private long sum = 0;

    FileSize(boolean h, boolean c, boolean si) {
        this.h = h;
        this.c = c;
        this.si = si;
    }

    private long fileSize(File fileN) {
        if (fileN.isFile()) return fileN.length();
        else {
            File[] list = fileN.listFiles();
            if (list != null) {
                for (File i : list) {
                    if (i.isDirectory()) sum += fileSize(i);
                    else sum += i.length();
                }
            }
            return sum;
        }
    }

    List print(String[] args) {
        int base = 1024;
        if (si) base = 1000;
        int type = 0;
        String[] list = {"B", "KB", "MB", "GB"};
        List<String> res = new LinkedList<>();
        for (String i : args) {
            File file = new File(i);
            if (!file.exists()) throw new IllegalArgumentException("no such file or directory");
            if (c) sum += fileSize(file);
            else {
                long size = fileSize(file);
                if (h) {
                    if (size / base > 0) {
                        size /= base;
                        type++;
                    }
                    res.add("Size of" + " " + i + " " + "equals" + " " + size + " " + list[type]);
                } else {
                    size /= base;
                    res.add("Size of" + " " + i + " " + "equals" + " " + size);
                }
            }
            type = 0;
        }
        if (c) {
            if (h) {
                if (sum / base > 0) {
                    sum /= base;
                    type++;
                }
                res.add("Full size of files equals" + " " + sum + " " + list[type]);
            } else {
                sum /= base;
                res.add("Full size of files equals" + " " + sum);
            }
        }
        return res;
    }
}