package com.csgo.iz.test;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import com.csgo.iz.LoginActivity;
import com.csgo.iz.modal.Utility;
import com.csgo.iz.modal.bean.Profile;
import com.csgo.iz.modal.http.JSONUserExtractor;

/**
 * Created by Yusuf on 10/12/2015.
 */

public class TestLogin extends ActivityInstrumentationTestCase2<LoginActivity> {
    private LoginActivity activity;
    private Context context;
    private String actualResult = null;
    private static final String PLAYER_NOT_STRING = "Player Not Found";

    public TestLogin() {
        super(LoginActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
        context = getActivity().getApplicationContext();
    }

    public void testPublicInput() {
        String expectedInput = "76561198055876515";
        JSONUserExtractor jsonUserExtractor = new JSONUserExtractor(expectedInput, context);
        LoginActivity activity = new LoginActivity();
        activity.launchIntent(expectedInput);
        assertEquals(expectedInput, actualResult);
    }
    public void testUserNameInput(){
        String expectedInput ="StabilisHero";


    }
    public void testIfNotAvailableInput() {
        String expectedInput = "765611980558783515";
    }
    public void testGameExistsFailedInput() {
        String expectedInput = "765611980558355";
        Utility utils = new Utility(context);
        boolean actualOutput = utils.checkIsSteamID(expectedInput);
        assertEquals(false,actualOutput);
    }
    public void testGameExistsSuccessInput() {
        String expectedInput = "765611980558783515";
        Utility utils = new Utility(context);
        boolean actualOutput = utils.checkIsSteamID(expectedInput);
        assertEquals("The ID is 17 Digits Long",true,actualOutput);
    }

}
