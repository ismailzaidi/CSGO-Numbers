package com.csgo.iz.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csgo.iz.R;
import com.csgo.iz.adapters.viewpager.DisableSwipeViewPager;
import com.csgo.iz.adapters.viewpager.MapViewPagerAdapter;
import com.csgo.iz.compare.MapCompareViewPagerAdapter;
import com.csgo.iz.modal.bean.Map;
import com.csgo.iz.modal.bean.Summary;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MapFragment extends Fragment {

	private ListView cipherListView;
	private static String TAG_MAP = "com.csgo.iz.map";
	private static String TAG_MAP_COMPARE = "com.csgo.iz.map.compare";
	private static String TAG_MAP_COMPARE_BOOLEAN = "com.csgo.iz.map.compare.BOOLEAN";
	private static String TAG_MAP_COMPARE_SUMMARY = "com.csgo.iz.map.compare.summary";
	private TabLayout tabLayout;
	private DisableSwipeViewPager viewPager;
	private MapViewPagerAdapter adapter;
	private MapCompareViewPagerAdapter compareAdapter;
	private HashMap<String, List<Map>> hashMap;
	private ArrayList<ArrayList<Map>> listOfMaps;
	private ArrayList<Summary> listOfSummeries;

	public static MapFragment InstanceOf(boolean isCompare, HashMap<String, ArrayList<Map>> hashMap) {
		MapFragment fragment = new MapFragment();
		Bundle bundle = new Bundle();
		bundle.putSerializable(TAG_MAP, hashMap);
		bundle.putSerializable(TAG_MAP_COMPARE, hashMap);
		fragment.setArguments(bundle);
		return fragment;
	}

	public static MapFragment InstanceOf(boolean isCompare, ArrayList<ArrayList<Map>> listOfMaps, ArrayList<Summary> listOfSummeries) {
		MapFragment fragment = new MapFragment();
		Bundle bundle = new Bundle();
		bundle.putSerializable(TAG_MAP_COMPARE, listOfMaps);
		bundle.putSerializable(TAG_MAP_COMPARE_SUMMARY, listOfSummeries);
		bundle.putBoolean(TAG_MAP_COMPARE_BOOLEAN, isCompare);
		
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		Context context = getActivity().getApplicationContext();
		View rootView = inflater.inflate(R.layout.map_fragment, container, false);
		tabLayout = (TabLayout) rootView.findViewById(R.id.sliding_tabs);
		viewPager = (DisableSwipeViewPager) rootView.findViewById(R.id.viewpager);
		final boolean isCompare = getArguments().getBoolean(TAG_MAP_COMPARE_BOOLEAN);
		// viewPager.setOffscreenPageLimit(4);
		if (isCompare) {
			listOfMaps = (ArrayList<ArrayList<Map>>) getArguments().getSerializable(TAG_MAP_COMPARE);
			listOfSummeries = (ArrayList<Summary>) getArguments().getSerializable(TAG_MAP_COMPARE_SUMMARY);
			
			compareAdapter = new MapCompareViewPagerAdapter(getChildFragmentManager(), context,listOfMaps,listOfSummeries);
			viewPager.setAdapter(compareAdapter);
		} else {
			hashMap = (HashMap<String, List<Map>>) getArguments().getSerializable(TAG_MAP);
			Log.v("Map Status", "MapFragment: " + String.valueOf(hashMap.size()));
			adapter = new MapViewPagerAdapter(getChildFragmentManager(), hashMap);
			viewPager.setOffscreenPageLimit(3);
			viewPager.setAdapter(adapter);
		}
		tabLayout.post(new Runnable() {
			@Override
			public void run() {
				tabLayout.setupWithViewPager(viewPager);
			}
		});
		return rootView;
	}

	// private void animationSetup(View rootView) {
	// Log.v("animation", "Reached here");
	// YoYo.with(Techniques.BounceInLeft).duration(ANIMATION_TIME).playOn(rootView.findViewById(R.id.schemalayout));
	// }

}
