package console;

import com.beust.jcommander.Parameter;

import java.util.ArrayList;

public class Args {

    @Parameter(names = "-r", description = "Searching in All subdirectories")
    public boolean r = false;

    @Parameter(names = "-d", description = "Name of directory")
    public String dPath = null;

    @Parameter(description = "File name", required = true)
    public ArrayList name = new ArrayList();

}
