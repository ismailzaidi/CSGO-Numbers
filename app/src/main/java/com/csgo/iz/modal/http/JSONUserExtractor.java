package com.csgo.iz.modal.http;

import org.json.JSONException;
import org.json.JSONObject;

import com.csgo.iz.modal.APICall;
import com.csgo.iz.modal.bean.Profile;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class JSONUserExtractor {
    private static final String ERROR_NO_RESPONSE = "Error";
    private String userName;
    private Context context;

    public interface UserIDCallBack {
        void UserIDIsAvailable(Profile userID);
    }

    public interface UserIDCheckerCallBack {
        void UserIDIsExist(Profile userID);
    }

    public JSONUserExtractor(String userName, Context context) {
        super();
        this.userName = userName;
        this.context = context;
    }

    public void getSteam64IDAsync(final UserIDCallBack callback) {
        new AsyncTask<Void, Void, Profile>() {
            @Override
            protected Profile doInBackground(Void... params) {
                String URL = "http://api.steampowered.com/ISteamUser/ResolveVanityURL/v0001/?key=" + APICall.API_KEY + "&vanityurl="
                        + userName;
                String steamID = "";
                try {
                    HTTPHandler handler = new HTTPHandler();
                    String jsonUser = handler.readHTTPRequest(URL);
                    JSONObject obj = new JSONObject(jsonUser);
                    JSONObject responseObj = obj.getJSONObject("response");
                    int status = responseObj.getInt("success");
                    if (status == 1) {// Success
                        steamID = responseObj.getString("steamid");
                        Profile profile = new UserProfile(steamID).getProfile();
                        ;
                        return profile;
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Profile result) {
                // TODO Auto-generated method stub
                callback.UserIDIsAvailable(result);
            }
        }.execute();
    }

    public void getSteam64IDIsExistAsync(final UserIDCheckerCallBack callback) {
        new AsyncTask<Void, Void, Profile>() {


            @Override
            protected Profile doInBackground(Void... params) {
                String URL = "http://api.steampowered.com/ISteamUserStats/GetUserStatsForGame/v0002/?appid=730&key=" + APICall.API_KEY + "&steamid="
                        + userName;
                HTTPHandler handler = new HTTPHandler();
                String jsonUser = handler.readHTTPRequest(URL);
                Log.v("String_exist_size", "Object: " + jsonUser + " UserName: " + userName);
                if (jsonUser!=null) {
                    Profile userProfile = new UserProfile(userName).getProfile();
                    return userProfile;
                }
                return null;
            }

            @Override
            protected void onPostExecute(Profile result) {
                callback.UserIDIsExist(result);
            }
        }.execute();
    }


}
