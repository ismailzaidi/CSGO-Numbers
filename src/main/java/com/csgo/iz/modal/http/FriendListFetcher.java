package com.csgo.iz.modal.http;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;

import com.csgo.iz.modal.APICall;
import com.csgo.iz.modal.bean.Profile;
import com.csgo.iz.modal.http.HTTPHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FriendListFetcher {
	private String steamID;

	public FriendListFetcher(String steamID) {
		this.steamID = steamID;
	}

	public ArrayList<Profile> getProfiles() {
		final ArrayList<Profile> friendList = new ArrayList<Profile>();
		JSONArray statsArr = null;
		String friendListURL = "http://api.steampowered.com/ISteamUser/GetFriendList/v0001/?key="+ APICall.API_KEY+"&steamid="
				+ steamID + "&relationship=friend";
		HTTPHandler handler = new HTTPHandler();
		String friendListJson = handler.readHTTPRequest(friendListURL);
		try {
			JSONObject outObj = new JSONObject(friendListJson);
			JSONObject innerObj = outObj.getJSONObject("friendslist");
			statsArr = innerObj.getJSONArray("friends");
			String listOfFriends = getListOfFriends(statsArr);
			String userFriendsProfile = "http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key="+APICall.API_KEY+"&steamids="
					+ listOfFriends;
			String friendProfileJson = handler.readHTTPRequest(userFriendsProfile);
			JSONObject json_friend = new JSONObject(friendProfileJson);
			JSONObject response = json_friend.getJSONObject("response");
			JSONArray players = response.getJSONArray("players");
			for (int i = 0; i < players.length(); i++) {
				Profile profile = getProfile(players, i);
				friendList.add(profile);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return friendList;
	}

	private String getProfileGameStatus(String userID) {
		String URL = "http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key="+APICall.API_KEY+"&steamid="
				+ userID + "&include_played_free_games=0&include_appinfo=0&appids_filter[0]=730&format=json";
		HTTPHandler handler = new HTTPHandler();
		String friendProfileJson = handler.readHTTPRequest(URL);
		int gameID = 730;
		try {
			JSONObject parent = new JSONObject(friendProfileJson);
			JSONObject inner = parent.getJSONObject("response");
			int gameCount = inner.getInt("game_count");
			if (gameCount != 0) {
				JSONArray gamesArray = inner.getJSONArray("games");
				JSONObject gameObj = gamesArray.getJSONObject(0);
				int appID = gameObj.getInt("appid");
				String twoWeeksPlayed = (gameObj.isNull("playtime_2weeks")) ? " "
						: String.valueOf(gameObj.getInt("playtime_2weeks"));
				String hoursPlayed = gameObj.getString("playtime_forever");
				if (appID == gameID) {
					return "true" + "," + twoWeeksPlayed + "," + hoursPlayed;
				}
			} else {
				return "false" + "," + "0" + "," + "0";
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "false" + "," + "0" + "," + "0";

	}

	private Profile getProfile(JSONArray arr, int position) {
		Log.v("FRIEND_STAT", "Inside UserFriend Retriever getProfile Size: " + arr.length());
		String userID;
		boolean isHasGame = false;
		String twoWeeksHours = "";
		String totalHoursPlayed = "";
		try {
			JSONObject obj = arr.getJSONObject(position);
			userID = obj.getString("steamid");
			int communityState = obj.getInt("communityvisibilitystate");
			int profileState = obj.getInt("profilestate");
			String profileURL = obj.getString("profileurl");
			String profileAvatarURL = obj.getString("avatarfull");
			int personstate = obj.getInt("personastate");
			String userName = obj.getString("personaname");
			int lastLogin = obj.getInt("lastlogoff");
			String timeCreated = (obj.isNull("timecreated")) ? " " : obj.getString("timecreated");
			Log.v("FRIEND_STAT", "User Friend: " + userID);
			return new Profile(userID, communityState, profileState, profileURL, profileAvatarURL, personstate,
					userName, lastLogin, timeCreated, "", isHasGame, twoWeeksHours, totalHoursPlayed);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private String getListOfFriends(JSONArray arrayFriends) {
		String listOfUsers = "";

		for (int i = 0; i < arrayFriends.length(); i++) {
			try {
				JSONObject obj = arrayFriends.getJSONObject(i);
				String steamidString = obj.getString("steamid");
				listOfUsers += steamidString + ":";
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		listOfUsers = listOfUsers.substring(0, listOfUsers.length() - 1);

		return listOfUsers;
	}
}
