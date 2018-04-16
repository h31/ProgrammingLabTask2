package console

import com.beust.jcommander.Parameter

class Args {
    @Parameter(names = ["-r"], description = "Check subdirectories")
    var checkSubdirectories: Boolean = false

    @Parameter(names = ["-d"], description = "Directory for finding")
    var directory: String? = null

    @Parameter(description = "File name for finding", required = true)
    var filename: List<String> = mutableListOf()
}