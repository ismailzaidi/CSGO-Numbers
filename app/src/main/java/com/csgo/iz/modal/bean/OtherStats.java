package com.csgo.iz.modal.bean;

import java.io.Serializable;

public class OtherStats implements Serializable {
	private int kills, deaths, headshot, money_spent, total_damage, total_score;
	private int bombSet, bombDefused, hostageRescured, matchesPlayed, matchesWon, roundsPlayed, roundsWon, mvpStars,
			weaponGivenToTeam;
	private int killsWithEnemy, blindEnemies, killedInKnife, aimingSnipersKilled;
	private int shotsFired, shotsHit, dominations, dominationOverKills, dominationRevenge;
	private int pistolsWon, teasredEnemies, windowsBroken;

	public OtherStats() {
	}

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

	public int getHeadshot() {
		return headshot;
	}

	public void setHeadshot(int headshot) {
		this.headshot = headshot;
	}

	public int getMoney_spent() {
		return money_spent;
	}

	public void setMoney_spent(int money_spent) {
		this.money_spent = money_spent;
	}

	public int getTotal_damage() {
		return total_damage;
	}

	public void setTotal_damage(int total_damage) {
		this.total_damage = total_damage;
	}

	public int getTotal_score() {
		return total_score;
	}

	public void setTotal_score(int total_score) {
		this.total_score = total_score;
	}

	public int getBombSet() {
		return bombSet;
	}

	public void setBombSet(int bombSet) {
		this.bombSet = bombSet;
	}

	public int getBombDefused() {
		return bombDefused;
	}

	public void setBombDefused(int bombDefused) {
		this.bombDefused = bombDefused;
	}

	public int getHostageRescured() {
		return hostageRescured;
	}

	public void setHostageRescured(int hostageRescured) {
		this.hostageRescured = hostageRescured;
	}

	public int getMatchesPlayed() {
		return matchesPlayed;
	}

	public void setMatchesPlayed(int matchesPlayed) {
		this.matchesPlayed = matchesPlayed;
	}

	public int getMatchesWon() {
		return matchesWon;
	}

	public void setMatchesWon(int matchesWon) {
		this.matchesWon = matchesWon;
	}

	public int getRoundsPlayed() {
		return roundsPlayed;
	}

	public void setRoundsPlayed(int roundsPlayed) {
		this.roundsPlayed = roundsPlayed;
	}

	public int getRoundsWon() {
		return roundsWon;
	}

	public void setRoundsWon(int roundsWon) {
		this.roundsWon = roundsWon;
	}

	public int getMvpStars() {
		return mvpStars;
	}

	public void setMvpStars(int mvpStars) {
		this.mvpStars = mvpStars;
	}

	public int getWeaponGivenToTeam() {
		return weaponGivenToTeam;
	}

	public void setWeaponGivenToTeam(int weaponGivenToTeam) {
		this.weaponGivenToTeam = weaponGivenToTeam;
	}

	public int getKillsWithEnemy() {
		return killsWithEnemy;
	}

	public void setKillsWithEnemy(int killsWithEnemy) {
		this.killsWithEnemy = killsWithEnemy;
	}

	public int getBlindEnemies() {
		return blindEnemies;
	}

	public void setBlindEnemies(int blindEnemies) {
		this.blindEnemies = blindEnemies;
	}

	public int getKilledInKnife() {
		return killedInKnife;
	}

	public void setKilledInKnife(int killedInKnife) {
		this.killedInKnife = killedInKnife;
	}

	public int getAimingSnipersKilled() {
		return aimingSnipersKilled;
	}

	public void setAimingSnipersKilled(int aimingSnipersKilled) {
		this.aimingSnipersKilled = aimingSnipersKilled;
	}

	public int getShotsFired() {
		return shotsFired;
	}

	public void setShotsFired(int shotsFired) {
		this.shotsFired = shotsFired;
	}

	public int getShotsHit() {
		return shotsHit;
	}

	public void setShotsHit(int shotsHit) {
		this.shotsHit = shotsHit;
	}

	public int getDominations() {
		return dominations;
	}

	public void setDominations(int dominations) {
		this.dominations = dominations;
	}

	public int getDominationOverKills() {
		return dominationOverKills;
	}

	public void setDominationOverKills(int dominationOverKills) {
		this.dominationOverKills = dominationOverKills;
	}

	public int getDominationRevenge() {
		return dominationRevenge;
	}

	public void setDominationRevenge(int dominationRevenge) {
		this.dominationRevenge = dominationRevenge;
	}

	public int getPistolsWon() {
		return pistolsWon;
	}

	public void setPistolsWon(int pistolsWon) {
		this.pistolsWon = pistolsWon;
	}

	public int getTeasredEnemies() {
		return teasredEnemies;
	}

	public void setTeasredEnemies(int teasredEnemies) {
		this.teasredEnemies = teasredEnemies;
	}

	public int getWindowsBroken() {
		return this.windowsBroken;
	}

	public void setWindowsBroken(int windowsBroken) {
		this.windowsBroken = windowsBroken;
	}
}
