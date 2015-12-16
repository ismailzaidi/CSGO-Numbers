package com.csgo.iz.modal.http;

import android.content.Context;
import android.support.annotation.Nullable;

import com.csgo.iz.modal.bean.Map;
import com.csgo.iz.modal.bean.Profile;

import java.util.ArrayList;

public class ModelData {

    private static ModelData instance;

    private ModelData() {
    }

    public static ModelData getInstance(String userID, Context context) {
        if (instance == null) {
            instance = new ModelData();
        }
        return instance;
    }

    public static void setToNull() {
        instance = null;
    }

    @Nullable
    public ArrayList<Profile> getFriendsItem() {
        return null;
    }

    @Nullable
    public ArrayList<Map> getMapList() {
        return null;
    }
}
