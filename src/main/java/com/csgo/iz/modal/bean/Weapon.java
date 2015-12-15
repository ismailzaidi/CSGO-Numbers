package com.csgo.iz.modal.bean;

import java.io.Serializable;

public class Weapon implements Comparable<Weapon>,Serializable{
	private int weaponID;
	private String weaponType;
	private String weaponName; // AK 47 
	private int weaponKills;
	private int weaponHit;
	private int weaponAccuracy;
	private int missedShots;
	private int weaponShot;
	public Weapon(int weaponID, String weaponType, String weaponName, int weaponKills, int weaponHit,
			int weaponAccuracy, int missedShots, int weaponShot) {
		super();
		this.weaponID = weaponID;
		this.weaponType = weaponType;
		this.weaponName = weaponName;
		this.weaponKills = weaponKills;
		this.weaponHit = weaponHit;
		this.weaponAccuracy = weaponAccuracy;
		this.missedShots = missedShots;
		this.weaponShot = weaponShot;
	}
	public int getWeaponID() {
		return weaponID;
	}
	public void setWeaponID(int weaponID) {
		this.weaponID = weaponID;
	}
	public String getWeaponName() {
		return weaponName;
	}
	public void setWeaponName(String weaponName) {
		this.weaponName = weaponName;
	}
	public int getWeaponKills() {
		return weaponKills;
	}
	public void setWeaponKills(int weaponKills) {
		this.weaponKills = weaponKills;
	}
	public int getWeaponHit() {
		return weaponHit;
	}
	public void setWeaponHit(int weaponHit) {
		this.weaponHit = weaponHit;
	}
	public int getWeaponAccuracy() {
		return weaponAccuracy;
	}
	public void setWeaponAccuracy(int weaponAccuracy) {
		this.weaponAccuracy = weaponAccuracy;
	}
	public int getMissedShots() {
		return missedShots;
	}
	public void setMissedShots(int missedShots) {
		this.missedShots = missedShots;
	}
	public int getWeaponShot() {
		return weaponShot;
	}
	public void setWeaponShot(int weaponShot) {
		this.weaponShot = weaponShot;
	}
	@Override
	public String toString() {
		return "Weapon [weaponID=" + weaponID + ", weaponName=" + weaponName + ", weaponKills=" + weaponKills
				+ ", weaponHit=" + weaponHit + ", weaponAccuracy=" + weaponAccuracy + ", missedShots=" + missedShots
				+ ", weaponShot=" + weaponShot + "]";
	}
	public String getWeaponType() {
		return weaponType;
	}
	public void setWeaponType(String weaponType) {
		this.weaponType = weaponType;
	}
	@Override
	public int compareTo(Weapon another) {
		// TODO Auto-generated method stub
		return this.weaponName.compareTo(another.getWeaponName());
	}
}
