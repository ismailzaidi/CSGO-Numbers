package com.csgo.iz.Adapters;

import java.util.ArrayList;
import java.util.List;

import com.csgo.iz.R;
import com.csgo.iz.modal.bean.Profile;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomFriendsAdapter extends BaseAdapter implements Filterable {
    private String key = "com.csgo.spray.SprayPatternView";
    private List<Profile> original_list;
    private List<Profile> filter_list;
    private Activity context;
    private ViewHolder holder;
    private ProfileFilter profileFilter;

    public CustomFriendsAdapter(Activity context, List<Profile> objects) {
        this.original_list = objects;
        this.context = context;
        this.filter_list = original_list;
    }

    private static class ViewHolder {
        ImageView userAvatar;
        TextView userName;
        TextView userLastLogin;
        TextView userProfileState;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        holder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_friends_item, parent, false);
            holder.userAvatar = (ImageView) convertView.findViewById(R.id.friend_avatar);
            holder.userName = (TextView) convertView.findViewById(R.id.friend_name);
            holder.userLastLogin = (TextView) convertView.findViewById(R.id.friend_login);
            holder.userProfileState = (TextView) convertView.findViewById(R.id.friend_status);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Profile profile = original_list.get(position);
        if (profile != null) {
            holder.userName.setText(profile.getUserName());
            holder.userLastLogin.setText(profile.getLastLogin());
            updateTextViews(profile.isPrivate());
            Picasso.with(context).load(profile.getProfileAvatarURL()).into(holder.userAvatar);
        }
        return convertView;
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
        boolean isNotPrivate = !original_list.get(position).isPrivate();
        return isNotPrivate;
    }

    @Override
    public boolean areAllItemsEnabled() {
        // TODO Auto-generated method stub
        return false;
    }

    private void updateTextViews(boolean isPrivate) {
        if (isPrivate) {
            holder.userProfileState.setText("Private Profile");
            holder.userName.setTextColor(ContextCompat.getColor(context, R.color.drawingcolor));
            holder.userLastLogin.setTextColor(ContextCompat.getColor(context, R.color.drawingcolor));
        } else {
            holder.userProfileState.setText("");
            holder.userName.setTextColor(ContextCompat.getColor(context, R.color.primarycolour));
            holder.userLastLogin.setTextColor(ContextCompat.getColor(context, R.color.primarycolour));
        }

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
