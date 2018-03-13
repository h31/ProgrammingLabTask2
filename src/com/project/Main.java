package com.project;

import com.project.casing.Receiver;

public class Main {
    public static void main(String[] args) {
        Receiver.create();
        System.out.println(Receiver.getAnswer());
    }
}
