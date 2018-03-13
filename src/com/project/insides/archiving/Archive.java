package com.project.insides.archiving;

import com.project.insides.ByteObject;
import com.project.insides.files.Reader;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class Archive {

    private static List<String> file;

    public static void start(String inputName, String outputName, boolean packing) {
        Reader reader = new Reader(inputName);
        file = reader.getAnswer();
        System.out.println(reader.getAnswer());
        if (packing) {
            packing(outputName);
        } else {
            unpacking(outputName);
        }
    }

    private static void packing(String outputName) {
        try {
            List<ByteObject> buffer = new ArrayList<>();
            for (String aFile : file) {

                byte[] bytesLine = aFile.getBytes("UTF-8");
                byte lastByte = bytesLine[0];
                buffer.add(new ByteObject(lastByte));
                for (int byteIndex = 1; byteIndex < bytesLine.length; byteIndex++) {
                    ByteObject lastElement = buffer.get(buffer.size() - 1);
                    if (bytesLine[byteIndex] == lastByte) {
                        lastElement.inc();
                    } else {
                        buffer.add(new ByteObject(lastByte));
                        lastByte = bytesLine[byteIndex];
                    }
                }
            }
            System.out.println(Arrays.toString(file.get(0).getBytes()));
            System.out.println(buffer);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("An error occurred while reading the file, invalid encoding");
        }

    }

    private static void unpacking(String outputName) {
        for (String line : file) {

        }
    }

}
