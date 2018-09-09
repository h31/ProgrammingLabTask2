package logic;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Uniq {

    private List<String> strings;
    private Set<String> uniq;

    public Uniq(List<String> strings) {
        this.strings = strings;
    }

    public Set<String> getUniqStrings(boolean ignoreRegister, boolean onlyUniqStrings,
                               boolean countOfUniqString, int ignoredSymbols) {
        strings.add("\n");
        Set<String> result = new HashSet<>();
        int countOfStr = 1;
        for (int i = 0; i < strings.size(); i++) {
            if (i < strings.size() - 1) {
                String firstString = strings.get(i);
                String secondString = strings.get(i + 1);

                if (ignoreRegister) {
                    firstString = firstString.toLowerCase();
                    secondString = secondString.toLowerCase();
                }

                if (ignoredSymbols != -1 && ignoredSymbols < firstString.length()
                        && ignoredSymbols < secondString.length()) {
                    firstString = firstString.substring(ignoredSymbols);
                    secondString = secondString.substring(ignoredSymbols);
                }

                if (!firstString.equals(secondString)) {
                    String str = strings.get(i);
                    if (onlyUniqStrings) {
                        if (countOfStr == 1) result.add(str);
                        countOfStr = 1;
                    } else {
                        if (countOfUniqString) {
                            str = countOfStr + " " + str;
                            countOfStr = 1;
                        }
                        result.add(str);
                    }
                } else {
                    countOfStr++;
                }
            }
        }

        return result;
    }
}
