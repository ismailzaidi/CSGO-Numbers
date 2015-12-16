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
import java.util.Hashtable;
import java.util.List;

public class MainFragment extends Fragment {

    private static String TAG_MAIN_STEAMID = "com.csgo.iz.mainfragment.steamid";
    /**
     * KEYS FOR COLLECTIONS
     */
    private static String TAG_MAIN_HASHTABLE = "com.csgo.iz.mainfragment.HASHTABLE";
    private static String TAG_MAIN_ACHIVEMENTS = "com.csgo.iz.mainfragment.ACHIEVEMENTS";
    private static String TAG_MAIN_PROFILE = "com.csgo.iz.mainfragment.PROFILE";
    private TabLayout tabLayout;
    private DisableSwipeViewPager pager;
    private ViewPagerAdapter adapterMain;
    private Context context;
    private ArrayList<Summary> summary;
    private Hashtable<String, Integer> stats;
    private HashMap<String, ArrayList<Weapon>> listOfWeapons;
    private HashMap<String, ArrayList<GameMap>> listOfMaps;
    private HashMap<Integer, List<Achievement>> listOfAchievements;
    private Profile profile;

    public static MainFragment InstanceOf(Hashtable<String, Integer> stats,
                                          HashMap<Integer, List<Achievement>> listOfAchievements, Profile profile) {
        MainFragment fragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TAG_MAIN_HASHTABLE, stats);
        bundle.putSerializable(TAG_MAIN_ACHIVEMENTS, listOfAchievements);
        bundle.putSerializable(TAG_MAIN_PROFILE, profile);
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
        stats = (Hashtable<String, Integer>) getArguments().getSerializable(TAG_MAIN_HASHTABLE);

        profile = (Profile) getArguments().getSerializable(TAG_MAIN_PROFILE);
        listOfAchievements = (HashMap<Integer, List<Achievement>>) getArguments().getSerializable(TAG_MAIN_ACHIVEMENTS);
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
        adapterMain = new ViewPagerAdapter(this.getChildFragmentManager(), this.getActivity().getApplicationContext(),
                summary, listOfMaps, listOfAchievements, listOfWeapons);
        pager.setAdapter(adapterMain);
        pager.setPagingEnabled(true);
        pager.setOffscreenPageLimit(3);
    }

    private void generateTabs() {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(adapterMain.getTabView(i));
        }
    }
}
