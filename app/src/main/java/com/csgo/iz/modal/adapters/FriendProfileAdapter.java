package com.csgo.iz.modal.adapters;

import com.csgo.iz.modal.bean.Profile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FriendProfileAdapter {

    public static class FriendProfileAdapterException extends Exception {

        private FriendProfileAdapterException(Exception source) {
            super(source);
        }
    }

    public ArrayList<Profile> adapterFriendProfileJson(String profileJson) throws FriendProfileAdapterException {
        try {
            ArrayList<Profile> friendList = new ArrayList<>();
            JSONArray players = new JSONObject(profileJson).getJSONObject("response").getJSONArray("players");
            for (int i = 0; i < players.length(); i++) {
                friendList.add(getProfile(players.getJSONObject(i)));
            }
            return friendList;
        } catch (JSONException e) {
            throw new FriendProfileAdapterException(e);
        }
    }

    private static Profile getProfile(JSONObject obj) throws JSONException {
        String userID;
        String twoWeeksHours = "";
        String totalHoursPlayed = "";

        userID = obj.getString("steamid");
        int communityState = obj.getInt("communityvisibilitystate");
        int profileState = obj.getInt("profilestate");
        String profileURL = obj.getString("profileurl");
        String profileAvatarURL = obj.getString("avatarfull");
        int personstate = obj.getInt("personastate");
        String userName = obj.getString("personaname");
        int lastLogin = obj.getInt("lastlogoff");
        String timeCreated = obj.optString("timecreated");

        return new Profile(userID, communityState, profileState, profileURL, profileAvatarURL, personstate,
                userName, lastLogin, timeCreated, "", false, twoWeeksHours, totalHoursPlayed);
    }
}
