package console;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class GrepParser {

        GrepParser(String command) {
            CmdLineParser parser = new CmdLineParser(this);
            try {
                parser.parseArgument(command.split("\\s+"));
            } catch (CmdLineException ignored) {
            }
        }

        @Option(name = "-v")
        private String findExceptRegex;

        @Option(name = "-i")
        private String ignoreCase;

        @Option(name = "-r")
        private String findOnRegex;

        @Argument(metaVar = "inputFileName")
        private String[] inputFileName;

        public String getFindExceptRegex() {
            return findExceptRegex;
        }

        public String getIgnoreCase() {
            return ignoreCase;
        }

        public String getFindOnRegex() {
            return findOnRegex;
        }

        public String getInputFileName() {
            return inputFileName[inputFileName.length - 1];
        }

}
