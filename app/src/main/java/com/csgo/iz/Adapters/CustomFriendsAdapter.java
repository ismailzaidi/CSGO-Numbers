package com.csgo.iz.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.csgo.iz.R;
import com.csgo.iz.modal.bean.Profile;
import com.csgo.iz.views.customviews.FriendListItemView;

import java.util.ArrayList;
import java.util.List;

public class CustomFriendsAdapter extends BaseAdapter implements Filterable {
    private List<Profile> original_list;
    private List<Profile> filter_list;

    private ProfileFilter profileFilter;

    public CustomFriendsAdapter(List<Profile> objects) {
        this.original_list = objects;
        this.filter_list = original_list;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        FriendListItemView friendView = (FriendListItemView) convertView;
        if (friendView == null) {
            friendView = (FriendListItemView) LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_friends_item, parent, false);
        }

        friendView.setProfile(original_list.get(position));
        return friendView;
    }

    @Override
    public int getCount() {
        return original_list.size();
    }

    @Override
    public Object getItem(int pos) {
        return original_list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public boolean isEnabled(int position) {
        return !original_list.get(position).isPrivate();
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public Filter getFilter() {

        if (profileFilter == null) {
            profileFilter = new ProfileFilter();
        }
        return profileFilter;
    }

    private class ProfileFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String filterString = constraint.toString().toLowerCase();
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = filter_list;
                results.count = filter_list.size();
            } else {
                ArrayList<Profile> nlist = new ArrayList<Profile>();
                for (int i = 0; i < filter_list.size(); i++) {
                    String countryName = filter_list.get(i).getUserName().toLowerCase();
                    if (countryName.contains(filterString)) {
                        Profile element = filter_list.get(i);
                        Log.v("CountryFilter Checker", "True: " + countryName);
                        nlist.add(element);
                    }
                }
                results.values = nlist;
                results.count = nlist.size();

            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            original_list = (ArrayList<Profile>) results.values;
            notifyDataSetChanged();
        }

    }
}
