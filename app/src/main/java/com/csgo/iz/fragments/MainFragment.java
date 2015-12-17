package com.csgo.iz.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.csgo.iz.R;
import com.csgo.iz.adapters.viewpager.DisableSwipeViewPager;
import com.csgo.iz.adapters.viewpager.ViewPagerAdapter;
import com.csgo.iz.modal.MapModel;
import com.csgo.iz.modal.SummaryModel;
import com.csgo.iz.modal.WeaponModel;
import com.csgo.iz.modal.bean.Achievement;
import com.csgo.iz.modal.bean.GameMap;
import com.csgo.iz.modal.bean.Profile;
import com.csgo.iz.modal.bean.Summary;
import com.csgo.iz.modal.bean.Weapon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainFragment extends Fragment {

    /**
     * KEYS FOR COLLECTIONS
     */
    private static final String TAG_MAIN_HASHTABLE = "com.csgo.iz.mainfragment.HASHTABLE";
    private static final String TAG_MAIN_ACHIVEMENTS = "com.csgo.iz.mainfragment.ACHIEVEMENTS";
    private static final String TAG_MAIN_ACHIVEMENTS_SIZE = "size";
    private static final String TAG_MAIN_PROFILE = "com.csgo.iz.mainfragment.PROFILE";

    private TabLayout tabLayout;
    private DisableSwipeViewPager pager;
    private ViewPagerAdapter adapterMain;
    private Context context;
    private ArrayList<Summary> summary;
    private HashMap<String, Integer> stats;
    private HashMap<String, ArrayList<Weapon>> listOfWeapons;
    private HashMap<String, ArrayList<GameMap>> listOfMaps;
    private List<ArrayList<Achievement>> listOfAchievements;
    private Profile profile;

    public static MainFragment InstanceOf(HashMap<String, Integer> stats,
                                          List<List<Achievement>> listOfAchievements, Profile profile) {
        MainFragment fragment = new MainFragment();
        Bundle bundle = new Bundle();
        //bundle.putParcelableArrayList(TAG_MAIN_HASHTABLE, new ArrayList<>(stats.keySet()));
        String keyList = "";
        String valueList = "";
        for (String s : stats.keySet()) {
            keyList+=s+",";
            valueList += stats.get(s)+",";
        }
        keyList = keyList.substring(0, keyList.length()-1);
        bundle.putString(TAG_MAIN_HASHTABLE+"_KEYS", keyList);
        bundle.putString(TAG_MAIN_HASHTABLE+"_VALUES", valueList);
        bundle.putInt(TAG_MAIN_ACHIVEMENTS_SIZE, listOfAchievements.size());
        for (int i = 0; i < listOfAchievements.size(); i++) {
            bundle.putParcelableArrayList(TAG_MAIN_ACHIVEMENTS+i, new ArrayList<>(listOfAchievements.get(i)));
        }
        bundle.putParcelable(TAG_MAIN_PROFILE, profile);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();

        View rootView = inflater.inflate(R.layout.main_fragment, container, false);
        pager = (DisableSwipeViewPager) rootView.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) rootView.findViewById(R.id.sliding_tabs);
        pager.setPagingEnabled(true);
        stats = new HashMap<>();

        String[] keys = getArguments().getString(TAG_MAIN_HASHTABLE + "_KEYS").split(",");
        String[] values = getArguments().getString(TAG_MAIN_HASHTABLE + "_VALUES").split(",");
        for (int i = 0; i < keys.length; i++) {
            stats.put(keys[i], Integer.parseInt(values[i]));
        }

        profile = getArguments().getParcelable(TAG_MAIN_PROFILE);
        int achievementSize = getArguments().getInt(TAG_MAIN_ACHIVEMENTS_SIZE);
        listOfAchievements = new ArrayList<>();
        for (int i = 0; i < achievementSize; i++) {
            ArrayList<Achievement> achievementsArray = getArguments().getParcelableArrayList(TAG_MAIN_ACHIVEMENTS + i);
            listOfAchievements.add(achievementsArray);
        }

        genearateLists();
        allocateAdapter();
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(pager);
                generateTabs();
            }
        });
        return rootView;
    }

    private void genearateLists() {
        summary = new SummaryModel(context, stats, profile).getListOfSummary();
        listOfWeapons = new WeaponModel(context, stats).getWeaponHash();
        listOfMaps = new MapModel(context, stats).getListOfMaps();
    }

    private void allocateAdapter() {
        adapterMain = new ViewPagerAdapter(getChildFragmentManager(), getActivity().getApplicationContext(),
                summary, listOfMaps, listOfAchievements, listOfWeapons);
        pager.setAdapter(adapterMain);
        pager.setPagingEnabled(true);
        pager.setOffscreenPageLimit(3);
    }

    private void generateTabs() {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(adapterMain.getTabView(i));
            }
        }
    }
}
