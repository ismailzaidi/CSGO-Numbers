package com.csgo.iz.compare;

import java.util.ArrayList;

import com.csgo.iz.modal.bean.Summary;
import com.csgo.iz.modal.bean.Weapon;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class WeaponCompareViewPagerAdapter extends FragmentPagerAdapter {
	private int number_tabs = 4;
	private Fragment fragment;
	private String[] fragment_titles = {"Pistols", "Rifles", "SMG", "Heavy"};
	private Context context;
	private ArrayList<ArrayList<Weapon>> weaponList;
	private ArrayList<Summary> listOfSummary;
	public WeaponCompareViewPagerAdapter(FragmentManager fm,Context context,ArrayList<ArrayList<Weapon>> weaponList,ArrayList<Summary> listOfSummary) {
		super(fm);
		this.context = context;
		this.weaponList = weaponList;
		this.listOfSummary=listOfSummary;
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
			fragment = WeaponItemCompareFragment.InstanceOf(KEYS[position],weaponList,listOfSummary);
			break;
		case 1:
			fragment = WeaponItemCompareFragment.InstanceOf(KEYS[position],weaponList,listOfSummary);
			break;
		case 2:
			fragment = WeaponItemCompareFragment.InstanceOf(KEYS[position],weaponList,listOfSummary);
			break;
		case 3:
			fragment = WeaponItemCompareFragment.InstanceOf(KEYS[position],weaponList,listOfSummary);
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
