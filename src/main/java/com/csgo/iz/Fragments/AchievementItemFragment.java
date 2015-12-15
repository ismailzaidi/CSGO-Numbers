package com.csgo.iz.Fragments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.csgo.iz.R;
import com.csgo.iz.Adapters.CustomAchievementAdapter;
import com.csgo.iz.compare.CustomAchievementCompareAdapter;
import com.csgo.iz.modal.Model;
import com.csgo.iz.modal.bean.Achievement;
import com.csgo.iz.modal.bean.Summary;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class AchievementItemFragment extends Fragment {

	private ListView achievementListView;
	private static String TAG_CSGO_NUMBERS = "com.csgo.iz.Fragment";
	private static String TAG_CSGO_NUMBERS_COMPARE = "com.csgo.iz.Fragment.compare";
	private static String TAG_CSGO_NUMBERS_COMPARE_POSITION = "com.csgo.iz.Fragment.compare.position";
	private static String TAG_CSGO_NUMBERS_COMPARE_Summary = "com.csgo.iz.Fragment.compare.summary";
	private CustomAchievementAdapter adapter;
	private CustomAchievementCompareAdapter adapterCompare;
	private Model model;
	private Context context;
	private ArrayList<HashMap<Integer, List<Achievement>>> listOfAchievementsCompare;
	private ArrayList<Achievement> listofAchievements;
	private ArrayList<Summary> listOfSummaries;
	public static AchievementItemFragment InstanceOf(ArrayList<Achievement> list) {
		AchievementItemFragment fragment = new AchievementItemFragment();
		Bundle bundle = new Bundle();
		Collections.sort(list);
		bundle.putSerializable(TAG_CSGO_NUMBERS, list);
		
		fragment.setArguments(bundle);
		return fragment;
	}
	public static AchievementItemFragment InstanceOfCompare(int achievementPosition, ArrayList<HashMap<Integer, List<Achievement>>> list,ArrayList<Summary> listOfSummaries) {
		AchievementItemFragment fragment = new AchievementItemFragment();
		Bundle bundle = new Bundle();
		bundle.putSerializable(TAG_CSGO_NUMBERS_COMPARE, list);
		bundle.putSerializable(TAG_CSGO_NUMBERS_COMPARE_Summary, listOfSummaries);
		bundle.putInt(TAG_CSGO_NUMBERS_COMPARE_POSITION, achievementPosition);
		
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
		View rootView = inflater.inflate(R.layout.achievement_item_fragment, container, false);
		achievementListView = (ListView) rootView.findViewById(R.id.listofAchievementsListView);
		listofAchievements = (ArrayList<Achievement>) getArguments().getSerializable(TAG_CSGO_NUMBERS);
		if(listofAchievements==null){
			listOfAchievementsCompare = (ArrayList<HashMap<Integer, List<Achievement>>>) getArguments().getSerializable(TAG_CSGO_NUMBERS_COMPARE);
			listOfSummaries = (ArrayList<Summary>) getArguments().getSerializable(TAG_CSGO_NUMBERS_COMPARE_Summary);
			int achievementPosition = getArguments().getInt(TAG_CSGO_NUMBERS_COMPARE_POSITION);
			setupListViewCompare(listOfAchievementsCompare,achievementPosition, listOfSummaries);

		}else{
			setupListView(listofAchievements);
		}
		return rootView;
	}
	private void setupListView(ArrayList<Achievement> list){
		adapter = new CustomAchievementAdapter(getActivity(),list);
		adapter.notifyDataSetChanged();
		achievementListView.setAdapter(adapter);
	}
	private void setupListViewCompare(ArrayList<HashMap<Integer, List<Achievement>>> list, int achievementPosition, ArrayList<Summary> listOfSummaries){
		List<Achievement> achievementList = list.get(0).get(achievementPosition);
		adapterCompare = new CustomAchievementCompareAdapter(getActivity(),list,achievementPosition,achievementList,listOfSummaries);
		adapterCompare.notifyDataSetChanged();
		achievementListView.setAdapter(adapterCompare);
	}


	// private void animationSetup(View rootView) {
	// Log.v("animation", "Reached here");
	// YoYo.with(Techniques.BounceInLeft).duration(ANIMATION_TIME).playOn(rootView.findViewById(R.id.schemalayout));
	// }

}
