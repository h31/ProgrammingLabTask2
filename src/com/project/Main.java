package com.project;

import com.project.insides.files.Reader;

public class Main {
    public static void main(String[] args) {
        Reader reader = new Reader("/Users/sergey/Desktop/settings.md");
        System.out.println(reader.getAnswer());
    }
}
