import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.*;

public class Main {
    private static Cli c = new Cli();

    public static void main(String args[]) {
        Program prg = c.parse(args);
        if (c.output != null) {
            try {
                if (!new File(c.output).exists()) {
                    new File(c.output).createNewFile();
                }
                PrintWriter out = new PrintWriter(c.output);
                out.println(prg.work());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                System.out.println(prg.work());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class Cli{
        @Option(name = "-c", usage = "Encrypt file with key")
        private String ckey = null;

        @Option(name = "-d", usage = "Decrypt ile with key")
        private String dkey = null;

        @Argument
        private String input;

        @Option(name = "-o", usage = "Output file")
        private String output;

        public Program parse(final String[] args) {
            CmdLineParser cmd = new CmdLineParser(this);
            if (args.length >= 1) {
                try {
                    cmd.parseArgument(args);
                } catch (CmdLineException e) {
                    System.out.println("ERROR: Unable to parse command-line options: " + e);
                }
            } else {
                cmd.printUsage(System.out);
                System.exit(-1);
            }
            if (!new File(input).isFile() || (ckey == null && dkey == null)) {
                cmd.printUsage(System.out);
                System.exit(-1);
            }
            try {
                if (dkey == null) {
                    return new Program(ckey, new BufferedReader(new FileReader(input), ckey.length()));
                } else {
                    return new Program(dkey, new BufferedReader(new FileReader(input), dkey.length()));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                }
            return null;
        }
    }
}
