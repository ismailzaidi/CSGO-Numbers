package com.csgo.iz.views.customviews;

import com.csgo.iz.R;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomOtherStatView extends CardView {
	private Context context;
	private TextView otherKills;
	private TextView otherDeaths;
	private TextView otherHeadShot;
	private TextView otherMoneySpent;
	private TextView otherDamage;
	private TextView otherScore;
	private TextView otherBombset;
	private TextView otherBombDefuse;
	private TextView otherHostageRescure;
	private TextView otherMatchesPlayed;
	private TextView otherMatchWon;
	private TextView otherRoundsPlayed;
	private TextView otherRoundsWon;
	private TextView otherMVP;
	private TextView otherWeaponToTeam;
	private TextView otherEnemyWeapon;
	private TextView otherBlindEnemies;
	private TextView otherKnifeFight;
	private TextView otherZoomedSniper;
	private TextView otherTotalShots;
	private TextView otherTotalHits;
	private TextView otherDominations;
	private TextView otherDominationOverKill;
	private TextView otherDominationsRevenge;
	private TextView otherPistolRoundsWon;
	private TextView otherZeusKills;
	private TextView otherWindowsBroken;

	public CustomOtherStatView(Context context) {
		this(context, null, 0);
		this.context = context;
	}

	public CustomOtherStatView(Context context, AttributeSet attr) {
		this(context, attr, 0);
	}

	public CustomOtherStatView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
		otherKills = (TextView) findViewById(R.id.otherKills);
		otherDeaths = (TextView) findViewById(R.id.otherDeaths);
		otherHeadShot = (TextView) findViewById(R.id.otherHeadShot);
		otherMoneySpent = (TextView) findViewById(R.id.otherMoneySpent);
		otherDamage = (TextView) findViewById(R.id.otherDamage);
		otherScore = (TextView) findViewById(R.id.otherScore);
		otherBombset = (TextView) findViewById(R.id.otherBombset);
		otherBombDefuse = (TextView) findViewById(R.id.otherBombDefuse);
		otherHostageRescure = (TextView) findViewById(R.id.otherHostageRescure);
		otherMatchesPlayed = (TextView) findViewById(R.id.otherMatchesPlayed);
		otherMatchWon = (TextView) findViewById(R.id.otherMatchWon);
		otherRoundsPlayed = (TextView) findViewById(R.id.otherRoundsPlayed);
		otherRoundsWon = (TextView) findViewById(R.id.otherRoundsWon);
		otherMVP = (TextView) findViewById(R.id.otherMVP);
		otherWeaponToTeam = (TextView) findViewById(R.id.otherWeaponToTeam);
		otherEnemyWeapon = (TextView) findViewById(R.id.otherEnemyWeapon);
		otherBlindEnemies = (TextView) findViewById(R.id.otherBlindEnemies);
		otherKnifeFight = (TextView) findViewById(R.id.otherKnifeFight);
		otherZoomedSniper = (TextView) findViewById(R.id.otherZoomedSniper);
		otherTotalShots = (TextView) findViewById(R.id.otherTotalShots);
		otherTotalHits = (TextView) findViewById(R.id.otherTotalHits);
		otherDominations = (TextView) findViewById(R.id.otherDominations);
		otherDominationOverKill = (TextView) findViewById(R.id.otherDominationOverKill);
		otherDominationsRevenge = (TextView) findViewById(R.id.otherDominationsRevenge);
		otherPistolRoundsWon = (TextView) findViewById(R.id.otherPistolRounds);
		otherZeusKills = (TextView) findViewById(R.id.otherZeusKills);
		otherWindowsBroken = (TextView) findViewById(R.id.otherWindowsBroken);

	}

	public void setOtherKills(String otherKillsStr) {
		otherKills.setText(otherKillsStr);;
	}

	public void setOtherDeaths(String otherDeathsStr) {
		otherDeaths.setText(otherDeathsStr);;
	}

	public void setOtherHeadShot(String otherHeadShotStr) {
		otherHeadShot.setText(otherHeadShotStr);;
	}

	public void setOtherMoneySpent(String otherMoneySpentStr) {
		otherMoneySpent.setText(otherMoneySpentStr);;
	}

	public void setOtherDamage(String otherDamageStr) {
		otherDamage.setText(otherDamageStr);;
	}

	public void setOtherScore(String otherScoreStr) {
		otherScore.setText(otherScoreStr);;
	}

	public void setOtherBombset(String otherBombsetStr) {
		otherBombset.setText(otherBombsetStr);;
	}

	public void setOtherBombDefuse(String otherBombDefuseStr) {
		otherBombDefuse.setText(otherBombDefuseStr);;
	}

	public void setOtherHostageRescure(String otherHostageRescureStr) {
		otherHostageRescure.setText(otherHostageRescureStr);;
	}

	public void setOtherMatchesPlayed(String otherMatchesPlayedStr) {
		otherMatchesPlayed.setText(otherMatchesPlayedStr);;
	}

	public void setOtherMatchWon(String otherMatchWonStr) {
		otherMatchWon.setText(otherMatchWonStr);;
	}

	public void setOtherRoundsPlayed(String otherRoundsPlayedStr) {
		otherRoundsPlayed.setText(otherRoundsPlayedStr);;
	}

	public void setOtherRoundsWon(String otherRoundsWonStr) {
		otherRoundsWon.setText(otherRoundsWonStr);;
	}

	public void setOtherMVP(String otherMVPStr) {
		otherMVP.setText(otherMVPStr);;
	}

	public void setOtherWeaponToTeam(String otherWeaponToTeamStr) {
		otherWeaponToTeam.setText(otherWeaponToTeamStr);;
	}

	public void setOtherEnemyWeapon(String otherEnemyWeaponStr) {
		otherEnemyWeapon.setText(otherEnemyWeaponStr);;
	}

	public void setOtherBlindEnemies(String otherBlindEnemiesStr) {
		otherBlindEnemies.setText(otherBlindEnemiesStr);;
	}

	public void setOtherKnifeFight(String otherKnifeFightStr) {
		otherKnifeFight.setText(otherKnifeFightStr);;
	}

	public void setOtherZoomedSniper(String otherZoomedSniperStr) {
		otherZoomedSniper.setText(otherZoomedSniperStr);;
	}

	public void setOtherTotalShots(String otherTotalShotsStr) {
		otherTotalShots.setText(otherTotalShotsStr);;
	}

	public void setOtherTotalHits(String otherTotalHitsStr) {
		otherTotalHits.setText(otherTotalHitsStr);;
	}

	public void setOtherDominations(String otherDominationsStr) {
		otherDominations.setText(otherDominationsStr);;
	}

	public void setOtherDominationOverKill(String otherDominationOverKillStr) {
		otherDominationOverKill.setText(otherDominationOverKillStr);;
	}

	public void setOtherDominationsRevenge(String otherDominationsRevengeStr) {
		otherDominationsRevenge.setText(otherDominationsRevengeStr);;
	}

	public void setOtherPistolRoundsWon(String otherPistolRoundsWonStr) {
		otherPistolRoundsWon.setText(otherPistolRoundsWonStr);;
	}

	public void setOtherZeusKills(String otherZeusKillsStr) {
		otherZeusKills.setText(otherZeusKillsStr);;
	}

	public void setOtherWindowsBroken(String otherWindowsBrokenStr) {
		otherWindowsBroken.setText(otherWindowsBrokenStr);;
	}
}
