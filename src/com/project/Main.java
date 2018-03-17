package com.project;

import com.project.casing.Receiver;
import com.project.insides.files.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        Receiver.testMode("pack-rle -u -out /Users/sergey/Desktop/text1 /Users/sergey/Desktop/archive3.uz");
        Receiver.create();
        System.out.println(System.currentTimeMillis());
    }
}
