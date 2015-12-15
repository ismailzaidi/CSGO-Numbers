package com.csgo.iz.modal.http;

import java.util.ArrayList;
import java.util.Hashtable;

import com.csgo.iz.modal.bean.Achievement;
import com.csgo.iz.modal.bean.Map;
import com.csgo.iz.modal.bean.Profile;
import com.csgo.iz.modal.bean.Summary;
import com.csgo.iz.modal.bean.Weapon;

import android.content.Context;

public class ModelData {
	private static ModelData instance;
	private String userID;
	private Context context;
	private Hashtable<String, Integer> tableStats;
	private Hashtable<String, String> tableAchievements;
	private Summary summary;
	private Profile profile;
	private ArrayList<Profile> friendsProfiles;
	private ArrayList<Weapon> weaponList;
	private ArrayList<Map> mapList;

	private ModelData(final String userID, final Context context) {
		this.userID = userID;
		this.context = context;
	}

	public static ModelData getInstance(String userID, Context context) {
		if (instance == null) {
			instance = new ModelData(userID, context);
		}
		return instance;
	}

	public static void setToNull() {
		instance = null;
	}

	public Summary getUserSummary() {
		return summary;
	}

	public ArrayList<Profile> getFriendsItem() {
		return friendsProfiles;
	}

	public ArrayList<Weapon> getWeaponList() {
		return weaponList;
	}

	public ArrayList<Map> getMapList() {
		return mapList;
	}

	public ArrayList<Achievement> getAchievementList(String[] achievementArr, String[] achievementArrNormal,
			String[] achievementArrGrey) {
		ArrayList<Achievement> list = new ArrayList<Achievement>();
		Achievement achievement;
		int count = 0;
		for (String item : achievementArr) {
			String[] achievementAPI = tableAchievements.get(item).split("\\+");

			String achievementName = achievementAPI[0];
			String achievementDescription = achievementAPI[1];
			int achievementLock = Integer.parseInt(achievementAPI[2]);
			String resourceID = achievementArrNormal[count];
			if (achievementLock == 0) {
				resourceID = achievementArrGrey[count];
			}
			achievement = new Achievement(resourceID, achievementName, achievementDescription, achievementLock);
			list.add(achievement);
			count++;
		}
		return list;
	}
}
