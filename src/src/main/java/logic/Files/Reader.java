package logic.Files;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Reader {
    public String[] listOfLines = new String[0];
    String dirPath;
    String name;

    public Reader() {
    }

    public Reader(String dirPath, String name) {
        this.dirPath = dirPath;
        this.name = name;
    }

    public void readLines(String dirPath, String name) {
        try (FileReader reader = new FileReader(dirPath + name)) {
            int c;
            StringBuilder allContent = new StringBuilder();
            while ((c = reader.read()) != -1) {
                allContent.append((char) c);
            }
            listOfLines = allContent.toString().split("\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void readLines() {
        try (FileReader reader = new FileReader(dirPath + name)) {
            int c;
            StringBuilder allContent = new StringBuilder();
            while ((c = reader.read()) != -1) {
                allContent.append((char) c);
            }
            listOfLines = allContent.toString().split("\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<String> getLines(String dirPath, String name) {
        try (FileReader reader = new FileReader(dirPath + name)) {
            int c;
            StringBuilder allContent = new StringBuilder();
            while ((c = reader.read()) != -1) {
                allContent.append((char) c);
            }
            return new ArrayList<>(Arrays.asList(allContent.toString().split("\n")));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public ArrayList<String> getLines() {
        try (FileReader reader = new FileReader(dirPath + name)) {
            int c;
            StringBuilder allContent = new StringBuilder();
            while ((c = reader.read()) != -1) {
                allContent.append((char) c);
            }
            return new ArrayList<>(Arrays.asList(allContent.toString().split("\n")));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public ArrayList<String> getLines(File file) {
        try (FileReader reader = new FileReader(file.getPath())) {
            int c;
            StringBuilder allContent = new StringBuilder();
            while ((c = reader.read()) != -1) {
                allContent.append((char) c);
            }
            return new ArrayList<>(Arrays.asList(allContent.toString().split("\n")));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public Map<String, String> getPathFragments(File file) {
        Map<String, String> map = new HashMap<>();
        String path = file.getPath();
        String[] elements = path.split("/|\\.");
        map.put("extension", elements[elements.length - 1]);
        map.put("name", elements[elements.length - 2]);
        StringBuilder buildPath = new StringBuilder("/");
        for (int i = 0; i < elements.length - 2; i++) {
            buildPath.append(elements[i]).append("/");
        }
        map.put("path", buildPath.toString());
        return map;
    }

}
