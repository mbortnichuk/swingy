package com.unitfactory.mbortnic.model.artifact;

public class Weapon extends Artifact {

    public int attack = 80;

    public Weapon(String type) {
        super(type);
    }

    public int getAttack() {
        return this.attack;
    }
}
