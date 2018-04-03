package ls.java;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class LsLogic {


    public List<String> flagLong(String name) throws IOException {
        File testDir = new File(name);
        List<String> output = new ArrayList<String>();
        if (testDir.isDirectory()) {
            for (File element : testDir.listFiles()) {
                if (element.isDirectory()) {
                    output.add(element.getName());
                } else {
                    output.add(element.canRead() + " " + element.canWrite() + " " + element.canExecute() + " " +
                            element.getName() + " " + element.lastModified() + " " + element.length());
                }
            }
        }
        else {
            output.add(testDir.canRead() + " " + testDir.canWrite() + " " + testDir.canExecute()+ " " +
                    testDir.getName() + " " + testDir.lastModified() + " " + testDir.length());
        }
        return output;
    }

    public List<String> flagReverse (String name) throws IOException{
        File testDir = new File(name);
        List<String> output = new ArrayList<String>();
        if (testDir.isDirectory()) {
            for (File element : testDir.listFiles()) {
                if (element.isDirectory()) {
                    output.add(0, element.getName());
                } else {
                    output.add(0, element.canRead() + " " + element.canWrite() + " " + element.canExecute() + " " +
                            element.getName() + " " + element.lastModified() + " " + element.length());
                }
            }
        }
        else {
            output.add(0, testDir.canRead() + " " + testDir.canWrite() + " " + testDir.canExecute()+ " " +
                    testDir.getName() + " " + testDir.lastModified() + " " + testDir.length());
        }
        return output;
    }

    public List<String> flagHumanReadable (String name) throws IOException {
        File testDir = new File(name);
        List<String> output = new ArrayList<String>();
        if (testDir.isDirectory()) {
            for (File element : testDir.listFiles()) {
                if (element.isDirectory()) {
                    output.add(element.getName());
                } else {
                    Date date = new Date(element.lastModified());
                    output.add(String.format("%s %s %s %s %s %d", element.canRead(), element.canWrite(),
                            element.canExecute(), element.getName(), date.toString(), element.length()));
                }
            }
        }
        else {
            Date date = new Date(testDir.lastModified());
            output.add(String.format("%s %s %s %s %s %d", testDir.canRead(), testDir.canWrite(), testDir.canExecute(), testDir.getName(), date.toString(), testDir.length()));
        }
        return output;
    }

}
