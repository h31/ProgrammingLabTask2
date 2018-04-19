import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Program {
    private String key;
    private String msg;
    private String out = null;

    public Program(String key, String msg) {
        this.key = key;
        this.msg = msg;
    }

    public String work() throws IOException {
        StringBuilder res = new StringBuilder();
        byte[] tmp = Files.readAllBytes(Paths.get(msg));
        int c = 0;
        do {
            for (int i = 0; i < key.length() && c * key.length() + i < tmp.length; i++) {
                res.append((char) (tmp[c * key.length() + i] ^ key.charAt(i)));
            }
            c++;
        } while (c * key.length() < tmp.length);
        return res.toString();
    }
}
