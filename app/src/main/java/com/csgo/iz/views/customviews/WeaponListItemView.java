package com.csgo.iz.views.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csgo.iz.R;
import com.csgo.iz.modal.Utility;
import com.csgo.iz.modal.bean.Weapon;

import java.util.Locale;

public class WeaponListItemView extends LinearLayout {

    private ImageView weaponImage;
    private TextView weaponName;
    private TextView weaponKills;
    private TextView weaponHits;
    private TextView weaponShots;
    private TextView weaponAccuracy;
    private TextView weaponMissed;
    private Utility utils;

    public WeaponListItemView(Context context) {
        this(context, null, 0);
    }

    public WeaponListItemView(Context context, AttributeSet attrs) {
        this(context, null, 0);
    }

    public WeaponListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Utility.setFontForView(this);
        utils = new Utility();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        weaponImage = (ImageView) findViewById(R.id.weaponImage);
        weaponName = (TextView) findViewById(R.id.weaponName);
        weaponKills = (TextView) findViewById(R.id.mapGamesPlayed);
        weaponHits = (TextView) findViewById(R.id.mapRoundsPlayed);
        weaponShots = (TextView) findViewById(R.id.mapRoundsWon);
        weaponAccuracy = (TextView) findViewById(R.id.weaponAccuracy);
        weaponMissed = (TextView) findViewById(R.id.weaponMissed);
    }

    public void displayWeapon(Weapon weapon)
    {
        weaponImage.setBackgroundResource(weapon.getWeaponID());
        weaponName.setText(weapon.getWeaponName().toUpperCase(new Locale("EN")));
        weaponKills.setText(utils.getFormatSorter(weapon.getWeaponKills()));
        weaponHits.setText(utils.getFormatSorter(weapon.getWeaponHit()));
        weaponShots.setText(utils.getFormatSorter(weapon.getWeaponShot()));
        weaponAccuracy.setText(utils.getFormatSorter(weapon.getWeaponAccuracy()) + "%");
        weaponMissed.setText(utils.getFormatSorter(weapon.getMissedShots()));
    }
}
