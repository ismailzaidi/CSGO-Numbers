package com.csgo.iz.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.csgo.iz.Fragments.AchievementItemFragment;
import com.csgo.iz.R;
import com.csgo.iz.modal.bean.Achievement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AchievementViewPagerAdapter extends FragmentPagerAdapter {
    private String[] fragment_titles = {"Team Tactics", "Combat Skills", "Weapon Specialist", "Global Expert", "Arsenal Mode"};
    private Context context;
    private HashMap<Integer, List<Achievement>> map;

    public AchievementViewPagerAdapter(FragmentManager fm, Context context, HashMap<Integer, List<Achievement>> map) {
        super(fm);
        this.context = context;
        this.map = map;
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_tab_achievement, null);
        TextView tabName = (TextView) view.findViewById(R.id.achievement_title);
        TextView tabProgress = (TextView) view.findViewById(R.id.achievement_progress);
        int inComplete = getSizeHash(position) - getInCompleteTasks(position);
        int size = getSizeHash(position);
        String progress = "(" + inComplete + "/" + size + ")";
        Log.v("getTabView Achievements", "Incomplete: " + progress + " Tab Title: " + fragment_titles[position]);
        tabName.setText(fragment_titles[position]);
        tabProgress.setText(progress);
        return view;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragment_titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return AchievementItemFragment.InstanceOf(getAchievementList(position));
    }

    public int getInCompleteTasks(int keyPosition) {
        int incomplete = 0;
        List<Achievement> item = map.get(keyPosition);
        for (int i = 0; i < item.size(); i++) {
            Achievement achievement = item.get(i);
            int lockInfo = achievement.getLockInfo();
            if (lockInfo == 0) {
                incomplete++;
            }
        }
        return incomplete;
    }

    public int getSizeHash(int keyPosition) {
        return map.get(keyPosition).size();
    }

    public ArrayList<Achievement> getAchievementList(int keyPosition) {
        return (ArrayList<Achievement>) map.get(keyPosition);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return 5;
    }

}
