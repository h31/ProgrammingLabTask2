import java.io.BufferedReader;
import java.io.IOException;

public class Program {
    private String key;
    private BufferedReader msg;
    private String out = null;

    public Program(String key, BufferedReader msg) {
        this.key = key;
        this.msg = msg;
    }

    public String work() throws IOException {
        StringBuilder res = new StringBuilder();
        char[] buf = new char[this.key.length()];
        while ((msg.read(buf)) != -1) {
            for (int i = 0; i < buf.length; i++) {
                res.append((char) (buf[i] ^ key.charAt(i)));
            }
        }
        return res.toString();
    }
}
