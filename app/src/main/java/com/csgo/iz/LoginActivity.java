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

import com.csgo.iz.modal.Model;
import com.csgo.iz.modal.SharedPreferenceModel;
import com.csgo.iz.modal.Utility;
import com.csgo.iz.modal.bean.Profile;
import com.csgo.iz.modal.http.JSONUserExtractor;
import com.csgo.iz.modal.http.JSONUserExtractor.UserIDCallBack;
import com.csgo.iz.modal.http.JSONUserExtractor.UserIDCheckerCallBack;

public class LoginActivity extends AppCompatActivity implements OnClickListener {
    public static final String HASH_KEY_ID = "#";
    private static final String PLAYER_NOT_FOUND = "Player Not Found";
    private static final String PLAYED_IS_PRIVATE = "private";
    private static final String PLAYED_IS_PUBLIC = "public";
    private EditText userSteamInput;
    private boolean isLaunch = true;
    private FrameLayout launchButton;
    public Model model;
    public static final String INTENT_KEY = "steamID.LoginActivity";
    private Utility utils;
    private CoordinatorLayout coordinateLayout;
    public static final String MODEL_KEY = "com.modelkey";
    private ImageButton steamLoginButton;
    private SharedPreferenceModel sharedModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        RelativeLayout viewGroup = (RelativeLayout) findViewById(R.id.layout);
        userSteamInput = (EditText) findViewById(R.id.steamIDEditText);
        launchButton = (FrameLayout) findViewById(R.id.launchButton);
        steamLoginButton = (ImageButton) findViewById(R.id.steamLoginButton);
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
        intent.putExtra(MODEL_KEY, model);
        if (isLaunch) {// Disables multiple activities from running
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
        if(v.getId() == R.id.steamLoginButton){
            Intent intent = new Intent(getApplicationContext(),SteamLoginActivity.class);
            startActivity(intent);
        }

    }

    private void loginUser(final String userInput) {
        new JSONUserExtractor(userInput, LoginActivity.this).getSteam64IDAsync(new UserIDCallBack() {
            @Override
            public void UserIDIsAvailable(Profile userID) {
                if (userID != null) {
                    Profile profile = userID;
                    Log.v("TEST", "UserID: " + profile.getUserID());
                    if (!profile.isPrivate() && profile.isHasGame()) {
                        launchIntent(profile.getUserID());
                    } else {
                        Utility.showSnackBar(getApplicationContext(), "Profile Is Private, make it public.  Try again !", coordinateLayout);
                    }
                } else {
                    // Snackbar
                    Utility.showSnackBar(getApplicationContext(), "Profile Not Found. Try again !", coordinateLayout);
                }
            }
        });
    }

    private void checkUserSteamID(String userID) {
        new JSONUserExtractor(userID, LoginActivity.this).getSteam64IDIsExistAsync(new UserIDCheckerCallBack() {

            @Override
            public void UserIDIsExist(Profile userID) {
                if (userID != null) {
                    Log.v("MAIN_ACTIVITY_LOGIN", "LoginActivity: " + userID.getUserID());

                    launchIntent(userID.getUserID());
                } else {
                    Utility.showSnackBar(getApplicationContext(), "Incorrect Input Sir :) . Try again !", coordinateLayout);
                }
            }
        });
    }

//	private class fetchModelData extends Thread implements Runnable {
//		private String steamID;
//
//		public fetchModelData(String steamID) {
//			// TODO Auto-generated constructor stub
//			this.steamID = steamID;
//		}
//
//		@Override
//		public void run() {
//			super.run();
//			model = Model.getInstance(getApplicationContext(), "76561197963801763");
//			runOnUiThread(new Runnable() {
//				public void run() {
//					launchIntent(steamID);
//				}
//			});
//		}
//	}

}