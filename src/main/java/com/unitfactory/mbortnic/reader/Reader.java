package com.unitfactory.mbortnic.reader;

import com.unitfactory.mbortnic.messages.Messages;
import com.unitfactory.mbortnic.model.players.Player;

import java.io.*;

public class Reader {

//    public static final File FILE = new File("Players.txt");

    public static void updatePlayersList(Player player) {
        try {
            File file = new File("Players.txt");
            FileWriter fileWriter = new FileWriter(file);
            String[] elements = readLines();
            String delLine = null;
            String newLine = null;
            for (String str : elements) {
                if (str.contains(player.getPlayer()) && str.contains(player.getStatistics().getType())) {
                    delLine =str;
                }
            }
            newLine = (player.getStatistics().getType() + " " + player.getPlayer() + " " + Integer.toString(player.getStatistics().getLvl()) + " " +
                    Integer.toString(player.getStatistics().getAttack()) + " " + Integer.toString(player.getStatistics().getDefence()) + " " +
                    Integer.toString(player.getStatistics().getHp()) + " " + Integer.toString(player.getStatistics().getExp()) + " " +
                    player.getArtifact().getType().toUpperCase());
            if (newLine != null) {
                for (String str : elements) {
                    if (str.equals(delLine)) {
                        fileWriter.write(newLine + "\n");
                    } else {
                        fileWriter.write(str + "\n");
                    }
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(Messages.READ_FILE_ERROR + e);
        }
    }

    public static String[] readLines() {
        try {
            File file = new File("Players.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String str = null;
            String elements[] = new String[getLinesNumber()];
            int id = 0;
            while ((str = bufferedReader.readLine()) != null) {
                elements[id] = str;
                id++;
            }
            bufferedReader.close();
            return elements;
        } catch (IOException e) {
            e.getMessage();
        }
        return null;
    }

    public static int getLinesNumber() {
        try {
            File file = new File("Players.txt");
            FileReader fileReader = new FileReader(file);
            LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
            lineNumberReader.skip(Long.MAX_VALUE);
            int counter = lineNumberReader.getLineNumber();
            lineNumberReader.close();
            return counter;
        } catch (IOException e) {
            e.getMessage();
        }
        return -1;
    }

    public static void getAllPlayers() {
        String[] elements;
        int id = 0;
        int count = 1;
        elements = readLines();
        System.out.println("Choose from listed players");
        while (id < getLinesNumber()) {
            System.out.println(count++ + ". " + elements[id++]);
        }
    }

    public static String getPlayer(long player) {
        String[] elements = new String[getLinesNumber()];
        elements = readLines();
        return elements[(int)player - 1];
    }
}
