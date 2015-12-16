package com.csgo.iz.modal.bean;

import java.io.Serializable;

public class LastMatchStats implements Serializable {

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
}
