package logic;

import java.io.File;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FindTest {

    @Test
    void main() {
        Find path = new Find("C:\\Users\\loko\\IdeaProjects\\Find", true);

        assertEquals(new File("C:\\Users\\loko\\IdeaProjects\\Find\\src\\test\\test1.txt"),
                path.find("test1.txt"));

        path = new Find("C:\\Users\\loko\\IdeaProjects\\Find", false);

        assertEquals(new File("nothing"),
                path.find("test1.txt"));

        path = new Find("C:\\Users\\loko\\IdeaProjects\\Find\\src", false);

        assertEquals(new File("C:\\Users\\loko\\IdeaProjects\\Find\\src\\test2.txt"),
                path.find("test2.txt"));
    }

}