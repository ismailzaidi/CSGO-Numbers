package com.csgo.iz.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.csgo.iz.R;
import com.csgo.iz.adapters.viewpager.AchievementViewPagerAdapter;
import com.csgo.iz.adapters.viewpager.DisableSwipeViewPager;
import com.csgo.iz.modal.bean.Achievement;

import java.util.ArrayList;
import java.util.List;

public class AchievementFragment extends Fragment {

    private static String TAG_Achievement = "com.csgo.iz.achievement";
    private static String TAG_Achievement_Size = "size";

    private TabLayout tabLayout;
    private DisableSwipeViewPager viewPager;
    private AchievementViewPagerAdapter adapter;

    public static AchievementFragment InstanceOf(List<ArrayList<Achievement>> achievements) {
        AchievementFragment fragment = new AchievementFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TAG_Achievement_Size, achievements.size());
        for (int i = 0; i < achievements.size(); i++) {
            bundle.putParcelableArrayList(TAG_Achievement+i, achievements.get(i));
        }
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.achievement_fragment, container, false);
        tabLayout = (TabLayout) rootView.findViewById(R.id.sliding_tabs);
        viewPager = (DisableSwipeViewPager) rootView.findViewById(R.id.viewpager);
        int achievementSize = getArguments().getInt(TAG_Achievement_Size);
        List<ArrayList<Achievement>> achievements = new ArrayList<>();
        for (int i = 0; i < achievementSize; i++) {
            ArrayList<Parcelable> parcelableArrayList = getArguments().getParcelableArrayList(TAG_Achievement + i);

            ArrayList<Achievement> arrayList = new ArrayList<>();
            for (Parcelable parcelable : parcelableArrayList) {
                arrayList.add((Achievement) parcelable);
            }
            achievements.add(arrayList);
        }
        adapter = new AchievementViewPagerAdapter(getChildFragmentManager(), achievements);
        viewPager.setAdapter(adapter);

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
                generateTabs();
            }
        });
        return rootView;
    }

    private void generateTabs() {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(adapter.getTabView(getActivity(), i));
        }
    }
}
