package cut;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*;


public class CutClasses {

    //    void cutWritter (String inputFile, String n, String k) throws IOException{
//        try(
//            InputStream textFromFile = new FileInputStream(inputFile);
//            OutputStream outputText = new FileOutputStream(inputFile);
//
//        ){
//            cutFlagC(textFromFile,n,k);
//        }
//
//    }
    public static String treatment(String inputString, String[] tokens) throws IOException {
        String textName = tokens[tokens.length - 2];
        String inputFile = "";
        String unexpectedToken = tokens[tokens.length - 3];
        if (tokens.length > 3) {
            if ((textName != null) && (unexpectedToken != "-o")) {
                FileReader text = null;
                try {
                    text = new FileReader("cut.txt");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Scanner scan = new Scanner(text);
                int i = 1;
                while (scan.hasNextLine()) {
                    inputFile += scan.nextLine();
                    i++;
                }
                text.close();
            } else {
                Scanner scan = new Scanner(System.in);
                inputFile += scan.nextLine();
            }
        }
        return inputFile;
    }

    public String cutFlagC(String inputFile, String n, String k) {
        String answer = "";
        if ((n.equals("")) && (!k.equals(""))) {
            answer = inputFile.substring(0, Integer.parseInt(k) + 1);
        } else if ((!n.equals("")) && (k.equals(""))) {
            answer = inputFile.substring(Integer.parseInt(n), inputFile.length() - 1);
        } else if ((!n.equals("")) && (!k.equals(""))) {
            answer = inputFile.substring(Integer.parseInt(n), Integer.parseInt(k) + 1);
        }
        return answer;
    }


    public String cutFlagW(String inputFile, String n, String k) {
        String answer = "";
        List<String> words = getWordsFromString(inputFile);
        if ((n.equals("")) && (!k.equals(""))) {
            for (Integer i = 0; i < Integer.parseInt(k); i++) {
                answer += words.indexOf(i);
            }
        } else if ((!n.equals("")) && (k.equals(""))) {
            for (Integer i = Integer.parseInt(n); i <= words.toString().length(); i++) {
                answer += words.indexOf(i);
            }
        } else if ((!n.equals("")) && (!k.equals(""))) {
            for (Integer i = Integer.parseInt(n); i <= Integer.parseInt(k); i++) {
                answer += words.indexOf(i);
            }
        }
        return answer;
    }

    private List<String> getWordsFromString(String inputFile) {
        inputFile = inputFile.replaceAll("[;:!?,.-]", "");
        String[] words = inputFile.split("\\s+");
        return Arrays.asList(words);
    }


}
