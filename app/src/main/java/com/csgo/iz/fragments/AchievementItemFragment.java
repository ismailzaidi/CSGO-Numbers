package com.csgo.iz.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.csgo.iz.R;
import com.csgo.iz.adapters.listadapters.AchievementAdapter;
import com.csgo.iz.modal.bean.Achievement;

import java.util.ArrayList;
import java.util.Collections;

public class AchievementItemFragment extends Fragment {

    private static String TAG_CSGO_NUMBERS = "com.csgo.iz.Fragment";

    public static AchievementItemFragment InstanceOf(ArrayList<Achievement> list) {
        AchievementItemFragment fragment = new AchievementItemFragment();
        Bundle bundle = new Bundle();
        Collections.sort(list);
        bundle.putSerializable(TAG_CSGO_NUMBERS, list);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.achievement_item_fragment, container, false);
        ListView achievementListView = (ListView) rootView.findViewById(R.id.listofAchievementsListView);
        ArrayList<Achievement> listofAchievements = (ArrayList<Achievement>) getArguments().getSerializable(TAG_CSGO_NUMBERS);
        setupListView(listofAchievements, achievementListView);
        return rootView;
    }

    private void setupListView(ArrayList<Achievement> list, ListView achievementListView) {
        AchievementAdapter adapter = new AchievementAdapter(getActivity(), list);
        achievementListView.setAdapter(adapter);
    }
}
