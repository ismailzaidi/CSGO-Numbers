package com.csgo.iz.modal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class SharedPreferenceModel {
	private String FRIEND_KEY = "com.csgo.iz.friends";
	private String USER_KEY = "com.csgo.iz.friends";
	private String INTERNET_KEY = "com.csgo.iz.internet";
	private Context context;
	public SharedPreferenceModel(Context context) {
		super();
		this.context = context;
	}

	public void saveSharedPreferenceFriend(String steamID) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		Editor editor = pref.edit();
		editor.putString(FRIEND_KEY, steamID);
		editor.apply();
	}

	public String loadSharedPreferenceFriend() {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		return pref.getString(FRIEND_KEY, "0");
	}
	public void saveSharedUserID(String steamID) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		Editor editor = pref.edit();
		editor.putString(USER_KEY, steamID);
		editor.apply();
	}
	
	public String loadSharedPreferenceUserID() {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		return pref.getString(USER_KEY, "0");
	}
	public void saveSharedPreferenceInternetUser(boolean isInternet) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		Editor editor = pref.edit();
		editor.putBoolean(INTERNET_KEY, isInternet);
		editor.apply();
	}

	public boolean loadSharedPreferenceInternet() {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		return pref.getBoolean(INTERNET_KEY, false);
	}
}
