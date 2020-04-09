package com.unitfactory.mbortnic.controller;

import com.unitfactory.mbortnic.model.players.Enemy;
import com.unitfactory.mbortnic.model.players.NewPlayer;
import com.unitfactory.mbortnic.model.players.Player;
import com.unitfactory.mbortnic.reader.Reader;
import com.unitfactory.mbortnic.view.gui.GuiDisplay;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

public class GuiMap extends JFrame {

    public static int cordY;
    public static int cordX;
    public int[][] map;
    public int size;
    public int enemies;
    public int posx;
    public int posy;
    public int xold;
    public int yold;
    public int lvl;
    public Player player;
    public Enemy enemy = new Enemy();
    public boolean set = false;
    public JTextArea textArea = new JTextArea();
    public JFrame jFrame;
    public static ArrayList<Enemy> enemyArr = new ArrayList<Enemy>();
    public static ArrayList<Enemy> tempArr = new ArrayList<Enemy>();

    public GuiMap(Player player, JFrame jFrame) {
        this.player = player;
        this.jFrame = jFrame;
    }

    public void setMapSize() {
        size = (player.getStatistics().getLvl() - 1) * 5 + 10 - (player.getStatistics().getLvl() % 2);
        cordX = size;
        cordY = size;
        map = new int[size][size];
    }

    public void setEnemies() {
        this.enemies = player.getStatistics().getLvl() * 8;
        //area.append(this.enemies + " enemy number\n");
    }

    public static void registerEnemy(Enemy enemy) {
        if (enemyArr.contains(enemy))
            return;
        enemyArr.add(enemy);
    }

    public void createEnemies() {
        for (int i = 0; i < this.enemies; i++) {
            Random random = new Random();
            int xpose = random.nextInt(size);
            int ypose = random.nextInt(size);
            while (ypose == this.posy || xpose == this.posx) {
                xpose = random.nextInt(size);
                ypose = random.nextInt(size);
            }
            enemy = NewPlayer.newGameEnemy(player);
            enemy.setEnemyPosition(xpose, ypose);
            registerEnemy(enemy);
        }
    }

    public Enemy getEnemyCollision() {
        for (int i = 0; i < enemyArr.size(); i++) {
            if (enemyArr.get(i).getEnemyYcord() == this.posy && enemyArr.get(i).getEnemyXcord() == this.posx)
                return enemyArr.get(i);
        }
        return null;
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
        this.posx = x;
        this.posy = y;
    }

    public void hasVictory() {
        if (player.getStatistics().getExp() > 1000 && player.getStatistics().getExp() < 2450) {
            this.lvl = 1;
        } else if (player.getStatistics().getExp() >= 2450 && player.getStatistics().getExp() < 4800) {
            this.lvl = 2;
        } else if (player.getStatistics().getExp() >= 4800 && player.getStatistics().getExp() < 8050) {
            this.lvl = 3;
        } else if (player.getStatistics().getExp() >= 8050 && player.getStatistics().getExp() < 12200) {
            this.lvl = 4;
        } else if (player.getStatistics().getExp() == 12200) {
            this.lvl = 5;
        }

        if (this.lvl > player.getStatistics().getLvl()) {
            player.getStatistics().setLvl(this.lvl);
            Reader.updatePlayersList(player);
            JOptionPane.showMessageDialog(null, "You've got level up!");
            enemyArr.removeAll(enemyArr);
            textArea.append(this.lvl + "\n");
        } else if (this.lvl == player.getStatistics().getLvl()) {
            textArea.selectAll();
            textArea.replaceSelection("");
            tempArr.addAll(enemyArr);
            enemyArr.removeAll(enemyArr);
        }
    }

    public void updatePlayerPos(int posx, int posy) {
        this.xold = this.posx;
        this.yold = this.posy;
        this.posx += posx;
        if (this.posx < 0) {
            this.posx = (int) (size / 2);
            upgradeExperience(1);
            hasVictory();
            set = false;
            showMap();
        } else if (this.posx >= this.size) {
            this.posx = (int) (size / 2);
            upgradeExperience(1);
            hasVictory();
            set = false;
            showMap();
        } else {
            textArea.selectAll();
            textArea.replaceSelection("");
            showMap();
        }

        this.posy += posy;
        if (this.posy < 0) {
            this.posy = (int) (size / 2);
            upgradeExperience(1);
            hasVictory();
            set = false;
            showMap();
        } else if (this.posy >= this.size) {
            this.posy = (int) (size / 2);
            upgradeExperience(1);
            hasVictory();
            set = false;
            showMap();
        } else {
            textArea.selectAll();
            textArea.replaceSelection("");
            showMap();
        }
    }

    public JTextArea showMap() {

        if (set == false) {
            setMapSize();
            setPlayerPos();
            setEnemies();
            createEnemies();
            set = true;
        }

        /* initialize map array to zeros */
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                map[y][x] = 0;
            }
        }

        //Villian random initialization
        for (Enemy enemy : enemyArr) {
            map[enemy.getEnemyYcord()][enemy.getEnemyXcord()] = enemy.getIdType();
        }

        //initializing hero
        map[this.posy][this.posx] = 4;

        //Villian collision
        for (Enemy enemy : enemyArr) {
            boolean t = collidedWithEnemy(this.posy, this.posx, enemy.getEnemyYcord(), enemy.getEnemyXcord());
            if (t == true) {
                enemyArr.remove(enemy);
                set = false;
                showMap();
                break;
            }

        }

        textArea.append("Level: " + String.valueOf(player.getStatistics().getLvl()) + " | " +
                "Attack: " + player.getStatistics().getAttack() + " | " +
                "Defence: " + player.getStatistics().getDefence() + " | " +
                "Hit points: " + String.valueOf(player.getStatistics().getHp()) + " | " +
                "Experience: " + String.valueOf(player.getStatistics().getExp()) + "\n\n");

        for (int y = 0; y < cordY; y++) {
            for (int x = 0; x < cordX; x++) {
                switch (map[y][x]) {
                    case 0:
                        textArea.append("|    |");
                        break;
                    case 1:
                        textArea.append("| d |");
                        break;
                    case 2:
                        textArea.append("| u |");
                        break;
                    default:
                        textArea.append("| H |");
                        break;
                }
            }
            textArea.append("\n");
        }

        return textArea;
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
                System.out.println("Your adventure will continue... But next time...");
                GuiDisplay.endGame();
            }
            hasVictory();
        } else if (type == 2) {
            hasVictory();
        }
    }

    public boolean collidedWithEnemy(int yh, int xh, int ye, int xe) {
        if ((xh == xe) && (yh == ye)) {
            enemy = getEnemyCollision();
            int showButton = JOptionPane.YES_NO_OPTION;
            int showResult = JOptionPane.showConfirmDialog(this, "Do you want to encounter your enemy?", "Fight or Flee?", showButton);
            if (showResult == 0) {
                if (battle() == 1) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "You have lost\n\n<<<<GAME OVER>>>>>");
                    jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));

                }
            } else {
                Random random = new Random();
                int flee = random.nextInt(2) + 1;

                if (flee == 1) {
                    textArea.selectAll();
                    textArea.replaceSelection("");
                    textArea.append("Goose\n\n");
                    this.posy = this.yold;
                    this.posx = this.xold;
                } else {
                    if (battle() == 1) {
                        enemyArr.remove(enemy);
                        upgradeExperience(2);
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(null, "You lost your battle!\n\nSWINGY is over for you!");
                        jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));
                    }
                }

            }
        }
        return false;
    }

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
