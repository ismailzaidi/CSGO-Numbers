package com.csgo.iz.adapters.viewpager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csgo.iz.R;
import com.csgo.iz.fragments.AchievementFragment;
import com.csgo.iz.fragments.MapFragment;
import com.csgo.iz.fragments.SummaryFragment;
import com.csgo.iz.fragments.WeaponFragment;
import com.csgo.iz.modal.bean.Achievement;
import com.csgo.iz.modal.bean.Map;
import com.csgo.iz.modal.bean.Summary;
import com.csgo.iz.modal.bean.Weapon;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewPagerAdapter extends FragmentPagerAdapter {
	private int number_tabs = 4;
	private Fragment fragment;
	private Context context;
	private String[] fragment_titles = { "Summary", "Weapon", "Map", "Achievements" };
	private int[] imageResID = { R.drawable.summary_icon, R.drawable.ak_icon, R.drawable.maps_icon,
			R.drawable.medal_icon };
	private HashMap<String,ArrayList<Map>>  listOfMaps;
	private HashMap<Integer, List<Achievement>> listOfAchievements;
	private HashMap<String,ArrayList<Weapon>> listOfWeapons;
	private ArrayList<Summary> summary;
	public ViewPagerAdapter(FragmentManager fm, Context context ,ArrayList<Summary> summary, HashMap<String,ArrayList<Map>>  listOfMaps ,HashMap<Integer, List<Achievement>> listOfAchievements, HashMap<String,ArrayList<Weapon>>listOfWeapons ) {
		super(fm);
		this.context = context;
		this.listOfAchievements=listOfAchievements;
		this.listOfMaps = listOfMaps;
		this.listOfWeapons = listOfWeapons;
		this.summary = summary;
		
	}

	public View getTabView(int position) {
		View view = LayoutInflater.from(context).inflate(R.layout.custom_tab, null);
		ImageView tabIcon = (ImageView) view.findViewById(R.id.icon);
		TextView tabTitle = (TextView) view.findViewById(R.id.title);
		tabTitle.setText(fragment_titles[position]);
		tabIcon.setImageResource(imageResID[position]);
		return view;
	}

	@Override
	public Fragment getItem(int position) {
		Log.v("getItem ViewPager", "Position: " + position);
		
		switch (position) {
		case 0:
			fragment = SummaryFragment.InstanceOf(summary);
			break;
		case 1:
			fragment = WeaponFragment.InstanceOf(false,this.listOfWeapons);
			break;
		case 2:
			fragment = MapFragment.InstanceOf(false,this.listOfMaps);
			break;
		case 3:
			fragment = AchievementFragment.InstanceOf(this.listOfAchievements);
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
