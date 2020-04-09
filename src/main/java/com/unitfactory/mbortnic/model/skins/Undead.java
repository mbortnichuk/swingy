package com.unitfactory.mbortnic.model.skins;

import com.unitfactory.mbortnic.model.artifact.Artifact;
import com.unitfactory.mbortnic.model.players.Enemy;

public class Undead extends Enemy {
    public Undead(int lvl, int attack, int defence, int hp, int exp, Artifact artifact) {
        super(lvl, attack, defence, hp, exp, artifact);
        int idType = 2;
        super.setIdType(idType);
    }
}
