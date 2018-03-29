package com.project;

import com.project.UI.Parsers.LibParser;

public class Main {
    public static void main(String[] args) {
        LibParser libParser = new LibParser("pack-rle -z -out fileName fileNameee");
        System.out.println(libParser.isPacking());

    }
}
