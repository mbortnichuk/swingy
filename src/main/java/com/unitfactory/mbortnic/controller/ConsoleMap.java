package com.unitfactory.mbortnic.controller;

import com.unitfactory.mbortnic.messages.Messages;
import com.unitfactory.mbortnic.model.artifact.Armor;
import com.unitfactory.mbortnic.model.artifact.Helm;
import com.unitfactory.mbortnic.model.artifact.Weapon;
import com.unitfactory.mbortnic.model.players.Enemy;
import com.unitfactory.mbortnic.model.players.NewPlayer;
import com.unitfactory.mbortnic.model.players.Player;
import com.unitfactory.mbortnic.reader.Reader;
import com.unitfactory.mbortnic.utils.Util;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ConsoleMap {

    public static ArrayList<Enemy> enemyArr = new ArrayList<Enemy>();
    public static ArrayList<Enemy> tempArr = new ArrayList<Enemy>();
    public static int ymap;
    public static int xmap;
    public static int size;
    public static int[][] map;
    public int enemies;
    public int cordX;
    public int cordY;
    public int lvl;
    private boolean set = false;
    public static Player player;
    private Enemy enemy = new Enemy();

    public ConsoleMap(Player player) {
        this.player = player;
    }

    public void setPlayerPos() {
        int x = 0;
        int y = 0;
        if ((size % 2) == 1) {
            x = (int) (size / 2);
            y = (int) (size / 2);
        } else if ((size % 2) == 0) {
            x = (size / 2);
            y = (size / 2);
        }
        this.cordX = x;
        this.cordY = y;
    }

    public void setEnemies() {
        switch (this.enemies = player.getStatistics().getLvl() * 8) {}
    }

    public void hasVictory() {
        if (player.getStatistics().getExp() > 1000 && player.getStatistics().getExp() < 2450) {
            this.lvl = 1;
        }
        else if (player.getStatistics().getExp() >= 2450 && player.getStatistics().getExp() < 4800) {
            this.lvl = 2;
        }
        else if (player.getStatistics().getExp() >= 4800 && player.getStatistics().getExp() < 8050) {
            this.lvl = 3;
        }
        else if (player.getStatistics().getExp() >= 8050 && player.getStatistics().getExp() < 12200) {
            this.lvl = 4;
        } else if (player.getStatistics().getExp() == 12200) {
            this.lvl = 5;
        }

        if (this.lvl > player.getStatistics().getLvl()) {
            player.getStatistics().setLvl(this.lvl);
            Reader.updatePlayersList(player);
            System.out.println(Messages.ENEMY_DEFEATED);
            System.out.println("1. Continue playing");
            System.out.println("2. Exit game");

            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                if (str.matches("\\s*[1-2]\\s*")) {
                    int opt = Integer.parseInt(str);
                    if (opt == 1) {
                        enemyArr.removeAll(enemyArr);
                        ConsoleController.start(player);
                        System.out.println("Continue to play");
                    } else if (opt == 2) {
                        System.out.println(Messages.ON_EXIT);
                        System.exit(0);
                    }
                } else {
                    System.out.println(Messages.INVALID_INPUT);
                }
            }
        } else if (this.lvl == player.getStatistics().getLvl()) {
            enemyArr.removeAll(enemyArr);
        }
    }

    public void updatePlayerPos(int posx, int posy) {
        int xprev = this.cordX;
        int yprev = this.cordY; // posible may crash here -> set cordX
        this.cordX += posx;
        if (this.cordX < 0) {
            this.cordX = (int) (size / 2);
            upgradeExperience(1);
            hasVictory();
            set = false;
            showMap();
        } else if (this.cordX >= this.size) {
            this.cordX = (int) (size / 2);
            upgradeExperience(1);
            hasVictory();
            set = false;
            showMap();
        } else {
            showMap();
        }

        this.cordY += posy;
        if (this.cordY < 0) {
            this.cordY = (int) (size / 2);
            upgradeExperience(1);
            hasVictory();
            set = false;
            showMap();
        } else if (this.cordY >= this.size) {
            this.cordY = (int) (size / 2);
            upgradeExperience(1);
            hasVictory();
            set = false;
            showMap();
        } else {
            showMap();
        }
    }

    public void upgradeExperience(int type) {
        if (type == 1) {
            int exp;
            if (player.getStatistics().getExp() < 2450) {
                exp = 2450;
                player.getStatistics().setExp(exp);
            } else if (player.getStatistics().getExp() < 4800) {
                exp = 4800;
                player.getStatistics().setExp(exp);
            } else if (player.getStatistics().getExp() < 8050) {
                exp = 8050;
                player.getStatistics().setExp(exp);
            } else if (player.getStatistics().getExp() < 12200) {
                exp = 12200;
                player.getStatistics().setExp(exp);
            } else if (player.getStatistics().getExp() < 12201) {
                System.out.println(Messages.MAX_EXP_GAINED);
                System.exit(0);
            }
            hasVictory();
        } else if (type == 2) {
            player.getStatistics().setExp(player.getStatistics().getExp() + enemy.getPow());
            Reader.updatePlayersList(player);
            hasVictory();
        }
    }

    public static void setMapSize() {
        size = Util.GetMapSize(player.getStatistics().getLvl());
        xmap = size;
        ymap = size;
        map = new int[size][size];
    }

    public void showMap() {
        if (set == false) {
            setMapSize();
            setPlayerPos();
            setEnemies();
            if (tempArr.isEmpty()) {
                createEnemy();
            } else {
                enemyArr.addAll(tempArr);
            }
            set = true;
        }
        // initialize map array to zeros
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                map[y][x] = 0;
            }
        }
        // initialization of enemy
        for (Enemy enemy : enemyArr) {
            map[enemy.getEnemyYcord()][enemy.getEnemyXcord()] = enemy.getIdType();
        }
        // initialization of hero
        map[this.cordY][this.cordX] = 4;
        // check if hero  has crossed paths with enemy
        for (Enemy enemy : enemyArr) {
            boolean meetEnemy = collidedWithEnemy( enemy.getEnemyYcord(), enemy.getEnemyXcord(), this.cordY, this.cordX);
            if (meetEnemy == true) {
                break;
            }
        }
        System.out.println("Level: " + player.getStatistics().getLvl() + " | " + "Attack: " + player.getStatistics().getAttack() + " | " +
                "Defence: " + player.getStatistics().getDefence() + " | " + "HP: " + player.getStatistics().getHp() + " | " +
                "Experience: " + player.getStatistics().getExp() + "\n");

        for (int y = 0; y < ymap; y++) {
            for (int x = 0; x < xmap; x++) {
                switch (map[y][x]) {
                    case 0:
                        System.out.print("|   |");
                        break;
                    case 1:
                        System.out.print("| d |");
                        break;
                    case 2:
                        System.out.print("| u |");
                        break;
                    default:
                        System.out.print("| H |");
                        break;
                }
            }
            System.out.println();
        }
    }

    public void createEnemy() {
        for (int i = 0; i < this.enemies; i++) {
            Random random = new Random();
            int xpose = random.nextInt(size);
            int ypose = random.nextInt(size);
            while (ypose == this.cordY || xpose == this.cordX) {
                xpose = random.nextInt(size);
                ypose = random.nextInt(size);
            }
            enemy = NewPlayer.newGameEnemy(player);
            enemy.setEnemyPosition(xpose, ypose);
            registerEnemy(enemy);
        }
    }

    public static void registerEnemy(Enemy enemy) {
        if (enemyArr.contains(enemy)) {
            return;
        }
        enemyArr.add(enemy);
    }

    public static void deleteEnemy(Enemy enemy) {
        if (enemyArr.contains(enemy)) {
            enemyArr.remove(enemy);
        }
    }

    public Enemy getEnemyCollision() {
        for (int i = 0; i < enemyArr.size(); i++) {
            if (enemyArr.get(i).getEnemyYcord() == this.cordY && enemyArr.get(i).getEnemyXcord() == this.cordX) {
                return enemyArr.get(i);
            }
        }
        return null;
    }

    public boolean collidedWithEnemy( int yv, int xv, int yplayer, int xplayer) {
        if ((xplayer == xv) && (yplayer == yv)) {
            System.out.println(Messages.ENEMY_ENCOUNTER);
            System.out.println("1. Flee");
            System.out.println("2. Fight");

            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                if (str.matches("\\s*[1-2]\\s*")) {
                    int ch = Integer.parseInt(str);
                    if (ch == 1) {
                        Random random = new Random();
                        int flee = random.nextInt(2) + 1;
                        if (flee == 1) {
                            System.out.println(Messages.COWARDICE);
                            System.out.println("Your current XP: " + (player.getStatistics().getExp() - 10));
                            showMap();
                        }
                    } else if (ch == 2) {
                        Enemy encountered = getEnemyCollision();
                        int win = ConsoleController.battle(encountered, player);
                        if (win == 1) {
                            win(encountered);
                            deleteEnemy(encountered);
                            return true;
                        } else {
                            fail();
                            break;
                        }
                    } else {
                        System.out.println(Messages.INVALID_INPUT);
                    }
                } else {
                    System.out.println(Messages.INVALID_INPUT);
                }
            }
        }
        return false;
    }

    public void fail() {
        System.out.println(Messages.LOST_BATTLE);
        System.exit(0);
    }

    public void win(Enemy encountered) {
        enemyArr.remove(encountered);
        upgradeExperience(2);
        if (ConsoleController.chance() == true) {
            System.out.println("You killed encountered enemy, he dropped an artifact.\n" +
                    "You can pickup his artifact (" + encountered.getArtifact().getType() + ")");
            System.out.println("1. Pick up");
            System.out.println("2. Leave and continue fighting");

            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                if (str.matches("\\s*[1-2]\\s*")) {
                    int opt = Integer.parseInt(str);
                    if (opt == 1) {
                        String type = enemy.getArtifact().getType();
                        if (type.equals("WEAPON")) {
                            Weapon weapon = new Weapon("WEAPON");
                            player.setArtifact(weapon);
                            player.getStatistics().setAttack(70);
                            Reader.updatePlayersList(player);
                            ConsoleController.start(player);
//                            System.out.println("weapon");
                        } else if (type.equals("ARMOR")) {
                            Armor armor = new Armor("ARMOR");
                            player.setArtifact(armor);
                            player.getStatistics().setDefence(60);
                            Reader.updatePlayersList(player);
                            ConsoleController.start(player);
//                            System.out.println("armor");
                        } else if (type.equals("HELM")) {
                            Helm helm = new Helm("HELM");
                            player.setArtifact(helm);
                            player.getStatistics().setHp(80);
                            Reader.updatePlayersList(player);
                            ConsoleController.start(player);
//                            System.out.println("helm");
                        }
                    } else if (opt == 2) {
                        upgradeExperience(2);
                    }
                } else {
                    System.out.println(Messages.INVALID_INPUT);
                }
            }
        } else {
            upgradeExperience(2);
            System.out.println(Messages.WON_BATTLE);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.exit(0);
            }
            ConsoleController.start(player);
        }
    }

}


