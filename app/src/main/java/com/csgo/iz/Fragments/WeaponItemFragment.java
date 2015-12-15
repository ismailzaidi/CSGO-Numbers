package com.csgo.iz.Fragments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.csgo.iz.R;
import com.csgo.iz.Adapters.CustomWeaponAdapter;
import com.csgo.iz.modal.Model;
import com.csgo.iz.modal.Utility;
import com.csgo.iz.modal.bean.Map;
import com.csgo.iz.modal.bean.Weapon;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class WeaponItemFragment extends Fragment {

	private ListView weaponListView;
	private static String TAG_CSGO_NUMBER = "com.csgo.iz.weapon";
	private static String TAG_CSGO_NUMBER_COMPARE = "com.csgo.iz.weapon.compare";
	private CustomWeaponAdapter adapter;
	private Model model;
	private Context context;

	public static WeaponItemFragment InstanceOf(ArrayList<Weapon> list) {
		WeaponItemFragment fragment = new WeaponItemFragment();
		Bundle bundle = new Bundle();
		Collections.sort(list);
		bundle.putSerializable(TAG_CSGO_NUMBER, list);
		fragment.setArguments(bundle);
		return fragment;
	}
	public static WeaponItemFragment InstanceOfCompare(ArrayList<ArrayList<Weapon>> list) {
		WeaponItemFragment fragment = new WeaponItemFragment();
		Bundle bundle = new Bundle();
		bundle.putSerializable(TAG_CSGO_NUMBER_COMPARE, list);
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
		View rootView = inflater.inflate(R.layout.weapon_fragment, container, false);
		weaponListView = (ListView) rootView.findViewById(R.id.listOfWeaponListView);
		List<Weapon> listOfWeapon = (List<Weapon>) getArguments().getSerializable(TAG_CSGO_NUMBER);
		ArrayList<ArrayList<Weapon>> listOfWeaponCompare = (ArrayList<ArrayList<Weapon>>) getArguments().getSerializable(TAG_CSGO_NUMBER);
		if(listOfWeapon!=null){
			setupListView(listOfWeapon);
		}else{
			// Setup Compare Adapter
		}
		return rootView;
	}
	private void setupListView(List<Weapon> listOfWeapon){
		adapter = new CustomWeaponAdapter(getActivity(),listOfWeapon);
		weaponListView.setAdapter(adapter);
	}


	// private void animationSetup(View rootView) {
	// Log.v("animation", "Reached here");
	// YoYo.with(Techniques.BounceInLeft).duration(ANIMATION_TIME).playOn(rootView.findViewById(R.id.schemalayout));
	// }

}
