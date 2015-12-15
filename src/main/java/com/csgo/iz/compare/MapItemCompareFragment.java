package com.csgo.iz.compare;

import java.util.ArrayList;

import com.csgo.iz.R;
import com.csgo.iz.modal.Utility;
import com.csgo.iz.modal.bean.Map;
import com.csgo.iz.modal.bean.Summary;
import com.csgo.iz.modal.bean.Weapon;
import com.csgo.iz.views.customviews.CustomMapView;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MapItemCompareFragment extends Fragment {

	private static String TAG_CSGO_NUMBER_KEY = "com.csgo.iz.map.KEY";
	private static String TAG_CSGO_NUMBER_COMPARE = "com.csgo.iz.map.COMPARE";
	private static String TAG_CSGO_NUMBER_SUMMARY = "com.csgo.iz.map.SUMMARY";
	private String ERROR_TAG = "com.csgo.iz.error.mapcompare";
	private Context context;
	private ArrayList<ArrayList<Map>> listOfMapCompare;
	private static int ID = 60000;
	private ArrayList<Summary> listOfSummeries;
	private String key;
	private LinearLayout tableLayout;
	private ArrayList<Weapon> listWeapon;
	private Utility utils;

	public static MapItemCompareFragment InstanceOf(String weaponkey, ArrayList<ArrayList<Map>> list,
			ArrayList<Summary> summary) {
		MapItemCompareFragment fragment = new MapItemCompareFragment();
		Bundle bundle = new Bundle();
		bundle.putString(TAG_CSGO_NUMBER_KEY, weaponkey);
		bundle.putSerializable(TAG_CSGO_NUMBER_COMPARE, list);
		bundle.putSerializable(TAG_CSGO_NUMBER_SUMMARY, summary);
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
		View rootView = inflater.inflate(R.layout.map_compare_fragment, container, false);
		tableLayout = (LinearLayout) rootView.findViewById(R.id.tableLayout);
		key = getArguments().getString(TAG_CSGO_NUMBER_KEY);
		listOfMapCompare = (ArrayList<ArrayList<Map>>) getArguments().getSerializable(TAG_CSGO_NUMBER_COMPARE);
		listOfSummeries = (ArrayList<Summary>) getArguments().getSerializable(TAG_CSGO_NUMBER_SUMMARY);
		Log.v(ERROR_TAG, "Size of Map list " + listOfMapCompare.size());
		Log.v(ERROR_TAG, "Size of User list " + listOfSummeries.size());
		utils = new Utility(context);
		ArrayList<Map> mapMainList = getUserMapList(key, 0);
		for (Map mapItem : mapMainList) {
			CustomMapView childView = (CustomMapView) LayoutInflater.from(context)
					.inflate(R.layout.custom_compare_map_elfessho, null);
			childView.setId(ID);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
			childView.setLayoutParams(params);
			childView.setMapImage(mapItem.getMapID());
			childView.setMapName(mapItem.getMapName());
			for (int i = 0; i < listOfSummeries.size(); i++) {
				ArrayList<Map> mapList = getUserMapList(key, i);
				generateWeaponView(childView,listOfSummeries, mapList);
			}
			tableLayout.addView(childView);
		}

		return rootView;
	}

	private ArrayList<Map> getUserMapList(String type, int userPosition) {
		ArrayList<Map> weaponList = new ArrayList<Map>();
		Log.v(ERROR_TAG, "getUserWeaponList " + listOfMapCompare.get(userPosition).size());
		for (Map item : listOfMapCompare.get(userPosition)) {
			if (item.getMapType().equals(type)) {
				weaponList.add(item);
			}
		}
		return weaponList;
	}

	public void generateWeaponView(CustomMapView childView,ArrayList<Summary> user, ArrayList<Map> userWeaponInfo) {
		int size = user.size();
		switch (size) {
		case 1:
			updateOne(childView, user, userWeaponInfo);
			break;
		case 2:
			updateTwo(childView, user, userWeaponInfo);
			break;

		case 3:
			updateThree(childView, user, userWeaponInfo);
			break;

		case 4:
			updateFour(childView, user, userWeaponInfo);
			break;

		}
	}

	private void updateOne(CustomMapView childView, ArrayList<Summary> userList, ArrayList<Map> map) {
		int index = 0;
		childView.setUserOne(userList.get(index).getUserProfile().getUserName());
		childView.setRoundsPlayedOne(utils.getFormatSorter(map.get(index).getRoundPlayed()));
		childView.setRoundsWonOne(utils.getFormatSorter(map.get(index).getRoundsWon()));
		childView.setAccuracyOne(utils.getFormatSorter(map.get(index).getMapAccuracy()));
	}

	private void updateTwo(CustomMapView childView, ArrayList<Summary> userList, ArrayList<Map> map) {
		int index = 0;
		childView.setUserOne(userList.get(index).getUserProfile().getUserName());
		childView.setUserTwo(userList.get(index + 1).getUserProfile().getUserName());
		childView.setRoundsPlayedOne(utils.getFormatSorter(map.get(index).getRoundPlayed()));
		childView.setRoundsPlayedTwo(utils.getFormatSorter(map.get(index + 1).getRoundPlayed()));
		childView.setRoundsWonOne(utils.getFormatSorter(map.get(index).getRoundsWon()));
		childView.setRoundsWonTwo(utils.getFormatSorter(map.get(index + 1).getRoundsWon()));
		childView.setAccuracyOne(utils.getFormatSorter(map.get(index).getMapAccuracy()));
		childView.setAccuracyTwo(utils.getFormatSorter(map.get(index + 1).getMapAccuracy()));
	}

	private void updateThree(CustomMapView childView, ArrayList<Summary> userList, ArrayList<Map> weapon) {
		int index = 0;
		childView.setUserOne(userList.get(index).getUserProfile().getUserName());
		childView.setUserTwo(userList.get(index + 1).getUserProfile().getUserName());
		childView.setUserThree(userList.get(index + 2).getUserProfile().getUserName());
		childView.setRoundsPlayedOne(utils.getFormatSorter(weapon.get(index).getRoundPlayed()));
		childView.setRoundsPlayedTwo(utils.getFormatSorter(weapon.get(index + 1).getRoundPlayed()));
		childView.setRoundsPlayedThree(utils.getFormatSorter(weapon.get(index + 2).getRoundPlayed()));
		childView.setRoundsWonOne(utils.getFormatSorter(weapon.get(index).getRoundsWon()));
		childView.setRoundsWonTwo(utils.getFormatSorter(weapon.get(index + 1).getRoundsWon()));
		childView.setRoundsWonThree(utils.getFormatSorter(weapon.get(index + 2).getRoundsWon()));
		childView.setAccuracyOne(utils.getFormatSorter(weapon.get(index).getMapAccuracy()));
		childView.setAccuracyTwo(utils.getFormatSorter(weapon.get(index + 1).getMapAccuracy()));
		childView.setAccuracyThree(utils.getFormatSorter(weapon.get(index + 2).getMapAccuracy()));
	}

	private void updateFour(CustomMapView childView, ArrayList<Summary> userList, ArrayList<Map> weapon) {
		int index = 0;
		childView.setUserOne(userList.get(index).getUserProfile().getUserName());
		childView.setUserTwo(userList.get(index + 1).getUserProfile().getUserName());
		childView.setUserThree(userList.get(index + 2).getUserProfile().getUserName());
		childView.setUserFour(userList.get(index + 3).getUserProfile().getUserName());
		childView.setRoundsPlayedOne(utils.getFormatSorter(weapon.get(index).getRoundPlayed()));
		childView.setRoundsPlayedTwo(utils.getFormatSorter(weapon.get(index + 1).getRoundPlayed()));
		childView.setRoundsPlayedThree(utils.getFormatSorter(weapon.get(index + 2).getRoundPlayed()));
		childView.setRoundsPlayedFour(utils.getFormatSorter(weapon.get(index + 3).getRoundPlayed()));
		childView.setRoundsWonOne(utils.getFormatSorter(weapon.get(index).getRoundsWon()));
		childView.setRoundsWonTwo(utils.getFormatSorter(weapon.get(index + 1).getRoundsWon()));
		childView.setRoundsWonThree(utils.getFormatSorter(weapon.get(index + 2).getRoundsWon()));
		childView.setRoundsWonFour(utils.getFormatSorter(weapon.get(index + 3).getRoundsWon()));
		childView.setAccuracyOne(utils.getFormatSorter(weapon.get(index).getMapAccuracy()));
		childView.setAccuracyTwo(utils.getFormatSorter(weapon.get(index + 1).getMapAccuracy()));
		childView.setAccuracyThree(utils.getFormatSorter(weapon.get(index + 2).getMapAccuracy()));
		childView.setAccuracyFour(utils.getFormatSorter(weapon.get(index + 3).getMapAccuracy()));
	}

}
