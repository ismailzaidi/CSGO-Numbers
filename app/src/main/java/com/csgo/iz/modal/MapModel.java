package com.csgo.iz.modal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import com.csgo.iz.R;
import com.csgo.iz.modal.bean.Map;
import com.csgo.iz.modal.http.ModelData;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;

public class MapModel extends ModelAbstract {
    private HashMap<String, ArrayList<Map>> listOfMaps;
    private Context context;
    private Hashtable<String, Integer> hashTable;

    public MapModel(Context context, Hashtable<String, Integer> hashTable) {
        super(context);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.hashTable = hashTable;
        generateMapList();
    }

    public MapModel(String userID, Context context) {
        super(userID, context);
        // TODO Auto-generated constructor stub
        this.context = context;
    }

    public void generateMapList(ModelData data) {
        if (hashTable != null) {
            listOfMaps = new HashMap<String, ArrayList<Map>>();
            ArrayList<Map> listMap = getMapList();
            String[] KEYS = {"DEFUSAL", "ARMS_RACE", "HOSTAGE", "DEMOLITION"};
            for (int i = 0; i < KEYS.length; i++) {
                // Create ArrayListTemp
                ArrayList<Map> tempArr = new ArrayList<Map>();
                for (int j = 0; j < listMap.size(); j++) {
                    String mapType = listMap.get(j).getMapType();
                    if (mapType.equals(KEYS[i])) {
                        tempArr.add(listMap.get(j));
                    }
                }
                listOfMaps.put(KEYS[i], tempArr);
            }
            Log.v("Map Status", String.valueOf(data.getMapList().size()));
        }
    }

    public void generateMapList() {
        if (hashTable != null) {
            listOfMaps = new HashMap<String, ArrayList<Map>>();
            ArrayList<Map> listMap = getMapList();
            String[] KEYS = {"DEFUSAL", "ARMS_RACE", "HOSTAGE", "DEMOLITION"};
            for (int i = 0; i < KEYS.length; i++) {
                // Create ArrayListTemp
                ArrayList<Map> tempArr = new ArrayList<Map>();
                for (int j = 0; j < listMap.size(); j++) {
                    String mapType = listMap.get(j).getMapType();
                    if (mapType.equals(KEYS[i])) {
                        tempArr.add(listMap.get(j));
                    }
                }
                listOfMaps.put(KEYS[i], tempArr);
            }
        }
    }

    public ArrayList<Map> getMapList() {
        if(hashTable!=null) {
            ArrayList<Map> list = new ArrayList<Map>();
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
                        : (int) 100 * mapRoundsWon / mapRoundsPlayed;
                map = new Map(resource_id, mapType, mapName, mapRoundsPlayed, mapRoundsWon, mapWinRate);
                Log.v("getMapList Data", map.toString());
                list.add(map);
                counter++;
            }
            return list;
        }
        return null;
    }

    // public ArrayList<ArrayList<Map>> getCompareMapList() {
    // return compareData.getMapListArray();
    // }

    public HashMap<String, ArrayList<Map>> getListOfMaps() {
        return listOfMaps;
    }
}
