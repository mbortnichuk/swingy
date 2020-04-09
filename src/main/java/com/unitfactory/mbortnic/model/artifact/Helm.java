package com.unitfactory.mbortnic.model.artifact;

public class Helm extends Artifact {

    public int hp = 90;

    public Helm(String type) {
        super(type);
    }

    public int getHp() {
        return this.hp;
    }
}
