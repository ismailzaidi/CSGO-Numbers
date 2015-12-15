package com.csgo.iz.compare;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.csgo.iz.R;
import com.csgo.iz.modal.Utility;
import com.csgo.iz.modal.bean.GeneralStats;
import com.csgo.iz.modal.bean.LastMatchStats;
import com.csgo.iz.modal.bean.OtherStats;
import com.csgo.iz.modal.bean.Profile;
import com.csgo.iz.modal.bean.Summary;
import com.csgo.iz.modal.bean.Weapon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class SummaryCompareFragment extends Fragment {

	private static String TAG_SUMMARY = "com.io.iz.summary.compare";
	private ArrayList<Summary> summary;
	private Context context;
	private TableLayout tableLayout;
	private static int ID_GENERATOR = 445654;
	private static final String TEST_LOG_SUMMARY = "com.io.iz.summary.test";
	private Utility utils;

	public static SummaryCompareFragment InstanceOf(ArrayList<Summary> summary) {
		SummaryCompareFragment fragment = new SummaryCompareFragment();
		Bundle bundle = new Bundle();
		bundle.putSerializable(TAG_SUMMARY, summary);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		context = getActivity().getApplicationContext();
		View rootView = inflater.inflate(R.layout.summary_compare_fragment, container, false);
		utils = new Utility(context);
		summary = (ArrayList<Summary>) getArguments().getSerializable(TAG_SUMMARY);
		tableLayout = (TableLayout) rootView.findViewById(R.id.summaryTableLayout);
		for (int i = 0; i < summary.size(); i++) {
			populateTable(tableLayout, i);
		}
		// generateGeneralLastMatch(rootView);
		// generateGeneralOtherStats(rootView);
		Log.v("TEST Summary", "KDA: " + summary.get(0).getGeneralStats().getKda());

		Utility.setFontForView((ViewGroup) rootView, context);
		return rootView;
	}

	private TableRow generateTableRow(String value) {
		TableRow tableRow = new TableRow(context);
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		tableRow.setPadding(10, 10, 10, 10);
		tableRow.setLayoutParams(params);
		TextView textView = new TextView(context);
		textView.setId(ID_GENERATOR);
		textView.setLayoutParams(params);
		textView.setText(value);
		textView.setGravity(Gravity.START);
		textView.setTextColor(ContextCompat.getColor(context, R.color.whitetext));
		tableRow.addView(textView);
		ID_GENERATOR++;

		return tableRow;
	}

	private void populateTable(TableLayout tableLayout, int position) {
		GeneralStats generalStats = summary.get(position).getGeneralStats();
		Profile profileStats = summary.get(position).getUserProfile();
		LastMatchStats lastMatch = summary.get(position).getLastMatchStats();
		OtherStats otherStats = summary.get(position).getOtherStats();
		int numOfRows = tableLayout.getChildCount();
		Log.v(TEST_LOG_SUMMARY, " Stat: " + otherStats.getBombDefused());
		TableRow tableRow = new TableRow(context);
		tableRow.setId(position);
		for (int i = 0; i < numOfRows; i++) {
			View child = tableLayout.getChildAt(i);
			if (child instanceof TableRow) {
				int childID = child.getId();
				if (childID == R.id.userRow) {
					String profileName = profileStats.getUserName();
					TableRow innerChild = generateTableRow(profileName);
					((TableRow) child).addView(innerChild);
				} else if (childID == R.id.content_header || childID == R.id.content_row1
						|| childID == R.id.content_weapon) {
					continue;
				} else {
					String stats = "";
					if (i <= 7) {
						stats = getGeneralStats(i, generalStats);
					} else if (i > 7 && i <= 21) {
						stats = getLastMatchStats(i, lastMatch, lastMatch.getBestWeapon());
					} else if (i > 22) {
						stats = getOtherStats(i, otherStats);
					}
					TableRow innerChild = generateTableRow(stats);
					((TableRow) child).addView(innerChild);
				}

			}
		}
	}

	private String getGeneralStats(int position, GeneralStats generalStats) {
		switch (position) {
		case 1:
			return utils.getFormatSorter(generalStats.getStateKills());
		case 2:
			return utils.getFormatSorter(generalStats.getKda());
		case 3:
			return utils.getFormatSorter(generalStats.getAccuracy());
		case 4:
			return utils.getFormatSorter(generalStats.getMvp());
		case 5:
			return generalStats.getTimePlayed();
		case 6:
			return utils.getFormatSorter( generalStats.getHeadShot());
		case 7:
			return utils.getFormatSorter(generalStats.getWinRate());
		}

		return null;
	}

	private String getLastMatchStats(int position, LastMatchStats lastMatch, Weapon weapon) {
		switch (position) {
		case 9:
			return lastMatch.getRoundsWonRatio();
		case 10:
			return utils.getFormatSorter(weapon.getWeaponAccuracy());
		case 11:
			return utils.getFormatSorter(lastMatch.getKda());
		case 12:
			return utils.getFormatSorter(lastMatch.getKills());
		case 13:
			return utils.getFormatSorter(lastMatch.getDeaths());
		case 15:
			return weapon.getWeaponName();
		case 16:
			return utils.getFormatSorter(weapon.getWeaponHit());
		case 17:
			return utils.getFormatSorter(weapon.getWeaponAccuracy());
		case 18:
			return utils.getFormatSorter(lastMatch.getDamage());
		case 19:
			return utils.getFormatSorter(lastMatch.getMoneyspent());
		case 20:
			return utils.getFormatSorter(lastMatch.getMvp());
		case 21:
			return utils.getFormatSorter(lastMatch.getDominations());
		}
		return null;
	}

	private String getOtherStats(int position, OtherStats stats) {

		switch (position) {
		case 23:
			Log.v(TEST_LOG_SUMMARY, "Position: " + position + " Kills: " + stats.getKills());
			return utils.getFormatSorter(stats.getKills());
		case 24:
			return utils.getFormatSorter(stats.getDeaths());
		case 25:
			return utils.getFormatSorter(stats.getHeadshot());
		case 26:
			return utils.getFormatSorter(stats.getMoney_spent());
		case 27:
			return utils.getFormatSorter(stats.getTotal_damage());
		case 28:
			return utils.getFormatSorter(stats.getTotal_score());
		case 29:
			return utils.getFormatSorter(stats.getBombSet());
		case 30:
			return utils.getFormatSorter(stats.getBombDefused());
		case 31:
			return utils.getFormatSorter(stats.getHostageRescured());
		case 32:
			return utils.getFormatSorter(stats.getMatchesPlayed());
		case 33:
			return utils.getFormatSorter(stats.getMatchesWon());
		case 34:
			return utils.getFormatSorter(stats.getRoundsPlayed());
		case 35:
			return utils.getFormatSorter(stats.getRoundsWon());
		case 36:
			return utils.getFormatSorter(stats.getMvpStars());
		case 37:
			return utils.getFormatSorter(stats.getWeaponGivenToTeam());
		case 38:
			return utils.getFormatSorter(stats.getKillsWithEnemy());
		case 39:
			return utils.getFormatSorter(stats.getBlindEnemies());
		case 40:
			return utils.getFormatSorter(stats.getKilledInKnife());
		case 41:
			return utils.getFormatSorter(stats.getAimingSnipersKilled());
		case 42:
			return utils.getFormatSorter(stats.getShotsFired());
		case 43:
			return utils.getFormatSorter(stats.getShotsHit());
		case 44:
			return utils.getFormatSorter(stats.getDominations());
		case 45:
			return utils.getFormatSorter(stats.getDominationOverKills());
		case 46:
			return utils.getFormatSorter(stats.getDominationRevenge());
		case 47:
			return utils.getFormatSorter(stats.getPistolsWon());
		case 48:
			return utils.getFormatSorter(stats.getTeasredEnemies());
		case 49:
			return utils.getFormatSorter(stats.getWindowsBroken());
		}
		return "";

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
}
