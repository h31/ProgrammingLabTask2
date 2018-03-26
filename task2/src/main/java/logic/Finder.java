package logic;

import java.io.*;
import java.util.*;

public class Finder {

    public String find(boolean r, String dPath, String name) {
        File dir = new File(dPath);
        if (dir.exists()) {
            for (File file : Objects.requireNonNull(dir.listFiles())) {
                if (Objects.equals(file.getName(), name)) {
                    return file.getPath() + "\\" + name;
                } else {
                    if (file.isDirectory() && r) {
                        String newDPath = dPath + "\\" + file.getName();
                        find(true, newDPath, name);
                    }
                }
            }
        }
        return "no result for: " + name;
    }

}
