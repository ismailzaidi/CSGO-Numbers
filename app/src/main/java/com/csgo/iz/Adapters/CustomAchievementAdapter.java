package com.csgo.iz.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.csgo.iz.R;
import com.csgo.iz.modal.Utility;
import com.csgo.iz.modal.bean.Achievement;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAchievementAdapter extends ArrayAdapter<Achievement> {
	private String key = "com.csgo.spray.SprayPatternView";
	private List<Achievement> objects;
	private Activity context;
	private ViewHolder holder;
	public CustomAchievementAdapter(Activity context, List<Achievement> objects) {
		super(context, R.layout.custom_achievement_item, objects);
		this.objects = objects;
		this.context = context;
	}

	private static class ViewHolder {
		ImageView achievementImage;
		TextView achievementName;
		TextView achievementDescription;
		ImageView achievementLock;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		holder = new ViewHolder();
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.custom_achievement_item, null, false);
			holder.achievementImage = (ImageView) convertView.findViewById(R.id.achievementImage);
			holder.achievementName = (TextView) convertView.findViewById(R.id.achievementName);
			holder.achievementDescription = (TextView) convertView.findViewById(R.id.achievementDescription);
			holder.achievementLock = (ImageView) convertView.findViewById(R.id.achievementLock);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.achievementName.setText(objects.get(position).getAchievementName());
		holder.achievementDescription.setText(objects.get(position).getAchievementDescription());
		Utility.setFontForView((ViewGroup) convertView);
		int lock = objects.get(position).getLockInfo();
		if (lock == 0) {
			holder.achievementLock.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.lock_1));
		} else {
			holder.achievementLock.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.lock_0));
		}
		Picasso.with(context).load(objects.get(position).getAchievementResID()).into(holder.achievementImage);
		return convertView;
	}
	@Override
	public boolean isEnabled(int position) {
		return false;
	}


}
