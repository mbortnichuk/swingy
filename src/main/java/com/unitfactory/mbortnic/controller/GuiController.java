package com.unitfactory.mbortnic.controller;

import com.unitfactory.mbortnic.model.players.Enemy;
import com.unitfactory.mbortnic.model.players.Player;
import com.unitfactory.mbortnic.reader.Reader;

import javax.swing.*;
import java.util.Random;

public class GuiController {

    public static Player player;
    public static Enemy enemy = new Enemy();

    public GuiController() {}

    public boolean chance() {
        Random random = new Random();
        int chance = random.nextInt(2) + 1;

        if (chance == 1)
            return true;
        return false;
    }

    public int battle() {
        int battle = 0;
        int win = 0;
        int damage = 0;
        Random random = new Random();

        if (chance() == true || player.getStatistics().getPow() > enemy.getPow()) {
            battle = 1;
        }

        if (player.getStatistics().getHp() > 0) {
            while (player.getStatistics().getHp() > 0 && enemy.getHp() > 0) {
                if (battle == 0) {
                    damage = random.nextInt(20) + 1;
                    if (enemy.getHp() > 0) {
                        player.getStatistics().setHp(-damage);
                        Reader.updatePlayersList(player);

                        if (player.getStatistics().getHp() <= 0) {
                            win = 0;
                            break;
                        }
                        battle = 1;
                    }
                } else if (battle == 1) {
                    damage = random.nextInt(50) + 1;
                    if (player.getStatistics().getHp() > 0) {
                        enemy.setHp(-damage);
                        if (enemy.getHp() <= 0) {
                            win = 1;
                            break;
                        }
                        battle = 0;
                    }
                }
            }
        } else
            JOptionPane.showMessageDialog(null, "You do not have enough strengh to fight! Go and relax a bit");
        return win;
    }
}
