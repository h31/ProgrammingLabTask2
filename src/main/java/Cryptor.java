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
                res.append((char) (msg[i] ^ key.charAt(i % key.length())));
        return res.toString();
    }
}
