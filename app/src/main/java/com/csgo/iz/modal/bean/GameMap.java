package com.csgo.iz.modal.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class GameMap implements Parcelable {

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

    protected GameMap(Parcel in) {
        mapID = in.readInt();
        mapType = in.readString();
        mapName = in.readString();
        roundPlayed = in.readInt();
        roundsWon = in.readInt();
        mapAccuracy = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mapID);
        dest.writeString(mapType);
        dest.writeString(mapName);
        dest.writeInt(roundPlayed);
        dest.writeInt(roundsWon);
        dest.writeInt(mapAccuracy);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GameMap> CREATOR = new Creator<GameMap>() {
        @Override
        public GameMap createFromParcel(Parcel in) {
            return new GameMap(in);
        }

        @Override
        public GameMap[] newArray(int size) {
            return new GameMap[size];
        }
    };

    @Override
    public String toString() {
        return "GameMap [mapID=" + mapID + ", mapName=" + mapName + ", roundPlayed="
                + roundPlayed + ", roundsWon=" + roundsWon + ", mapAccuracy=" + mapAccuracy + "]";
    }
}
