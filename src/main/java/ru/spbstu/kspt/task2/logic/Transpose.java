package ru.spbstu.kspt.task2.logic;

import java.io.*;
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

    public List<List<String>> allLogic(String inputFile) {
        return transpose(format(reader(inputFile)));
    }

    public List<String> reader(String inputFile) {
        List<String> l = new ArrayList<>();
        try {
            File file = new File(inputFile);
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader readeR = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = readeR.readLine();
            l.add(line);
            while (line != null) {
                // считываем остальные строки в цикле
                line = readeR.readLine();
                l.add(line);
            }
            l.remove(l.size() - 1);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return l;
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

    public void writer(String inputFile) {
        try {
            List<List<String>> list = allLogic(inputFile);
            FileWriter fstream = new FileWriter(isOutputFile);
            BufferedWriter out = new BufferedWriter(fstream);
            for (List<String> line: list){
                String s = String.join(" ", line);
                if (!list.get(list.size() - 1).equals(line)) {
                    out.write(s + "\r\n");
                }
                else out.write(s);
            }
            out.close();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}

