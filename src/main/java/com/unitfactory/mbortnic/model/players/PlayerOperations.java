package com.unitfactory.mbortnic.model.players;

import com.unitfactory.mbortnic.model.artifact.Armor;
import com.unitfactory.mbortnic.model.artifact.Artifact;
import com.unitfactory.mbortnic.model.artifact.Helm;
import com.unitfactory.mbortnic.model.artifact.Weapon;
import com.unitfactory.mbortnic.writer.Writer;

public class PlayerOperations {
    public static String art;
    public static String statistics;
    public static int lvl;
    public static int attack;
    public static int defence;
    public static int hp;
    public static int exp;
    public static Player newPlayer = new Player();
    public static Player playerToDB = new Player();

    public static Player newPlayer(String player, long type){
        if (type == 1){
            return addPlayer("Archer", player);
        }
        else if (type == 2){
            return addPlayer("BattleMage", player);
        }
        else
            return null;
    }

    public static Player playerToDB(String hero){
        int i = 0;
        String[] elements;
        elements = hero.split(" ");

        String type = elements[0];
        String player = elements[1];
        int lvl = Integer.parseInt(elements[2]);
        int attack = Integer.parseInt(elements[3]);
        int defence = Integer.parseInt(elements[4]);
        int hp = Integer.parseInt(elements[5]);
        int exp = Integer.parseInt(elements[6]);
        String art = elements[7];
        Statistics statistics = new Statistics(type, lvl, attack, defence, hp, exp);

        if (art.equals("WEAPON")){
            Weapon weapon = new Weapon("Weapon");
            playerToDB = NewPlayer.newGamePlayer(type, player, statistics, weapon);
        }
        else if (art.equals("ARMOR")){
            Armor armor = new Armor("Armor");
            playerToDB = NewPlayer.newGamePlayer(type, player, statistics, armor);
        }
        else if (art.equals("HELM")){
            Helm helm = new Helm("Helm");
            playerToDB = NewPlayer.newGamePlayer(type, player, statistics, helm);
        }
        return playerToDB;
    }

    public static Player addPlayer(String type, String hero){
        art = Artifact.randomArtifact();

        if (art.equals("WEAPON")){
            Weapon weapon = new Weapon("Weapon");
            lvl = 1;
            attack = 100 + weapon.getAttack();
            defence = 100;
            hp = 100;
            exp = 1000;
            Statistics statistic = new Statistics(type, lvl, attack, defence, hp, exp);
            newPlayer = NewPlayer.newGamePlayer(type, hero, statistic, weapon);
            statistics = type + " " + hero + " " + lvl + " " + attack + " " + defence + " " + hp + " " + exp + " " + art;
        }
        else if (art.equals("ARMOR")){
            Armor armor = new Armor("Armor");
            lvl = 1;
            attack = 100;
            defence = 100 + armor.getDefence();
            hp = 100;
            exp = 1000;
            Statistics statistic = new Statistics(type, lvl, attack, defence, hp, exp);
            newPlayer = NewPlayer.newGamePlayer(type, hero, statistic, armor);
            statistics = type + " " + hero + " " + lvl + " " + attack + " " + defence + " " + hp + " " + exp + " " + art;
        }
        else if (art.equals("HELM")){
            Helm helm = new Helm("Helm");
            lvl = 1;
            attack = 100 + helm.getHp();
            defence = 100;
            hp = 100;
            exp = 1000;
            Statistics statistic = new Statistics(type, lvl, attack, defence, hp, exp);
            newPlayer = NewPlayer.newGamePlayer(type, hero, statistic, helm);
            statistics = type + " " + hero + " " + lvl + " " + attack + " " + defence + " " + hp + " " + exp + " " + art;
        }

        Writer.writeToPlayersFile(statistics);
        return newPlayer;
    }

}
