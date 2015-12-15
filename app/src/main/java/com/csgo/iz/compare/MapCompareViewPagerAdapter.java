package com.csgo.iz.compare;

import java.util.ArrayList;

import com.csgo.iz.modal.bean.Map;
import com.csgo.iz.modal.bean.Summary;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MapCompareViewPagerAdapter extends FragmentPagerAdapter {
	private int number_tabs = 4;
	private Fragment fragment;
	private String[] fragment_titles = {"Bomb Defusal", "Hostage", "Arms Race", "Demolition"};
	private Context context;
	private ArrayList<ArrayList<Map>> map;
	private ArrayList<Summary> listOfSummeries;
	public MapCompareViewPagerAdapter(FragmentManager fm,Context context,ArrayList<ArrayList<Map>> map , ArrayList<Summary> listOfSummeries) {
		super(fm);
		this.context = context;
		this.map = map;
		this.listOfSummeries = listOfSummeries;
	}
	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		return fragment_titles[position];
	}

	@Override
	public Fragment getItem(int position) {
		String[] KEYS = {"DEFUSAL","HOSTAGE","ARMS_RACE","DEMOLITION"};
		switch(position){
		case 0:
			fragment = MapItemCompareFragment.InstanceOf(KEYS[position],map,listOfSummeries);
			break;
		case 1:
			fragment = MapItemCompareFragment.InstanceOf(KEYS[position],map,listOfSummeries);
			break;
		case 2:
			fragment = MapItemCompareFragment.InstanceOf(KEYS[position],map,listOfSummeries);
			break;
		case 3:
			fragment = MapItemCompareFragment.InstanceOf(KEYS[position],map,listOfSummeries);
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
