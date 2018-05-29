import org.junit.jupiter.api.Test;
import ru.spbstu.kspt.task2.logic.Transposition;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class GeneralTest {
    @Test
    void firstTest() {
        Transposition t = new Transposition(true, true, 4, "src/test/java/outputFile.txt");
        t.writer("src/test/java/inputFile.txt");
        try {
            String sCurrentLine;
            List<String> listOfMainFile = new ArrayList<>();
            List<String> listOfFirstFile = new ArrayList<>();
            BufferedReader br1 = new BufferedReader(new FileReader("src/test/java/firstFileForTest.txt"));
            BufferedReader br2 = new BufferedReader(new FileReader("src/test/java/outputFile.txt"));
            while ((sCurrentLine = br1.readLine()) != null) {
                listOfMainFile.add(sCurrentLine);
            }
            while ((sCurrentLine = br2.readLine()) != null) {
                listOfFirstFile.add(sCurrentLine);
            }
            assertEquals(listOfFirstFile, listOfMainFile);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter stream = new FileWriter("src/test/java/outputFile.txt");
            BufferedWriter out = new BufferedWriter(stream);
            out.write("");
            out.close();
        } catch (Exception e)
        {System.err.println("Error in file cleaning: " + e.getMessage());
        }
    }

    @Test
    void secondTest() {
        Transposition t = new Transposition(false, true, 0, "src/test/java/outputFile.txt");
        t.writer("src/test/java/inputFile.txt");
        try {
            String sCurrentLine;
            List<String> listOfMainFile = new ArrayList<>();
            List<String> listOfSecondFile = new ArrayList<>();
            BufferedReader br1 = new BufferedReader(new FileReader("src/test/java/secondFileForTest.txt"));
            BufferedReader br2 = new BufferedReader(new FileReader("src/test/java/outputFile.txt"));
            while ((sCurrentLine = br1.readLine()) != null) {
                listOfMainFile.add(sCurrentLine);
            }
            while ((sCurrentLine = br2.readLine()) != null) {
                listOfSecondFile.add(sCurrentLine);
            }
            assertEquals(listOfSecondFile, listOfMainFile);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter stream = new FileWriter("src/test/java/outputFile.txt");
            BufferedWriter out = new BufferedWriter(stream);
            out.write("");
            out.close();
        } catch (Exception e)
        {System.err.println("Error in file cleaning: " + e.getMessage());
        }
    }

    @Test
    void thirdTest() {
        Transposition t = new Transposition(true, true, 5, "src/test/java/outputFile.txt");
        t.writer("src/test/java/inputFile.txt");
        try {
            String sCurrentLine;
            List<String> listOfMainFile = new ArrayList<>();
            List<String> listOfThirdFile = new ArrayList<>();
            BufferedReader br1 = new BufferedReader(new FileReader("src/test/java/thirdFileForTest.txt"));
            BufferedReader br2 = new BufferedReader(new FileReader("src/test/java/outputFile.txt"));
            while ((sCurrentLine = br1.readLine()) != null) {
                listOfMainFile.add(sCurrentLine);
            }
            while ((sCurrentLine = br2.readLine()) != null) {
                listOfThirdFile.add(sCurrentLine);
            }
            assertNotEquals(listOfThirdFile, listOfMainFile);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter stream = new FileWriter("src/test/java/outputFile.txt");
            BufferedWriter out = new BufferedWriter(stream);
            out.write("");
            out.close();
        } catch (Exception e)
        {System.err.println("Error in file cleaning: " + e.getMessage());
        }
    }

    @Test
    void forthTest() {
        Transposition t = new Transposition(false, false, 1, "src/test/java/outputFile.txt");
        t.writer("src/test/java/inputFile.txt");
        try {
            String sCurrentLine;
            List<String> listOfMainFile = new ArrayList<>();
            List<String> listOfForthFile = new ArrayList<>();
            BufferedReader br1 = new BufferedReader(new FileReader("src/test/java/forthFileForTest.txt"));
            BufferedReader br2 = new BufferedReader(new FileReader("src/test/java/outputFile.txt"));
            while ((sCurrentLine = br1.readLine()) != null) {
                listOfMainFile.add(sCurrentLine);
            }
            while ((sCurrentLine = br2.readLine()) != null) {
                listOfForthFile.add(sCurrentLine);
            }
            assertEquals(listOfForthFile, listOfMainFile);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter stream = new FileWriter("src/test/java/outputFile.txt");
            BufferedWriter out = new BufferedWriter(stream);
            out.write("");
            out.close();
        } catch (Exception e)
        {System.err.println("Error in file cleaning: " + e.getMessage());
        }
    }
}