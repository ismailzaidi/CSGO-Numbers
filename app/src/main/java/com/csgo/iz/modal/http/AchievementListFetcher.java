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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AchievementListFetcher {
    private String userID;
    private Context context;
    private HashMap<String, String> tableAchievements;
    private IOOperations ioOperations;

    public AchievementListFetcher(String userID, Context context) {
        this.userID = userID;
        this.context = context;
        ioOperations = new IOOperations(context);
        fetchAchievements();
    }

    public List<List<Achievement>> getAchievementList() {
        if (!tableAchievements.isEmpty()) {
            List<List<Achievement>> achievementMap = new ArrayList<>();
            achievementMap.add(getAchievementTeamTactic());
            achievementMap.add(getCombatSkills());
            achievementMap.add(getWeaponSpeacialist());
            achievementMap.add(getGlobalExpert());
            achievementMap.add(getArsenalMode());
            return achievementMap;
        }
        return null;
    }

    private void fetchAchievements() {
        String achievementListURL = "http://api.steampowered.com/ISteamUserStats/GetPlayerAchievements/v0001/?appid=730&L=EN&" +
                "steamid=" + userID +
                "&key=" + APICall.API_KEY
                + "&format=json";

        tableAchievements = new HTTPHandler().readHTTPRequest(achievementListURL, new HTTPHandler.HTTPHandlerCallback<HashMap<String, String>>() {
            @Override
            public void notFound() {

            }

            @Override
            public void badRequest() {

            }

            @Override
            public HashMap<String, String> response(String achievementListJson) {
                HashMap<String ,String> tableAchievements = new HashMap<>();
                try {
                    ioOperations.writeToFile(IOOperations.USERACHIEVEMENTFILE, achievementListJson);
                    Log.v("DATA_FILE", ioOperations.readFile(IOOperations.USERACHIEVEMENTFILE));
                    JSONObject outObj = new JSONObject(achievementListJson);
                    JSONObject innerObj = outObj.getJSONObject("playerstats");
                    JSONArray statsArr = innerObj.getJSONArray("achievements");
                    for (int j = 0; j < statsArr.length(); j++) {
                        JSONObject obj = statsArr.getJSONObject(j);
                        tableAchievements.put(obj.getString("apiname"), obj.getString("name") + "+" + obj.getString("description") + "+" + obj.getInt("achieved"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return tableAchievements;
            }

            @Override
            public void connectionError(IOException e) {

            }
        });

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

        if (!tableAchievements.isEmpty()) {
            ArrayList<Achievement> list = new ArrayList<>();
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
