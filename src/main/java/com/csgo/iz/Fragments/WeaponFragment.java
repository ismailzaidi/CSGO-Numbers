package com.csgo.iz.Fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csgo.iz.R;
import com.csgo.iz.Adapters.DisableSwipeViewPager;
import com.csgo.iz.Adapters.WeaponViewPagerAdapter;
import com.csgo.iz.compare.AchievementViewPagerCompareAdapter;
import com.csgo.iz.compare.WeaponCompareViewPagerAdapter;
import com.csgo.iz.modal.bean.Summary;
import com.csgo.iz.modal.bean.Weapon;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class WeaponFragment extends Fragment {

	private ListView cipherListView;
	private static String TAG_MAP = "com.csgo.iz.map";
	private static String TAG_MAP_COMPARE = "com.csgo.iz.map.compare";
	private static String TAG_MAP_COMPARE_SUMMARY = "com.csgo.iz.map.compare.summary";
	private static String TAG_MAP_COMPARE_BOOLEAN = "com.csgo.iz.map.compare.BOOLEAN";
	private TabLayout tabLayout;
	private DisableSwipeViewPager viewPager;
	private WeaponViewPagerAdapter adapter;
	private WeaponCompareViewPagerAdapter compareAdapter;
	private HashMap<String, List<Weapon>> hashMap;
	private ArrayList<ArrayList<Weapon>>  listOfWeaponCompare;
	private ArrayList<Summary> summaryList;

	public static WeaponFragment InstanceOf(boolean isCompare, HashMap<String, ArrayList<Weapon>> hashMap) {
		WeaponFragment fragment = new WeaponFragment();
		Bundle bundle = new Bundle();
		bundle.putSerializable(TAG_MAP, hashMap);
		bundle.putSerializable(TAG_MAP_COMPARE, hashMap);
		fragment.setArguments(bundle);
		return fragment;
	}

	public static WeaponFragment InstanceOf(boolean isCompare, ArrayList<ArrayList<Weapon>> hashMap, ArrayList<Summary> listOfSummary) {
		WeaponFragment fragment = new WeaponFragment();
		Bundle bundle = new Bundle();
		bundle.putSerializable(TAG_MAP_COMPARE, hashMap);
		bundle.putBoolean(TAG_MAP_COMPARE_BOOLEAN, isCompare);
		bundle.putSerializable(TAG_MAP_COMPARE_SUMMARY, listOfSummary);
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
			listOfWeaponCompare = (ArrayList<ArrayList<Weapon>>) getArguments().getSerializable(TAG_MAP_COMPARE);
			summaryList = (ArrayList<Summary>) getArguments().getSerializable(TAG_MAP_COMPARE_SUMMARY);
			compareAdapter = new WeaponCompareViewPagerAdapter(getChildFragmentManager(), context, listOfWeaponCompare, summaryList);
			viewPager.setAdapter(compareAdapter);
		} else {
			hashMap = (HashMap<String, List<Weapon>>) getArguments().getSerializable(TAG_MAP);
			Log.v("Weapon Tab Stat", "WeaponFragment: " + hashMap.size());
			adapter = new WeaponViewPagerAdapter(getChildFragmentManager(), context, hashMap);
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
