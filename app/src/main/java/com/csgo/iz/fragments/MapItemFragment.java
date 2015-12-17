package com.csgo.iz.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.csgo.iz.R;
import com.csgo.iz.adapters.listadapters.MapAdapter;
import com.csgo.iz.modal.bean.GameMap;

import java.util.ArrayList;

public class MapItemFragment extends Fragment {

    private static String TAG_CSGO_NUMBERS = "com.csgo.iz.map";

    public static MapItemFragment InstanceOf(ArrayList<GameMap> list) {
        MapItemFragment fragment = new MapItemFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(TAG_CSGO_NUMBERS, list);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.map_tab_fragment, container, false);
        setupListView(getArguments().<GameMap>getParcelableArrayList(TAG_CSGO_NUMBERS), (ListView) rootView.findViewById(R.id.mapListView));
        return rootView;
    }

    private void setupListView(ArrayList<GameMap> listOfGameMaps, ListView listView) {
        listView.setAdapter(new MapAdapter(getActivity(), listOfGameMaps));
    }
}
