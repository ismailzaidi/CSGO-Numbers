package com.csgo.iz.compare;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.csgo.iz.Fragments.AchievementFragment;
import com.csgo.iz.Fragments.MapFragment;
import com.csgo.iz.Fragments.WeaponFragment;
import com.csgo.iz.R;
import com.csgo.iz.modal.bean.Achievement;
import com.csgo.iz.modal.bean.Map;
import com.csgo.iz.modal.bean.Summary;
import com.csgo.iz.modal.bean.Weapon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// ok a can see it so ill try :)

public class ViewPagerCompareAdapter extends FragmentPagerAdapter {
	private int number_tabs = 4;
	private Fragment fragment;
	private Context context;
//	private String[] fragment_titles = { "Summary", "Weapon", "Map", "Achievements" };
	private String[] fragment_titles = { "Summary","Weapon","Maps","Achievements" };
	private int[] imageResID = { R.drawable.summary_icon, R.drawable.weapon_icon, R.drawable.maps_icon,
			R.drawable.medal_icon };
	private ArrayList<ArrayList<Map>> listOfMaps;
	private ArrayList<HashMap<Integer, List<Achievement>>> listOfAchievements;
	private ArrayList<ArrayList<Weapon>> listOfWeapons;
	private ArrayList<Summary> listOfSummaries;
	
	public ViewPagerCompareAdapter(FragmentManager fm, Context context ,ArrayList<Summary> listOfSummaries, ArrayList<ArrayList<Map>> listOfMaps ,ArrayList<HashMap<Integer, List<Achievement>>> listOfAchievements, ArrayList<ArrayList<Weapon>>  listOfWeapons ) {
		super(fm);
		this.context = context;
		this.listOfAchievements=listOfAchievements;
		this.listOfSummaries = listOfSummaries;
		this.listOfMaps = listOfMaps;
		this.listOfWeapons = listOfWeapons;
		
		Log.v("com.achievement.fragment", "ViewPagerCompareAdapter() Size Of Object: " + listOfAchievements.size());
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
			fragment = SummaryCompareFragment.InstanceOf(listOfSummaries);
			break;
		case 1:
			fragment = WeaponFragment.InstanceOf(true,listOfWeapons,listOfSummaries);
			break;
		case 2:
			fragment = MapFragment.InstanceOf(true,listOfMaps, listOfSummaries);
			break;
		case 3:
			fragment = AchievementFragment.InstanceOf(true,listOfAchievements,listOfSummaries);
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
