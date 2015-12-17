package com.csgo.iz.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.csgo.iz.R;
import com.csgo.iz.adapters.viewpager.DisableSwipeViewPager;
import com.csgo.iz.adapters.viewpager.MapViewPagerAdapter;
import com.csgo.iz.modal.bean.GameMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapFragment extends Fragment {

    private static String TAG_MAP = "com.csgo.iz.map";
    private TabLayout tabLayout;
    private DisableSwipeViewPager viewPager;
    private MapViewPagerAdapter adapter;
    private HashMap<String, List<GameMap>> hashMap;

    public static MapFragment InstanceOf(HashMap<String, ArrayList<GameMap>> hashMap) {
        MapFragment fragment = new MapFragment();
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
        hashMap = (HashMap<String, List<GameMap>>) getArguments().getSerializable(TAG_MAP);
        Log.v("Map Status", "MapFragment: " + String.valueOf(hashMap.size()));
        adapter = new MapViewPagerAdapter(getChildFragmentManager(), hashMap);
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
