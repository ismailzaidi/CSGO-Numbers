package com.csgo.iz.compare;

import java.util.List;
import java.util.Locale;

import com.csgo.iz.R;
import com.csgo.iz.modal.bean.Weapon;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomWeaponCompareAdapter extends ArrayAdapter<Weapon> {
	private String key = "com.csgo.spray.SprayPatternView";
	private List<Weapon> objects;
	private Activity context;
	private ViewHolder holder;

	public CustomWeaponCompareAdapter(Activity context, List<Weapon> objects) {
		super(context, R.layout.custom_weapon_item, objects);
		this.objects = objects;
		this.context = context;

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
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		holder = new ViewHolder();
		Log.v("customWeaponAdapterIDs", "Item: " + position  +  objects.get(position));
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.custom_weapon_item, null, false);
			holder.weaponImage = (ImageView) convertView.findViewById(R.id.weaponImage);
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
		holder.weaponImage.setImageResource(objects.get(position).getWeaponID());
		holder.weaponName.setText(objects.get(position).getWeaponName().toUpperCase(new Locale("EN")));
		holder.weaponKills.setText(String.valueOf(objects.get(position).getWeaponKills()));
		holder.weaponHits.setText(String.valueOf(objects.get(position).getWeaponHit()));
		holder.weaponShots.setText(String.valueOf(objects.get(position).getWeaponShot()));
		holder.weaponAccuracy.setText(String.valueOf(objects.get(position).getWeaponAccuracy()+"%"));
		holder.weaponMissed.setText(String.valueOf(objects.get(position).getMissedShots()));
		
		return convertView;
	}

}
