package com.csgo.iz.modal.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.List;

public class GlobalData implements Parcelable {

    public final Profile personalProfile;
    public final List<Profile> listOfFriends;
    public final HashMap<String, Integer> stats;
    public final List<List<Achievement>> listOfAchievements;
    public GlobalData(Profile personalProfile, List<Profile> listOfFriends, HashMap<String, Integer> stats,
                      List<List<Achievement>> listOfAchievements) {
        this.personalProfile = personalProfile;
        this.listOfFriends = listOfFriends;
        this.stats = stats;
        this.listOfAchievements = listOfAchievements;
    }

    protected GlobalData(Parcel in) {
        personalProfile = in.readParcelable(Profile.class.getClassLoader());
        listOfFriends = in.createTypedArrayList(Profile.CREATOR);
        stats = in.readHashMap(Integer.class.getClassLoader());
        listOfAchievements = in.readArrayList(Achievement.class.getClassLoader());
    }

    public static final Creator<GlobalData> CREATOR = new Creator<GlobalData>() {
        @Override
        public GlobalData createFromParcel(Parcel in) {
            return new GlobalData(in);
        }

        @Override
        public GlobalData[] newArray(int size) {
            return new GlobalData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(personalProfile, flags);
        dest.writeTypedList(listOfFriends);
        dest.writeMap(stats);
        dest.writeList(listOfAchievements);
    }
}
