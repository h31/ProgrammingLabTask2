package ls.java;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ls test = new ls();
        System.out.println(test.flagLong("C:/Users/hp/Desktop/testFolder"));
        System.out.println(test.flagReverse("C:/Users/hp/Desktop/testFolder"));
        System.out.println(test.flagHumanReadable("C:/Users/hp/Desktop/testFolder"));

    }
}
