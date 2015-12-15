package com.csgo.iz.modal.http;

import com.csgo.iz.modal.APICall;
import com.csgo.iz.modal.bean.Profile;
import com.csgo.iz.modal.http.HTTPHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Yusuf on 08/12/2015.
 */
public class UserProfile {
    private Profile profile;
    private String userID;

    public UserProfile(String userID) {
        this.userID = userID;
        generatePersonalProfile();
        ;
    }

    private void generatePersonalProfile() {
        profile = null;
        try {
            HTTPHandler handler = new HTTPHandler();
            String userFriendsProfile = "http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key="+APICall.API_KEY+"&steamids="
                    + userID;
            String friendProfileJson = handler.readHTTPRequest(userFriendsProfile);

            JSONObject json_friend = new JSONObject(friendProfileJson);
            JSONArray arr = json_friend.getJSONObject("response").getJSONArray("players");
            JSONObject obj = arr.getJSONObject(0);
            userID = obj.getString("steamid");
            int communityState = obj.getInt("communityvisibilitystate");
            int profileState = obj.getInt("profilestate");
            String profileURL = obj.getString("profileurl");
            String profileAvatarURL = obj.getString("avatarfull");
            int personstate = obj.getInt("personastate");
            String userName = obj.getString("personaname");
            int lastLogin = obj.getInt("lastlogoff");
            String timeCreated = (obj.isNull("timecreated")) ? " " : obj.getString("timecreated");
            String extraInfo = getProfileGameStatus(userID);
            boolean isHasGame = Boolean.parseBoolean(extraInfo.split(",")[0]);
            String twoWeeksHours = extraInfo.split(",")[1];
            String totalHoursPlayed = extraInfo.split(",")[2];
            profile = new Profile(userID, communityState, profileState, profileURL, profileAvatarURL, personstate,
                    userName, lastLogin, timeCreated, "", isHasGame, twoWeeksHours, totalHoursPlayed);
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
            JSONArray gamesArray = inner.getJSONArray("games");
            int gameCount = inner.getInt("game_count");
            if (gameCount != 0) {
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

    public Profile getProfile() {
        return profile;
    }
}
