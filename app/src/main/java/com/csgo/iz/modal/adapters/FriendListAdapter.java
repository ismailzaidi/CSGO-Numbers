package com.csgo.iz.modal.adapters;

import com.csgo.iz.modal.APICall;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class FriendListAdapter {

    public static class FriendListAdapterException extends Exception {
        private FriendListAdapterException(Exception source) {
            super(source);
        }
    }

    public String adapterFriendListJSON(String friendlistjson) throws FriendListAdapterException {
        try {
            JSONObject outObj = new JSONObject(friendlistjson);
            JSONObject innerObj = outObj.getJSONObject("friendslist");
            JSONArray friendListJsonArray = innerObj.getJSONArray("friends");
            String listOfFriends = getListOfFriends(friendListJsonArray);
            return new URL("http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=" + APICall.API_KEY + "&steamids="
                    + listOfFriends).toExternalForm();
        } catch (MalformedURLException | JSONException e) {
            throw new FriendListAdapterException(e);
        }
    }

    private String getListOfFriends(JSONArray arrayFriends) throws JSONException {
        String listOfUsers = "";
        for (int i = 0; i < arrayFriends.length(); i++) {
            listOfUsers += arrayFriends.getJSONObject(i).getString("steamid") + ":";
        }
        listOfUsers = listOfUsers.substring(0, listOfUsers.length() - 1);
        return listOfUsers;
    }
}
