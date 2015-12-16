package com.csgo.iz.adapters.viewpager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;

import com.csgo.iz.fragments.AchievementItemFragment;
import com.csgo.iz.R;
import com.csgo.iz.modal.bean.Achievement;
import com.csgo.iz.views.customviews.AchievementTabView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AchievementViewPagerAdapter extends FragmentPagerAdapter {
    private String[] fragment_titles = {"Team Tactics", "Combat Skills", "Weapon Specialist", "Global Expert", "Arsenal Mode"};
    private HashMap<Integer, List<Achievement>> achievementMap;

    public AchievementViewPagerAdapter(FragmentManager fm, HashMap<Integer, List<Achievement>> achievementMap) {
        super(fm);
        this.achievementMap = achievementMap;
    }

    @NonNull
    public View getTabView(Context context, int position) {
        AchievementTabView view = (AchievementTabView) LayoutInflater.from(context).inflate(R.layout.custom_tab_achievement, null);
        view.displayTabForAchievements(fragment_titles[position], achievementMap.get(position));
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


    public ArrayList<Achievement> getAchievementList(int keyPosition) {
        return (ArrayList<Achievement>) achievementMap.get(keyPosition);
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
