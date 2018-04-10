package ls.java;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class LsLogic {

    public List<String> ls(String name) throws IOException {
        File test = new File(name);
        List<String> output = new ArrayList<String>();
        if (test.isFile()) {
            output.add(test.getName() + " " + test.lastModified() + " " + test.length());
        } else {
            for (File element : test.listFiles()) {
                output.add(element.getName());
            }
        }
        return output;
    }


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
        } else {
            output.add(testDir.canRead() + " " + testDir.canWrite() + " " + testDir.canExecute() + " " +
                    testDir.getName() + " " + testDir.lastModified() + " " + testDir.length());
        }
        return output;
    }

    public List<String> flagReverse(List<String> list) throws IOException {
        List<String> newList = new ArrayList<String>(list);
        Collections.reverse(newList);
        return newList;
    }

    public List<String> flagHumanReadable(String name) throws IOException {
        File testDir = new File(name);
        List<String> output = new ArrayList<String>();
        if (testDir.isDirectory()) {
            for (File element : testDir.listFiles()) {
                if (element.isDirectory()) {
                    output.add(element.getName());
                } else {
                    Date date = new Date(element.lastModified());
                    output.add(String.format("%s %s %s %s", getRWX(element),
                            element.getName(), date.toString(), bytesToHuman(element.length())));
                }
            }
        } else {
            Date date = new Date(testDir.lastModified());
            output.add(String.format("%s %s %s %s", getRWX(testDir), testDir.getName(), date.toString(), bytesToHuman(testDir.length())));
        }
        return output;
    }

    public void flagOutput(String fileName, List<String> list) throws IOException {
        File file = new File(fileName);
        List<String> newList = new ArrayList<String>(list);
        FileWriter writer = new FileWriter(fileName);
        writer.write(String.valueOf(newList));
        writer.close();
        FileReader fr = new FileReader(file);
        int ch;
        while ((ch = fr.read()) != -1)
            System.out.print((char) ch);
    }

    public static String floatForm(double d) {
        return new DecimalFormat("#.##").format(d);
    }


    public static String bytesToHuman(long size) {
        long Kb = 1024;
        long Mb = Kb * 1024;
        long Gb = Mb * 1024;
        long Tb = Gb * 1024;
        long Pb = Tb * 1024;

        if (size < Kb) return floatForm(size) + " byte";
        if (size >= Kb && size < Mb) return floatForm((double) size / Kb) + " Kb";
        if (size >= Mb && size < Gb) return floatForm((double) size / Mb) + " Mb";
        if (size >= Gb && size < Tb) return floatForm((double) size / Gb) + " Gb";
        if (size >= Tb && size < Pb) return floatForm((double) size / Tb) + " Tb";
        return "???";
    }


    public String getRWX(File element) {
        String r;
        String w;
        String x;
        if (element.canRead()) {
            r = "r";
        } else {
            r = "-";
        }
        if (element.canWrite()) {
            w = "w";
        } else {
            w = "-";
        }
        if (element.canExecute()) {
            x = "x";
        } else {
            x = "-";
        }
        return (r + w + x);

    }

}
