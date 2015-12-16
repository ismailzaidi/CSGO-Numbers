package com.csgo.iz.compare;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.csgo.iz.R;
import com.csgo.iz.modal.Utility;
import com.csgo.iz.modal.bean.Profile;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

public class CheckableFriendsAdapter extends BaseAdapter implements Filterable {

	private Activity context;
	private ViewHolder holder;
	private ArrayList<Profile> objects;
	private ArrayList<Profile> filter_list;
	private ProfileFilter filter;

	public CheckableFriendsAdapter(Activity activity, ArrayList<Profile> original_list) {
		this.context = activity;
		this.objects = original_list;
		this.filter_list = original_list;
	}

	private static class ViewHolder {
		ImageView userAvatar;
		TextView userName;
		TextView userLastLogin;
	}

	@Override
	public int getCount() {
		return objects.size();
	}

	@Override
	public Object getItem(int pos) {
		return objects.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return pos;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup viewGroup) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		holder = new ViewHolder();
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.checkable_friends_layout, viewGroup, false);
			holder.userAvatar = (ImageView) convertView.findViewById(R.id.friend_avatar);
			holder.userName = (TextView) convertView.findViewById(R.id.friend_name);
			holder.userLastLogin = (TextView) convertView.findViewById(R.id.friend_login);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.userName.setText(objects.get(position).getUserName());
		holder.userLastLogin.setText(objects.get(position).getLastLogin());
		Utility.setFontForView((ViewGroup) convertView);
		try {
			Drawable d = new BitmapDrawable(context.getResources(),
					new ImageHTTPReader().execute(objects.get(position).getProfileAvatarURL()).get());
			holder.userAvatar.setImageDrawable(d);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return convertView;
	}

	@Override
	public Filter getFilter() {
		if (filter == null) {
			filter = new ProfileFilter();
		}
		return filter;
	}

	private class ImageHTTPReader extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			return getBitmapFromURL(params[0]);
		}

		public Bitmap getBitmapFromURL(String src) {
			try {
				URL url = new URL(src);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setDoInput(true);
				connection.connect();
				InputStream input = connection.getInputStream();
				Bitmap myBitmap = BitmapFactory.decodeStream(input);
				return myBitmap;
			} catch (IOException e) {
				// Log exception
				return null;
			}
		}
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
			objects = (ArrayList<Profile>) results.values;
			notifyDataSetChanged();
		}

	}
}