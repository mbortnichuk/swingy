package com.unitfactory.mbortnic.view.console;

import com.unitfactory.mbortnic.controller.ConsoleController;
import com.unitfactory.mbortnic.messages.Messages;
import com.unitfactory.mbortnic.model.players.Player;
import com.unitfactory.mbortnic.model.players.PlayerOperations;
import com.unitfactory.mbortnic.reader.Reader;
import com.unitfactory.mbortnic.utils.Util;

import java.util.Scanner;

public class ConsoleDisplay {

    public static void start() {
        String player;
        int type;
        int newPlayer;
        int opt = 0;
        int start;
        Player hero;

        try {
            newPlayer = createOrSelect();
            if (newPlayer == 1) {
                player = enterHeroName();
                type = choosePlayerType();
                hero = PlayerOperations.newPlayer(type, player);
                start = printStatistics(player, hero, type);
                if (start == 1) {
                    ConsoleController.start(hero);
                } else {
                    System.out.println(Messages.ON_EXIT);
                    System.exit(0);
                }
            } else if (newPlayer == 2) {
                Reader.getAllPlayers();
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNextLine()) {
                    String str = scanner.nextLine();
                    int counter = Reader.getLinesNumber();
                    if (Util.IsDigit(str) == true) {
                        try {
                            int id = Integer.parseInt(str);
                            if (id > 0 && id <= counter) {
                                opt = id;
                                break;
                            }
                        } catch (Exception e) {
                            System.out.println(Messages.INVALID_INPUT);
                        }
                    } else {
                        System.out.println(Messages.INVALID_INPUT);
                    }
                }
                hero = PlayerOperations.playerToDB(Reader.getPlayer(opt));
                ConsoleController.start(hero);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void directions() {
        System.out.println("\n" + Util.ANSI_GREEN_BACKGROUND + Util.ANSI_BLACK + "Select your way: " + Util.ANSI_RESET);
        System.out.println("1. North");
        System.out.println("2. South");
        System.out.println("3. East");
        System.out.println("4. West");
        System.out.println("5. Exit");
    }

    public static int createOrSelect() {
        System.out.println("\n" + Util.ANSI_GREEN_BACKGROUND + Util.ANSI_BLACK + "SWINGY" + Util.ANSI_RESET + "\n");
        System.out.println("1. Create player");
        System.out.println("2. Select player");
        int ch = 0;
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            if (str.matches("\\s*[1-2]\\s*")) {
                ch = Integer.parseInt(str);
                break;
            } else {
                System.out.println(Messages.INVALID_INPUT);
            }
        }
        return ch;
    }

    public static String enterHeroName() {
        System.out.println(Util.ANSI_GREEN_BACKGROUND + Util.ANSI_BLACK + "To continue enter your player name:" + Util.ANSI_RESET);
        String player = null;
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            player = scanner.nextLine();
            player = player.trim();
            if (player.length() > 0) {
                String[] ch = player.split("\\s");
                if (ch != null) {
                    player = String.join("_", ch);
                }
                break;
            } else {
                System.out.println(Messages.INVALID_NAME);
            }
        }
        return player;
    }

    public static int choosePlayerType() {
        System.out.println(Util.ANSI_GREEN_BACKGROUND + Util.ANSI_BLACK + "Select your player type:" + Util.ANSI_RESET);
        System.out.println("1. Archer");
        System.out.println("2. BattleMage");
        int ch = 0;
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            if (str.matches("\\s*[1-2]\\s*")) {
                ch = Integer.parseInt(str);
                break;
            } else {
                System.out.println(Messages.INVALID_INPUT);
            }
        }
        return ch;
    }

    public static void startOrQuit() {
        System.out.println(Util.ANSI_YELLOW_BACKGROUND + Util.ANSI_BLACK + "What would you do next?" + Util.ANSI_RESET);
        System.out.println("1. Start game");
        System.out.println("2. Exit game");
    }

    public static int printStatistics(String hero, Player player, long ch) {
        System.out.println(Util.ANSI_PURPLE_BACKGROUND + Util.ANSI_BLACK + "Welcome to SWINGY" + Util.ANSI_RESET);
        System.out.println(Util.ANSI_RED + hero + Util.ANSI_RESET + ", here is some of your statistics: ");
        if (ch == 1) {
            System.out.println(Util.ANSI_RED_BACKGROUND + Util.ANSI_BLACK + "Fighter " + hero + Util.ANSI_RESET);
            System.out.println("Level: " + player.getStatistics().getLvl());
            System.out.println("Attack: " + player.getStatistics().getAttack());
            System.out.println("Defence: " + player.getStatistics().getDefence());
            System.out.println("Experience: " + player.getStatistics().getExp());
            System.out.println("HP: " + player.getStatistics().getHp() + "\n");
            startOrQuit();
        } else if (ch == 2) {
            System.out.println(Util.ANSI_RED_BACKGROUND + Util.ANSI_BLACK + "Hero " + hero + Util.ANSI_RESET);
            System.out.println("Level: " + player.getStatistics().getLvl());
            System.out.println("Attack: " + player.getStatistics().getAttack());
            System.out.println("Defence: " + player.getStatistics().getDefence());
            System.out.println("Experience: " + player.getStatistics().getExp());
            System.out.println("HP: " + player.getStatistics().getHp() + "\n");
            startOrQuit();
        }
        int opt = 0;
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            if (str.matches("\\s*[1-2]\\s*")) {
                opt = Integer.parseInt(str);
                break;
            } else {
                System.out.println(Messages.INVALID_INPUT);
            }
        }
        return opt;
    }
}
