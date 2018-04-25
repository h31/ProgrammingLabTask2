package task2.console;
import org.kohsuke.args4j.*;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CmdCheck {
    @Option(name = "-c", aliases = {"-d"}, metaVar = "key", required = true,
            usage = "Indicate coding or decoding")
    private String key;

    @Option(name = "-o", metaVar = "outputName", usage = "Output the file")
    private String outputName;

    @Argument(metaVar = "inputName", usage = "Input the file", required = true)
    private String inputName;

    public void readCmd(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
            if (!checkHexadecimal(key)) throw new IllegalArgumentException("Key is wrong");
            if (!(new File(inputName).exists())) throw new IllegalArgumentException("inputFile is empty");
            if (outputName == null) {
                StringBuilder sb = new StringBuilder();
                outputName = sb.append("need write down outputName").toString();
            }
        }catch (CmdLineException e) {
            throw new IllegalArgumentException("Command Line: [-c key] [-d key]" +
                    " inputname.txt [-o] outputname.txt");
        }
    }
    private Boolean checkHexadecimal(String args) {
        Pattern p = Pattern.compile("^(([a-fA-F0-9]{2})*)$");
        Matcher m = p.matcher(args);
        return m.matches();
    }

    public String getKey() {return this.key; }

    public String getInputName() {
        return this.inputName;
    }

    public String getOutputName() {
        return this.outputName;
    }
}
