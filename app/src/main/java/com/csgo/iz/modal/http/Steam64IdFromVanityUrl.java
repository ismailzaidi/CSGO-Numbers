package com.csgo.iz.modal.http;

import android.os.AsyncTask;

import com.csgo.iz.modal.APICall;
import com.csgo.iz.modal.bean.Profile;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Steam64IdFromVanityUrl {

    public interface UserIDCallBack {

        void UserIDIsAvailable(Profile userID);
    }

    public interface UserIDCheckerCallBack {

        void UserIDIsExist(Profile userID);
    }

    private static class ProfileHTTPHandlerCallback implements HTTPHandler.HTTPHandlerCallback<Profile> {

        @Override
        public void notFound() {

        }

        @Override
        public void badRequest() {

        }

        @Override
        public Profile response(String jsonUser) {
            try {
                JSONObject obj = new JSONObject(jsonUser);
                JSONObject responseObj = obj.getJSONObject("response");
                int status = responseObj.getInt("success");
                if (status == 1) {
                    String steamID = responseObj.getString("steamid");
                    return new UserProfile(steamID).getProfile();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void connectionError(IOException e) {

        }

    }

    private class UserExistsHTTPHandlerCallback implements HTTPHandler.HTTPHandlerCallback<Profile> {


        @Override
        public void notFound() {

        }

        @Override
        public void badRequest() {

        }

        @Override
        public Profile response(String jsonUser) {
            return new UserProfile(userName).getProfile();
        }

        @Override
        public void connectionError(IOException e) {

        }
    }

    private String userName;

    public Steam64IdFromVanityUrl(String userName) {
        this.userName = userName;
    }

    public void getSteam64IDAsync(final UserIDCallBack callback) {
        new AsyncTask<Void, Void, Profile>() {
            @Override
            protected Profile doInBackground(Void... params) {
                String URL = "http://api.steampowered.com/ISteamUser/ResolveVanityURL/v0001/" +
                        "?key=" + APICall.API_KEY +
                        "&vanityurl=" + userName;

                HTTPHandler handler = new HTTPHandler();
                ProfileHTTPHandlerCallback profileHTTPHandlerCallback = new ProfileHTTPHandlerCallback();
                return handler.readHTTPRequest(URL, profileHTTPHandlerCallback);
            }

            @Override
            protected void onPostExecute(Profile result) {
                callback.UserIDIsAvailable(result);
            }
        }.execute();
    }

    public void getSteam64IDIsExistAsync(final UserIDCheckerCallBack callback) {
        new AsyncTask<Void, Void, Profile>() {
            @Override
            protected Profile doInBackground(Void... params) {
                String URL = "http://api.steampowered.com/ISteamUserStats/GetUserStatsForGame/v0002/" +
                        "?appid=730" +
                        "&key=" + APICall.API_KEY +
                        "&steamid=" + userName;
                HTTPHandler handler = new HTTPHandler();
                UserExistsHTTPHandlerCallback existsHTTPHandlerCallback = new UserExistsHTTPHandlerCallback();
                return handler.readHTTPRequest(URL, existsHTTPHandlerCallback);
            }

            @Override
            protected void onPostExecute(Profile result) {
                callback.UserIDIsExist(result);
            }
        }.execute();
    }
}
