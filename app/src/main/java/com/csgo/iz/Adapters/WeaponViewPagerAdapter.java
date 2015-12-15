package com.csgo.iz.Adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csgo.iz.Fragments.WeaponItemFragment;
import com.csgo.iz.modal.bean.Weapon;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class WeaponViewPagerAdapter extends FragmentPagerAdapter {
	private int number_tabs = 4;
	private Fragment fragment;
	private String[] fragment_titles = {"Pistols", "Rifles", "SMG", "Heavy"};
	private Context context;
	private HashMap<String, List<Weapon>> map;
	public WeaponViewPagerAdapter(FragmentManager fm,Context context,HashMap<String, List<Weapon>> map) {
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
		String[] KEYS = {"PISTOLS","RIFLES","SMG","HEAVY"};
		switch(position){
		case 0:
			Log.v("Weapon Tab Stat", "WeaponViewPager getItem(0): " + getWeaponList(KEYS[position]).size());
			fragment = WeaponItemFragment.InstanceOf(getWeaponList(KEYS[position]));
			break;
		case 1:
			fragment = WeaponItemFragment.InstanceOf(getWeaponList(KEYS[position]));
			break;
		case 2:
			fragment = WeaponItemFragment.InstanceOf(getWeaponList(KEYS[position]));
			break;
		case 3:
			fragment = WeaponItemFragment.InstanceOf(getWeaponList(KEYS[position]));
			break;
		}
		return fragment;
	}

	public ArrayList<Weapon> getWeaponList(String keyPosition) {
		return (ArrayList<Weapon>) map.get(keyPosition);
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
