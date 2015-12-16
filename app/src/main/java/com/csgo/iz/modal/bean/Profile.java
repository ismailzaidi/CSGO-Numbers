package com.csgo.iz.modal.bean;

import android.text.TextUtils;

import com.csgo.iz.modal.Utility;

import java.io.Serializable;
import java.util.Date;

public class Profile implements Comparable<Profile>, Serializable {

    //private static final int COMMUNITY_STATE_PUBLIC = 3;
    private static final int COMMUNITY_STATE_PRIVATE = 1;
    private static final int SECOND = 1000;
    private static final long serialVersionUID = 1L;

    public final String userID;
    public final int communityState;
    public final int profileState;
    public final String profileURL;
    public final String profileAvatarURL;
    public final int personstate;
    public final String userName;
    public final String lastLogin;
    public final String timeCreated;
    public final String userLocation;
    public final boolean hasGame;
    public final String twoWeeksPlayed;
    public final String totalHoursPlayed;
    public final boolean isPrivate;

    public Profile(String userID, int communityState, int profileState, String profileURL, String profileAvatarURL,
                   int personstate, String userName, int lastLogin, String timeCreated, String userLocation) {
        this.userID = userID;
        this.communityState = communityState;
        this.profileState = profileState;
        this.profileURL = profileURL;
        this.profileAvatarURL = profileAvatarURL;
        this.personstate = personstate;
        this.userName = userName;
        this.timeCreated = timeCreated;
        this.userLocation = userLocation;
        this.hasGame = false;
        this.twoWeeksPlayed = "0";
        this.totalHoursPlayed = "0";
        this.isPrivate = communityState == COMMUNITY_STATE_PRIVATE;

        Date date = new Date();
        date.setTime((long) lastLogin * SECOND);
        String updatedDate = date.toString();
        updatedDate = Utility.generateTimeDifference(updatedDate);
        this.lastLogin = "Last Login " + updatedDate;
    }

    public Profile(String userID, int communityState, int profileState, String profileURL, String profileAvatarURL,
                   int personstate, String userName, int lastLogin, String timeCreated, String userLocation, boolean hasGame,
                   String twoWeeksPlayed, String totalHoursPlayed) {
        this.userID = userID;
        this.communityState = communityState;
        this.profileState = profileState;
        this.profileURL = profileURL;
        this.profileAvatarURL = profileAvatarURL;
        this.personstate = personstate;
        this.userName = userName;
        this.timeCreated = timeCreated;
        this.userLocation = userLocation;
        this.hasGame = hasGame;
        this.isPrivate = communityState == COMMUNITY_STATE_PRIVATE;

        if (!TextUtils.isEmpty(twoWeeksPlayed) && !twoWeeksPlayed.equals("0")) {
            long hoursPlayed = Utility.convertToHours(twoWeeksPlayed);
            this.twoWeeksPlayed = hoursPlayed + " Hours in the past two weeks";
        }
        else {
            this.twoWeeksPlayed = twoWeeksPlayed;
        }

        if (!TextUtils.isEmpty(totalHoursPlayed) && !totalHoursPlayed.equals("0")) {
            long hoursPlayed = Utility.convertToHours(totalHoursPlayed);
            this.totalHoursPlayed = hoursPlayed + " Hours";
        }
        else {
            this.totalHoursPlayed = totalHoursPlayed;
        }

        Date date = new Date();
        date.setTime((long) lastLogin * SECOND);
        String updatedDate = date.toString();
        updatedDate = Utility.generateTimeDifference(updatedDate);
        this.lastLogin = "Last Login " + updatedDate;
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
        return this.userName.compareTo(another.userName);
    }

}
