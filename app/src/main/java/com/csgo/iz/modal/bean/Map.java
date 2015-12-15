package com.csgo.iz.modal.bean;

import java.io.Serializable;

public class Map implements Serializable{
	private int mapID;
	private String mapType;
	private String mapName;
	private int roundPlayed;
	private int roundsWon;
	private int mapAccuracy;
	public Map(int mapID, String mapType, String mapName, int roundPlayed, int roundsWon, int mapAccuracy) {
		super();
		this.mapID = mapID;
		this.mapType = mapType;
		this.mapName = mapName;
		this.roundPlayed = roundPlayed;
		this.roundsWon = roundsWon;
		this.mapAccuracy = mapAccuracy;
	}
	public int getMapID() {
		return mapID;
	}
	public void setMapID(int mapID) {
		this.mapID = mapID;
	}
	public String getMapName() {
		return mapName;
	}
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	public int getRoundPlayed() {
		return roundPlayed;
	}
	public void setRoundPlayed(int roundPlayed) {
		this.roundPlayed = roundPlayed;
	}
	public int getRoundsWon() {
		return roundsWon;
	}
	public void setRoundsWon(int roundsWon) {
		this.roundsWon = roundsWon;
	}
	public int getMapAccuracy() {
		return mapAccuracy;
	}
	public void setMapAccuracy(int mapAccuracy) {
		this.mapAccuracy = mapAccuracy;//
	}
	@Override
	public String toString() {
		return "Map [mapID=" + mapID + ", mapName=" + mapName  + ", roundPlayed="
				+ roundPlayed + ", roundsWon=" + roundsWon + ", mapAccuracy=" + mapAccuracy + "]";
	}
	public String getMapType() {
		return mapType;
	}
	public void setMapType(String mapType) {
		this.mapType = mapType;
	}
}
