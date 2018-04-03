package console;

import com.beust.jcommander.JCommander;
import logic.Finder;

import java.io.File;

public class Main {

    public static void main(String... argv) {
        Finder finder = new Finder();
        Args args = new Args();
        JCommander jCommander = new JCommander();
        try {
            if (args.name.size() > 1) {
                throw new IllegalArgumentException("please, enter one file name!");
            }
            jCommander.addObject(args);
            jCommander.parse(argv);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String name;
        if (args.name.size() > 0) {
            name = args.name.get(0).toString();
        } else {
            throw new IllegalArgumentException("please enter a file name");
        }
        assert args.dPath != null;
        System.out.println(finder.find(args.r, new File(args.dPath), name));
    }

}
