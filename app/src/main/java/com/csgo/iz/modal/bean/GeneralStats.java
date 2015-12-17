package com.csgo.iz.modal.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class GeneralStats implements Parcelable {
    public final double kda;
    public final double accuracy;
    public final double headShot;
    public final double winRate;
    public final int stateKills, mvp;
    public final String timePlayed;

    public GeneralStats(int stateKills, double kda, double accuracy, int mvp, String timePlayed, double headShot, double winRate) {
        this.stateKills = stateKills;
        this.kda = kda;
        this.accuracy = accuracy;
        this.mvp = mvp;
        this.timePlayed = timePlayed;
        this.headShot = headShot;
        this.winRate = winRate;
    }

    protected GeneralStats(Parcel in) {
        kda = in.readDouble();
        accuracy = in.readDouble();
        headShot = in.readDouble();
        winRate = in.readDouble();
        stateKills = in.readInt();
        mvp = in.readInt();
        timePlayed = in.readString();
    }

    public static final Creator<GeneralStats> CREATOR = new Creator<GeneralStats>() {
        @Override
        public GeneralStats createFromParcel(Parcel in) {
            return new GeneralStats(in);
        }

        @Override
        public GeneralStats[] newArray(int size) {
            return new GeneralStats[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(kda);
        dest.writeDouble(accuracy);
        dest.writeDouble(headShot);
        dest.writeDouble(winRate);
        dest.writeInt(stateKills);
        dest.writeInt(mvp);
        dest.writeString(timePlayed);
    }
}
