package ui;

import org.apache.commons.cli.*;

class Parser {

    private CommandLine cmd;

    Parser(String... args) {
        Options options = new Options();

        Option ignoreRegister = new Option("i", "ignore", false, "Ignore register");
        ignoreRegister.setRequired(false);
        options.addOption(ignoreRegister);

        Option onlyUnique = new Option("u", "unique", false, "Show only unique strings");
        onlyUnique.setRequired(false);
        options.addOption(onlyUnique);

        Option countUniqStr = new Option("c", "count",
                false, "Show count unique string before string");
        countUniqStr.setRequired(false);
        options.addOption(countUniqStr);

        Option stringLenIgnore = new Option("s", "string", true,
                "Count symbols for ignored with compare");
        stringLenIgnore.setRequired(false);
        options.addOption(stringLenIgnore);

        Option outputFileName = new Option("o", "output", true, "Output File name");
        outputFileName.setRequired(false);
        options.addOption(outputFileName);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);
        }
    }

    boolean registerIgnored() {
        return cmd.hasOption("ignore");
    }

    boolean onlyUniqueStrings() {
        return cmd.hasOption("unique");
    }

    boolean countOfString() {
        return cmd.hasOption("count");
    }

    int countIgnoreSymbols() {
        if (cmd.getOptionValue("string") != null) {
            return Integer.parseInt(cmd.getOptionValue("string"));
        } else {
            return -1;
        }
    }

    String outputFileName() {
        if (cmd.getOptionValue("output") != null) {
            return cmd.getOptionValue("output");
        } else {
            return null;
        }
    }

    String inputFileName() {
        String lastArg = cmd.getArgs()[cmd.getArgs().length - 1];
        if (lastArg.contains(".")) {
            return lastArg;
        } else {
            return null;
        }
    }

}
