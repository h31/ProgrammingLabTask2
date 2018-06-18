package cut;

import java.util.Arrays;
import java.util.Scanner;

public class Cut {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        CutClasses flags = new CutClasses();
        String input = scan.nextLine();
        String[] tokens = input.split(" ");
        String range = tokens[tokens.length - 1];
        String[] numbersOfRange = range.split("-");
        Character firstOfChar = range.charAt(0);
        Character lastOfChar = range.charAt(range.length() - 1);
        String n = "";
        String k = "";
        if (range.contains("-")) {
            if ((firstOfChar != '-') && (lastOfChar != '-')) {
                n = numbersOfRange[0];
                k = numbersOfRange[1];
            } else {
                if (firstOfChar != '-') {
                    n = numbersOfRange[0];
                } else {
                    if (lastOfChar != '-') {
                        k = numbersOfRange[1];
                    }
                }
            }
        }



//            switch (scan) {
//                case "-c": {
//                }
//                case "-w": {
//                }
//                case "-o": {
//            }
    }
}
//}
//}
