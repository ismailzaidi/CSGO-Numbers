package com.csgo.iz.Adapters;

import java.util.List;
import java.util.Locale;

import com.csgo.iz.R;
import com.csgo.iz.modal.Utility;
import com.csgo.iz.modal.bean.Weapon;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomWeaponAdapter extends ArrayAdapter<Weapon> {
    private String key = "com.csgo.spray.SprayPatternView";
    private List<Weapon> objects;
    private Activity context;
    private ViewHolder holder;
    private Utility utils;


    public CustomWeaponAdapter(Activity context, List<Weapon> objects) {
        super(context, R.layout.custom_weapon_item, objects);
        Log.v("Weapon Tab Stat", "Custom Weapon Adapter: " + objects.size());
        this.objects = objects;
        this.context = context;
        utils = new Utility(context);
    }

    private static class ViewHolder {
        ImageView weaponImage;
        TextView weaponName;
        TextView weaponKills;
        TextView weaponHits;
        TextView weaponShots;
        TextView weaponAccuracy;
        TextView weaponMissed;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        holder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_weapon_item, null, false);
            holder.weaponImage = (ImageView) convertView.findViewById(R.id.mapImage);
            holder.weaponName = (TextView) convertView.findViewById(R.id.weaponName);
            holder.weaponKills = (TextView) convertView.findViewById(R.id.mapGamesPlayed);
            holder.weaponHits = (TextView) convertView.findViewById(R.id.mapRoundsPlayed);
            holder.weaponShots = (TextView) convertView.findViewById(R.id.mapRoundsWon);
            holder.weaponAccuracy = (TextView) convertView.findViewById(R.id.weaponAccuracy);
            holder.weaponMissed = (TextView) convertView.findViewById(R.id.weaponMissed);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Weapon weapon = objects.get(position);
        if (weapon != null) {
            holder.weaponImage.setBackgroundResource(weapon.getWeaponID());
            holder.weaponName.setText(weapon.getWeaponName().toUpperCase(new Locale("EN")));
            holder.weaponKills.setText(utils.getFormatSorter(weapon.getWeaponKills()));
            holder.weaponHits.setText(utils.getFormatSorter(weapon.getWeaponHit()));
            holder.weaponShots.setText(utils.getFormatSorter(weapon.getWeaponShot()));
            holder.weaponAccuracy.setText(utils.getFormatSorter(weapon.getWeaponAccuracy()) + "%");
            holder.weaponMissed.setText(utils.getFormatSorter(weapon.getMissedShots()));
        }
        Utility.setFontForView((ViewGroup) convertView, context);
        return convertView;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

}
