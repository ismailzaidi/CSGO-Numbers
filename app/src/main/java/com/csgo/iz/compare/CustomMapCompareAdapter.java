package com.csgo.iz.compare;

import java.util.List;
import java.util.Locale;

import com.csgo.iz.R;
import com.csgo.iz.modal.bean.Map;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomMapCompareAdapter extends ArrayAdapter<Map> {
	private String key = "com.csgo.spray.SprayPatternView";
	private List<Map> objects;
	private Activity context;
	private ViewHolder holder;

	public CustomMapCompareAdapter(Activity context, List<Map> objects) {
		super(context, R.layout.custom_map_item, objects);
		this.objects = objects;
		this.context = context;

	}

	private static class ViewHolder {
		ImageView mapImage;
		TextView mapName;
		TextView mapRoundPlayed;
		TextView mapRoundWon;
		TextView mapWinRate;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		holder = new ViewHolder();
		Log.v("customWeaponAdapterIDs", "Item: " + position  +  objects.get(position));
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.custom_map_item, null, false);
			holder.mapImage = (ImageView) convertView.findViewById(R.id.weaponImage);
			holder.mapName = (TextView) convertView.findViewById(R.id.weaponName);
			holder.mapRoundPlayed = (TextView) convertView.findViewById(R.id.mapRoundsPlayed);
			holder.mapRoundWon = (TextView) convertView.findViewById(R.id.mapRoundsWon);
			holder.mapWinRate = (TextView) convertView.findViewById(R.id.mapWinRate);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.mapImage.setImageResource(objects.get(position).getMapID());
		holder.mapName.setText(objects.get(position).getMapName().toUpperCase(new Locale("EN")));
		holder.mapRoundPlayed.setText(String.valueOf(objects.get(position).getRoundPlayed()));
		holder.mapRoundWon.setText(String.valueOf(objects.get(position).getRoundsWon()));
		holder.mapWinRate.setText(String.valueOf(objects.get(position).getMapAccuracy()+"%"));
		
		return convertView;
	}

}
