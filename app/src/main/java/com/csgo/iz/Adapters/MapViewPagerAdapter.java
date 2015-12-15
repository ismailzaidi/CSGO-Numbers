package com.csgo.iz.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.csgo.iz.Fragments.MapItemFragment;
import com.csgo.iz.modal.bean.Map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapViewPagerAdapter extends FragmentPagerAdapter {
	private String[] fragment_titles = {"Bomb Defusal", "Hostage", "Arms Race", "Demolition"};
	private HashMap<String, List<Map>> map;

	public MapViewPagerAdapter(FragmentManager fm, HashMap<String, List<Map>> map) {
		super(fm);
		this.map = map;
	}
	@Override
	public CharSequence getPageTitle(int position) {
		return fragment_titles[position];
	}

	@Override
	public Fragment getItem(int position) {
		String[] KEYS = {"DEFUSAL","ARMS_RACE","HOSTAGE","DEMOLITION"};
		return MapItemFragment.InstanceOf(getAchievementList(KEYS[position]));
	}

	public ArrayList<Map> getAchievementList(String keyPosition) {
		return (ArrayList<Map>) map.get(keyPosition);
	}
	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	@Override
	public int getCount() {
		return 4;
	}

}
