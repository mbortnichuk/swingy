package com.unitfactory.mbortnic.model.skins;

import com.unitfactory.mbortnic.model.artifact.Artifact;
import com.unitfactory.mbortnic.model.players.Enemy;

public class Demon extends Enemy {
    public Demon(int lvl, int attack, int defence, int hp, int exp, Artifact artifact) {
        super(lvl, attack, defence, hp, exp, artifact);
        int idType = 1;
        super.setIdType(idType);
    }
}
