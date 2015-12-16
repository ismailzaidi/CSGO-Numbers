package com.csgo.iz.modal.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class GlobalData implements Serializable {
    private Profile personalProfile;

    public GlobalData(Profile personalProfile, ArrayList<Profile> listOfFriends, Hashtable<String, Integer> stats,
                      HashMap<Integer, List<Achievement>> listOfAchievements) {
        super();
        this.personalProfile = personalProfile;
        this.listOfFriends = listOfFriends;
        this.stats = stats;
        this.listOfAchievements = listOfAchievements;
    }

    public GlobalData(Profile personalProfile, Hashtable<String, Integer> stats,
                      HashMap<Integer, List<Achievement>> listOfAchievements) {
        super();
        this.personalProfile = personalProfile;
        this.stats = stats;
        this.listOfAchievements = listOfAchievements;
    }

    private ArrayList<Profile> listOfFriends;
    private Hashtable<String, Integer> stats;
    private HashMap<Integer, List<Achievement>> listOfAchievements;

    public Profile getPersonalProfile() {
        return personalProfile;
    }

    public void setPersonalProfile(Profile personalProfile) {
        this.personalProfile = personalProfile;
    }

    public ArrayList<Profile> getListOfFriends() {
        return listOfFriends;
    }

    public void setListOfFriends(ArrayList<Profile> listOfFriends) {
        this.listOfFriends = listOfFriends;
    }

    public Hashtable<String, Integer> getStats() {
        return stats;
    }

    public void setStats(Hashtable<String, Integer> stats) {
        this.stats = stats;
    }

    public HashMap<Integer, List<Achievement>> getListOfAchievements() {
        return listOfAchievements;
    }

    public void setListOfAchievements(HashMap<Integer, List<Achievement>> listOfAchievements) {
        this.listOfAchievements = listOfAchievements;
    }

}
