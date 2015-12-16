package com.csgo.iz.modal.bean;

import java.io.Serializable;

public class GameMap implements Serializable {

    public final int mapID;
    public final String mapType;
    public final String mapName;
    public final int roundPlayed;
    public final int roundsWon;
    public final int mapAccuracy;

    public GameMap(int mapID, String mapType, String mapName, int roundPlayed, int roundsWon, int mapAccuracy) {
        this.mapID = mapID;
        this.mapType = mapType;
        this.mapName = mapName;
        this.roundPlayed = roundPlayed;
        this.roundsWon = roundsWon;
        this.mapAccuracy = mapAccuracy;
    }

    @Override
    public String toString() {
        return "GameMap [mapID=" + mapID + ", mapName=" + mapName + ", roundPlayed="
                + roundPlayed + ", roundsWon=" + roundsWon + ", mapAccuracy=" + mapAccuracy + "]";
    }
}
