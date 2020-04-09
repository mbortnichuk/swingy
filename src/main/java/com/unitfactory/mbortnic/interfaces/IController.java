package com.unitfactory.mbortnic.interfaces;

import com.unitfactory.mbortnic.model.players.Enemy;

public interface IController {

    void setPlayerPos();
    void updatePlayerPos(int posx, int posy);
    void upgradeExperience(int type);
    void createEnemies();
    void setEnemies();
    Enemy getEnemyCollision();
    boolean collidedWithEnemy(int yp, int xp, int yv, int xv);
    void hasVictory();
    //    void setMapSize(); // ???????? static
    //    void registerEnemy(Enemy enemy); // ???????? static

}
