package com.csgo.iz.modal.http;

import com.csgo.iz.modal.APICall;
import com.csgo.iz.modal.adapters.FriendListAdapter;
import com.csgo.iz.modal.adapters.FriendProfileAdapter;
import com.csgo.iz.modal.bean.Profile;

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
        ArrayList<Profile> friendList = new ArrayList<>();
        HTTPHandler handler = new HTTPHandler();
        String friendListJson = handler.readHTTPRequest(friendListURL);
        try {
            String friendProfileJson = handler.readHTTPRequest(friendListAdapter.adapterFriendListJSON(friendListJson));
            friendList = friendProfileAdapter.adapterFriendProfileJson(friendProfileJson);
        } catch (FriendListAdapter.FriendListAdapterException | FriendProfileAdapter.FriendProfileAdapterException e) {
            e.printStackTrace();
        }
        return friendList;
    }
}
