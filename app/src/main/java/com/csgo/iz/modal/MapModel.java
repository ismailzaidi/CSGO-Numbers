package com.csgo.iz.modal;

import android.content.Context;
import android.content.res.TypedArray;

import com.csgo.iz.R;
import com.csgo.iz.modal.bean.GameMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class MapModel {
    private HashMap<String, ArrayList<GameMap>> listOfMaps;
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
            ArrayList<GameMap> listGameMap = getMapList();
            String[] KEYS = {"DEFUSAL", "ARMS_RACE", "HOSTAGE", "DEMOLITION"};
            for (String key : KEYS) {
                ArrayList<GameMap> tempArr = new ArrayList<>();
                for (GameMap gameMap : listGameMap) {
                    if (gameMap.mapType.contentEquals(key))
                    {
                        tempArr.add(gameMap);
                    }
                }
                listOfMaps.put(key, tempArr);
            }
        }
    }

    public ArrayList<GameMap> getMapList() {
        if(hashTable!=null) {
            ArrayList<GameMap> list = new ArrayList<>();
            String[] main_array = context.getResources().getStringArray(R.array.map_data);
            TypedArray image_arr = context.getResources().obtainTypedArray(R.array.map_images);

            for (int counter = 0; counter < main_array.length; counter++) {
                String item = main_array[counter];
                int resource_id = image_arr.getResourceId(counter, -1);
                String[] mapComponents = item.split(",");

                Integer mapWinObj = hashTable.get("total_wins_map_" + mapComponents[2]);
                Integer mapRoundObj = hashTable.get("total_rounds_map_" + mapComponents[2]);

                int mapRoundsWon = (mapWinObj == null) ? 0 : mapWinObj;
                int mapRoundsPlayed = (mapRoundObj == null) ? 0 : mapRoundObj;
                int mapWinRate = (mapRoundsPlayed == 0 && mapRoundsWon == 0) ? 0 : 100 * mapRoundsWon / mapRoundsPlayed;

                list.add(new GameMap(resource_id, mapComponents[1], mapComponents[0], mapRoundsPlayed, mapRoundsWon, mapWinRate));
            }
            image_arr.recycle();
            return list;
        }
        return null;
    }

    public HashMap<String, ArrayList<GameMap>> getListOfMaps() {
        return listOfMaps;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
