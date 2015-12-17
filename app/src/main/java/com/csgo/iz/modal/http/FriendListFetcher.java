package com.csgo.iz.modal.http;

import com.csgo.iz.modal.APICall;
import com.csgo.iz.modal.adapters.FriendListAdapter;
import com.csgo.iz.modal.adapters.FriendProfileAdapter;
import com.csgo.iz.modal.bean.Profile;

import java.io.IOException;
import java.util.ArrayList;

public class FriendListFetcher {

    private final String steamID;
    private final FriendListAdapter friendListAdapter = new FriendListAdapter();
    private final FriendProfileAdapter friendProfileAdapter = new FriendProfileAdapter();
    private final String friendListURL;

    public FriendListFetcher(String steamID) {
        this.steamID = steamID;
        friendListURL = "http://api.steampowered.com/ISteamUser/GetFriendList/v0001/?key="
                + APICall.API_KEY + "&steamid=" + this.steamID + "&relationship=friend";
    }

    public ArrayList<Profile> getProfiles() {
        return getFriendProfiles();
    }

    private ArrayList<Profile> getFriendProfiles() {
        return new HTTPHandler().readHTTPRequest(getFriendListUrl(), new HTTPHandler.HTTPHandlerCallback<ArrayList<Profile>>() {
            @Override
            public void notFound() {

            }

            @Override
            public void badRequest() {

            }

            @Override
            public ArrayList<Profile> response(String response) {
                try {
                    return friendProfileAdapter.adapterFriendProfileJson(response);
                } catch (FriendProfileAdapter.FriendProfileAdapterException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public void connectionError(IOException e) {

            }
        });
    }

    private String getFriendListUrl() {
        return new HTTPHandler().readHTTPRequest(friendListURL, new HTTPHandler.HTTPHandlerCallback<String>() {
            @Override
            public void notFound() {

            }

            @Override
            public void badRequest() {

            }

            @Override
            public String response(String response) {
                try {
                    return friendListAdapter.adapterFriendListJSON(response);
                } catch (FriendListAdapter.FriendListAdapterException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public void connectionError(IOException e) {

            }
        });
    }
}
