import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    private static Cli c = new Cli();

    public static void main(String args[]) {
        Cryptor prg = c.parse(args);
        try {
            prg.setMsg(Files.readAllBytes(Paths.get(c.input)));
            PrintWriter out = new PrintWriter(c.output);
            out.print(prg.crypt());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    static class Cli {
        @Option(name = "-c", usage = "Encrypt file with key")
        private String ckey = null;

        @Option(name = "-d", usage = "Decrypt file with key")
        private String dkey = null;

        @Argument
        private String input;

        @Option(name = "-o", usage = "Output file")
        private String output;

        Cryptor parse(final String[] args) {
            CmdLineParser cmd = new CmdLineParser(this);
            if (args.length >= 1 && (!new File(input).isFile() || (ckey == null && dkey == null))) {
                try {
                    cmd.parseArgument(args);
                } catch (CmdLineException e) {
                    System.err.println("ERROR: Unable to parse command-line options: " + e);
                }
            } else {
                cmd.printUsage(System.out);
                System.exit(-1);
            }
            if (c.output == null)
                c.output = c.input + ".cry";
            if (dkey == null) {
                return new Cryptor(ckey);
            } else {
                return new Cryptor(dkey);
            }
        }
    }
}
