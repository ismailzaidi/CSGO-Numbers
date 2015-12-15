package com.csgo.iz.modal.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.csgo.iz.modal.Utility;

public class Profile implements  Comparable<Profile>,Serializable{
	private static final int COMMUNITY_STATE_PRIVATE = 1;
	private static final int SECOND = 1000;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userID;
	/**
	 * communitystate
	 * 1 - Private
	 * 3 - Public
	 */
	private int communityState;
	private int profileState;
	private String profileURL;
	private String profileAvatarURL;
	private int personstate;
	private String userName;
	private int lastLogin;
	private String timeCreated;
	private String userLocation;
	private boolean isHasGame;
	private String twoWeeksPlayed,totalHoursPlayed;
	public Profile(){}
	public Profile(String userID, int communityState, int profileState, String profileURL, String profileAvatarURL,
			int personstate, String userName, int lastLogin, String timeCreated, String userLocation) {
		super();
		this.userID = userID;
		this.communityState = communityState;
		this.profileState = profileState;
		this.profileURL = profileURL;
		this.profileAvatarURL = profileAvatarURL;
		this.personstate = personstate;
		this.userName = userName;
		this.lastLogin = lastLogin;
		this.timeCreated = timeCreated;
		this.userLocation = userLocation;
	}
	public Profile(String userID, int communityState, int profileState, String profileURL, String profileAvatarURL,
			int personstate, String userName, int lastLogin, String timeCreated, String userLocation, boolean isHasGame,
			String twoWeeksPlayed, String totalHoursPlayed) {
		super();
		this.userID = userID;
		this.communityState = communityState;
		this.profileState = profileState;
		this.profileURL = profileURL;
		this.profileAvatarURL = profileAvatarURL;
		this.personstate = personstate;
		this.userName = userName;
		this.lastLogin = lastLogin;
		this.timeCreated = timeCreated;
		this.userLocation = userLocation;
		this.isHasGame = isHasGame;
		this.twoWeeksPlayed = twoWeeksPlayed;
		this.totalHoursPlayed = totalHoursPlayed;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public int getCommunityState() {
		return communityState;
	}
	public void setCommunityState(int communityState) {
		this.communityState = communityState;
	}
	public int getProfileState() {
		return profileState;
	}
	public void setProfileState(int profileState) {
		this.profileState = profileState;
	}
	public String getProfileURL() {
		return profileURL;
	}
	public void setProfileURL(String profileURL) {
		this.profileURL = profileURL;
	}
	public String getProfileAvatarURL() {
		return profileAvatarURL;
	}
	public void setProfileAvatarURL(String profileAvatarURL) {
		this.profileAvatarURL = profileAvatarURL;
	}
	public int getPersonstate() {
		return personstate;
	}
	public void setPersonstate(int personstate) {
		this.personstate = personstate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTimeCreated() {
		return timeCreated;
	}
	public void setTimeCreated(String timeCreated) {
		this.timeCreated = timeCreated;
	}
	public String getUserLocation() {
		return userLocation;
	}
	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}
	public String getLastLogin() {
		Date date = new Date();
		date.setTime((long)this.lastLogin*SECOND);
		String updatedDate = date.toString();
		updatedDate = Utility.generateTimeDifference(updatedDate);
		return "Last Login "+updatedDate;
	}
	public void setLastLogin(int lastLogin) {
		this.lastLogin = lastLogin;
	}
	@Override
	public String toString() {
		return "Profile [userID=" + userID + ", communityState=" + communityState + ", profileState=" + profileState
				+ ", profileURL=" + profileURL + ", profileAvatarURL=" + profileAvatarURL + ", personstate="
				+ personstate + ", userName=" + userName + ", lastLogin=" + lastLogin + ", timeCreated=" + timeCreated
				+ ", userLocation=" + userLocation + "]";
	}
	@Override
	public int compareTo(Profile another) {
		// TODO Auto-generated method stub
		return this.userName.compareTo(another.getUserName());
	}
	public boolean isPrivate() {
		return (communityState == COMMUNITY_STATE_PRIVATE);
	}
	public boolean isHasGame() {
		return isHasGame;
	}
	public void setHasGame(boolean isHasGame) {
		this.isHasGame = isHasGame;
	}
	public String getTwoWeeksPlayed() {
		String defaultValue = "0";
		if(!this.twoWeeksPlayed.equals("0")){
			long hoursPlayed = Utility.convertToHours(this.twoWeeksPlayed);
			defaultValue = hoursPlayed +  " Hours in the past two weeks";
		}
		return defaultValue;
	}
	public void setTwoWeeksPlayed(String twoWeeksPlayed) {
		this.twoWeeksPlayed = twoWeeksPlayed;
	}
	public String getTotalHoursPlayed() {
		String defaultValue = "0";
		if(!this.totalHoursPlayed.equals("0")){
			long hoursPlayed = Utility.convertToHours(this.totalHoursPlayed);
			defaultValue = hoursPlayed +  " Hours";
		}
		return defaultValue;
	}
	public void setTotalHoursPlayed(String totalHoursPlayed) {
		this.totalHoursPlayed = totalHoursPlayed;
	}
}
