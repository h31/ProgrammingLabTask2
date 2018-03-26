package console;

import com.beust.jcommander.JCommander;

import java.io.File;


import logic.Finder;

public class Main {

    public static void main(String...argv) {
        Finder finder = new Finder();
        Args args = new Args();
        JCommander jCommander = new JCommander(args);
        Main main = new Main();
        jCommander.addObject(main);
        jCommander.parse(argv);
        System.out.println(finder.find(args.r,args.dPath,args.name));
    }

}
