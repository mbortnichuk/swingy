package com.unitfactory.mbortnic.model.players;

import com.unitfactory.mbortnic.model.artifact.Artifact;

public class Enemy {

    public String[] ENEMIES = {"DEMON", "UNDEAD"};
    public Artifact artifact;
    public long counterId = 1;
    public long id;
    public int idType;
    public int cordX;
    public int cordY;
    public int lvl;
    public int attack;
    public int defence;
    public int hp;
    public int exp;
    public int pow;

    public Enemy() {}

    public Enemy(int lvl, int attack, int defence, int hp, int exp, Artifact artifact) {
        this.lvl = lvl;
        this.attack = attack;
        this.defence = defence;
        this.hp = hp;
        this.exp = exp;
        this.id = nextId();
        this.pow = attack + defence;
        this.artifact = artifact;
    }

    public long nextId() {
        counterId += 1;
        return counterId;
    }

    public int getEnemyXcord() {
        return this.cordX;
    }

    public int getEnemyYcord() {
        return this.cordY;
    }

    public int getIdType() {
        return this.idType;
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public int getLvl() {
        return lvl;
    }

    public int getHp() {
        return hp;
    }

    public int getPow() {
        return pow;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public void setEnemyPosition(int cordX, int cordY) {
        this.cordX = cordX;
        this.cordY = cordY;
    }

    public void setHp(int hp) {
        this.hp += hp;
        if (this.hp <= 0) {
            this.hp = 0;
        }
    }
}
