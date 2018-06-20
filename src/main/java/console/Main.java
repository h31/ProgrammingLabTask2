package console;

import com.beust.jcommander.JCommander;
import logic.Finder;

import java.io.File;

public class Main {

    public static void main(String... args) {
        Finder finder = new Finder();
        Args argv = new Args();
        JCommander jCommander = new JCommander();
        if (args.length > 4) {
            throw new IllegalArgumentException("please, enter a ONE file name!");
        }
        try {
            jCommander.addObject(argv);
            jCommander.parse(args);
        } catch (Exception e) {
            throw new IllegalArgumentException("please, enter a file name!");
        }
        String name = argv.name.get(0).toString();
        if (argv.dPath != null) {
            File result = finder.find(argv.r, new File(argv.dPath), name);
            if (result != null) fileIsDetected = true;
            System.out.println(result);
        }
    }

    public static boolean fileIsDetected = false;

}
