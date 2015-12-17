package com.csgo.iz.fragments;

import java.util.ArrayList;

import com.csgo.iz.R;
import com.csgo.iz.adapters.listadapters.CustomMapAdapter;
import com.csgo.iz.modal.bean.GameMap;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MapItemFragment extends Fragment {

	private ListView weaponListView;
	private static String TAG_CSGO_NUMBERS = "com.csgo.iz.map";
	private static String TAG_CSGO_NUMBERS_COMPARE = "com.csgo.iz.map.compare";
	private CustomMapAdapter adapter;
	private Context context;
	public static MapItemFragment InstanceOf(ArrayList<GameMap> list) {
		MapItemFragment fragment = new MapItemFragment();
		Bundle bundle = new Bundle();
		bundle.putSerializable(TAG_CSGO_NUMBERS, list);
		fragment.setArguments(bundle);
		return fragment;
	}
	public static MapItemFragment InstanceOfCompare(ArrayList<ArrayList<GameMap>> list) {
		MapItemFragment fragment = new MapItemFragment();
		Bundle bundle = new Bundle();
		bundle.putSerializable(TAG_CSGO_NUMBERS_COMPARE, list);
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
		View rootView = inflater.inflate(R.layout.map_tab_fragment, container, false);
		weaponListView = (ListView) rootView.findViewById(R.id.mapListView);
		ArrayList<GameMap> listOfGameMaps = (ArrayList<GameMap> ) getArguments().getSerializable(TAG_CSGO_NUMBERS);
		ArrayList<ArrayList<GameMap>> listOfMapsCompare =  (ArrayList<ArrayList<GameMap>>) getArguments().getSerializable(TAG_CSGO_NUMBERS_COMPARE);
		if(listOfGameMaps !=null){
			setupListView(listOfGameMaps);
		}else{
			// GameMap Compare Info
			
		}
		
		return rootView;
	}
	private void setupListView(ArrayList<GameMap> listOfGameMaps){
		adapter = new CustomMapAdapter(getActivity(), listOfGameMaps);
		weaponListView.setAdapter(adapter);
	}


	// private void animationSetup(View rootView) {
	// Log.v("animation", "Reached here");
	// YoYo.with(Techniques.BounceInLeft).duration(ANIMATION_TIME).playOn(rootView.findViewById(R.id.schemalayout));
	// }

}
