package com.csgo.iz.modal.bean;

import java.io.Serializable;

public class Achievement implements Serializable, Comparable<Achievement> {
	private String achievementResID;
	private String achievementName;
	private String achievementDescription;
	private int lockInfo;

	public Achievement(String achievementResID, String achievementName, String achievementDescription, int lockInfo) {
		this.achievementResID = achievementResID;
		this.achievementName = achievementName;
		this.achievementDescription = achievementDescription;
		this.lockInfo = lockInfo;
	}

	public int getLockInfo() {
		return lockInfo;
	}

	public boolean isLocked()
	{
		return lockInfo == 0;
	}

	public void setLockInfo(int lockInfo) {
		this.lockInfo = lockInfo;
	}

	public String getAchievementName() {
		return achievementName;
	}

	public void setAchievementName(String achievementName) {
		this.achievementName = achievementName;
	}

	public String getAchievementDescription() {
		return achievementDescription;
	}

	public void setAchievementDescription(String achievementDescription) {
		this.achievementDescription = achievementDescription;
	}

	public String getAchievementResID() {
		return achievementResID;
	}

	public void setAchievementResID(String achievementResID) {
		this.achievementResID = achievementResID;
	}

	@Override
	public int compareTo(Achievement another) {
		Integer lock1 = this.lockInfo;
		Integer lock2 = another.getLockInfo();
		return lock2.compareTo(lock1);
	}
}
