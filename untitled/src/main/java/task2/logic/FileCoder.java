package task2.logic;
import java.io.*;

public class FileCoder {
    private String key;

    public FileCoder(String key) {
        this.key = key;
    }

    public void transform(String inputFile, String outputFile) {
        Cipher c = new Cipher(key);
        int len;
        byte text[] = new byte[150];
        try {
            FileInputStream in = new FileInputStream(inputFile);
            FileOutputStream out = new FileOutputStream(outputFile);
            while ((len = in.read(text)) != -1) {
                out.write(c.xorCipher(text));
            }
            in.close();
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }
}
