public class Program {
    private String key;
    private String msg;
    private String out = null;

    public Program(String key, String msg, String out) {
        this.key = key;
        this.msg = msg;
        this.out = out;
    }

    public Program(String key, String msg) {
        this.key = key;
        this.msg = msg;
    }

    public byte[] crypt(){
        return new byte[]{};
    }

    public void decrypt(){

    }
}
