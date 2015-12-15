package com.csgo.iz.modal.bean;

import java.io.Serializable;

public class LastMatchStats implements Serializable {
	private String roundsWonRatio, matchResult;
	private Weapon bestWeapon;
	private double kda;
	private int kills, deaths, damage, moneyspent, mvp, dominations, dominationRevenge;

	public LastMatchStats(String roundsWonRatio, String matchResult, Weapon bestWeapon, double kda, int kills,
			int deaths, int damage, int moneyspent, int mvp, int dominations, int dominationRevenge) {
		super();
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

	public String getRoundsWonRatio() {
		return roundsWonRatio;
	}

	public void setRoundsWonRatio(String roundsWonRatio) {
		this.roundsWonRatio = roundsWonRatio;
	}

	public String getMatchResult() {
		return matchResult;
	}

	public void setMatchResult(String matchResult) {
		this.matchResult = matchResult;
	}

	public Weapon getBestWeapon() {
		return bestWeapon;
	}

	public void setBestWeapon(Weapon bestWeapon) {
		this.bestWeapon = bestWeapon;
	}

	public double getKda() {
		return kda;
	}

	public void setKda(double kda) {
		this.kda = kda;
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getMoneyspent() {
		return moneyspent;
	}

	public void setMoneyspent(int moneyspent) {
		this.moneyspent = moneyspent;
	}

	public int getMvp() {
		return mvp;
	}

	public void setMvp(int mvp) {
		this.mvp = mvp;
	}

	public int getDominations() {
		return dominations;
	}

	public void setDominations(int dominations) {
		this.dominations = dominations;
	}

	public int getDominationRevenge() {
		return dominationRevenge;
	}

	public void setDominationRevenge(int dominationRevenge) {
		this.dominationRevenge = dominationRevenge;
	}
}
