package com.unitfactory.mbortnic.controller;

import com.unitfactory.mbortnic.model.players.Enemy;
import com.unitfactory.mbortnic.model.players.Player;
import com.unitfactory.mbortnic.reader.Reader;
import com.unitfactory.mbortnic.view.console.ConsoleDisplay;

import java.util.Random;
import java.util.Scanner;

public class ConsoleController {

    public ConsoleController() {}

    public static boolean chance() {
        Random random = new Random();
        int ch = random.nextInt(2) + 1;
        if (ch == 1) {
            return true;
        }
        return false;
    }

    public static void start(Player player) {
        ConsoleMap consoleMap = new ConsoleMap(player);
        consoleMap.showMap();
        ConsoleDisplay.directions();
        Scanner scanner = new Scanner(System.in);
//        Console console;
//        console = System.console();
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            if (str.matches("\\s*[1-5]\\s*")) {
                int dir = Integer.parseInt(str);
                switch (dir) {
                    case 1:
                        consoleMap.updatePlayerPos(1, 0);
                        consoleMap.showMap();
                        ConsoleDisplay.directions();
                        break;
                    case 2:
                        consoleMap.updatePlayerPos(0, 1);
                        consoleMap.showMap();
                        ConsoleDisplay.directions();
                        break;
                    case 3:
                        consoleMap.updatePlayerPos(-1, 0);
                        consoleMap.showMap();
                        ConsoleDisplay.directions();
                        break;
                    case 4:
                        consoleMap.updatePlayerPos(0, -1);
                        consoleMap.showMap();
                        ConsoleDisplay.directions();
                        break;
                    case 5:
                        System.exit(0);
                    default:
                        System.out.println("Choose correct direction");
                }
            } else {
                System.out.println("Choose correct direction");
            }
        }
    }

    public static int battle(Player player, Enemy enemy) {
        Random random = new Random();
        int damage = 0;
        int battle = 0;
        int victory = 0;
        if (chance() == true || player.getStatistics().getPow() > enemy.getPow()) {
            battle = 1;
        }
        if (player.getStatistics().getHp() > 0) {
            while (player.getStatistics().getHp() > 0 && enemy.getHp() > 0) {
                System.out.println("Enemy HP: " + enemy.getHp() + "\nPlayer HP: " + player.getStatistics().getHp());
                if (battle == 0) {
                    damage = random.nextInt(30) + 1;
                    if (enemy.getHp() > 0) {
                        player.getStatistics().setHp(-damage);
                        Reader.updatePlayersList(player);
                        System.out.println("Due to enemy attack you lost " + damage + " HP");
                        if (player.getStatistics().getHp() <= 0) {
                            victory = 0;
                            break;
                        }
                        battle = 1;
                    }
                } else if (battle == 1) {
                    damage = random.nextInt(50) + 1;
                    if (player.getStatistics().getHp() > 0) {
                        enemy.setHp(-damage);
                        System.out.println("Due to your attack your enemy lost " + damage + " HP");
                        if (enemy.getHp() <= 0) {
                            victory = 1;
                            break;
                        }
                        battle = 0;
                    }
                }
            }
        } else {
            System.out.println("You do not have enough HP to fight (" + player.getStatistics().getHp() + ")");
        }
        return victory;
    }
}
