package ru.spbstu.kspt.task2;

import org.junit.jupiter.api.Test;
import ru.spbstu.kspt.task2.logic.Transpose;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LogicTest {

    @Test
    void transpose() {
        Transpose t = new Transpose(true, false, 4, "");
        List<List<String>> l = new ArrayList<>();
        List<String> l1 = new ArrayList<>();
        List<String> l2 = new ArrayList<>();
        List<String> l3 = new ArrayList<>();
        l1 = Arrays.asList("REEE1", "2KRUTO", "44eee3");
        l2 = Arrays.asList("4KEKAEM", "5TORPEDO", "6WEEEEE", "7TIttt", "8");
        l3 = Arrays.asList("9POTATO", "0LISTO4ek");
        l = Arrays.asList(l1, l2, l3);
        System.out.println(l);
        System.out.print(t.transpose(l));
    }
}