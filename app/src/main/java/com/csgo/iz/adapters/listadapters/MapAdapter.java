package com.csgo.iz.adapters.listadapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.csgo.iz.R;
import com.csgo.iz.modal.bean.GameMap;
import com.csgo.iz.views.customviews.MapView;

import java.util.List;

public class MapAdapter extends ArrayAdapter<GameMap> {

    public MapAdapter(Activity context, List<GameMap> objects) {
        super(context, R.layout.custom_map_item, objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MapView mapView = (MapView)convertView;
        if (mapView == null) {
            mapView = (MapView) LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_map_item, parent, false);
        }
        mapView.setMap(getItem(position));
        return mapView;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}
