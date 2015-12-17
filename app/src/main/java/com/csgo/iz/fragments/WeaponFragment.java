package com.csgo.iz.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.csgo.iz.R;
import com.csgo.iz.adapters.listadapters.WeaponViewPagerAdapter;
import com.csgo.iz.adapters.viewpager.DisableSwipeViewPager;
import com.csgo.iz.modal.bean.Weapon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WeaponFragment extends Fragment {

    private static String TAG_MAP = "com.csgo.iz.map";
    private TabLayout tabLayout;
    private DisableSwipeViewPager viewPager;
    private WeaponViewPagerAdapter adapter;
    private HashMap<String, List<Weapon>> hashMap;

    public static WeaponFragment InstanceOf(HashMap<String, ArrayList<Weapon>> hashMap) {
        WeaponFragment fragment = new WeaponFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TAG_MAP, hashMap);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.map_fragment, container, false);
        tabLayout = (TabLayout) rootView.findViewById(R.id.sliding_tabs);
        viewPager = (DisableSwipeViewPager) rootView.findViewById(R.id.viewpager);
        hashMap = (HashMap<String, List<Weapon>>) getArguments().getSerializable(TAG_MAP);
        Log.v("Weapon Tab Stat", "WeaponFragment: " + hashMap.size());
        adapter = new WeaponViewPagerAdapter(getChildFragmentManager(), hashMap);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
        return rootView;
    }
}
