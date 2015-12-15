package com.csgo.iz.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.csgo.iz.Adapters.AchievementViewPagerAdapter;
import com.csgo.iz.Adapters.DisableSwipeViewPager;
import com.csgo.iz.R;
import com.csgo.iz.compare.AchievementViewPagerCompareAdapter;
import com.csgo.iz.modal.bean.Achievement;
import com.csgo.iz.modal.bean.Summary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AchievementFragment extends Fragment {

	private ListView cipherListView;
	private static String TAG_Achievement = "com.csgo.iz.achievement";
	private static String TAG_Achievement_COMPARE = "com.csgo.iz.achievement.compare";
	private static String TAG_Achievement_COMPARE_BOOLEAN = "com.csgo.iz.achievement.compare.BOOLEAN";
	private static String TAG_Achievement_SUMMARY_COMPARE = "com.csgo.iz.achievement.compare.COMPARE";
	private TabLayout tabLayout;
	private DisableSwipeViewPager viewPager;
	private AchievementViewPagerAdapter adapter;
	private AchievementViewPagerCompareAdapter compareAdapter;
	private HashMap<Integer, List<Achievement>> hashMap;
	private ArrayList<Summary> listOfSummaries;
	private ArrayList<HashMap<Integer, List<Achievement>>> listOfAchievementsCompare;

	public static AchievementFragment InstanceOf(boolean isCompare, HashMap<Integer, List<Achievement>> hashMap) {
		AchievementFragment fragment = new AchievementFragment();
		Bundle bundle = new Bundle();
		bundle.putSerializable(TAG_Achievement, hashMap);
		bundle.putSerializable(TAG_Achievement_COMPARE, hashMap);
		fragment.setArguments(bundle);
		return fragment;
	}

	public static AchievementFragment InstanceOf(boolean isCompare,
			ArrayList<HashMap<Integer, List<Achievement>>> hashMap, ArrayList<Summary> listOfSummaries) {
		AchievementFragment fragment = new AchievementFragment();
		Bundle bundle = new Bundle();
		bundle.putSerializable(TAG_Achievement_COMPARE, hashMap);
		bundle.putBoolean(TAG_Achievement_COMPARE_BOOLEAN, isCompare);
		bundle.putSerializable(TAG_Achievement_SUMMARY_COMPARE, listOfSummaries);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		Context context = getActivity().getApplicationContext();
		View rootView = inflater.inflate(R.layout.achievement_fragment, container, false);
		tabLayout = (TabLayout) rootView.findViewById(R.id.sliding_tabs);
		viewPager = (DisableSwipeViewPager) rootView.findViewById(R.id.viewpager);
		final boolean isCompare = getArguments().getBoolean(TAG_Achievement_COMPARE_BOOLEAN);
		// viewPager.setOffscreenPageLimit(4);
		if (isCompare) {
			listOfAchievementsCompare = (ArrayList<HashMap<Integer, List<Achievement>>>) getArguments()
					.getSerializable(TAG_Achievement_COMPARE);
			listOfSummaries = (ArrayList<Summary>) getArguments().getSerializable(TAG_Achievement_SUMMARY_COMPARE);
			compareAdapter = new AchievementViewPagerCompareAdapter(getChildFragmentManager(), context,
					listOfAchievementsCompare, listOfSummaries);
			viewPager.setAdapter(compareAdapter);
		} else {
			hashMap = (HashMap<Integer, List<Achievement>>) getArguments().getSerializable(TAG_Achievement);
			adapter = new AchievementViewPagerAdapter(getChildFragmentManager(), hashMap);
			viewPager.setAdapter(adapter);
		}
		tabLayout.post(new Runnable() {
			@Override
			public void run() {
				tabLayout.setupWithViewPager(viewPager);
				generateTabs(isCompare);
			}
		});
		return rootView;
	}

	private void generateTabs(boolean isCompare) {
		for (int i = 0; i < tabLayout.getTabCount(); i++) {
			TabLayout.Tab tab = tabLayout.getTabAt(i);
			if (isCompare) {
				tab.setCustomView(compareAdapter.getTabView(i));
			} else {
				tab.setCustomView(adapter.getTabView(getActivity(), i));
			}
		}
	}

	// private void animationSetup(View rootView) {
	// Log.v("animation", "Reached here");
	// YoYo.with(Techniques.BounceInLeft).duration(ANIMATION_TIME).playOn(rootView.findViewById(R.id.schemalayout));
	// }

}
