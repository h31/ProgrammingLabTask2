import logic.Grep;
import logic.GrepOption;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTest {
    private Path inputFile = Paths.get("src/test/java/file.txt");

    @Test
    public void grepWord() {
        Grep g1 = new Grep(new GrepOption("Kotlin", false, false, false));
        Grep g2 = new Grep(new GrepOption("kotlin", false, false, false));
        List<String> expected1 = Arrays.asList("\uFEFFKotlin is a statically typed programming Language that",
                "Kotlin is a fully supported programming language by Google.");
        List<String> expected2 = Arrays.asList(" While the syntax is not compatible with Java, kotlin");
        assertEquals(expected1, g1.find(inputFile));
        assertEquals(expected2, g2.find(inputFile));
    }

    @Test
    public void grepWithoutWordWithIgnoreCase() {
        Grep g1 = new Grep(new GrepOption("KOTLIN", false, true, true));
        List<String> expected1 = Arrays.asList("to JavaScript source code or use the LLVM compiler infrastructure.",
                "Its primary development based in Saint Petersburg, Russia.", "designed to interoperate with Java CODE");
        assertEquals(expected1, g1.find(inputFile));
    }

    @Test
    public void grepRegex() {
        Grep g1 = new Grep(new GrepOption("[0-9]", true, false, false));
        Grep g2 = new Grep(new GrepOption("JetBrains|Google", true, false, false));
        List<String> expected1 = Arrays.asList("As of Android Studio 3.0 (October 2017) kotLIng");
        List<String> expected2 = Arrays.asList("Kotlin is a fully supported programming language by Google.");
        assertEquals(expected1, g1.find(inputFile));
        assertEquals(expected2, g2.find(inputFile));
    }

    @Test
    public void grepWithoutRegex() {
        Grep g1 = new Grep(new GrepOption("Java|Android", true, true, false));
        Grep g2 = new Grep(new GrepOption("Java|Android|Kotlin", true, true, false));
        List<String> expected1 = Arrays.asList("\uFEFFKotlin is a statically typed programming Language that",
                "Its primary development based in Saint Petersburg, Russia.",
                "Kotlin is a fully supported programming language by Google.");
        List<String> expected2 = Arrays.asList("Its primary development based in Saint Petersburg, Russia.");
        assertEquals(expected1, g1.find(inputFile));
        assertEquals(expected2, g2.find(inputFile));
    }

    @Test
    public void grepRegexWithIgnoreCase() {
        Grep g1 = new Grep(new GrepOption("CoDe", true, false, true));
        Grep g2 = new Grep(new GrepOption("Language", true, false, true));
        List<String> expected1 = Arrays.asList("to JavaScript source code or use the LLVM compiler infrastructure.",
                "designed to interoperate with Java CODE");
        List<String> expected2 = Arrays.asList("\uFEFFKotlin is a statically typed programming Language that",
                "Kotlin is a fully supported programming language by Google.");
        assertEquals(expected1, g1.find(inputFile));
        assertEquals(expected2, g2.find(inputFile));
    }

    @Test
    public void grepWithoutRegexWithIgnoreCase() {
        Grep g1 = new Grep(new GrepOption("JAva|KOTlin|anDROID", true, true, true));
        List<String> expected1 = Arrays.asList("Its primary development based in Saint Petersburg, Russia.");
        assertEquals(expected1, g1.find(inputFile));
    }
}