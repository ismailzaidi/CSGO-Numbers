package com.csgo.iz.modal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csgo.iz.R;
import com.csgo.iz.compare.JSONCompareData;
import com.csgo.iz.modal.bean.Achievement;
import com.csgo.iz.modal.bean.Map;
import com.csgo.iz.modal.bean.Profile;
import com.csgo.iz.modal.bean.Summary;
import com.csgo.iz.modal.bean.Weapon;
import com.csgo.iz.modal.http.ModelData;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

public class Model implements Serializable {
	/**
	 * 
	 */
	private ModelData data;
	private Context context;
	private static Model instance;
	private static Model instanceCompare;
	private JSONCompareData compareData;
	private ArrayList<String> listOfUsers;
	private WeaponModel weaponModel;
	private MapModel mapModel;
	private SummaryModel summaryModel;
	private static int memory_count = 0;

	private Model(Context context, String userID) {
		this.context = context;
		data = ModelData.getInstance(userID, context);
		weaponModel = new WeaponModel(userID, context);
//		achievementModel = new AchievementModel(userID, context);
		summaryModel = new SummaryModel(userID, context);
		mapModel = new MapModel(userID, context);
		memory_count++;
		Log.v("MEMORY_JSON", "Model Class " + String.valueOf(memory_count));
	}

	private Model(Context context, ArrayList<String> listOfUsers) {
		this.context = context;
		this.listOfUsers = listOfUsers;
//		compareData = JSONCompareData.getInstance(listOfUsers, context);
		setToNull();
	}

	public static Model getInstance(Context context, String userID) {
		Log.v("Friend Detail Tag", "Instance: " + (instance == null));
		if (instance == null) {
			instance = new Model(context, userID);
			return instance;
		}
		return instance;
	}
	public static Model getInstance(Context context, ArrayList<String> listOfUsers) {
		if (instance == null) {
			instanceCompare = new Model(context, listOfUsers);
			return instanceCompare;
		}
		return instanceCompare;
	}
	public HashMap<String, ArrayList<Weapon>> getWeaponList(){
		weaponModel.generateWeaponList(data);
		return weaponModel.getWeaponHash();
	}
	public HashMap<Integer, List<Achievement>> getAchievement(){
//		achievementModel.generateAchievementList(data);
//		return achievementModel.getListOfAchievements();
		
		return null;
	}
	public ArrayList<Profile> getFriendsProfiles() {
		return data.getFriendsItem();
	}


	public HashMap<String, ArrayList<Map>> getMapList() {
		mapModel.generateMapList(data);
		return mapModel.getListOfMaps();
	}
	public ArrayList<Summary> getSummary() {
		summaryModel.generateSummary(data);
		return summaryModel.getListOfSummary();
	}
	public static void setToNull() {
		instance = null;
	}
	
	public static void setToNullCompare() {
		instance = null;
	}
}
