

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class UniqProcess {
    private static UniqProcess uniqProcess = new UniqProcess();

    private Parser parser;
    private Scanner input;
    private PrintStream output;

    public static UniqProcess getInstance() {
        return uniqProcess;
    }

    public void run(String[] arguments) throws Exception {
        parser = new Parser();
        parser.analyzeArgs(arguments);
        setOutput();
        setInput();
    }

    public void setInput() throws Exception {
        if (parser.isReadFile()) try {
            input = new Scanner(new BufferedReader(new FileReader(parser.getInputFileName())));
            BufferedReader br = new BufferedReader(new FileReader(new File(parser.getInputFileName())));
            hasInputFileNameAndOutput(br);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        else {
            input = new Scanner(System.in);
            hasNoInputFileNameAndOutput();
        }
    }

    public void setOutput() {
        if (parser.isOutputToFile())
            try {
                output = new PrintStream(new FileOutputStream(new File(parser.getOutputFileName())));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        else output = System.out;
    }

    private String commandSAndI(String line) {//大小写和忽略字符
        String newLine = line;
        if (parser.getIgnoreChar() > newLine.length()) newLine = "";
        else newLine = newLine.substring(parser.getIgnoreChar());
        if (parser.isCaseInsensetive()) newLine = newLine.toLowerCase();
        return newLine;
    }

    public void hasInputFileNameAndOutput(BufferedReader br) throws Exception {//输入文件名
        if (input == null || output == null) throw new IllegalStateException();
        HashMap<String, Integer> newMap = new HashMap<String, Integer>();
        HashMap<String, String> newToPrevious = new HashMap<String, String>();
        HashMap<String, Integer> resultMap = new HashMap<String, Integer>();

        String previousReadLine = "";
        while ((previousReadLine = br.readLine()) != null) {
            int count = 0;
            String newReadLine=commandSAndI(previousReadLine);
            if (newMap.containsKey(newReadLine)) count = newMap.get(newReadLine);
            newMap.put(newReadLine,count+1);
            newToPrevious.put(newReadLine,previousReadLine);
        }
        for (String key:newMap.keySet()){
            resultMap.put(newToPrevious.get(key),newMap.get(key));
        }
        output(resultMap);
    }

    public void hasNoInputFileNameAndOutput() throws Exception {//输入文件名
        if (input == null || output == null) throw new IllegalStateException();
        String previousReadLine = "";
        List<String> lines = new ArrayList<>();
        String string = "";
        int i = 0;
        do {
            string = input.nextLine();
            lines.add(string);
            i++;
        } while (!lines.get(i - 1).equals("end"));
        input.close();

        HashMap<String, Integer> newMap = new HashMap<String, Integer>();
        HashMap<String, String> newToPrevious = new HashMap<String, String>();
        HashMap<String, Integer> resultMap = new HashMap<String, Integer>();
        for (String str:lines) {
            int count = 0;
            previousReadLine=str;
            String newReadLine=commandSAndI(previousReadLine);
            if (newMap.containsKey(newReadLine)) count = newMap.get(newReadLine);
            newMap.put(newReadLine, count + 1);
            newToPrevious.put(newReadLine, previousReadLine);
        }
        for (String key:newMap.keySet()){
            resultMap.put(newToPrevious.get(key),newMap.get(key));
        }
        output(resultMap);
    }

    private void output(String line, int count) {
        if (parser.isUnique() && count != 1 || line.length() == 0) return;
        if (parser.isCount()) output.println(count + " " + line);
        else output.println(line + "\n");
    }

    private void output(HashMap<String, Integer> map) {
        if (map == null) throw new IllegalStateException();
        for (int i = 0; i < map.keySet().size(); i++) {
            output(map.keySet().toArray()[i].toString(), Integer.parseInt(map.values().toArray()[i].toString()));
        }
    }

    public void delete() {
        if (input == null || output == null || parser == null) {
            throw new NullPointerException();
        }
        input.close();
        output.close();
        input = null;
        output = null;
        parser = null;
    }

}
