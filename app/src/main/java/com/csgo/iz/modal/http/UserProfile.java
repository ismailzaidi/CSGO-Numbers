package com.csgo.iz.modal.http;

import com.csgo.iz.modal.APICall;
import com.csgo.iz.modal.adapters.ProfileAdapter;
import com.csgo.iz.modal.bean.Profile;

import java.io.IOException;

/**
 * Created by Yusuf on 08/12/2015.
 */
public class UserProfile {

    public static final int CSGO_GAME_ID = 730;
    private Profile profile;
    private String userID;

    public UserProfile(String userID) {
        this.userID = userID;
        generatePersonalProfile();
    }

    public Profile getProfile() {
        return profile;
    }

    private void generatePersonalProfile() {
        profile = null;

        HTTPHandler handler = new HTTPHandler();
        String userFriendsProfile = "http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/" +
                "?key=" + APICall.API_KEY +
                "&steamids=" + userID;

        profile = handler.readHTTPRequest(userFriendsProfile, new HTTPHandler.HTTPHandlerCallback<Profile>() {
            @Override
            public void notFound() {

            }

            @Override
            public void badRequest() {

            }

            @Override
            public Profile response(String personalProfile) {
                try {
                    return new ProfileAdapter().adapt(personalProfile);
                } catch (ProfileAdapter.ProfileAdapterException e) {
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
