package com.csgo.iz.modal.http.threads;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.csgo.iz.modal.bean.Achievement;
import com.csgo.iz.modal.bean.GlobalData;
import com.csgo.iz.modal.bean.Profile;
import com.csgo.iz.modal.http.AchievementListFetcher;
import com.csgo.iz.modal.http.FriendListFetcher;
import com.csgo.iz.modal.http.UserHashTableStats;
import com.csgo.iz.modal.http.UserProfile;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class GlobalAsync extends AsyncTask<Object, Integer, GlobalData> {

    public interface UserGlobalCallback {
        void UserGlobalIsAvailable(GlobalData data);
    }

    public interface UpdateUserProgress {
        void updateProgress(int value);
    }

    private static int PROGRESS_ITEM = 10;
    private Context context;
    private final WeakReference<UpdateUserProgress> progressListener;
    private UserGlobalCallback listener;
    private String userID;
    private boolean isFriend;

    public GlobalAsync(UserGlobalCallback listener, UpdateUserProgress progressListener, String userID, Context context, boolean isFriend) {
        this.listener = listener;
        this.progressListener = new WeakReference<UpdateUserProgress>(progressListener);
        this.userID = userID;
        this.context = context;
        this.isFriend = isFriend;
    }

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
//		dialog = Utility.generateProgressBar(context, "Loading Profile", "Loading...");
    }

    @Override
    protected void onPostExecute(GlobalData result) {
        listener.UserGlobalIsAvailable(result);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        UpdateUserProgress userProgress = progressListener.get();
        if (userProgress != null) {
            Log.v("progress", "Values: " + values[0]);
            userProgress.updateProgress(values[0]);
        }
    }

    @Override
    protected GlobalData doInBackground(Object... params) {
        Profile personalProfile;
        ArrayList<Profile> friendList;
        HashMap<String, Integer> statTable;
        List<List<Achievement>> listOfAchievements;
        GlobalData data;
        if (isFriend) {
            updateValues(0,20);
            updateValues(20,40);
            personalProfile = new UserProfile(userID).getProfile();
            updateValues(40,80);
            updateValues(80,100);
            statTable = new UserHashTableStats(userID).getListOfTable();
            listOfAchievements = new AchievementListFetcher(userID, context).getAchievementList();
            data = new GlobalData(personalProfile, Collections.<Profile>emptyList(), statTable, listOfAchievements);
            if(statTable == null){
                return null;
            }
        } else {
            updateValues(0,5);
            updateValues(5,10);
            personalProfile = new UserProfile(userID).getProfile();
            updateValues(10,15);
            updateValues(15,20);
            updateValues(20,25);
            friendList = new FriendListFetcher(userID).getProfiles();
            updateValues(25,60);
            updateValues(60,80);
            statTable = new UserHashTableStats(userID).getListOfTable();
            updateValues(80,85);
            listOfAchievements = new AchievementListFetcher(userID, context).getAchievementList();
            updateValues(85,100);
            if(statTable == null){
                return null;
            }
            data = new GlobalData(personalProfile, friendList, statTable, listOfAchievements);
        }
        return data;
    }

    public void updateValues(int from,int value) {

        for (int i = from; i <= value; i++) {
            publishProgress(i);
        }
    }

}
