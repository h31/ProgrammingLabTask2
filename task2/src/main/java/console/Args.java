package console;

import com.beust.jcommander.Parameter;

public class Args {
    @Parameter(names = "-r", description = "searching in All subdirectories")
    public boolean r = false;

    @Parameter(names = "-d", description = "name of directorie", required = true)
    public String dPath = null;

    @Parameter(description = "file name", required = true)
    public String name = "";
}
