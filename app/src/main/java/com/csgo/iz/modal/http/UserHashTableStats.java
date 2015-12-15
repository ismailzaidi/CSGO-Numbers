package com.csgo.iz.modal.http;

import android.content.Context;

import com.csgo.iz.modal.APICall;
import com.csgo.iz.modal.IOOperations;
import com.csgo.iz.modal.http.HTTPHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;

/**
 * Created by Yusuf on 08/12/2015.
 */
public class UserHashTableStats {
    private Hashtable<String, Integer> listOfTable;
    private String userID;
    private Context context;
    private IOOperations ioOperations;
    public UserHashTableStats(String userID,Context context){
        this.userID = userID;
        this.context = context;
        ioOperations = new IOOperations(context);
        generateStatTable();
    }
    private void generateStatTable() {
        String userStatsURL = "http://api.steampowered.com/ISteamUserStats/GetUserStatsForGame/v0002/?appid=730&key="+ APICall.API_KEY+"&steamid="
                + userID;
        HTTPHandler handler = new HTTPHandler();
        Object jsonObject = handler.readHTTPRequest(userStatsURL);
        // Log.v("WEAPON_COMPARE_OPTIMIZATION",
        // String.valueOf((JSONCompareData.COUNTER++)));
        try {
            if(jsonObject!=null){
                listOfTable = new Hashtable<String, Integer>();
                String jsonData = (String) jsonObject;
                ioOperations.writeToFile(IOOperations.USERSTATEFILE,jsonData);
                JSONObject outObj = new JSONObject(jsonData);
                JSONObject innerObj = outObj.getJSONObject("playerstats");
                JSONArray statsArr = innerObj.getJSONArray("stats");

                for (int i = 0; i < statsArr.length(); i++) {
                    JSONObject obj = statsArr.getJSONObject(i);
                    String objName = obj.getString("name");
                    listOfTable.put(objName, obj.getInt("value"));
                }
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public Hashtable<String, Integer> getListOfTable(){
        return listOfTable;
    }
}
