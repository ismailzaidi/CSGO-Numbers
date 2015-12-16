package com.csgo.iz.modal.adapters;

import com.csgo.iz.modal.APICall;
import com.csgo.iz.modal.bean.Profile;
import com.csgo.iz.modal.http.HTTPHandler;
import com.csgo.iz.modal.http.UserProfile;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ProfileAdapter {

    private static class GameStatus {

        public final boolean ownsGame;
        public final String twoWeekPlayTime;
        public final String totalPlayTime;

        private GameStatus(boolean ownsGame, String twoWeekPlayTime, String totalPlayTime) {
            this.ownsGame = ownsGame;
            this.twoWeekPlayTime = twoWeekPlayTime;
            this.totalPlayTime = totalPlayTime;
        }
    }

    public static class ProfileAdapterException extends Exception {

        private ProfileAdapterException(Exception source) {
            super(source);
        }
    }

    public Profile adapt(String profileJsonString) throws ProfileAdapterException {
        try {
            JSONObject profileJson = new JSONObject(profileJsonString);
            JSONObject obj = profileJson.getJSONObject("response").getJSONArray("players").getJSONObject(0);
            GameStatus extraInfo = getProfileGameStatus(obj.getString("steamid"));
            return new Profile(obj.getString("steamid"), obj.getInt("communityvisibilitystate"), obj.getInt("profilestate"), obj.getString("profileurl"), obj.getString("avatarfull"), obj.getInt("personastate"),
                    obj.getString("personaname"), obj.getInt("lastlogoff"), obj.getString("timecreated"), "", extraInfo.ownsGame, extraInfo.twoWeekPlayTime, extraInfo.totalPlayTime);
        } catch (JSONException e) {
            throw new ProfileAdapterException(e);
        }
    }

    private GameStatus getProfileGameStatus(String userID) {
        String URL = "http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key=" + APICall.API_KEY + "&steamid="
                + userID + "&include_played_free_games=0&include_appinfo=0&appids_filter[0]=730&format=json";
        HTTPHandler handler = new HTTPHandler();
        return handler.readHTTPRequest(URL, new HTTPHandler.HTTPHandlerCallback<GameStatus>() {
            @Override
            public void notFound() {

            }

            @Override
            public void badRequest() {

            }

            @Override
            public GameStatus response(String friendProfileJson) {
                try {
                    JSONObject parent = new JSONObject(friendProfileJson);

                    int gameCount = parent.getJSONObject("response").getInt("game_count");
                    if (gameCount != 0) {
                        JSONObject gameObj = parent.getJSONObject("response").getJSONArray("games").getJSONObject(0);
                        return new GameStatus(gameObj.getInt("appid") == UserProfile.CSGO_GAME_ID, gameObj.getInt("playtime_2weeks") + "", gameObj.getString("playtime_forever"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return new GameStatus(false, "0", "0");
            }

            @Override
            public void connectionError(IOException e) {

            }
        });
    }
}
