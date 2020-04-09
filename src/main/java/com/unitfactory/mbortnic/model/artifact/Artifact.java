package com.unitfactory.mbortnic.model.artifact;

import java.util.Random;

public class Artifact {
    public String type;
    public static final String[] ARTIFACTS = {"ARMOR", "HELM", "WEAPON"};

    public static String randomArtifact() {
        Random random = new Random();
        return(ARTIFACTS[random.nextInt(3)]);
    }

    Artifact(String type) {
        this.type = type;
    }

    public static  String[] getArtifacts() {
        return ARTIFACTS;
    }

    public String getType() {
        return this.type;
    }
}
