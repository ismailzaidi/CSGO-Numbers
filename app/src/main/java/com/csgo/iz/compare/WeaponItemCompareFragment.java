package com.csgo.iz.compare;

import java.util.ArrayList;

import com.csgo.iz.R;
import com.csgo.iz.modal.Utility;
import com.csgo.iz.modal.bean.Summary;
import com.csgo.iz.modal.bean.Weapon;
import com.csgo.iz.views.customviews.CustomWeaponView;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class WeaponItemCompareFragment extends Fragment {

	private static String TAG_CSGO_NUMBER_KEY = "com.csgo.iz.weapon.KEY";
	private static String TAG_CSGO_NUMBER_COMPARE = "com.csgo.iz.weapon.COMPARE";
	private static String TAG_CSGO_NUMBER_SUMMARY = "com.csgo.iz.weapon.SUMMARY";
	private String ERROR_TAG = "com.csgo.iz.error.weaponcompare";
	private Context context;
	private ArrayList<ArrayList<Weapon>> listOfWeaponCompare;
	private static int ID = 60000;
	private ArrayList<Summary> listOfSummeries;
	private String key;
	private LinearLayout tableLayout;
	private ArrayList<Weapon> listWeapon;
	private Utility utils;

	public static WeaponItemCompareFragment InstanceOf(String weaponkey, ArrayList<ArrayList<Weapon>> list,
			ArrayList<Summary> summary) {
		WeaponItemCompareFragment fragment = new WeaponItemCompareFragment();
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
		View rootView = inflater.inflate(R.layout.weapon_compare_fragment, container, false);
		tableLayout = (LinearLayout) rootView.findViewById(R.id.tableLayout);
		key = getArguments().getString(TAG_CSGO_NUMBER_KEY);
		utils = new Utility();
		listOfWeaponCompare = (ArrayList<ArrayList<Weapon>>) getArguments().getSerializable(TAG_CSGO_NUMBER_COMPARE);
		listOfSummeries = (ArrayList<Summary>) getArguments().getSerializable(TAG_CSGO_NUMBER_SUMMARY);
		Log.v(ERROR_TAG, "Size of Weapon list " + listOfWeaponCompare.size());

		ArrayList<Weapon> weaponMainList = getUserWeaponList(key, 0);
		for (Weapon weaponItem : weaponMainList) {
			CustomWeaponView childView = (CustomWeaponView) LayoutInflater.from(context)
					.inflate(R.layout.custom_compare_weapon_elfessho, null);
			childView.setId(ID);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
			childView.setLayoutParams(params);
			childView.setWeaponImage(weaponItem.getWeaponID());
			childView.setWeaponName(weaponItem.getWeaponName());
			for (int i = 0; i < listOfSummeries.size(); i++) {
				ArrayList<Weapon> weaponList = getUserWeaponList(key, i);
				generateWeaponView(childView,listOfSummeries, weaponItem, weaponList);
			}
			tableLayout.addView(childView);
		}
		
		Utility.setFontForView((ViewGroup )rootView);

		return rootView;
	}

	private ArrayList<Weapon> getUserWeaponList(String type, int userPosition) {
		ArrayList<Weapon> weaponList = new ArrayList<Weapon>();
		Log.v(ERROR_TAG, "getUserWeaponList " + listOfWeaponCompare.get(userPosition).size());
		for (Weapon item : listOfWeaponCompare.get(userPosition)) {
			Log.v(ERROR_TAG, "Weapon Type(Temp) " + type + " Weapon Remote: " + item.getWeaponType()
					+ " Are they Equal " + (type.equals(item.getWeaponType())));
			if (item.getWeaponType().equals(type)) {
				weaponList.add(item);
			}
		}
		return weaponList;
	}

	public void generateWeaponView(CustomWeaponView childView,ArrayList<Summary> user, Weapon weapon, ArrayList<Weapon> userWeaponInfo) {
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

	private void updateOne(CustomWeaponView childView, ArrayList<Summary> userList, ArrayList<Weapon> weapon) {
		int index = 0;
		childView.setUserOne(userList.get(index).getUserProfile().getUserName());
		childView.setShotOne(utils.getFormatSorter(weapon.get(index).getWeaponShot()));
		childView.setHitOne(utils.getFormatSorter(weapon.get(index).getWeaponHit()));
		childView.setAccuracyOne(utils.getFormatSorter(weapon.get(index).getWeaponAccuracy()));
		childView.setMissedHitOne(utils.getFormatSorter(weapon.get(index).getMissedShots()));
		childView.setKillOne(utils.getFormatSorter(weapon.get(index).getWeaponKills()));
	}

	private void updateTwo(CustomWeaponView childView, ArrayList<Summary> userList, ArrayList<Weapon> weapon) {
		int index = 0;
		childView.setUserOne(userList.get(index).getUserProfile().getUserName());
		childView.setUserTwo(userList.get(index + 1).getUserProfile().getUserName());
		childView.setShotOne(utils.getFormatSorter(weapon.get(index).getWeaponShot()));
		childView.setShotTwo(utils.getFormatSorter(weapon.get(index + 1).getWeaponShot()));
		childView.setHitOne(utils.getFormatSorter(weapon.get(index).getWeaponHit()));
		childView.setHitTwo(utils.getFormatSorter(weapon.get(index + 1).getWeaponHit()));
		childView.setAccuracyOne(utils.getFormatSorter(weapon.get(index).getWeaponAccuracy()));
		childView.setAccuracyTwo(utils.getFormatSorter(weapon.get(index + 1).getWeaponAccuracy()));
		childView.setMissedHitOne(utils.getFormatSorter(weapon.get(index).getMissedShots()));
		childView.setMissedHitTwo(utils.getFormatSorter(weapon.get(index + 1).getMissedShots()));
		childView.setKillOne(utils.getFormatSorter(weapon.get(index).getWeaponKills()));
		childView.setKillTwo(utils.getFormatSorter(weapon.get(index + 1).getWeaponKills()));
	}

	private void updateThree(CustomWeaponView childView, ArrayList<Summary> userList, ArrayList<Weapon> weapon) {
		int index = 0;
		childView.setUserOne(userList.get(index).getUserProfile().getUserName());
		childView.setUserTwo(userList.get(index + 1).getUserProfile().getUserName());
		childView.setUserThree(userList.get(index + 2).getUserProfile().getUserName());
		childView.setShotOne(utils.getFormatSorter(weapon.get(index).getWeaponShot()));
		childView.setShotTwo(utils.getFormatSorter(weapon.get(index + 1).getWeaponShot()));
		childView.setShotThree(utils.getFormatSorter(weapon.get(index + 2).getWeaponShot()));
		childView.setHitOne(utils.getFormatSorter(weapon.get(index).getWeaponHit()));
		childView.setHitTwo(utils.getFormatSorter(weapon.get(index + 1).getWeaponHit()));
		childView.setHitThree(utils.getFormatSorter(weapon.get(index + 2).getWeaponHit()));
		childView.setAccuracyOne(utils.getFormatSorter(weapon.get(index).getWeaponAccuracy()));
		childView.setAccuracyTwo(utils.getFormatSorter(weapon.get(index + 1).getWeaponAccuracy()));
		childView.setAccuracyThree(utils.getFormatSorter(weapon.get(index + 2).getWeaponAccuracy()));
		childView.setMissedHitOne(utils.getFormatSorter(weapon.get(index).getMissedShots()));
		childView.setMissedHitTwo(utils.getFormatSorter(weapon.get(index + 1).getMissedShots()));
		childView.setMissedHitThree(utils.getFormatSorter(weapon.get(index + 2).getMissedShots()));
		childView.setKillOne(utils.getFormatSorter(weapon.get(index).getWeaponKills()));
		childView.setKillTwo(utils.getFormatSorter(weapon.get(index + 1).getWeaponKills()));
		childView.setKillThree(utils.getFormatSorter(weapon.get(index + 2).getWeaponKills()));
	}

	private void updateFour(CustomWeaponView childView, ArrayList<Summary> userList, ArrayList<Weapon> weapon) {
		int index = 0;
		childView.setUserOne(userList.get(index).getUserProfile().getUserName());
		childView.setUserTwo(userList.get(index + 1).getUserProfile().getUserName());
		childView.setUserThree(userList.get(index + 2).getUserProfile().getUserName());
		childView.setUserFour(userList.get(index + 3).getUserProfile().getUserName());
		childView.setShotOne(utils.getFormatSorter(weapon.get(index).getWeaponShot()));
		childView.setShotTwo(utils.getFormatSorter(weapon.get(index + 1).getWeaponShot()));
		childView.setShotThree(utils.getFormatSorter(weapon.get(index + 2).getWeaponShot()));
		childView.setShotFour(utils.getFormatSorter(weapon.get(index + 3).getWeaponShot()));
		childView.setHitOne(utils.getFormatSorter(weapon.get(index).getWeaponHit()));
		childView.setHitTwo(utils.getFormatSorter(weapon.get(index + 1).getWeaponHit()));
		childView.setHitThree(utils.getFormatSorter(weapon.get(index + 2).getWeaponHit()));
		childView.setHitFour(utils.getFormatSorter(weapon.get(index + 3).getWeaponHit()));
		childView.setAccuracyOne(utils.getFormatSorter(weapon.get(index).getWeaponAccuracy()));
		childView.setAccuracyTwo(utils.getFormatSorter(weapon.get(index + 1).getWeaponAccuracy()));
		childView.setAccuracyThree(utils.getFormatSorter(weapon.get(index + 2).getWeaponAccuracy()));
		childView.setAccuracyFour(utils.getFormatSorter(weapon.get(index + 3).getWeaponAccuracy()));
		childView.setMissedHitOne(utils.getFormatSorter(weapon.get(index).getMissedShots()));
		childView.setMissedHitTwo(utils.getFormatSorter(weapon.get(index + 1).getMissedShots()));
		childView.setMissedHitThree(utils.getFormatSorter(weapon.get(index + 2).getMissedShots()));
		childView.setMissedHitFour(utils.getFormatSorter(weapon.get(index + 3).getMissedShots()));
		childView.setKillOne(utils.getFormatSorter(weapon.get(index).getWeaponKills()));
		childView.setKillTwo(utils.getFormatSorter(weapon.get(index + 1).getWeaponKills()));
		childView.setKillThree(utils.getFormatSorter(weapon.get(index + 2).getWeaponKills()));
		childView.setKillFour(utils.getFormatSorter(weapon.get(index + 3).getWeaponKills()));

	}

}
