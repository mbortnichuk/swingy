package com.unitfactory.mbortnic.writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    public static File file = null;
    public static FileWriter fileWriter;

    public Writer() {}

    public static void close() {
        try {
            if (fileWriter != null) {
                fileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createPlayersFile() {
        try {
            if (file == null) {
                file = new File("Players.txt");
                file.createNewFile();
            }
            fileWriter = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToPlayersFile(String line) {
        try {
            file = new File("Players.txt");
            fileWriter = new FileWriter(file, true);
            fileWriter.append(line + "\n");
            fileWriter.close();
            System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
