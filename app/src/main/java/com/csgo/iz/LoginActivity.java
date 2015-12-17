package com.csgo.iz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.csgo.iz.modal.SharedPreferenceModel;
import com.csgo.iz.modal.Utility;
import com.csgo.iz.modal.bean.Profile;
import com.csgo.iz.modal.http.Steam64IdFromVanityUrl;
import com.csgo.iz.modal.http.Steam64IdFromVanityUrl.UserIDCallBack;
import com.csgo.iz.modal.http.Steam64IdFromVanityUrl.UserIDCheckerCallBack;

public class LoginActivity extends AppCompatActivity implements OnClickListener {

    public static final String INTENT_KEY = "steamID.LoginActivity";
    private EditText userSteamInput;
    private boolean isLaunch = true;
    private Utility utils;
    private CoordinatorLayout coordinateLayout;
    private SharedPreferenceModel sharedModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        RelativeLayout viewGroup = (RelativeLayout) findViewById(R.id.layout);
        userSteamInput = (EditText) findViewById(R.id.steamIDEditText);
        FrameLayout launchButton = (FrameLayout) findViewById(R.id.launchButton);
        ImageButton steamLoginButton = (ImageButton) findViewById(R.id.steamLoginButton);
        coordinateLayout = (CoordinatorLayout) findViewById(R.id.snackbarCoordinatorLayout);
        utils = new Utility();
        launchButton.setOnClickListener(this);
        steamLoginButton.setOnClickListener(this);
        sharedModel = new SharedPreferenceModel(getApplicationContext());
        Utility.setFontForView(viewGroup);
    }

    public void launchIntent(String steamID) {
        Intent intent = new Intent(this, MainActivity.class);
        sharedModel.saveSharedUserID(steamID);
        intent.putExtra(INTENT_KEY, steamID);
        if (isLaunch) {
            isLaunch = false;
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.launchButton) {
            String userInput = userSteamInput.getText().toString();
            Log.v("MAIN_ACTIVITY_LOGIN", "User ID" + userInput);
            if (Utility.isNetworkAvailable(getApplicationContext())) {
                if (userInput == null || userInput.length() <= 0) {
                    Utility.showSnackBar(getApplicationContext(), "Input Steam ID", coordinateLayout);
                } else {
                    boolean isSteam64 = utils.checkIsSteamID(userInput);
                    if (isSteam64) {
                        checkUserSteamID(userInput);
                    } else {
                        loginUser(userInput);
                    }
                }
            } else {
                Utility.showSnackBar(getApplicationContext(), "No Internet Connection", coordinateLayout);
            }
        }
        if (v.getId() == R.id.steamLoginButton) {
            Intent intent = new Intent(getApplicationContext(), SteamLoginActivity.class);
            startActivity(intent);
        }
    }

    private void loginUser(final String userInput) {
        new Steam64IdFromVanityUrl(userInput).getSteam64IDAsync(new UserIDCallBack() {
            @Override
            public void userIDIsAvailable(Profile profile) {
                if (!profile.isPrivate && profile.hasGame) {
                    launchIntent(profile.userID);
                } else {
                    displayProfileNotPublic();
                }
            }

            @Override
            public void userIDNotFound() {
                displayLoginNotFound();
            }
        });
    }

    private void displayProfileNotPublic() {
        Utility.showSnackBar(getApplicationContext(), "Profile Is Private, make it public.  Try again !", coordinateLayout);
    }

    private void displayLoginNotFound() {
        Utility.showSnackBar(getApplicationContext(), "Profile Not Found. Try again !", coordinateLayout);
    }

    private void checkUserSteamID(String userID) {
        new Steam64IdFromVanityUrl(userID).getSteam64IDIsExistAsync(new UserIDCheckerCallBack() {
            @Override
            public void UserIDIsExist(Profile profile) {
                launchIntent(profile.userID);
            }

            @Override
            public void userIDDoesNotExist() {
                Utility.showSnackBar(getApplicationContext(), "Incorrect Input Sir :) . Try again !", coordinateLayout);
            }
        });
    }
}