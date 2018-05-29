package ru.spbstu.kspt.task2.logic;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Transposition {
    boolean isRightSide;
    boolean isCut;
    String isOutputFile;
    int num;

    public Transposition(boolean isRightSide, boolean isCut, int num, String isOutputFile) {
        this.isRightSide = isRightSide;
        this.isCut = isCut;
        this.isOutputFile = isOutputFile;
        this.num = num;
    }

    public List<List<String>> allLogic(String inputFile) {
        return transposition(formatting(reader(inputFile)));
    }

    public List<String> reader(String inputFile) {
        List<String> initialText = new ArrayList<>();
        try {
            File text = new File(inputFile);
            FileReader readText = new FileReader(text);
            BufferedReader readerOfText = new BufferedReader(readText);
            String line = readerOfText.readLine();
            initialText.add(line);
            while (line != null) {
                line = readerOfText.readLine();
                initialText.add(line);
            }
            initialText.remove(initialText.size() - 1);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return initialText;
    }

    public List<List<String>> formatting(List<String> text) {
        List<List<String>> formattedText = new ArrayList<>();
        for (String line:text) {
            String[] words = line.split("[\\s]+");
            if ((num == 0) & ((isRightSide) | (isCut))){
                num = 10;
            }
            for (int i = 0; i < words.length; i++) {
                if (num != 0) {
                    if (num > words[i].length()){
                        int initialLength = words[i].length();
                        for (int j = initialLength; j < num; j++){
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
            List<String> formattedLine = new ArrayList<>();
            for (String formattedWords: words) {
                if (!formattedWords.equals("")) {
                    formattedLine.add(formattedWords);
                }
            }
            formattedText.add(formattedLine);
        }
        return formattedText;
    }

    public List<List<String>> transposition(List<List<String>> formattedText) {
        List<List<String>> transposedText = new ArrayList<>();
        for (List<String> lineIn: formattedText) {
            for (int i = 0; i < lineIn.size(); i++) {
                if (i >= transposedText.size()) {
                    transposedText.add(new ArrayList<>());
                }
                List<String> lineOut = transposedText.get(i);
                lineOut.add(lineIn.get(i));
                transposedText.set(i, lineOut);
            }
        }
        return transposedText;
    }

    public void writer(String inputFile) {
        try {
            if (isOutputFile == "")
                System.out.print(allLogic(inputFile));
            List<List<String>> transposedText = allLogic(inputFile);
            FileWriter stream = new FileWriter(isOutputFile);
            BufferedWriter out = new BufferedWriter(stream);
            for (List<String> line: transposedText){
                String listToString = String.join(" ", line);
                if (!transposedText.get(transposedText.size() - 1).equals(line)) {
                    out.write(listToString + "\r\n");
                }
                else out.write(listToString);
            }
            out.close();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}