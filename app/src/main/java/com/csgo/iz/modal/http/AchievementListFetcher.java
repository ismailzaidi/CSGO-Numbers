package com.csgo.iz.modal.http;

import android.content.Context;
import android.util.Log;

import com.csgo.iz.R;
import com.csgo.iz.modal.APICall;
import com.csgo.iz.modal.IOOperations;
import com.csgo.iz.modal.bean.Achievement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class AchievementListFetcher {
    private String userID;
    private HashMap<Integer, List<Achievement>> achievementMap;
    private Context context;
    private Hashtable<String, String> tableAchievements;
    private IOOperations ioOperations;

    public AchievementListFetcher(String userID, Context context) {
        this.userID = userID;
        this.context = context;
        ioOperations = new IOOperations(context);
        fetchAchievements();
    }

    public HashMap<Integer, List<Achievement>> getAchievementList() {
        if (!tableAchievements.isEmpty()) {
            achievementMap = new HashMap<>();
            achievementMap.put(0, getAchievementTeamTactic());
            achievementMap.put(1, getCombatSkills());
            achievementMap.put(2, getWeaponSpeacialist());
            achievementMap.put(3, getGlobalExpert());
            achievementMap.put(4, getArsenalMode());
            return achievementMap;
        }
        return null;
    }

    private void fetchAchievements() {
        String achievementListURL = "http://api.steampowered.com/ISteamUserStats/GetPlayerAchievements/v0001/?appid=730&L=EN&steamid="
                + userID + "&key=" + APICall.API_KEY + "&format=json";
        Log.v("achievements", "Achievement User List: " + userID);
        HTTPHandler handler = new HTTPHandler();
        String achievementListJson = (handler.readHTTPRequest(achievementListURL) == null) ? null : handler.readHTTPRequest(achievementListURL);
        if (achievementListJson != null) {
            tableAchievements = new Hashtable<>();
            try {
                ioOperations.writeToFile(IOOperations.USERACHIEVEMENTFILE, achievementListJson);
                Log.v("DATA_FILE", ioOperations.readFile(IOOperations.USERACHIEVEMENTFILE));
                JSONObject outObj = new JSONObject(achievementListJson);
                JSONObject innerObj = outObj.getJSONObject("playerstats");
                JSONArray statsArr = innerObj.getJSONArray("achievements");
                for (int j = 0; j < statsArr.length(); j++) {
                    JSONObject obj = statsArr.getJSONObject(j);
                    String objName = obj.getString("apiname");
                    String achievementName = obj.getString("name");
                    String achievementDescription = obj.getString("description");
                    int achievementLock = obj.getInt("achieved");
                    String value = achievementName + "+" + achievementDescription + "+" + achievementLock;
                    tableAchievements.put(objName, value);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private ArrayList<Achievement> getAchievementTeamTactic() {
        String[] achievementArr = context.getResources().getStringArray(R.array.achievement_1_id);
        String[] achievementArrNormal = context.getResources().getStringArray(R.array.achievement_1_icon_normal);
        String[] achievementArrGrey = context.getResources().getStringArray(R.array.achievement_1_icon_grey);
        return getAchievementList(achievementArr, achievementArrNormal, achievementArrGrey);
    }

    private ArrayList<Achievement> getCombatSkills() {
        String[] achievementArr = context.getResources().getStringArray(R.array.achievement_2_id);
        String[] achievementArrNormal = context.getResources().getStringArray(R.array.achievement_2_icon_normal);
        String[] achievementArrGrey = context.getResources().getStringArray(R.array.achievement_2_icon_grey);
        return getAchievementList(achievementArr, achievementArrNormal, achievementArrGrey);
    }

    private ArrayList<Achievement> getWeaponSpeacialist() {
        String[] achievementArr = context.getResources().getStringArray(R.array.achievement_3_id);
        String[] achievementArrNormal = context.getResources().getStringArray(R.array.achievement_3_icon_normal);
        String[] achievementArrGrey = context.getResources().getStringArray(R.array.achievement_3_icon_grey);
        return getAchievementList(achievementArr, achievementArrNormal, achievementArrGrey);
    }

    private ArrayList<Achievement> getGlobalExpert() {
        String[] achievementArr = context.getResources().getStringArray(R.array.achievement_4_id);
        String[] achievementArrNormal = context.getResources().getStringArray(R.array.achievement_4_icon_normal);
        String[] achievementArrGrey = context.getResources().getStringArray(R.array.achievement_4_icon_grey);
        return getAchievementList(achievementArr, achievementArrNormal, achievementArrGrey);
    }

    private ArrayList<Achievement> getArsenalMode() {
        String[] achievementArr = context.getResources().getStringArray(R.array.achievement_5_id);
        String[] achievementArrNormal = context.getResources().getStringArray(R.array.achievement_5_icon_normal);
        String[] achievementArrGrey = context.getResources().getStringArray(R.array.achievement_5_icon_grey);

        return getAchievementList(achievementArr, achievementArrNormal, achievementArrGrey);
    }

    private ArrayList<Achievement> getAchievementList(String[] achievementArr, String[] achievementArrNormal,
                                                      String[] achievementArrGrey) {

        Log.v("ACHIEVEMENT_LOG", "Achievment Table: " + tableAchievements);
        if (!tableAchievements.isEmpty()) {
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
        return null;
    }
}
