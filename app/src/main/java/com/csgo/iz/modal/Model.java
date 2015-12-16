package com.csgo.iz.modal;

import android.content.Context;
import android.util.Log;

import com.csgo.iz.modal.bean.Achievement;
import com.csgo.iz.modal.bean.Map;
import com.csgo.iz.modal.bean.Summary;
import com.csgo.iz.modal.bean.Weapon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Model implements Serializable {

    private static Model instance;
    private static Model instanceCompare;
    private static int memory_count = 0;
    private WeaponModel weaponModel;
    private MapModel mapModel;
    private SummaryModel summaryModel;

    private Model(Context context, String userID) {
        weaponModel = new WeaponModel(userID, context);
        summaryModel = new SummaryModel(userID, context);
        mapModel = new MapModel(userID, context);
        memory_count++;
        Log.v("MEMORY_JSON", "Model Class " + String.valueOf(memory_count));
    }

    private Model() {
        setToNull();
    }

    public static Model getInstance(Context context, String userID) {
        Log.v("Friend Detail Tag", "Instance: " + (instance == null));
        if (instance == null) {
            instance = new Model(context, userID);
            return instance;
        }
        return instance;
    }

    public static Model getInstance() {
        if (instance == null) {
            instanceCompare = new Model();
            return instanceCompare;
        }
        return instanceCompare;
    }

    public static void setToNull() {
        instance = null;
    }

    public static void setToNullCompare() {
        instance = null;
    }

    public HashMap<String, ArrayList<Weapon>> getWeaponList() {
        weaponModel.generateWeaponListTwo();
        return weaponModel.getWeaponHash();
    }

    public HashMap<Integer, List<Achievement>> getAchievement() {

        return null;
    }

    public HashMap<String, ArrayList<Map>> getMapList() {
        mapModel.generateMapList();
        return mapModel.getListOfMaps();
    }

    public ArrayList<Summary> getSummary() {
        summaryModel.generateSummary();
        return summaryModel.getListOfSummary();
    }
}
