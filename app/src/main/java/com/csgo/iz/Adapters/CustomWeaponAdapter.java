package com.csgo.iz.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.csgo.iz.R;
import com.csgo.iz.modal.bean.Weapon;
import com.csgo.iz.views.customviews.WeaponListItemView;

import java.util.List;

public class CustomWeaponAdapter extends ArrayAdapter<Weapon> {

    public CustomWeaponAdapter(Activity context, List<Weapon> objects) {
        super(context, R.layout.custom_weapon_item, objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        WeaponListItemView weaponView = (WeaponListItemView) convertView;
        if (weaponView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            weaponView = (WeaponListItemView) inflater.inflate(R.layout.custom_weapon_item, null, false);
        }
        weaponView.displayWeapon(getItem(position));
        return weaponView;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}
