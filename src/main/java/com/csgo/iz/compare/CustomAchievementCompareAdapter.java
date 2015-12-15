package com.csgo.iz.compare;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.csgo.iz.R;
import com.csgo.iz.modal.bean.Achievement;
import com.csgo.iz.modal.bean.Profile;
import com.csgo.iz.modal.bean.Summary;
import com.csgo.iz.views.customviews.CustomAchievementView;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

public class CustomAchievementCompareAdapter extends ArrayAdapter<Achievement> {
	private List<HashMap<Integer, List<Achievement>>> objects;
	private Activity context;
	private ViewHolder holder;
	private int achievement_position;
	private ArrayList<Summary> listOfSummaries;
	private static int COUNT_ID = 12343;

	public CustomAchievementCompareAdapter(Activity context, List<HashMap<Integer, List<Achievement>>> objects,
			int achievement_position, List<Achievement> listOfAchievements, ArrayList<Summary> listOfSummaries) {
		super(context, R.layout.custom_achievement_compare_item, listOfAchievements);
		this.objects = objects;
		this.context = context;
		this.achievement_position = achievement_position;
		this.listOfSummaries = listOfSummaries;
		Log.v("com.achievement.fragment", "Custom Achievement Constructor ");
	}

	private static class ViewHolder {
		CustomAchievementView viewLayout;
		LinearLayout layout;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		holder = new ViewHolder();
		if (convertView == null) {
			
			
			convertView = LayoutInflater.from(context).inflate(R.layout.custom_achievement_compare_item, null,false);
			holder.viewLayout = (CustomAchievementView) LayoutInflater.from(context).inflate(R.layout.custom_achievement_item_elfessho, null,
					false);
			holder.layout = (LinearLayout) convertView.findViewById(R.id.layout);
			holder.layout.addView(holder.viewLayout);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		int user_size = objects.size();
		Log.v("com.achievement.fragment", "Size: " +  objects.size());
		for (int i = 0; i < user_size; i++) {// 3 Users
			generateLocks(i, position, holder.viewLayout,listOfSummaries);
		}
		holder.viewLayout
				.setAchievementName(objects.get(0).get(achievement_position).get(position).getAchievementName());
		holder.viewLayout.setAchievementDescription(
				objects.get(0).get(achievement_position).get(position).getAchievementDescription());

		try {
			Drawable d = new BitmapDrawable(context.getResources(), new ImageHTTPReader()
					.execute(objects.get(0).get(achievement_position).get(position).getAchievementResID()).get());
			holder.viewLayout.setAchievementImage(d);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return convertView;
	}

	private Drawable getUserLock(int userposition, int position) {
		Drawable lockDraw = ContextCompat.getDrawable(context, R.drawable.lock_1);
		Drawable unlockDraw = ContextCompat.getDrawable(context, R.drawable.lock_0);
		int userAchievement = getLock(userposition, position);
		if (userAchievement == 0) {
			return lockDraw;
		} else {
			return unlockDraw;
		}
	}
	private void generateLocks(int userPosition, int position, CustomAchievementView view, ArrayList<Summary> profileList) {
		int index = 0;
		switch (userPosition) {
		case 0:
			Drawable lock_id = getUserLock(index, position);
			view.setUserLockOneImage(lock_id);
			view.setUserOne(profileList.get(index).getUserProfile().getUserName());
			break;
		case 1:
			Drawable lock_id1 = getUserLock(index, position);
			Drawable lock_id2 = getUserLock(index+1, position);
			view.setUserLockOneImage(lock_id1);
			view.setUserLockTwoImage(lock_id2);
			view.setUserOne(profileList.get(index).getUserProfile().getUserName());
			view.setUserTwo(profileList.get(index+1).getUserProfile().getUserName());

			break;
		case 2:
			Drawable lock_id11 = getUserLock(index, position);
			Drawable lock_id21 = getUserLock(index+1, position);
			Drawable lock_id3= getUserLock(index+2, position);
			view.setUserLockOneImage(lock_id11);
			view.setUserLockTwoImage(lock_id21);
			view.setUserLockThreeImage(lock_id3);
			
			view.setUserOne(profileList.get(index).getUserProfile().getUserName());
			view.setUserTwo(profileList.get(index+1).getUserProfile().getUserName());
			view.setUserThree(profileList.get(index+2).getUserProfile().getUserName());
			break;
		case 3:
			Drawable lock_id111 = getUserLock(index, position);
			Drawable lock_id211 = getUserLock(index+1, position);
			Drawable lock_id31= getUserLock(index+2, position);
			Drawable lock_id4 = getUserLock(index+3, position);
			view.setUserLockOneImage(lock_id111);
			view.setUserLockTwoImage(lock_id211);
			view.setUserLockThreeImage(lock_id31);
			view.setUserLockFourImage(lock_id4);
			view.setUserOne(profileList.get(index).getUserProfile().getUserName());
			view.setUserTwo(profileList.get(index+1).getUserProfile().getUserName());
			view.setUserThree(profileList.get(index+2).getUserProfile().getUserName());
			view.setUserFour(profileList.get(index+3).getUserProfile().getUserName());
			break;
		}
	}

	private int getLock(int user, int position) {
		HashMap<Integer, List<Achievement>> tempMap = objects.get(user);
		List<Achievement> achievement_list = tempMap.get(achievement_position);
		int lockInfo = achievement_list.get(position).getLockInfo();
		return lockInfo;

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

	/**
	 * <ImageView android:layout_width="wrap_content" android:layout_height=
	 * "wrap_content" android:adjustViewBounds="true" android:layout_marginLeft=
	 * "5dp" android:src="@drawable/lock_0" />
	 * 
	 * @return
	 */

}