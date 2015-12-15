package com.csgo.iz.Adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csgo.iz.Fragments.AchievementItemFragment;
import com.csgo.iz.Fragments.MapItemFragment;
import com.csgo.iz.modal.bean.Achievement;
import com.csgo.iz.modal.bean.Map;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MapViewPagerAdapter extends FragmentPagerAdapter {
	private int number_tabs = 4;
	private Fragment fragment;
	private String[] fragment_titles = {"Bomb Defusal", "Hostage", "Arms Race", "Demolition"};
	private Context context;
	private HashMap<String, List<Map>> map;
	public MapViewPagerAdapter(FragmentManager fm,Context context,HashMap<String, List<Map>> map) {
		super(fm);
		this.context = context;
		this.map = map;
	}
	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		return fragment_titles[position];
	}

	@Override
	public Fragment getItem(int position) {
		String[] KEYS = {"DEFUSAL","ARMS_RACE","HOSTAGE","DEMOLITION"};
		switch(position){
		case 0:
			fragment = MapItemFragment.InstanceOf(getAchievementList(KEYS[0]));
			break;
		case 1:
			fragment = MapItemFragment.InstanceOf(getAchievementList(KEYS[2]));
			break;
		case 2:
			fragment = MapItemFragment.InstanceOf(getAchievementList(KEYS[1]));
			break;
		case 3:
			fragment = MapItemFragment.InstanceOf(getAchievementList(KEYS[3]));
			break;
		}
		return fragment;
	}

	public ArrayList<Map> getAchievementList(String keyPosition) {
		return (ArrayList<Map>) map.get(keyPosition);
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
