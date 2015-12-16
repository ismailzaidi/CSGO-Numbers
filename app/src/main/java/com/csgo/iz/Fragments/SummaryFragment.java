package com.csgo.iz.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.csgo.iz.R;
import com.csgo.iz.modal.Utility;
import com.csgo.iz.modal.bean.GeneralStats;
import com.csgo.iz.modal.bean.LastMatchStats;
import com.csgo.iz.modal.bean.OtherStats;
import com.csgo.iz.modal.bean.Profile;
import com.csgo.iz.modal.bean.Summary;
import com.csgo.iz.modal.bean.Weapon;
import com.csgo.iz.views.customviews.CustomGeneralStatView;
import com.csgo.iz.views.customviews.CustomLastMatchView;
import com.csgo.iz.views.customviews.CustomOtherStatView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SummaryFragment extends Fragment {

	private static String TAG_SUMMARY = "com.io.iz.summary";
	private ArrayList<Summary> summary;
	private Context context;
	private Utility utils;
	private CustomLastMatchView childView;
	private CustomOtherStatView otherStatView;
	private LinearLayout linearLayout;

	public static SummaryFragment InstanceOf(ArrayList<Summary> summary) {
		SummaryFragment fragment = new SummaryFragment();
		Bundle bundle = new Bundle();
		bundle.putSerializable(TAG_SUMMARY, summary);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		context = getActivity().getApplicationContext();
		View rootView = inflater.inflate(R.layout.summary_fragment, container, false);
		childView = (CustomLastMatchView) LayoutInflater.from(context).inflate(R.layout.lastmatch_summary_fragment,
				null);
		summary = (ArrayList<Summary>) getArguments().getSerializable(TAG_SUMMARY);
		utils = new Utility();
		linearLayout = (LinearLayout) rootView.findViewById(R.id.linear_content);
		generateProfile(rootView);
		generateGeneralStats();
		generateGeneralLastMatch();
		generateGeneralOtherStats();
		return rootView;
	}

	private void addLinearLayout(View view) {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		Resources resource = getActivity().getResources();
		float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, resource.getDisplayMetrics());
		params.bottomMargin = (int) px;
		params.topMargin = (int) px;
		params.leftMargin = (int) px;
		params.rightMargin = (int) px;
		view.setLayoutParams(params);
		linearLayout.addView(view);

	}

	private void generateProfile(View view) {
		Log.v("THREAD_TEST",
				"Summary Profile (GenerateProfile) " + summary.get(0).getUserProfile().profileAvatarURL);
		final Profile profile = summary.get(0).getUserProfile();
		ImageView profileImage = (ImageView) view.findViewById(R.id.profile_image);
		TextView profileName = (TextView) view.findViewById(R.id.profile_name);
		TextView profileLink = (TextView) view.findViewById(R.id.profile_link);
		TextView profileTotalHours = (TextView) view.findViewById(R.id.profile_hours);
        String html = "<a href=\"" + profile.profileURL + "\"><u>View Profile</u></a>";
        profileLink.setText(Html.fromHtml(html));
        profileLink.setMovementMethod(LinkMovementMethod.getInstance());
		// Log.v("THREAD_TEST", "(Pre-execute) Global Async: " +
		// dialog.isShowing());
		profileLink.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String URL = profile.profileURL;
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(URL));
				startActivity(intent);
			}
		});
		Picasso.with(context).load(profile.profileAvatarURL).into(profileImage);
		profileName.setText(profile.userName);
		profileTotalHours.setText(profile.totalHoursPlayed);
	}
	private void changeOtherTableColor(View view) {
		TableLayout tableOtherStats = (TableLayout) view.findViewById(R.id.tableOtherStats);
		int size = tableOtherStats.getChildCount();
		for (int i = 0; i < size; i++) {
			View child = tableOtherStats.getChildAt(i);
			if (child instanceof TableRow && i % 2 == 0) {
				((TableRow) child).setBackgroundColor(ContextCompat.getColor(context, R.color.defaultcolor));
			}
			((TableRow) child).setPadding(5, 5, 5, 5);
		}
	}
	private void generateGeneralStats() {
		CustomGeneralStatView view = (CustomGeneralStatView) LayoutInflater.from(context)
				.inflate(R.layout.general_summary_fragment, null);
		GeneralStats generalStats = summary.get(0).getGeneralStats();
        LinearLayout kdaLayout = (LinearLayout) view.findViewById(R.id.kda_layout);
        double kda = generalStats.getKda();
        if(kda < 1){
            kdaLayout.setBackground(ContextCompat.getDrawable(context,R.drawable.kda_circle_loser));
        }else{
            kdaLayout.setBackground(ContextCompat.getDrawable(context,R.drawable.kda_circle));
        }

		view.setTotalKills(utils.getFormatSorter(generalStats.getStateKills()));
		view.setTotalKDA(String.format("%.2f", generalStats.getKda()));
		view.setTotalAccuracy(String.format("%.2f", generalStats.getAccuracy()));
		view.setTotalMVP(utils.getFormatSorter(generalStats.getMvp()));
		view.setTotalTimePlayed(generalStats.getTimePlayed());
		view.setTotalHeadShot(String.valueOf(generalStats.getHeadShot()));
		view.setTotalWinRate(String.format("%.2f", generalStats.getWinRate()));
        Utility.setFontForView( view);
		addLinearLayout(view);
	}


	private void generateGeneralLastMatch() {
		LastMatchStats lastMatch = summary.get(0).getLastMatchStats();
		CustomLastMatchView lastMatchView = (CustomLastMatchView) LayoutInflater.from(context)
				.inflate(R.layout.lastmatch_summary_fragment, null);
		Weapon weapon = lastMatch.bestWeapon;
		lastMatchView.setWeaponImage(weapon.id);
		lastMatchView.setWeaponShots(utils.getFormatSorter(weapon.shots));
		lastMatchView.setWeaponHits(utils.getFormatSorter(weapon.hit));
		lastMatchView.setWeaponAccruacy(utils.getFormatSorter(weapon.accuracy) + " %");
		TextView textLostView = (TextView) lastMatchView.findViewById(R.id.loseMatch);
		View strip =  (View) lastMatchView.findViewById(R.id.strip);
		String winningResult = lastMatch.roundsWonRatio;
		String[] winStatus = winningResult.split(",");
		String match_result = winStatus[1];
		if (match_result.equals("WON")) {
            strip.setBackgroundColor(ContextCompat.getColor(context, R.color.winningcolor));
            textLostView.setText("Win");
		} else if (match_result.equals("LOSE")) {
            strip.setBackgroundColor(ContextCompat.getColor(context, R.color.losingcolor));
            textLostView.setText("Lost");
		} else {
            strip.setBackgroundColor(ContextCompat.getColor(context, R.color.drawingcolor));
            textLostView.setText("Draw");
		}
        LinearLayout kdaLayout = (LinearLayout) lastMatchView.findViewById(R.id.kda_layout);
        double kda = lastMatch.kda;
        if(kda < 1){
            kdaLayout.setBackground(ContextCompat.getDrawable(context,R.drawable.kda_circle_loser));
        }else{
            kdaLayout.setBackground(ContextCompat.getDrawable(context,R.drawable.kda_circle));
        }
		lastMatchView.setRoundsWon(winStatus[0]);
		lastMatchView.setKillRatio(utils.getFormatSorter(lastMatch.kills));
		lastMatchView.setDeathRatio(utils.getFormatSorter(lastMatch.deaths));
		lastMatchView.setTotalMvp(utils.getFormatSorter(lastMatch.mvp));
		lastMatchView.setKdaRatio(utils.getFormatSorter(lastMatch.kda));
		lastMatchView.setTotalDmg(utils.getFormatSorter(lastMatch.damage));
		lastMatchView.setMoneySpent("$"+utils.getFormatSorter(lastMatch.moneyspent));
		lastMatchView.setTotalDominions(utils.getFormatSorter(lastMatch.dominations));
        Utility.setFontForView(lastMatchView);
		addLinearLayout(lastMatchView);
	}

	private void generateGeneralOtherStats() {
		OtherStats stats = summary.get(0).getOtherStats();
		CustomOtherStatView otherView = (CustomOtherStatView) LayoutInflater.from(context)
				.inflate(R.layout.otherstats_summary_fragment, null);
		otherView.setOtherKills(utils.getFormatSorter(stats.getKills()));
		otherView.setOtherDeaths(utils.getFormatSorter(stats.getDeaths()));
		otherView.setOtherHeadShot(utils.getFormatSorter(stats.getHeadshot()));
		otherView.setOtherMoneySpent("$"+utils.getFormatSorter(stats.getMoney_spent()));
		otherView.setOtherDamage(utils.getFormatSorter(stats.getTotal_damage()));
		otherView.setOtherScore(utils.getFormatSorter(stats.getTotal_score()));
		otherView.setOtherBombset(utils.getFormatSorter(stats.getBombSet()));
		otherView.setOtherBombDefuse(utils.getFormatSorter(stats.getBombDefused()));
		otherView.setOtherHostageRescure(utils.getFormatSorter(stats.getHostageRescured()));
		otherView.setOtherMatchesPlayed(utils.getFormatSorter(stats.getMatchesPlayed()));
		otherView.setOtherMatchWon(utils.getFormatSorter(stats.getMatchesWon()));
		otherView.setOtherRoundsPlayed(utils.getFormatSorter(stats.getRoundsPlayed()));
		otherView.setOtherRoundsWon(utils.getFormatSorter(stats.getRoundsWon()));
		otherView.setOtherMVP(utils.getFormatSorter(stats.getMvpStars()));
		otherView.setOtherWeaponToTeam(utils.getFormatSorter(stats.getWeaponGivenToTeam()));
		otherView.setOtherEnemyWeapon(utils.getFormatSorter(stats.getKillsWithEnemy()));
		otherView.setOtherBlindEnemies(utils.getFormatSorter(stats.getBlindEnemies()));
		otherView.setOtherKnifeFight(utils.getFormatSorter(stats.getKilledInKnife()));
		otherView.setOtherZoomedSniper(utils.getFormatSorter(stats.getAimingSnipersKilled()));
		otherView.setOtherTotalShots(utils.getFormatSorter(stats.getShotsFired()));
		otherView.setOtherTotalHits(utils.getFormatSorter(stats.getShotsHit()));
		otherView.setOtherPistolRoundsWon(utils.getFormatSorter(stats.getPistolsWon()));
		otherView.setOtherDominations(utils.getFormatSorter(stats.getDominations()));
		otherView.setOtherDominationOverKill(utils.getFormatSorter(stats.getDominationOverKills()));
		otherView.setOtherDominationsRevenge(utils.getFormatSorter(stats.getDominationRevenge()));
		otherView.setOtherZeusKills(utils.getFormatSorter(stats.getTeasredEnemies()));
		otherView.setOtherWindowsBroken(utils.getFormatSorter(stats.getWindowsBroken()));
		addLinearLayout(otherView);
        Utility.setFontForView(otherView);
		changeOtherTableColor(otherView);
	}
}
