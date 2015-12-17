package com.csgo.iz.modal.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class LastMatchStats implements Parcelable {

    public static final Creator<LastMatchStats> CREATOR = new Creator<LastMatchStats>() {
        @Override
        public LastMatchStats createFromParcel(Parcel in) {
            return new LastMatchStats(in);
        }

        @Override
        public LastMatchStats[] newArray(int size) {
            return new LastMatchStats[size];
        }
    };

    public final String roundsWonRatio;
    public final String matchResult;
    public final Weapon bestWeapon;
    public final double kda;
    public final int kills;
    public final int deaths;
    public final int damage;
    public final int moneyspent;
    public final int mvp;
    public final int dominations;
    public final int dominationRevenge;

    public LastMatchStats(String roundsWonRatio, String matchResult, Weapon bestWeapon, double kda, int kills,
                          int deaths, int damage, int moneyspent, int mvp, int dominations, int dominationRevenge) {
        this.roundsWonRatio = roundsWonRatio;
        this.matchResult = matchResult;
        this.bestWeapon = bestWeapon;
        this.kda = kda;
        this.kills = kills;
        this.deaths = deaths;
        this.damage = damage;
        this.moneyspent = moneyspent;
        this.mvp = mvp;
        this.dominations = dominations;
        this.dominationRevenge = dominationRevenge;
    }

    protected LastMatchStats(Parcel in) {
        roundsWonRatio = in.readString();
        matchResult = in.readString();
        bestWeapon = in.readParcelable(Weapon.class.getClassLoader());
        kda = in.readDouble();
        kills = in.readInt();
        deaths = in.readInt();
        damage = in.readInt();
        moneyspent = in.readInt();
        mvp = in.readInt();
        dominations = in.readInt();
        dominationRevenge = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(roundsWonRatio);
        dest.writeString(matchResult);
        dest.writeParcelable(bestWeapon, flags);
        dest.writeDouble(kda);
        dest.writeInt(kills);
        dest.writeInt(deaths);
        dest.writeInt(damage);
        dest.writeInt(moneyspent);
        dest.writeInt(mvp);
        dest.writeInt(dominations);
        dest.writeInt(dominationRevenge);
    }
}
