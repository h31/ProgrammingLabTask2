package logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class FinderTest {

    @Test
    void findTest() {
        Finder finder = new Finder();
        Assertions.assertEquals(
                new File("C:\\Users\\evgen\\IdeaProjects\\" +
                        "ProgrammingLabTask2\\task2\\pom.xml"),
                finder.find(true, new File("C:\\Users\\evgen\\IdeaProjects\\" +
                        "ProgrammingLabTask2\\task2"), "pom.xml"));
    }

}
