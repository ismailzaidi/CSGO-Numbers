package com.csgo.iz.compare;

import android.content.Context;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Hashtable;

public class JSONCompareData {
	private Object extractor;
	private String SPLIT_KEY = "IFJFGKGDKLFJGDLK";
	private ArrayList<String> listOfUsers;
	private Context context;
	private Hashtable<Integer, JSONArray> userStats;
	public static int COUNTER = 0;
//
//	private JSONCompareData(ArrayList<String> listOfUsers, Context context) {
//		this.listOfUsers = listOfUsers;
//		this.context = context;
////		extractor = new JSONExtractor(listOfUsers);
//		ArrayList<JSONArray> statArray = getStatsObjectArray();
//		generateJSONObject(statArray);
//	}
//
//	public static JSONCompareData getInstance(ArrayList<String> listOfUsers, Context context) {
//		if (instance == null) {
//			instance = new JSONCompareData(listOfUsers, context);
//		}
//		return instance;
//	}
//
//	public static void setToNull() {
//		instance = null;
//	}
//
//	public ArrayList<ArrayList<Map>> getMapListArray() {
//		ArrayList<ArrayList<Map>> list = new ArrayList<ArrayList<Map>>();
//		String[] main_array = context.getResources().getStringArray(R.array.map_data);
//		TypedArray image_arr = context.getResources().obtainTypedArray(R.array.map_images);
//		Map map;
//		Log.v("getMapList Compare", "Map Name: " + listOfUsers.size());
//		ArrayList<JSONArray> statArray = getStatsObjectArray();
//		for (int i = 0; i < listOfUsers.size(); i++) {
//			int counter = 0;
//			ArrayList<Map> maplist = new ArrayList<Map>();
//			for (String item : main_array) {
//				item = item.replaceAll("\\s+", "");
//				int resource_id = image_arr.getResourceId(counter, -1);
//				String mapName = item.split(",")[0];
//				String mapType = item.split(",")[1];
//				String mapAPI = item.split(",")[2];
//				Log.v("getMapList Data", "Map Name: " + mapName + " Map Type: " + mapType + " Map Key: " + mapAPI);
//				Integer mapRoundsWon = getValueForObject(i, "total_wins_map_" + mapAPI);
//				Integer mapRoundsPlayed = getValueForObject(i, "total_rounds_map_" + mapAPI);
//				int mapWinRate = (mapRoundsPlayed == 0 && mapRoundsWon == 0) ? 0
//						: (int) 100 * mapRoundsWon / mapRoundsPlayed;
//				map = new Map(resource_id, mapType, mapName, mapRoundsPlayed, mapRoundsWon, mapWinRate);
//				maplist.add(map);
//				counter++;
//			}
//			list.add(maplist);
//		}
//		return list;
//	}
//
//	private void updateJSONArray(ArrayList<JSONObject> json, String apiKey, String apiValue, int difference) {
//		try {
//			for (int i = 0; i < difference; i++) {
//				JSONObject outerObject = new JSONObject();
//				outerObject.put(apiKey, "0");
//				outerObject.put(apiValue, 0);
//				json.add(outerObject);
//			}
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void generateJSONObject(ArrayList<JSONArray> arr) {
//		userStats = new Hashtable<Integer, JSONArray>();
//		for (int i = 0; i < arr.size(); i++) {
//			JSONArray item = arr.get(i);
//			userStats.put(i, item);
//		}
//
//	}
//
//	public ArrayList<Summary> getUserSummaries() {
//		ArrayList<Summary> listOfSummeries = new ArrayList<Summary>();
//		for (int i = 0; i < listOfUsers.size(); i++) {
//			Profile profile = getProfile(listOfUsers.get(i));
//			GeneralStats generalStats = getSummaryGeneral(i);
//			LastMatchStats lastMatchStats = getSummaryLastMatch(i);
//			OtherStats otherStats = getSummaryOtherStats(i);
//			listOfSummeries.add(new Summary(profile, generalStats, lastMatchStats, otherStats));
//		}
//		return listOfSummeries;
//	}
//
//	private GeneralStats getSummaryGeneral(int userPosition) {
//		String[] list_of_keys = { "total_kills", "total_time_played", "total_deaths", "total_shots_hit",
//				"total_shots_fired", "total_kills_headshot", "total_mvps", "total_matches_won",
//				"total_matches_played" };
//		int totalKills = getValueForObject(userPosition, "total_kills");
//		int total_deaths = getValueForObject(userPosition, "total_deaths");
//		int total_shots_hit = getValueForObject(userPosition, "total_shots_hit");
//		int total_shots_fired = getValueForObject(userPosition, "total_shots_fired");
//		int total_time_played = getValueForObject(userPosition, "total_time_played");
//		int total_kills_headshot = getValueForObject(userPosition, "total_kills_headshot");
//		int total_mvps = getValueForObject(userPosition, "total_mvps");
//		int total_matches_won = getValueForObject(userPosition, "total_matches_won");
//		int total_matches_played = getValueForObject(userPosition, "total_matches_played");
//
//		double kda = (double) totalKills / total_deaths;
//		double accuracy = (double) 100 * total_shots_hit / total_shots_fired;
//		double headShot = (double) 100 * total_shots_hit / total_kills_headshot;
//		double matchWinRate = (double) 100 * total_matches_won / total_matches_played;
//		double time = (double) total_time_played * 1000 / (3600 * 1000);
//		String timeResult = String.format("%.2f", time) + "h";
//		return new GeneralStats(totalKills, kda, accuracy, total_mvps, timeResult, headShot, matchWinRate);
//	}
//
//	public int getValueForObject(int userPosition, String key) {
//
//		JSONArray item = userStats.get(userPosition);
//		for (int i = 0; i < item.length(); i++) {
//			try {
//				JSONObject jsonObject = item.getJSONObject(i);
//				String nameObj = jsonObject.getString("name");
//				if (nameObj.equals(key)) {
//					String valueObj = jsonObject.getString("value");
//					return Integer.parseInt(valueObj);
//				}
//
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return 0;
//	}
//
//	private LastMatchStats getSummaryLastMatch(int userPosition) {
//		String[] weapon_id = context.getResources().getStringArray(R.array.weapon_id);
//		String[] weapon_names = context.getResources().getStringArray(R.array.weapon_names);
//		TypedArray weapon_image_arr = context.getResources().obtainTypedArray(R.array.weapon_images);
//
//		int matchResult = getValueForObject(userPosition, "last_match_wins"); // 4
//		int rounds_won = getValueForObject(userPosition, "last_match_t_wins"); //
//		int rounds_Lost = getValueForObject(userPosition, "last_match_ct_wins");
//		int last_match_kills = getValueForObject(userPosition, "last_match_kills");
//		int last_match_deaths = getValueForObject(userPosition, "last_match_deaths");
//		int last_match_damage = getValueForObject(userPosition, "last_match_damage");
//		int last_match_money_spent = getValueForObject(userPosition, "last_match_money_spent");
//		int last_match_dominations = getValueForObject(userPosition, "last_match_dominations");
//		int last_match_mvps = getValueForObject(userPosition, "last_match_mvps");
//		int last_match_revenges = getValueForObject(userPosition, "last_match_revenges");
//		int weaponID = getValueForObject(userPosition, "last_match_favweapon_id");
//		int weaponShots = getValueForObject(userPosition, "last_match_favweapon_shots");
//		int weaponHits = getValueForObject(userPosition, "last_match_favweapon_hits");
//		int weaponKills = getValueForObject(userPosition, "last_match_favweapon_kills");
//		String roundsWinRatio = "";
//		double threhold = (double) 100 * matchResult / (rounds_won + rounds_Lost);
//		Log.v("TEST Summary", "Threshold: " + threhold);
//		int total_rounds = rounds_won + rounds_Lost;
//		if (threhold < 50) {// Lose
//			roundsWinRatio = "(" + matchResult + "/" + total_rounds + ")- Lost";
//		} else if (threhold == 50) {// Draw
//			roundsWinRatio = "(" + matchResult + "/" + total_rounds + ")- Draw";
//		} else {// Win
//			roundsWinRatio = "(" + matchResult + "/" + total_rounds + ")- Win";
//		}
//		double kda = (double) last_match_kills / last_match_deaths;
//
//		Weapon weapon = null;
//		int position = getWeaponIDPosition(weapon_id, weaponID);
//		int weaponImage = weapon_image_arr.getResourceId(position, -1);
//		String weaponName = weapon_names[position].split(",")[0];
//		String weaponType = weapon_names[position].split(",")[1];
//		int weaponAccuracy = (weaponHits == 0 || weaponShots == 0) ? 0 : 100 * weaponHits / weaponShots;
//		int missedShots = weaponShots - weaponHits;
//		weapon = new Weapon(weaponImage, weaponType, weaponName, weaponKills, weaponHits, weaponAccuracy, missedShots,
//				weaponShots);
//		return new LastMatchStats(roundsWinRatio, String.valueOf(matchResult), weapon, kda, last_match_kills,
//				last_match_deaths, last_match_damage, last_match_money_spent, last_match_mvps, last_match_dominations,
//				last_match_revenges);
//	}
//
//	private int getWeaponIDPosition(String[] weapon_id, int json_weapon_id) {
//		for (int i = 0; i < weapon_id.length; i++) {
//			if (Integer.parseInt(weapon_id[i]) == json_weapon_id) {
//				return i;
//			}
//		}
//		return 0;
//	}
//
//	private OtherStats getSummaryOtherStats(int userPosition) {
//		ArrayList<JSONArray> statArray = getStatsObjectArray();
//		int totalKills = getValueForObject(userPosition, "total_kills");
//		int total_deaths = getValueForObject(userPosition, "total_deaths");
//		int total_kills_headshot = getValueForObject(userPosition, "total_kills_headshot");
//		int total_money_earned = getValueForObject(userPosition, "total_money_earned");
//		int total_damage_done = getValueForObject(userPosition, "total_damage_done");
//		int total_contribution_score = getValueForObject(userPosition, "total_contribution_score");
//		int total_planted_bombs = getValueForObject(userPosition, "total_planted_bombs");
//		int total_defused_bombs = getValueForObject(userPosition, "total_defused_bombs");
//		int total_rescued_hostages = getValueForObject(userPosition, "total_rescued_hostages");
//		int total_matches_played = getValueForObject(userPosition, "total_matches_played");
//		int total_matches_won = getValueForObject(userPosition, "total_matches_won");
//		int total_rounds_played = getValueForObject(userPosition, "total_rounds_played");
//		int total_rounds_won = getValueForObject(userPosition, "total_wins");
//		int total_mvps = getValueForObject(userPosition, "total_mvps");
//		int total_weapons_donated = getValueForObject(userPosition, "total_weapons_donated");
//		int total_kills_enemy_weapon = getValueForObject(userPosition, "total_kills_enemy_weapon");
//		int total_kills_enemy_blinded = getValueForObject(userPosition, "total_kills_enemy_blinded");
//		int total_kills_knife_fight = getValueForObject(userPosition, "total_kills_knife_fight");
//		int total_kills_against_zoomed_sniper = getValueForObject(userPosition, "total_kills_against_zoomed_sniper");
//		int total_shots_fired = getValueForObject(userPosition, "total_shots_fired");
//		int total_shots_hit = getValueForObject(userPosition, "total_shots_hit");
//		int total_dominations = getValueForObject(userPosition, "total_dominations");
//		int total_domination_overkills = getValueForObject(userPosition, "total_domination_overkills");
//		int total_revenges = getValueForObject(userPosition, "total_revenges");
//		int total_wins_pistolround = getValueForObject(userPosition, "total_wins_pistolround");
//		int total_kills_taser = getValueForObject(userPosition, "total_kills_taser");
//		int total_broken_windows = getValueForObject(userPosition, "total_broken_windows");
//		return new OtherStats(totalKills, total_deaths, total_kills_headshot, total_money_earned, total_damage_done,
//				total_contribution_score, total_planted_bombs, total_defused_bombs, total_rescued_hostages,
//				total_matches_played, total_matches_won, total_rounds_played, total_rounds_won, total_mvps,
//				total_weapons_donated, total_kills_enemy_weapon, total_kills_enemy_blinded, total_kills_knife_fight,
//				total_kills_against_zoomed_sniper, total_shots_fired, total_shots_hit, total_dominations,
//				total_domination_overkills, total_revenges, total_wins_pistolround, total_kills_taser,
//				total_broken_windows);
//	}
//
//	public ArrayList<ArrayList<Achievement>> getAchievementList(String[] achievementArr, String[] achievementArrNormal,
//			String[] achievementArrGrey) {
//		ArrayList<ArrayList<Achievement>> list = new ArrayList<ArrayList<Achievement>>();
//		Achievement achievement;
//		ArrayList<JSONArray> achievmentArray = getAchivementObjectArray();
//		for (int i = 0; i < achievmentArray.size(); i++) {
//			int count = 0;
//			ArrayList<Achievement> achievement_list = new ArrayList<Achievement>();
//			for (String item : achievementArr) {
//				try {
//					// int resource_id =
//					// context.getResources().getIdentifier("sample",
//					// "drawable",
//					// "com.csgo.iz");
//					JSONObject obj = getAchievementItem(achievmentArray.get(i), item);
//					String achievementName = obj.getString("name");
//					String achievementDescription = obj.getString("description");
//					int achievementLock = obj.getInt("achieved");
//					String resourceID = achievementArrNormal[count];
//					if (achievementLock == 0) {
//						resourceID = achievementArrGrey[count];
//					}
//					achievement = new Achievement(resourceID, achievementName, achievementDescription, achievementLock);
//					achievement_list.add(achievement);
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//				count++;
//			}
//			list.add(achievement_list);
//		}
//		return list;
//	}
//
//	private JSONObject getAchievementItem(JSONArray achievementArray, String key) {
//		for (int i = 0; i < achievementArray.length(); i++) {
//			try {
//				JSONObject obj = achievementArray.getJSONObject(i);
//				String objName = obj.getString("apiname");
//				if (key.equals(objName)) {
//					return obj;
//				}
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
//		return null;
//	}
//
//	public ArrayList<ArrayList<Weapon>> getWeaponListArray() {
//		ArrayList<ArrayList<Weapon>> list = new ArrayList<ArrayList<Weapon>>(listOfUsers.size());
//		String[] arr = context.getResources().getStringArray(R.array.weapon_names);
//		TypedArray resource_array = context.getResources().obtainTypedArray(R.array.weapon_images);
//		Weapon weapon;
//		ArrayList<JSONArray> statArray = getStatsObjectArray();
//		for (int i = 0; i < listOfUsers.size(); i++) {
//			int counter = 0;
//			ArrayList<Weapon> weaponList = new ArrayList<Weapon>();
//
//			for (String item : arr) {
//				item = item.split(",")[0];
//				item = (item.equals("dual_berettas")) ? "elite" : item;
//				item = (item.equals("m4a4")) ? "m4a1" : item;
//				item = (item.equals("usp_silencer")) ? "hkp2000" : item;
//				// int resource_id =
//				// context.getResources().getIdentifier(item,
//				// "drawable", "com.csgo.iz");
//				int resource_id = resource_array.getResourceId(counter, -1);
//				String weaponName = item.toUpperCase();
//				String weaponType = arr[counter].split(",")[1].replaceAll("\\s+", "");
//				int weaponKills = getValueForObject(i, "total_kills_" + item);
//				int weaponHit = getValueForObject(i, "total_hits_" + item);
//				int weaponShots = getValueForObject(i, "total_shots_" + item);
//				int weaponAccuracy = (int) 100 * weaponHit / weaponShots;
//				int missedShots = weaponShots - weaponHit;
//				weapon = new Weapon(resource_id, weaponType, weaponName, weaponKills, weaponHit, weaponAccuracy,
//						missedShots, weaponShots);
//				Log.v("getWeaponList Special", weapon.toString());
//				weaponList.add(weapon);
//				counter++;
//			}
//			list.add(weaponList);
//		}
//		return list;
//	}

//	private ArrayList<JSONArray> getStatsObjectArray() {
//		ArrayList<JSONArray> statsArr = new ArrayList<JSONArray>();
//		ArrayList<String> listOfStats = extractor.getListOfStats();
//		Log.v("WEAPON_COMPARE_OPTIMIZATION", String.valueOf((COUNTER++)));
//		for (int i = 0; i < listOfStats.size(); i++) {
//			try {
//				String userStats = listOfStats.get(i).split(SPLIT_KEY)[0];
//				JSONObject outObj = new JSONObject(userStats);
//				JSONObject innerObj = outObj.getJSONObject("playerstats");
//				statsArr.add(innerObj.getJSONArray("stats"));
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return statsArr;
//	}
//
//	private ArrayList<JSONArray> getAchivementObjectArray() {
//		ArrayList<JSONArray> statsArr = new ArrayList<JSONArray>();
//		ArrayList<String> listOfStats = extractor.getListOfStats();
//		for (int i = 0; i < listOfStats.size(); i++) {
//			try {
//				String userStats = listOfStats.get(i).split(SPLIT_KEY)[1];
//				JSONObject outObj = new JSONObject(userStats);
//				JSONObject innerObj = outObj.getJSONObject("playerstats");
//				statsArr.add(innerObj.getJSONArray("achievements"));
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return statsArr;
//	}
//
//	public Profile getProfile(String steamID) {
//		JSONArray arr = getFriendProfileObject(steamID);
//		try {
//			JSONObject obj = arr.getJSONObject(0);
//			String userID = obj.getString("steamid");
//			int communityState = obj.getInt("communityvisibilitystate");
//			int profileState = obj.getInt("profilestate");
//			String profileURL = obj.getString("profileurl");
//			String profileAvatarURL = obj.getString("avatarfull");
//			int personstate = obj.getInt("personastate");
//			String userName = obj.getString("personaname");
//			int lastLogin = obj.getInt("lastlogoff");
//			String timeCreated = (obj.isNull("timecreated")) ? " " : obj.getString("timecreated");
//			Profile profile = new Profile(userID, communityState, profileState, profileURL, profileAvatarURL,
//					personstate, userName, lastLogin, timeCreated, "");
//			return profile;
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	private JSONArray getFriendProfileObject(String listOfFriendIDs) {
//		JSONArray statsArr = null;
//		try {
//			JSONObject outObj = new JSONObject(extractor.executeUserFriendsProfile(listOfFriendIDs));
//			JSONObject innerObj = outObj.getJSONObject("response");
//			statsArr = innerObj.getJSONArray("players");
//			
//			
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return statsArr;
//	}
}
