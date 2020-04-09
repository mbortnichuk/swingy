package com.unitfactory.mbortnic.controller;

import com.unitfactory.mbortnic.messages.Messages;
import com.unitfactory.mbortnic.model.players.Enemy;
import com.unitfactory.mbortnic.model.players.Player;
import com.unitfactory.mbortnic.reader.Reader;
import com.unitfactory.mbortnic.utils.Util;
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
        ConsoleMap consoleMap;
        consoleMap = new ConsoleMap(player);
        consoleMap.showMap();
        ConsoleDisplay.directions();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            if (str.matches("\\s*[1-5]\\s*")) {
                int dir = Integer.parseInt(str);
                switch (dir) {
                    case 1:
                        consoleMap.updatePlayerPos(0, -1);
                        consoleMap.showMap();
                        ConsoleDisplay.directions();
                        break;
                    case 2:
                        consoleMap.updatePlayerPos(0, 1);
                        consoleMap.showMap();
                        ConsoleDisplay.directions();
                        break;
                    case 3:
                        consoleMap.updatePlayerPos(1, 0);
                        consoleMap.showMap();
                        ConsoleDisplay.directions();
                        break;
                    case 4:
                        consoleMap.updatePlayerPos(-1, 0);
                        consoleMap.showMap();
                        ConsoleDisplay.directions();
                        break;
                    case 5:
                        System.exit(0);
                    default:
                        System.out.println(Messages.INVALID_INPUT);
                }
            } else {
                System.out.println(Messages.INVALID_INPUT);
            }
        }
    }

    public static int battle(Enemy enemy, Player player) {
        Random random = new Random();
        int damage = 0;
        int battle = 0;
        int victory = 0;
        if (chance() == true || player.getStatistics().getPow() > enemy.getPow()) {
            battle = 1;
        }
        if (player.getStatistics().getHp() > 0) {
            while (player.getStatistics().getHp() > 0 && enemy.getHp() > 0) {
                System.out.println(Util.ANSI_CYAN_BACKGROUND + Util.ANSI_BLACK + "Enemy HP: " + enemy.getHp() + Util.ANSI_RESET + "\n" +
                        Util.ANSI_CYAN_BACKGROUND + Util.ANSI_BLACK + "Player HP: " + player.getStatistics().getHp() + Util.ANSI_RESET);
                if (battle == 0) {
                    damage = random.nextInt(30) + 1;
                    if (enemy.getHp() > 0) {
                        player.getStatistics().setHp(-damage);
                        Reader.updatePlayersList(player);
                        System.out.println(Util.ANSI_YELLOW_BACKGROUND + Util.ANSI_BLACK + "Due to enemy attack you lost " + damage + " HP" + Util.ANSI_RESET);
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
                        System.out.println(Util.ANSI_YELLOW_BACKGROUND + Util.ANSI_BLACK + "Due to your attack your enemy lost " + damage + " HP" + Util.ANSI_RESET);
                        if (enemy.getHp() <= 0) {
                            victory = 1;
                            break;
                        }
                        battle = 0;
                    }
                }
            }
        } else {
            System.out.println(Util.ANSI_PURPLE_BACKGROUND + "You do not have enough HP to fight (" + player.getStatistics().getHp() + ")" + Util.ANSI_RESET);
        }
        return victory;
    }
}
