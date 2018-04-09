package logic;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class FinderTest {

    @Test
    public void findTest() {
        Finder finder = new Finder();
        assertEquals(
                new File("C:\\Users\\evgen\\IdeaProjects\\" +
                        "ProgrammingLabTask2\\pom.xml"),
                finder.find(true, new File("C:\\Users\\evgen\\IdeaProjects\\" +
                        "ProgrammingLabTask2"), "pom.xml"));
    }

}
