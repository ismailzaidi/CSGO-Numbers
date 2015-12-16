package com.csgo.iz.adapters.listadapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.csgo.iz.R;
import com.csgo.iz.modal.bean.Achievement;
import com.csgo.iz.views.customviews.AchievementView;

import java.util.List;

public class AchievementAdapter extends ArrayAdapter<Achievement> {

    public AchievementAdapter(Activity context, List<Achievement> objects) {
        super(context, R.layout.custom_achievement_item, objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        AchievementView achievementView = (AchievementView) convertView;
        if (achievementView == null) {
            achievementView = (AchievementView) LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_achievement_item, null, false);
        }
        achievementView.displayAchievement(getItem(position));
        return achievementView;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}
