package test;

import logic.Grep;
import org.junit.Test;


import java.io.IOException;


import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class grepTest {

    @Test
    public void grepWithWord() throws IOException {
        Grep g1 = new Grep("D://nikita//Studing//ProgrammingLabTask2//files//test1.txt");
        ArrayList<String> expected1 = new ArrayList<>();
        expected1.add("\uFEFFKotlin is a statically typed programming Language that");
        expected1.add("  Kotlin uses aggressive type inference to determine the type of values and expressions for which type");
        expected1.add("Kotlin is a fully supported programming language by Google");
        ArrayList<String> expected2 = new ArrayList<>();
        expected2.add(" While the syntax is not compatible with Java, kotlin");
        expected2.add("   The Android kotlin compiler lets the user choose between");
        assertEquals(expected1, g1.grepWithWord("Kotlin"));
        assertEquals(expected2, g1.grepWithWord("kotlin"));
    }

    @Test
    public void grepWithRegex() throws IOException {
        Grep g1 = new Grep("D://nikita//Studing//ProgrammingLabTask2//files//test1.txt");
        ArrayList<String> expected1 = new ArrayList<>();
        expected1.add("As of Android Studio 3.0 (October 2017)");
        expected1.add("  and is directly included in the Android Studio 3.0 IDE package as");
        expected1.add("  targeting Java 6- or Java 8-compatible bytecode.");
        ArrayList<String> expected2 = new ArrayList<>();
        expected2.add("Its primary development is from a team of JetBrains programmers based in Saint Petersburg, Russia.");
        expected2.add("Kotlin is a fully supported programming language by Google");
        assertEquals(expected1, g1.grepWithRegex("[0-9]"));
        assertEquals(expected2, g1.grepWithRegex("JetBrains|Google"));
    }

    @Test
    public void grepWithoutRegex() throws IOException {
        Grep g1 = new Grep("D://nikita//Studing//ProgrammingLabTask2//files//test1.txt");
        ArrayList<String> expected1 = new ArrayList<>();
        expected1.add("\uFEFFKotlin is a statically typed programming Language that");
        expected1.add("Its primary development is from a team of JetBrains programmers based in Saint Petersburg, Russia.");
        expected1.add("");
        expected1.add("  Kotlin uses aggressive type inference to determine the type of values and expressions for which type");
        expected1.add("   redundant type specifications.");
        expected1.add("");
        expected1.add("Kotlin is a fully supported programming language by Google");
        ArrayList<String> expected2 = new ArrayList<>();
        expected2.add("Its primary development is from a team of JetBrains programmers based in Saint Petersburg, Russia.");
        expected2.add("");
        expected2.add("   redundant type specifications.");
        expected2.add("");
        assertEquals(expected1, g1.grepWithoutRegex("Java|Android"));
        assertEquals(expected2, g1.grepWithoutRegex("Java|Android|Kotlin"));
    }

    @Test
    public void grepWithIgnoreRegister() {
        Grep g1 = new Grep("D://nikita//Studing//ProgrammingLabTask2//files//test1.txt");
        ArrayList<String> expected1 = new ArrayList<>();
        expected1.add("to JavaScript source code or use the LLVM compiler infrastructure.");
        expected1.add(" is designed to interoperate with Java CODE");
        expected1.add("  and is reliant on Java code from the existing");
        expected1.add("  targeting Java 6- or Java 8-compatible bytecode.");
        ArrayList<String> expected2 = new ArrayList<>();
        expected2.add("\uFEFFKotlin is a statically typed programming Language that");
        expected2.add("   has been left unstated. This reduces language verbosity relative to Java, which demands often entirely");
        expected2.add("Kotlin is a fully supported programming language by Google");
        assertEquals(expected1, g1.grepWithIgnoreRegister("CoDe"));
        assertEquals(expected2, g1.grepWithIgnoreRegister("Language"));
    }
}