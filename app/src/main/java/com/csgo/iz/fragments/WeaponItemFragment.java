package com.csgo.iz.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.csgo.iz.R;
import com.csgo.iz.adapters.listadapters.CustomWeaponAdapter;
import com.csgo.iz.modal.bean.Weapon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeaponItemFragment extends Fragment {

    private static String TAG_CSGO_NUMBER = "com.csgo.iz.weapon";
    private ListView weaponListView;
    private CustomWeaponAdapter adapter;

    public static WeaponItemFragment InstanceOf(ArrayList<Weapon> list) {
        WeaponItemFragment fragment = new WeaponItemFragment();
        Bundle bundle = new Bundle();
        Collections.sort(list);
        bundle.putSerializable(TAG_CSGO_NUMBER, list);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.weapon_fragment, container, false);
        weaponListView = (ListView) rootView.findViewById(R.id.listOfWeaponListView);
        List<Weapon> listOfWeapon = (List<Weapon>) getArguments().getSerializable(TAG_CSGO_NUMBER);
        setupListView(listOfWeapon);
        return rootView;
    }

    private void setupListView(List<Weapon> listOfWeapon) {
        adapter = new CustomWeaponAdapter(getActivity(), listOfWeapon);
        weaponListView.setAdapter(adapter);
    }
}
