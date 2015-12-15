package com.csgo.iz.Adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.csgo.iz.R;
import com.csgo.iz.modal.Utility;
import com.csgo.iz.modal.bean.Map;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class CustomMapAdapter extends ArrayAdapter<Map> {
    private String key = "com.csgo.spray.SprayPatternView";
    private List<Map> objects;
    private Activity context;
    private ViewHolder holder;
    private Utility utils;

    public CustomMapAdapter(Activity context, List<Map> objects) {
        super(context, R.layout.custom_map_item, objects);
        this.objects = objects;
        this.context = context;
        utils = new Utility(context);

    }

    private static class ViewHolder {
        RelativeLayout mapImage;
        TextView mapName;
        TextView mapRoundPlayed;
        TextView mapRoundWon;
        TextView mapWinRate;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater;
        holder = new ViewHolder();
        if (convertView == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_map_item, null, false);
            holder.mapImage = (RelativeLayout) convertView.findViewById(R.id.mapImage);
            holder.mapName = (TextView) convertView.findViewById(R.id.weaponName);
            holder.mapRoundPlayed = (TextView) convertView.findViewById(R.id.mapRoundsPlayed);
            holder.mapRoundWon = (TextView) convertView.findViewById(R.id.mapRoundsWon);
            holder.mapWinRate = (TextView) convertView.findViewById(R.id.mapWinRate);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Map map = objects.get(position);
        if (map != null) {
            holder.mapImage.setBackgroundResource(map.getMapID());
            holder.mapName.setText(map.getMapName().toUpperCase(new Locale("EN")));
            holder.mapRoundPlayed.setText(utils.getFormatSorter(map.getRoundPlayed()));
            holder.mapRoundWon.setText(utils.getFormatSorter(map.getRoundsWon()));
            holder.mapWinRate.setText(utils.getFormatSorter(map.getMapAccuracy()) + "%");
        }
        Utility.setFontForView((ViewGroup) convertView, context);
        return convertView;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}
