package com.csgo.iz.modal;

import android.content.Context;

public class ModelAbstract {
	private String userID;
	private Context context;
	public ModelAbstract(String userID, Context context) {
		super();
		this.userID = userID;
		this.context = context;
	}
	public ModelAbstract(Context context) {
		super();
		this.context = context;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public Context getContext() {
		return context;
	}
	public void setContext(Context context) {
		this.context = context;
	}
}
