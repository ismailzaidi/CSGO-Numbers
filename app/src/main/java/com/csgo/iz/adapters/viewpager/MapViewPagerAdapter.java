package com.csgo.iz.adapters.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.csgo.iz.fragments.MapItemFragment;
import com.csgo.iz.modal.bean.GameMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapViewPagerAdapter extends FragmentPagerAdapter {
	private String[] fragment_titles = {"Bomb Defusal", "Hostage", "Arms Race", "Demolition"};
	private HashMap<String, List<GameMap>> map;

	public MapViewPagerAdapter(FragmentManager fm, HashMap<String, List<GameMap>> map) {
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

	public ArrayList<GameMap> getAchievementList(String keyPosition) {
		return (ArrayList<GameMap>) map.get(keyPosition);
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
