package ru.spbstu.kspt.task2.logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Transpose {
    boolean isRightSide;
    boolean isCut;
    String isOutputFile;
    int num;

    public Transpose(boolean isRightSide, boolean isCut, int num, String isOutputFile) {
        this.isRightSide = isRightSide;
        this.isCut = isCut;
        this.isOutputFile = isOutputFile;
        this.num = num;
    }

    public void allLogic(Path inputFile) {
        this.transpose(this.format(this.reader(inputFile)));
    }

    public List<String> reader(Path inputFile) {
        try {
            return (Files.readAllLines(inputFile));
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }


    public List<List<String>> format(List<String> text) {
        List<List<String>> output = new ArrayList<>();
        for (String line:text) {
            String[] words = line.split("[\\s]+");
            if (num != 0) {
                if (words[0].length() < num) {
                    for (int j = words[0].length(); j < num; j++) {
                        if (isRightSide = true) {
                            words[0] = " " + words[0];
                        }
                            else words[0] = words[0] + " ";
                    }
                }
                else if (isCut = true) {
                    words[0] = words[0].substring(0, num);
                }
            }
            /*System.out.print(num + " ");
            System.out.println(words[0] + ";");*/
            List<String> l = new ArrayList<>();
            for (String w : words) {
                if (w.equals("")) {
                    l.add(w);
                }
            }
            output.add(l);
            System.out.println(output);

        }
        return output;
    }

    public List<List<String>> transpose(List<List<String>> input) {
        List<List<String>> output = new ArrayList<>();
        for (List<String> l:input) {
            for (int i = 0; i < l.size(); i++) {
                if (i >= output.size()) {
                    output.add(new ArrayList<>());
                }
                List<String> s = output.get(i);
                s.add(l.get(i));
                output.set(i, s);
            }
        }
        /*System.out.println(output);*/
        List<List<String>> temp = new ArrayList<>();
        for (List<String> l: output) {
            for (int i = 0; i < l.size(); i++) {
                temp.add(format(l).get(i));
            }
        }
        System.out.println(temp);
        return temp;
    }
}

