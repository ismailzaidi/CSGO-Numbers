package com.csgo.iz.adapters.listadapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.csgo.iz.fragments.WeaponItemFragment;
import com.csgo.iz.modal.bean.Weapon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WeaponViewPagerAdapter extends FragmentPagerAdapter {

    private String[] fragment_titles = {"Pistols", "Rifles", "SMG", "Heavy"};

    private HashMap<String, List<Weapon>> map;

    public WeaponViewPagerAdapter(FragmentManager fm, HashMap<String, List<Weapon>> map) {
        super(fm);
        this.map = map;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragment_titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        String[] KEYS = {"PISTOLS", "RIFLES", "SMG", "HEAVY"};
        return WeaponItemFragment.InstanceOf(getWeaponList(KEYS[position]));
    }

    public ArrayList<Weapon> getWeaponList(String keyPosition) {
        return (ArrayList<Weapon>) map.get(keyPosition);
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
