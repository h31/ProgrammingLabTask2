package TailLogic;

import org.junit.jupiter.api.Test;

import java.io.IOException;


class TailTest {
    TailTest() throws IOException {
    }

    private Tail tail = new Tail("/home/vladislav/IdeaProjects/ProgrammingLabTask2/src/Test/TestFile",
            true);

    @Test
    void flagN() {
        tail.flagN(4);
        tail.flagN(10, "/home/vladislav/IdeaProjects/ProgrammingLabTask2/src/Test/Result");
    }
}
