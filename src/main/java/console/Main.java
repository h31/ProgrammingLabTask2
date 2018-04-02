package console;

import java.io.File;

public class Main {

    File file;

    public void main(String[] args) {

        FindLauncher findLauncher = new FindLauncher(args);
        file = findLauncher.work();
    }
}
