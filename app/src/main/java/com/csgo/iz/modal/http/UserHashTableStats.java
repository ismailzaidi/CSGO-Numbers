package com.csgo.iz.modal.http;

import com.csgo.iz.modal.APICall;
import com.csgo.iz.modal.IOOperations;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Yusuf on 08/12/2015.
 */
public class UserHashTableStats {
    private HashMap<String, Integer> listOfTable;
    private String userID;
    private IOOperations ioOperations;
    public UserHashTableStats(String userID){
        this.userID = userID;
        generateStatTable();
    }
    private void generateStatTable() {
        String userStatsURL = "http://api.steampowered.com/ISteamUserStats/GetUserStatsForGame/v0002/?appid=730&key="+ APICall.API_KEY+"&steamid="
                + userID;
        HTTPHandler handler = new HTTPHandler();
        listOfTable = handler.readHTTPRequest(userStatsURL, new HTTPHandler.HTTPHandlerCallback<HashMap<String, Integer>>() {
            @Override
            public void notFound() {

            }

            @Override
            public void badRequest() {

            }

            @Override
            public HashMap<String, Integer> response(String response) {
                HashMap<String, Integer> listOfTable = new HashMap<>();

                try {
                    JSONArray statsArr = new JSONObject(response).getJSONObject("playerstats").getJSONArray("stats");

                    for (int i = 0; i < statsArr.length(); i++) {
                        JSONObject obj = statsArr.getJSONObject(i);
                        listOfTable.put(obj.getString("name"), obj.getInt("value"));
                    }
                    return listOfTable;
                }
                catch(JSONException e)
                {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public void connectionError(IOException e) {

            }
        });

    }
    public HashMap<String, Integer> getListOfTable(){
        return listOfTable;
    }
}
