package com.csgo.iz.modal.bean;

import java.io.Serializable;

public class Summary implements Serializable{
	private Profile userProfile;
	private GeneralStats generalStats;
	private LastMatchStats lastMatchStats;
	private OtherStats otherStats;
	public Summary(Profile userProfile, GeneralStats generalStats, LastMatchStats lastMatchStats,
			OtherStats otherStats) {
		super();
		this.userProfile = userProfile;
		this.generalStats = generalStats;
		this.lastMatchStats = lastMatchStats;
		this.otherStats = otherStats;
	}
	public Summary(GeneralStats generalStats, LastMatchStats lastMatchStats,
			OtherStats otherStats) {
		super();
		this.generalStats = generalStats;
		this.lastMatchStats = lastMatchStats;
		this.otherStats = otherStats;
	}
	public GeneralStats getGeneralStats() {
		return generalStats;
	}
	public void setGeneralStats(GeneralStats generalStats) {
		this.generalStats = generalStats;
	}
	public LastMatchStats getLastMatchStats() {
		return lastMatchStats;
	}
	public void setLastMatchStats(LastMatchStats lastMatchStats) {
		this.lastMatchStats = lastMatchStats;
	}
	public OtherStats getOtherStats() {
		return otherStats;
	}
	public void setOtherStats(OtherStats otherStats) {
		this.otherStats = otherStats;
	}
	public Profile getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(Profile userProfile) {
		this.userProfile = userProfile;
	}
}
