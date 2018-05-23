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

    public List<List<String>> allLogic(Path inputFile) {
        return transpose(format(reader(inputFile)));
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
            if ((num == 0) & ((isRightSide) | (isCut))){
                num = 10;
            }
            for (int i = 0; i < words.length; i++) {
                if (num != 0) {
                    if (num > words[i].length()){
                        int first = words[i].length();
                        for (int j = first; j < num; j++){
                            if (isRightSide) {
                                words[i] = " " + words[i];
                            }
                            else words[i] += " ";
                        }
                    }
                    else if (isCut) {
                        words[i] = words[i].substring(0, num);
                    }
                }
            }
            List<String> l = new ArrayList<>();
            for (String w : words) {
                if (!w.equals("")) {
                    l.add(w);
                }
            }
            output.add(l);

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
        return output;
    }
}

