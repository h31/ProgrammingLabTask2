import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Cryptor {
    private String key;
    private byte[] msg;
    private String out = null;

    public Cryptor(String key) {
        this.key = key;
    }

    public void setMsg(byte[] msg) {
        this.msg = msg;
    }

    public String crypt() {
        StringBuilder res = new StringBuilder();
            for (int i = 0; i < msg.length; i++)
                res.append((char) (msg[i % key.length()] ^ key.charAt(i)));
        return res.toString();
    }
}
