package logic;

public class GrepOption {
    final String word;

    final boolean isRegex;

    final boolean isInvert;

    final boolean isIgnoreCase;

    public GrepOption(String word, boolean isRegex, boolean isInvert, boolean isIgnoreCase) {
        this.word = word;
        this.isRegex = isRegex;
        this.isInvert = isInvert;
        this.isIgnoreCase = isIgnoreCase;
    }
}