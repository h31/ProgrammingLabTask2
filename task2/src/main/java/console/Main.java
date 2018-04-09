package console;

import com.beust.jcommander.JCommander;
import logic.Finder;

import java.io.File;

public class Main {

    public static void main(String... argv) {
        Finder finder = new Finder();
        Args args = new Args();
        JCommander jCommander = new JCommander();
        if (argv.length > 4) {
            throw new IllegalArgumentException("please, enter a ONE file name!");
        }
        try {
            jCommander.addObject(args);
            jCommander.parse(argv);
        } catch (Exception e) {
            throw new IllegalArgumentException("please, enter a file name!");
        }
        String name = args.name.get(0).toString();
        if (args.dPath != null) {
            System.out.println(finder.find(args.r, new File(args.dPath), name));
        }
    }

}
