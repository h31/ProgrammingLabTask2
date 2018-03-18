package com.project;

import com.project.UI.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        ConsoleUI.testMode("pack-rle -u -out /Users/sergey/Desktop/out /Users/sergey/Desktop/out.uz");
        ConsoleUI.create();
        System.out.println(System.currentTimeMillis());
    }
}
