package ru.spbstu.kspt.task2;

import org.junit.jupiter.api.Test;
import ru.spbstu.kspt.task2.logic.Transpose;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LogicTest {
    @Test
    void transpose() {
        Transpose t = new Transpose(true, true, 4, "");
        List<List<String>> l = new ArrayList<>();
        List<String> l1 = new ArrayList<>();
        List<String> l2 = new ArrayList<>();
        List<String> l3 = new ArrayList<>();
        l1 = Arrays.asList("1ULTRA", "2FUCK", "3DREMAL");
        l2 = Arrays.asList("4KEKARB", "5CHUH", "6LELELELE", "7r", "8r");
        l3 = Arrays.asList("9OBANA", "0EKKEKE");
        l = Arrays.asList(l1, l2, l3);
        System.out.println(l);
        /*System.out.println(t.format(l1));
        System.out.println(t.format(l2));
        System.out.println(t.format(l3));*/
        System.out.println(t.transpose(l));
    }
}