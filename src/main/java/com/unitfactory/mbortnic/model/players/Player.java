package com.unitfactory.mbortnic.model.players;

import com.unitfactory.mbortnic.model.artifact.Artifact;

import javax.validation.constraints.NotNull;

public class Player {

    @NotNull
    public String player;
    @NotNull
    public Statistics statistics = new Statistics();
    @NotNull
    public Artifact artifact;

    public Player() {}

    protected Player(@NotNull String player, @NotNull Statistics statistics, @NotNull Artifact artifact) {
        this.player = player;
        this.statistics = statistics;
        this.artifact = artifact;
    }

    public String getPlayer() {
        return player;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }
}
