package com.csgo.iz.views.customviews;

import com.csgo.iz.R;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomGeneralStatView extends CardView {
	private Context context;
	private TextView totalKills;
	private TextView totalKDA;
	private TextView totalAccuracy;
	private TextView totalMVP;
	private TextView totalTimePlayed;
	private TextView totalHeadShot;
	private TextView totalWinRate;

	public CustomGeneralStatView(Context context) {
		this(context, null, 0);
		this.context = context;
	}

	public CustomGeneralStatView(Context context, AttributeSet attr) {
		this(context, attr, 0);
	}

	public CustomGeneralStatView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		this.context = context;
	}
	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
		totalKills = (TextView) findViewById(R.id.totalKills);
		totalKDA = (TextView) findViewById(R.id.totalKda);
		totalAccuracy = (TextView) findViewById(R.id.accuracy);
		totalMVP = (TextView) findViewById(R.id.totalMVP);
		totalTimePlayed = (TextView) findViewById(R.id.totalTimePlayed);
		totalHeadShot = (TextView) findViewById(R.id.totalHeadShot);
		totalWinRate = (TextView) findViewById(R.id.totalWin);
	}
	public void generateID() {
		totalKills = (TextView) findViewById(R.id.totalKills);
		totalKDA = (TextView) findViewById(R.id.totalKda);
		totalAccuracy = (TextView) findViewById(R.id.accuracy);
		totalMVP = (TextView) findViewById(R.id.totalMVP);
		totalTimePlayed = (TextView) findViewById(R.id.totalTimePlayed);
		totalHeadShot = (TextView) findViewById(R.id.totalHeadShot);
		totalWinRate = (TextView) findViewById(R.id.totalWin);
	}

	public void setTotalKills(String totalKillsStr) {
		totalKills.setText(totalKillsStr);
	}

	public void setTotalKDA(String totalKDAStr) {
		totalKDA.setText(totalKDAStr);
	}

	public void setTotalAccuracy(String totalAccuracyStr) {
		totalAccuracy.setText(totalAccuracyStr);
	}

	public void setTotalMVP(String totalMVPStr) {
		totalMVP.setText(totalMVPStr);
	}

	public void setTotalTimePlayed(String totalTimePlayedStr) {
		totalTimePlayed.setText(totalTimePlayedStr);
	}

	public void setTotalHeadShot(String totalHeadShotStr) {
		totalHeadShot.setText(totalHeadShotStr);
	}

	public void setTotalWinRate(String totalWinRateStr) {
		totalWinRate.setText(totalWinRateStr);
	}
}
