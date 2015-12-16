package com.csgo.iz.modal;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;

import com.csgo.iz.R;
import com.csgo.iz.modal.bean.Map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class MapModel {
    private HashMap<String, ArrayList<Map>> listOfMaps;
    private Hashtable<String, Integer> hashTable;
    private String userID;
    private Context context;

    public MapModel(Context context, Hashtable<String, Integer> hashTable) {
        this.context = context;
        this.hashTable = hashTable;
        generateMapList();
    }

    public MapModel(String userID, Context context) {
        this.userID = userID;
        this.context = context;
    }

    public void generateMapList() {
        if (hashTable != null) {
            listOfMaps = new HashMap<>();
            ArrayList<Map> listMap = getMapList();
            String[] KEYS = {"DEFUSAL", "ARMS_RACE", "HOSTAGE", "DEMOLITION"};
            for (String key : KEYS) {
                ArrayList<Map> tempArr = new ArrayList<>();
                for (Map map : listMap) {
                    if (map.getMapType().contentEquals(key))
                    {
                        tempArr.add(map);
                    }
                }
                listOfMaps.put(key, tempArr);
            }
        }
    }

    public ArrayList<Map> getMapList() {
        if(hashTable!=null) {
            ArrayList<Map> list = new ArrayList<>();
            String[] main_array = context.getResources().getStringArray(R.array.map_data);
            TypedArray image_arr = context.getResources().obtainTypedArray(R.array.map_images);
            Map map;
            int counter = 0;
            for (String item : main_array) {
                item = item.replaceAll("\\s+", "");
                int resource_id = image_arr.getResourceId(counter, -1);
                String mapName = item.split(",")[0];
                String mapType = item.split(",")[1];
                String mapAPI = item.split(",")[2];
                Log.v("getMapList Data", "Map Name: " + mapName + " Map Type: " + mapType + " Map Key: " + mapAPI);
                Integer mapWinObj = hashTable.get("total_wins_map_" + mapAPI);
                Integer mapRoundObj = hashTable.get("total_rounds_map_" + mapAPI);

                int mapRoundsWon = (mapWinObj == null) ? 0 : mapWinObj;
                int mapRoundsPlayed = (mapRoundObj == null) ? 0 : mapRoundObj;
                int mapWinRate = (mapRoundsPlayed == 0 && mapRoundsWon == 0) ? 0
                        : 100 * mapRoundsWon / mapRoundsPlayed;
                map = new Map(resource_id, mapType, mapName, mapRoundsPlayed, mapRoundsWon, mapWinRate);
                Log.v("getMapList Data", map.toString());
                list.add(map);
                counter++;
            }
            image_arr.recycle();
            return list;
        }
        return null;
    }

    public HashMap<String, ArrayList<Map>> getListOfMaps() {
        return listOfMaps;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
