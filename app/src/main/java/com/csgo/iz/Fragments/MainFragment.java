package com.csgo.iz.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.csgo.iz.adapters.viewpager.DisableSwipeViewPager;
import com.csgo.iz.adapters.viewpager.ViewPagerAdapter;
import com.csgo.iz.R;
import com.csgo.iz.compare.ViewPagerCompareAdapter;
import com.csgo.iz.modal.MapModel;
import com.csgo.iz.modal.Model;
import com.csgo.iz.modal.SummaryModel;
import com.csgo.iz.modal.WeaponModel;
import com.csgo.iz.modal.bean.Achievement;
import com.csgo.iz.modal.bean.Map;
import com.csgo.iz.modal.bean.Profile;
import com.csgo.iz.modal.bean.Summary;
import com.csgo.iz.modal.bean.Weapon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class MainFragment extends Fragment {

	private ListView cipherListView;
	private static String TAG_MAIN_BOOLEAN = "com.csgo.iz.mainfragment.iscompare";
	private static String TAG_MAIN_STEAMID = "com.csgo.iz.mainfragment.steamid";
	private static String TAG_MAIN_STEAMID_COMPARE = "com.csgo.iz.mainfragment.steamid.compare";

	/**
	 * KEYS FOR COLLECTIONS
	 * 
	 * 
	 */
	private static String TAG_MAIN_HASHTABLE = "com.csgo.iz.mainfragment.HASHTABLE";
	private static String TAG_MAIN_ACHIVEMENTS = "com.csgo.iz.mainfragment.ACHIEVEMENTS";
	private static String TAG_MAIN_PROFILE = "com.csgo.iz.mainfragment.PROFILE";
	private TabLayout tabLayout;
	private DisableSwipeViewPager pager;
	private ViewPagerAdapter adapterMain;
	private ViewPagerCompareAdapter adapterCompare;
	private Model model;
	private Context context;
	private ArrayList<String> listOfFriends;
	private ArrayList<Summary> summary;
	private HashMap<String, ArrayList<Weapon>> listOfWeapons;
	private HashMap<String, ArrayList<Map>> listOfMaps;
	private HashMap<Integer, List<Achievement>> listOfAchievements;
	private Hashtable<String, Integer> stats;
	private Profile profile;

	public static MainFragment InstanceOf(boolean isCompare, String steamID, Hashtable<String, Integer> stats,
			HashMap<Integer, List<Achievement>> listOfAchievements, Profile profile) {
		MainFragment fragment = new MainFragment();
		Bundle bundle = new Bundle();
		bundle.putBoolean(TAG_MAIN_BOOLEAN, isCompare);
		bundle.putSerializable(TAG_MAIN_HASHTABLE, stats);
		bundle.putSerializable(TAG_MAIN_ACHIVEMENTS, listOfAchievements);
		bundle.putSerializable(TAG_MAIN_PROFILE, profile);
		fragment.setArguments(bundle);
		return fragment;
	}
	public static MainFragment InstanceOf(boolean isCompare, ArrayList<String> listOfFriends) {
		MainFragment fragment = new MainFragment();
		Bundle bundle = new Bundle();
		bundle.putBoolean(TAG_MAIN_BOOLEAN, isCompare);
		bundle.putSerializable(TAG_MAIN_STEAMID_COMPARE, listOfFriends);
		fragment.setArguments(bundle);
		return fragment;
	}


    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		context = getActivity();
		String userID = getArguments().getString(TAG_MAIN_STEAMID);

		View rootView = inflater.inflate(R.layout.main_fragment, container, false);
		pager = (DisableSwipeViewPager) rootView.findViewById(R.id.viewpager);
		tabLayout = (TabLayout) rootView.findViewById(R.id.sliding_tabs);
		pager.setPagingEnabled(true);
		final boolean isCompare = getArguments().getBoolean(TAG_MAIN_BOOLEAN);
		String steamID = getArguments().getString(TAG_MAIN_STEAMID);
		listOfFriends = (ArrayList<String>) getArguments().getSerializable(TAG_MAIN_STEAMID_COMPARE);
		profile = (Profile) getArguments().getSerializable(TAG_MAIN_PROFILE);
		stats = (Hashtable<String, Integer>) getArguments().getSerializable(TAG_MAIN_HASHTABLE);
		listOfAchievements = (HashMap<Integer, List<Achievement>>) getArguments().getSerializable(TAG_MAIN_ACHIVEMENTS);
		if (isCompare) {
			model = Model.getInstance();
			allocateCompareAdapter();
		} else {
			genearateLists();
			allocateAdapter();
		}
		tabLayout.post(new Runnable() {
			@Override
			public void run() {
				tabLayout.setupWithViewPager(pager);
				generateTabs(isCompare);
			}
		});
		return rootView;
	}

	private void genearateLists() {
		summary = new SummaryModel(context, stats,profile).getListOfSummary();
		listOfWeapons = new WeaponModel(context, stats).getWeaponHash();
		listOfMaps = new MapModel(context, stats).getListOfMaps();
	}

	private void allocateAdapter() {
		adapterMain = new ViewPagerAdapter(this.getChildFragmentManager(), this.getActivity().getApplicationContext(),
				summary, listOfMaps, listOfAchievements, listOfWeapons);
		pager.setAdapter(adapterMain);
		pager.setPagingEnabled(true);
		pager.setOffscreenPageLimit(3);
	}

	private void allocateCompareAdapter() {
		// Log.v("COMPARE_FRIENDS_TAG", "Model: " + model + " List Of Friend
		// Size :)") ;
		// ArrayList<ArrayList<Weapon>> listOfWeapons =
		// model.getCompareWeaponList();
		// ArrayList<Summary> listOfSummaries = model.getSummaries();
		// ArrayList<ArrayList<Map>> listOfMaps = model.getCompareMapList();
		// ArrayList<HashMap<Integer, List<Achievement>>> listOfAchievements =
		// model.getAchievementCompare();
		// FragmentManager fm = this.getChildFragmentManager();
		// adapterCompare = new ViewPagerCompareAdapter(fm,
		// context,listOfSummaries, listOfMaps, listOfAchievements,
		// listOfWeapons);
		// pager.setAdapter(adapterCompare);
		// pager.setPagingEnabled(true);
	}
	private void generateTabs(boolean isCompare) {
		for (int i = 0; i < tabLayout.getTabCount(); i++) {
			TabLayout.Tab tab = tabLayout.getTabAt(i);
			if (isCompare) {
				tab.setCustomView(adapterCompare.getTabView(i));
			} else {
				tab.setCustomView(adapterMain.getTabView(i));

			}
		}
	}

}
