package com.csgo.iz.modal.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class OtherStats implements Parcelable {

    public static final Creator<OtherStats> CREATOR = new Creator<OtherStats>() {
        @Override
        public OtherStats createFromParcel(Parcel in) {
            return new OtherStats(in);
        }

        @Override
        public OtherStats[] newArray(int size) {
            return new OtherStats[size];
        }
    };
    public final int kills;
    public final int deaths;
    public final int headshot;
    public final int money_spent;
    public final int total_damage;
    public final int total_score;
    public final int bombSet;
    public final int bombDefused;
    public final int hostageRescured;
    public final int matchesPlayed;
    public final int matchesWon;
    public final int roundsPlayed;
    public final int roundsWon;
    public final int mvpStars;
    public final int weaponGivenToTeam;
    public final int killsWithEnemy, blindEnemies, killedInKnife, aimingSnipersKilled;
    public final int shotsFired, shotsHit, dominations, dominationOverKills, dominationRevenge;
    public final int pistolsWon, teasredEnemies, windowsBroken;

    public OtherStats(int kills, int deaths, int headshot, int money_spent, int total_damage, int total_score,
                      int bombSet, int bombDefused, int hostageRescured, int matchesPlayed, int matchesWon, int roundsPlayed,
                      int roundsWon, int mvpStars, int weaponGivenToTeam, int killsWithEnemy, int blindEnemies, int killedInKnife,
                      int aimingSnipersKilled, int shotsFired, int shotsHit, int dominations, int dominationOverKills,
                      int dominationRevenge, int pistolsWon, int teasredEnemies, int windowsBroken) {
        super();
        this.kills = kills;
        this.deaths = deaths;
        this.headshot = headshot;
        this.money_spent = money_spent;
        this.total_damage = total_damage;
        this.total_score = total_score;
        this.bombSet = bombSet;
        this.bombDefused = bombDefused;
        this.hostageRescured = hostageRescured;
        this.matchesPlayed = matchesPlayed;
        this.matchesWon = matchesWon;
        this.roundsPlayed = roundsPlayed;
        this.roundsWon = roundsWon;
        this.mvpStars = mvpStars;
        this.weaponGivenToTeam = weaponGivenToTeam;
        this.killsWithEnemy = killsWithEnemy;
        this.blindEnemies = blindEnemies;
        this.killedInKnife = killedInKnife;
        this.aimingSnipersKilled = aimingSnipersKilled;
        this.shotsFired = shotsFired;
        this.shotsHit = shotsHit;
        this.dominations = dominations;
        this.dominationOverKills = dominationOverKills;
        this.dominationRevenge = dominationRevenge;
        this.pistolsWon = pistolsWon;
        this.teasredEnemies = teasredEnemies;
        this.windowsBroken = windowsBroken;
    }

    protected OtherStats(Parcel in) {
        kills = in.readInt();
        deaths = in.readInt();
        headshot = in.readInt();
        money_spent = in.readInt();
        total_damage = in.readInt();
        total_score = in.readInt();
        bombSet = in.readInt();
        bombDefused = in.readInt();
        hostageRescured = in.readInt();
        matchesPlayed = in.readInt();
        matchesWon = in.readInt();
        roundsPlayed = in.readInt();
        roundsWon = in.readInt();
        mvpStars = in.readInt();
        weaponGivenToTeam = in.readInt();
        killsWithEnemy = in.readInt();
        blindEnemies = in.readInt();
        killedInKnife = in.readInt();
        aimingSnipersKilled = in.readInt();
        shotsFired = in.readInt();
        shotsHit = in.readInt();
        dominations = in.readInt();
        dominationOverKills = in.readInt();
        dominationRevenge = in.readInt();
        pistolsWon = in.readInt();
        teasredEnemies = in.readInt();
        windowsBroken = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(kills);
        dest.writeInt(deaths);
        dest.writeInt(headshot);
        dest.writeInt(money_spent);
        dest.writeInt(total_damage);
        dest.writeInt(total_score);
        dest.writeInt(bombSet);
        dest.writeInt(bombDefused);
        dest.writeInt(hostageRescured);
        dest.writeInt(matchesPlayed);
        dest.writeInt(matchesWon);
        dest.writeInt(roundsPlayed);
        dest.writeInt(roundsWon);
        dest.writeInt(mvpStars);
        dest.writeInt(weaponGivenToTeam);
        dest.writeInt(killsWithEnemy);
        dest.writeInt(blindEnemies);
        dest.writeInt(killedInKnife);
        dest.writeInt(aimingSnipersKilled);
        dest.writeInt(shotsFired);
        dest.writeInt(shotsHit);
        dest.writeInt(dominations);
        dest.writeInt(dominationOverKills);
        dest.writeInt(dominationRevenge);
        dest.writeInt(pistolsWon);
        dest.writeInt(teasredEnemies);
        dest.writeInt(windowsBroken);
    }
}
