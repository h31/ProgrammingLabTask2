package test;

import console.grepLauncher;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class systemTests {

    @Test
    public void grepLauncherTest() throws IOException {
        String[] args1 = {"src//Main//java//test//file.txt", "Kotlin"};
        String[] args2 = {"src//Main//java//test//file.txt", "[0-9]", "-r"};
        String[] args3 = {"src//Main//java//test//file.txt", "Java|Android|Kotlin", "-r", "-v"};
        String[] args4 = {"src//Main//java//test//file.txt", "CoDe", "-r", "-i"};
        String[] args5 = {"src//Main//java//test//file.txt", "JAva|KOTlin|anDROID", "-r", "-i", "-v"};
        String[] args6 = {"src//Main//java//test//file.txt", "KOTLIN", "-i", "-v"};
        grepLauncher launcher1 = new grepLauncher();
        grepLauncher launcher2 = new grepLauncher();
        grepLauncher launcher3 = new grepLauncher();
        grepLauncher launcher4 = new grepLauncher();
        grepLauncher launcher5 = new grepLauncher();
        grepLauncher launcher6 = new grepLauncher();
        launcher1.launch(args1);
        launcher2.launch(args2);
        launcher3.launch(args3);
        launcher4.launch(args4);
        launcher5.launch(args5);
        launcher6.launch(args6);
        List<String> expected1 = Arrays.asList("\uFEFFKotlin is a statically typed programming Language that",
                "  Kotlin uses aggressive type inference to determine the type of values and expressions for which type",
                "Kotlin is a fully supported programming language by Google");
        List<String> expected2 = Arrays.asList("As of Android Studio 3.0 (October 2017)",
                "  and is directly included in the Android Studio 3.0 IDE package as",
                "  targeting Java 6- or Java 8-compatible bytecode.");
        List<String> expected3 = Arrays.asList("Its primary development is from a team of JetBrains programmers based" +
                " in Saint Petersburg, Russia.", "", "   redundant type specifications.", "");
        List<String> expected4 = Arrays.asList("to JavaScript source code or use the LLVM compiler infrastructure.",
                " is designed to interoperate with Java CODE", "  and is reliant on Java code from the existing",
                "  targeting Java 6- or Java 8-compatible bytecode.");
        List<String> expected5 = Arrays.asList("Its primary development is from a team of JetBrains " +
                "programmers based in Saint Petersburg, Russia.", "", "   redundant type specifications.", "");
        List<String> expected6 = Arrays.asList("runs on the Java virtual machine and also can be compiled",
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
        assertEquals(expected1, launcher1.output);
        assertEquals(expected2, launcher2.output);
        assertEquals(expected3, launcher3.output);
        assertEquals(expected4, launcher4.output);
        assertEquals(expected5, launcher5.output);
        assertEquals(expected6, launcher6.output);
    }
}
