package com.csgo.iz.fragments;

import java.util.ArrayList;

import com.csgo.iz.R;
import com.csgo.iz.adapters.listadapters.CustomMapAdapter;
import com.csgo.iz.modal.bean.Map;

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
	public static MapItemFragment InstanceOf(ArrayList<Map> list) {
		MapItemFragment fragment = new MapItemFragment();
		Bundle bundle = new Bundle();
		bundle.putSerializable(TAG_CSGO_NUMBERS, list);
		fragment.setArguments(bundle);
		return fragment;
	}
	public static MapItemFragment InstanceOfCompare(ArrayList<ArrayList<Map>> list) {
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
		ArrayList<Map>  listOfMaps = (ArrayList<Map> ) getArguments().getSerializable(TAG_CSGO_NUMBERS);
		ArrayList<ArrayList<Map>> listOfMapsCompare =  (ArrayList<ArrayList<Map>>) getArguments().getSerializable(TAG_CSGO_NUMBERS_COMPARE);
		if(listOfMaps!=null){
			setupListView(listOfMaps);
		}else{
			// Map Compare Info
			
		}
		
		return rootView;
	}
	private void setupListView(ArrayList<Map> listOfMaps){
		adapter = new CustomMapAdapter(getActivity(),listOfMaps);
		weaponListView.setAdapter(adapter);
	}


	// private void animationSetup(View rootView) {
	// Log.v("animation", "Reached here");
	// YoYo.with(Techniques.BounceInLeft).duration(ANIMATION_TIME).playOn(rootView.findViewById(R.id.schemalayout));
	// }

}
