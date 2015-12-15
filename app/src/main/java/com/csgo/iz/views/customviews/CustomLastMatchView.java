package com.csgo.iz.views.customviews;

import com.csgo.iz.R;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomLastMatchView extends CardView {
    private Context context;
    private TextView roundsWon;
    private TextView kdaRatio;
    private TextView killRatio;
    private TextView deathRatio;
    private TextView totalDmg;
    private TextView moneySpent;
    private TextView totalMvp;
    private TextView totalDominions;
    private ImageView weaponImage;
    private TextView weaponShots;
    private TextView weaponHits;
    private TextView weaponAccruacy;

    public CustomLastMatchView(Context context) {
        this(context, null, 0);
        this.context = context;
    }

    public CustomLastMatchView(Context context, AttributeSet attr) {
        this(context, attr, 0);
    }

    public CustomLastMatchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        // TODO Auto-generated method stub
        super.onFinishInflate();

        roundsWon = (TextView) findViewById(R.id.roundRatioWonLastMatch);
        kdaRatio = (TextView) findViewById(R.id.KDAMatch);
        killRatio = (TextView) findViewById(R.id.KillsLastMatch);
        deathRatio = (TextView) findViewById(R.id.DeathsLastMatch);
        totalDmg = (TextView) findViewById(R.id.damageStatLastMatch);
        moneySpent = (TextView) findViewById(R.id.moneySpentLastMatch);
        totalMvp = (TextView) findViewById(R.id.mvpLastMatch);
        totalDominions = (TextView) findViewById(R.id.dominationsLastMatch);
        /**
         * Weapon Section
         */
        weaponImage = (ImageView) findViewById(R.id.weaponIconLastMatch);
        weaponShots = (TextView) findViewById(R.id.weaponShotsLastMatch);
        weaponHits = (TextView) findViewById(R.id.weaponHitsLastMatch);
        weaponAccruacy = (TextView) findViewById(R.id.weaponAccuracyLastMatch);

    }


    public void setRoundsWon(String roundsWonStr) {
        this.roundsWon.setText(roundsWonStr);
    }


    public void setKdaRatio(String kdaRatioStr) {
        this.kdaRatio.setText(kdaRatioStr);
    }

    public void setKillRatio(String killRatioStr) {
        this.killRatio.setText(killRatioStr);
    }

    public void setDeathRatio(String deathRatioStr) {
        this.deathRatio.setText(deathRatioStr);
    }

    public void setTotalDmg(String totalDmgStr) {
        this.totalDmg.setText(totalDmgStr);
    }

    public void setMoneySpent(String moneySpentStr) {
        this.moneySpent.setText(moneySpentStr);
    }

    public void setTotalMvp(String totalMvpStr) {
        this.totalMvp.setText(totalMvpStr);
    }

    public void setTotalDominions(String totalDominionsStr) {
        this.totalDominions.setText(totalDominionsStr);
    }

    public void setWeaponImage(int weaponImageView) {
        this.weaponImage.setImageResource(weaponImageView);
    }

    public void setWeaponShots(String weaponShotsStr) {
        this.weaponShots.setText(weaponShotsStr);

    }

    public void setWeaponHits(String weaponHitsStr) {
        this.weaponHits.setText(weaponHitsStr);
    }

    public void setWeaponAccruacy(String weaponAccruacyStr) {
        this.weaponAccruacy.setText(weaponAccruacyStr);
    }
}
