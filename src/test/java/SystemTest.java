import console.GrepLauncher;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SystemTest {

    @Test
    public void grepLauncherTest() throws IOException {
        String inputFile = "src/test/java/file.txt";
        String[] args1 = {inputFile, "Kotlin"};
        String[] args2 = {inputFile, "[0-9]", "-r"};
        String[] args3 = {inputFile, "Java|Android|Kotlin", "-r", "-v"};
        String[] args4 = {inputFile, "CoDe", "-r", "-i"};
        String[] args5 = {inputFile, "JAva|KOTlin|anDROID", "-r", "-i", "-v"};
        String[] args6 = {inputFile, "KOTLIN", "-i", "-v"};
        GrepLauncher launcher1 = new GrepLauncher();
        GrepLauncher launcher2 = new GrepLauncher();
        GrepLauncher launcher3 = new GrepLauncher();
        GrepLauncher launcher4 = new GrepLauncher();
        GrepLauncher launcher5 = new GrepLauncher();
        GrepLauncher launcher6 = new GrepLauncher();
        launcher1.launch(args1);
        launcher2.launch(args2);
        launcher3.launch(args3);
        launcher4.launch(args4);
        launcher5.launch(args5);
        launcher6.launch(args6);
        List<String> expected1 = Arrays.asList("\uFEFFKotlin is a statically typed programming Language that",
                "Kotlin is a fully supported programming language by Google.");
        List<String> expected2 = Arrays.asList("As of Android Studio 3.0 (October 2017) kotLIng");
        List<String> expected3 = Arrays.asList("Its primary development based in Saint Petersburg, Russia.");
        List<String> expected4 = Arrays.asList("to JavaScript source code or use the LLVM compiler infrastructure.",
                "designed to interoperate with Java CODE");
        List<String> expected5 = Arrays.asList("Its primary development based in Saint Petersburg, Russia.");
        List<String> expected6 = Arrays.asList("to JavaScript source code or use the LLVM compiler infrastructure.",
                "Its primary development based in Saint Petersburg, Russia.", "designed to interoperate with Java CODE");
        assertEquals(expected1, launcher1.output);
        assertEquals(expected2, launcher2.output);
        assertEquals(expected3, launcher3.output);
        assertEquals(expected4, launcher4.output);
        assertEquals(expected5, launcher5.output);
        assertEquals(expected6, launcher6.output);
    }
}