package test;

import logic.Grep;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTests {

    private String inputDirectory = "src//Main//java//test//file.txt";

    @Test
    public void grepWord() {
        Grep g1 = new Grep("Kotlin", false, false, false);
        Grep g2 = new Grep("kotlin", false, false, false);
        List<String> expected1 = Arrays.asList("\uFEFFKotlin is a statically typed programming Language that",
                "  Kotlin uses aggressive type inference to determine the type of values and expressions for which type",
                "Kotlin is a fully supported programming language by Google");
        List<String> expected2 = Arrays.asList(" While the syntax is not compatible with Java, kotlin",
                "   The Android kotlin compiler lets the user choose between");
        assertEquals(expected1, g1.find(inputDirectory));
        assertEquals(expected2, g2.find(inputDirectory));
    }

    @Test
    public void grepWithoutWordWithIgnoreCase() {
        Grep g1 = new Grep("KOTLIN", false, true, true);
        List<String> expected1 = Arrays.asList("runs on the Java virtual machine and also can be compiled",
                "to JavaScript source code or use the LLVM compiler infrastructure.",
                "Its primary development is from a team of JetBrains programmers based in Saint Petersburg, Russia.",
                " is designed to interoperate with Java CODE", "", "  and is reliant on Java code from the existing",
                "  Java Class Library, such as the collections framework.",
                "   has been left unstated. This reduces language verbosity relative to Java, which demands often entirely",
                "   redundant type specifications.", "", "As of Android Studio 3.0 (October 2017)",
                " on the Android Operating System,",
                "  and is directly included in the Android Studio 3.0 IDE package as",
                "  an alternative to the standard Java compiler.",
                "  targeting Java 6- or Java 8-compatible bytecode.");
        assertEquals(expected1, g1.find(inputDirectory));
    }

    @Test
    public void grepRegex() {
        Grep g1 = new Grep("[0-9]", true, false, false);
        Grep g2 = new Grep("JetBrains|Google", true, false, false);
        List<String> expected1 = Arrays.asList("As of Android Studio 3.0 (October 2017)",
                "  and is directly included in the Android Studio 3.0 IDE package as",
                "  targeting Java 6- or Java 8-compatible bytecode.");
        List<String> expected2 = Arrays.asList("Its primary development is from a team of " +
                        "JetBrains programmers based in Saint Petersburg, Russia.",
                "Kotlin is a fully supported programming language by Google");
        assertEquals(expected1, g1.find(inputDirectory));
        assertEquals(expected2, g2.find(inputDirectory));
    }

    @Test
    public void grepWithoutRegex() {
        Grep g1 = new Grep("Java|Android", true, true, false);
        Grep g2 = new Grep("Java|Android|Kotlin", true, true, false);
        List<String> expected1 = Arrays.asList("\uFEFFKotlin is a statically typed programming Language that",
                "Its primary development is from a team of JetBrains programmers based in Saint Petersburg, Russia.", "",
                "  Kotlin uses aggressive type inference to determine the type of values and expressions for which type",
                "   redundant type specifications.", "", "Kotlin is a fully supported programming language by Google");
        List<String> expected2 = Arrays.asList("Its primary development is from a team of JetBrains programmers based" +
                " in Saint Petersburg, Russia.", "", "   redundant type specifications.", "");
        assertEquals(expected1, g1.find(inputDirectory));
        assertEquals(expected2, g2.find(inputDirectory));
    }

    @Test
    public void grepRegexWithIgnoreRegister() {
        Grep g1 = new Grep("CoDe", true, false, true);
        Grep g2 = new Grep("Language", true, false, true);
        List<String> expected1 = Arrays.asList("to JavaScript source code or use the LLVM compiler infrastructure.",
                " is designed to interoperate with Java CODE", "  and is reliant on Java code from the existing",
                "  targeting Java 6- or Java 8-compatible bytecode.");
        List<String> expected2 = Arrays.asList("\uFEFFKotlin is a statically typed programming Language that",
                "   has been left unstated. This reduces language verbosity relative to Java, which demands often entirely",
                "Kotlin is a fully supported programming language by Google");
        assertEquals(expected1, g1.find(inputDirectory));
        assertEquals(expected2, g2.find(inputDirectory));
    }

    @Test
    public void grepWithoutRegexWithIgnoreRegister() {
        Grep g1 = new Grep("JAva|KOTlin|anDROID", true, true, true);
        List<String> expected1 = Arrays.asList("Its primary development is from a team of JetBrains " +
                "programmers based in Saint Petersburg, Russia.", "", "   redundant type specifications.", "");
        assertEquals(expected1, g1.find(inputDirectory));
    }
}