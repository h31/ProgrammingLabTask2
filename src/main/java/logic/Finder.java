package logic;

import java.io.File;
import java.util.Objects;

public class Finder {
    private File result = null;

    private File find2(boolean r, File dPath, String name) {
        if (dPath.exists() && dPath.isDirectory()) {
            for (File file : Objects.requireNonNull(dPath.listFiles())) {
                if (Objects.equals(file.getName(), name)) {
                    result = new File(file.getPath());
                    break;
                } else if (file.isDirectory() && r) {
                    File newDPath = new File(dPath + "\\" + file.getName());
                    find2(true, newDPath, name);
                }
            }
        } else {
            throw new IllegalArgumentException("this directory doesn't exist");
        }
        return result;
    }

    public File find(boolean r, File dPath, String name) {
        if (find2(r, dPath, name) != null) {
            return find2(r, dPath, name);
        } else throw new IllegalArgumentException("no results for: " + name);
    }

}
