package com.unitfactory.mbortnic.model.players;

public class Statistics {

    public String type;
    public int attack;
    public int defence;
    public int hp;
    public int exp;
    public int lvl;
    public int pow;

    public Statistics() {}

    public Statistics(String type, int lvl, int attack, int defence, int hp, int exp) {
        this.type = type;
        this.lvl = lvl;
        this.attack = attack;
        this.defence = defence;
        this.hp = hp;
        this.exp = exp;
        this.pow = attack + defence;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) { // ???????????
        this.attack += attack;
        if (this.attack <= 0) {
            this.attack = 0;
        }
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence += defence;
        if (this.defence <= 0) {
            this.defence = 0;
        }
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp += hp;
        if (this.hp <= 0) {
            this.hp = 0;
        }
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int experience) { // ??????
        this.exp += exp;
        if (this.exp <= 0) {
            this.exp = 0;
        }
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) { // ????????
        this.lvl += lvl;
        if (this.lvl <= 0) {
            this.lvl = 0;
        }
    }

    public int getPow() {
        return pow;
    }

    public void setPow(int pow) {
        this.pow = pow;
    }
}
