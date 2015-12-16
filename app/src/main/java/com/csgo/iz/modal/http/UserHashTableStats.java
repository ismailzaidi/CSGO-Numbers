package com.csgo.iz.modal.http;

import android.content.Context;

import com.csgo.iz.modal.APICall;
import com.csgo.iz.modal.IOOperations;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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
        listOfTable = handler.readHTTPRequest(userStatsURL, new HTTPHandler.HTTPHandlerCallback<Hashtable<String, Integer>>() {
            @Override
            public void notFound() {

            }

            @Override
            public void badRequest() {

            }

            @Override
            public Hashtable<String, Integer> response(String response) {
                Hashtable<String, Integer> listOfTable = new Hashtable<>();

                ioOperations.writeToFile(IOOperations.USERSTATEFILE, response);

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
    public Hashtable<String, Integer> getListOfTable(){
        return listOfTable;
    }
}
