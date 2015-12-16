package com.csgo.iz.fragments;

import java.util.ArrayList;

import com.csgo.iz.R;
import com.csgo.iz.adapters.viewpager.DisableSwipeViewPager;
import com.csgo.iz.adapters.viewpager.ViewPagerAdapter;
import com.csgo.iz.compare.ViewPagerCompareAdapter;
import com.csgo.iz.modal.Model;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class TempFragment extends Fragment {

	private ListView cipherListView;
	private static String TAG_MAIN_BOOLEAN = "com.csgo.iz.mainfragment.iscompare";
	private static String TAG_MAIN_STEAMID = "com.csgo.iz.mainfragment.steamid";
	private static String TAG_MAIN_STEAMID_COMPARE = "com.csgo.iz.mainfragment.steamid.compare";
	private TabLayout tabLayout;
	private DisableSwipeViewPager pager;
	private ViewPagerAdapter adapterMain;
	private ViewPagerCompareAdapter adapterCompare;
	private Model model;
	private Context context;
	private ArrayList<String> listOfFriends;

	public static TempFragment InstanceOf(boolean isCompare, String steamID) {
		TempFragment fragment = new TempFragment();
		Bundle bundle = new Bundle();
		bundle.putBoolean(TAG_MAIN_BOOLEAN, isCompare);
		bundle.putString(TAG_MAIN_STEAMID, steamID);
		fragment.setArguments(bundle);
		return fragment;
	}

	public static TempFragment InstanceOf(boolean isCompare, ArrayList<String> listOfFriends) {
		TempFragment fragment = new TempFragment();
		Bundle bundle = new Bundle();
		bundle.putBoolean(TAG_MAIN_BOOLEAN, isCompare);
		bundle.putSerializable(TAG_MAIN_STEAMID_COMPARE, listOfFriends);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		context = getActivity().getApplicationContext();
		View rootView = inflater.inflate(R.layout.main_fragment, container, false);
		pager = (DisableSwipeViewPager) rootView.findViewById(R.id.viewpager);
		tabLayout = (TabLayout) rootView.findViewById(R.id.sliding_tabs);
		pager.setPagingEnabled(true);

		return rootView;
	}

}
