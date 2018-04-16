package logic;

import java.io.File;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FindTest {

    @Test
    void test1() throws Exception {
        Find path = new Find("src", true);
        assertEquals(new File("src/test/test1.txt"),
                path.find("test1.txt"));
    }

    @Test
    void test2() throws Exception {
        Find path = new Find("src/test", false);
        assertEquals(new File("src/test/test1.txt"),
                path.find("test1.txt"));
    }

    @Test
    void test3(){
        Find path = new Find("src", false);
        String messsage = "";
        try{
            path.find("test1.txt");
        }catch (Exception e){
            messsage = e.getMessage();
        }
        assertEquals("File does not exist", messsage);
    }

    @Test
    void test4() throws Exception {
        Find path = new Find("src", false);
        assertEquals(new File("src/test2.txt"),
                path.find("test2.txt"));
    }

    @Test
    void test5() throws Exception {
        Find path = new Find("src/test", true);
        String messsage = "";
        try{
            path.find("test3.txt");
        }catch (Exception e){
            messsage = e.getMessage();
        }
        assertEquals("File does not exist", messsage);
    }
}