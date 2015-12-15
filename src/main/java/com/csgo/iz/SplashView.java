package com.csgo.iz;

import com.csgo.iz.modal.SharedPreferenceModel;
import com.csgo.iz.modal.Utility;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class SplashView extends Activity {
    private final int SLEEP_TIME = 2;
    private SharedPreferenceModel model;
    private CoordinatorLayout coordinatorLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_activity);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.snackbarCoordinatorLayout);
        model = new SharedPreferenceModel(getApplicationContext());
        new SplashRunner().start();

    }

    class SplashRunner extends Thread {
        private static final String ERROR_LOG_LOGIN = "MAIN_ACTIVITY_LOGIN";

        public void run() {
            try {
                Thread.sleep(SLEEP_TIME * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                String userID = (model.loadSharedPreferenceUserID() != null) ? userID = model.loadSharedPreferenceUserID() : "0";
                Intent myIntent = null;
                boolean isConnected = Utility.isNetworkAvailable(getApplicationContext());
//                userID = "0"; //TODO //TEST
                if (isConnected) {
                    if (!userID.equals("0")) {
                        myIntent = new Intent(SplashView.this, MainActivity.class);
                    } else {
                        myIntent = new Intent(SplashView.this, LoginActivity.class);
                    }
                    startActivity(myIntent);
                    finish();
                    SplashView.this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                } else {
                    Utility.showSnackBar(getApplicationContext(), "No Internet Connection", coordinatorLayout);
                }

            }

        }

    }

}