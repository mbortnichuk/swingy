package com.unitfactory.mbortnic.model.players;

import com.unitfactory.mbortnic.model.artifact.Armor;
import com.unitfactory.mbortnic.model.artifact.Artifact;
import com.unitfactory.mbortnic.model.artifact.Helm;
import com.unitfactory.mbortnic.model.artifact.Weapon;
import com.unitfactory.mbortnic.model.skins.Archer;
import com.unitfactory.mbortnic.model.skins.BattleMage;
import com.unitfactory.mbortnic.model.skins.Undead;

import java.util.Random;

public class NewPlayer {

    public static Player newGamePlayer(String player, String hero, Statistics statistics, Artifact artifact) {
        if (player.equals("Archer")) {
            return new Archer(hero, statistics, artifact);
        } else if (player.equals("BattleMage")) {
            return new BattleMage(hero, statistics, artifact);
        } else {
            return null;
        }
    }

    public static Undead newGameEnemy(Player player) {
        Random random = new Random();
        int enemy = random.nextInt(2) + 1;
        String art = Artifact.randomArtifact();
        int lvl = 0;
        int attack = 0;
        int defense = 0;
        int hp = 0;
        int exp = 0;

        if (enemy == 1) {
            if (art.equals("WEAPON")){
                Weapon weapon = new Weapon("Weapon");
                lvl = player.getStatistics().getLvl();
                attack = 110 + weapon.getAttack();
                defense = 110;
                hp = 110;
                exp = 0;
                return new Undead(lvl, attack, defense, hp, exp, weapon);
            }
            else if (art.equals("ARMOR")){
                Armor armor = new Armor("Armor");
                lvl = player.getStatistics().getLvl();
                attack = 110;
                defense = 110 + armor.getDefence();
                hp = 110;
                exp = 0;
                return new Undead(lvl, attack, defense, hp, exp, armor);
            }
            else if (art.equals("HELM")){
                Helm helm = new Helm("Helm");
                lvl = player.getStatistics().getLvl();
                attack = 110 + helm.getHp();
                defense = 110;
                hp = 110;
                exp = 0;
                return new Undead(lvl, attack, defense, hp, exp, helm);
            }
        }
        else if (enemy == 2){
            if (art.equals("WEAPON")){
                Weapon weapon = new Weapon("Weapon");
                lvl = player.getStatistics().getLvl();
                attack = 110 + weapon.getAttack();
                defense = 110;
                hp = 110;
                exp = 0;
                return new Undead(lvl, attack, defense, hp, exp, weapon);
            }
            else if (art.equals("ARMOR")){
                Armor armor = new Armor("Armor");
                lvl = player.getStatistics().getLvl();
                attack = 110;
                defense = 110 + armor.getDefence();
                hp = 110;
                exp = 0;
                return new Undead(lvl, attack, defense, hp, exp, armor);
            }
            else if (art.equals("HELM")){
                Helm helm = new Helm("Helm");
                lvl = player.getStatistics().getLvl();
                attack = 110 + helm.getHp();
                defense = 110;
                hp = 110;
                exp = 0;
                return new Undead(lvl, attack, defense, hp, exp, helm);
            }
        }
        return null;
    }

}
