import org.apache.commons.cli.*;

public class Main {
    class Cli{
        private Options options = new Options();
        public Cli(){
            options.addOption(new Option("c", "crypt", true, "key for encription"));
            options.addOption(new Option("d", "decrypt", true, "key for decryption"));
            options.addOption(new Option("o", "output", true, "path to output file"));
        }

        public void parse(String args[]){
            CommandLineParser prs = new PosixParser();

            try {
                CommandLine cmd = prs.parse(options,args);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String args[]){

    }
}
