package com.csgo.iz.compare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csgo.iz.R;
import com.csgo.iz.Fragments.AchievementItemFragment;
import com.csgo.iz.modal.bean.Achievement;
import com.csgo.iz.modal.bean.Summary;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class AchievementViewPagerCompareAdapter extends FragmentPagerAdapter {
	private int number_tabs = 5;
	private Fragment fragment;
	private String[] fragment_titles = { "Team Tactics", "Combat Skills", "Weapon Specialist", "Global Expert",
			"Arsenal Mode" };
	private Context context;
	private ArrayList<Summary> listOfSummaries;
	private ArrayList<HashMap<Integer, List<Achievement>>> map;

	public AchievementViewPagerCompareAdapter(FragmentManager fm, Context context,
			ArrayList<HashMap<Integer, List<Achievement>>> map, ArrayList<Summary> listOfSummaries) {
		super(fm);
		this.context = context;
		this.map = map;
		this.listOfSummaries = listOfSummaries;
		Log.v("com.achievement.fragment", "AchievementViewPagerAdapter () Size Of Object: " + map.size());
	}

	public View getTabView(int position) {
		View view = LayoutInflater.from(context).inflate(R.layout.custom_tab_compare_achievement, null);
		TextView tabName = (TextView) view.findViewById(R.id.achievement_title);
		tabName.setText(fragment_titles[position]);
		return view;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		return fragment_titles[position];
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
		case 0:
			fragment = AchievementItemFragment.InstanceOfCompare(0, map, listOfSummaries);
			break;
		case 1:
			fragment = AchievementItemFragment.InstanceOfCompare(1, map, listOfSummaries);
			break;
		case 2:
			fragment = AchievementItemFragment.InstanceOfCompare(2, map, listOfSummaries);
			break;
		case 3:
			fragment = AchievementItemFragment.InstanceOfCompare(3, map, listOfSummaries);
			break;
		case 4:
			fragment = AchievementItemFragment.InstanceOfCompare(4, map, listOfSummaries);
			break;
		}
		return fragment;
	}

	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return POSITION_NONE;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return number_tabs;
	}

}
