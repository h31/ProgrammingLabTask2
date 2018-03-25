import org.apache.commons.cli.*;

import java.io.File;

public class Main {
    private static Cli c = new Cli();
    static class Cli{
        private Options options = new Options();
        public Cli(){
            options.addOption(new Option("c", "crypt", true, "key for encription"));
            options.addOption(new Option("d", "decrypt", true, "key for decryption"));
            options.addOption(new Option("o", "output", true, "path to output file"));
        }

        public void parse(String args[]){
            int type = 0;
            String key = "";
            String out = null;
            String in = "";
            CommandLineParser prs = new DefaultParser();
            try {
                CommandLine cmd = prs.parse(options,args);
                if(cmd.hasOption('c')) {
                    type = 1;
                    key = cmd.getOptionValue('c');
                }
                if(cmd.hasOption('d')){
                    if (type == 0)
                        type = 2;
                    key = cmd.getOptionValue('d');
                }
                if(cmd.hasOption('o'))
                    out = cmd.getOptionValue('o');
                int i = 0;
                while (i < cmd.getArgList().size()){
                    if(cmd.getArgList().get(i).equals("-d")
                            || cmd.getArgList().get(i).equals("-c")
                            || cmd.getArgList().get(i).equals("-o"))
                        i += 2;
                    else
                        in = cmd.getArgList().get(i);
                }
                File fl = new File(in);
                if(!fl.isFile()) throw new IllegalArgumentException();
                if(type == 0) throw new IllegalArgumentException();
            } catch (ParseException e) {
                HelpFormatter hp = new HelpFormatter();
                hp.printHelp("ciphxor",options);
            } catch (IllegalArgumentException ex){
                System.out.println("Недопустимый синтаксис");
                HelpFormatter hp = new HelpFormatter();
                hp.printHelp("ciphxor",options);
            }
        }
    }
    public static void main(String args[]){
        c.parse(args);
    }
}
