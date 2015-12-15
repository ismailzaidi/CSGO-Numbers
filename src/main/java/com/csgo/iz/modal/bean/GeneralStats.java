package com.csgo.iz.modal.bean;

import java.io.Serializable;

public class GeneralStats implements Serializable {
    private double kda, accuracy, headShot, winRate;
    private int stateKills, mvp;
    private String timePlayed;

    public GeneralStats(int stateKills, double kda, double accuracy, int mvp, String timePlayed, double headShot, double winRate) {
        super();
        this.stateKills = stateKills;
        this.kda = kda;
        this.accuracy = accuracy;
        this.mvp = mvp;
        this.timePlayed = timePlayed;
        this.headShot = headShot;
        this.winRate = winRate;
    }

    public int getStateKills() {
        return stateKills;
    }

    public void setStateKills(int stateKills) {
        this.stateKills = stateKills;
    }

    public double getKda() {
        return kda;
    }

    public void setKda(double kda) {
        this.kda = kda;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public int getMvp() {
        return mvp;
    }

    public void setMvp(int mvp) {
        this.mvp = mvp;
    }

    public String getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(String timePlayed) {
        this.timePlayed = timePlayed;
    }

    public double getHeadShot() {
        return headShot;
    }

    public void setHeadShot(double headShot) {
        this.headShot = headShot;
    }

    public double getWinRate() {
        return winRate;
    }

    public void setWinRate(double winRate) {
        this.winRate = winRate;
    }
}
