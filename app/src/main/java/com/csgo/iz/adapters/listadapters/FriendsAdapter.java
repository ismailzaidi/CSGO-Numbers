package com.csgo.iz.adapters.listadapters;

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

public class FriendsAdapter extends BaseAdapter implements Filterable {
    private List<Profile> originalList;
    private List<Profile> filterList;

    private ProfileFilter profileFilter;

    public FriendsAdapter(List<Profile> objects) {
        this.originalList = objects;
        this.filterList = originalList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        FriendListItemView friendView = (FriendListItemView) convertView;
        if (friendView == null) {
            friendView = (FriendListItemView) LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_friends_item, parent, false);
        }

        friendView.setProfile(originalList.get(position));
        return friendView;
    }

    @Override
    public int getCount() {
        return originalList.size();
    }

    @Override
    public Object getItem(int pos) {
        return originalList.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public boolean isEnabled(int position) {
        return !originalList.get(position).isPrivate;
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
            if (constraint.length() == 0) {
                results.values = filterList;
                results.count = filterList.size();
            } else {
                ArrayList<Profile> nlist = new ArrayList<>();
                for (int i = 0; i < filterList.size(); i++) {
                    String countryName = filterList.get(i).userName.toLowerCase();
                    if (countryName.contains(filterString)) {
                        Profile element = filterList.get(i);
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
            originalList = (ArrayList<Profile>) results.values;
            notifyDataSetChanged();
        }

    }
}
