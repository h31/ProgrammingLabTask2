package task2.logic;

public class Cipher {
    private int key[];

    public Cipher(String strKey) {
        key = new int[strKey.length() / 2];
        for (int i = 0, j = 0; i < strKey.length(); i += 2) {
            String twoChar = strKey.substring(i, i + 2);
            Integer num = Integer.parseInt(twoChar, 16);
            key[j++] = num.byteValue();
        }
    }

    public byte[] xorCipher(byte text[]) {
        byte res[] = new byte[text.length];
        for (int i = 0; i < text.length; i++) {
            res[i] = (byte) (text[i] ^ key[i % key.length]);
        }
        return res;
    }
}
