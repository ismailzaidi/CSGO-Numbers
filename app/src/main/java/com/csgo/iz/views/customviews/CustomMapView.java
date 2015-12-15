package com.csgo.iz.views.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csgo.iz.R;

public class CustomMapView extends LinearLayout {
    private static int ID = 0;
    private ImageView mapImageView;
    private TextView mapNameView;
    /**
     * Users
     */
    private TextView user1TextView, user2TextView, user3TextView, user4TextView;
    private TextView roundsPlayed1TextView, roundsPlayed2TextView, roundsPlayed3TextView, roundsPlayed4TextView;
    private TextView roundsWon1TextView, roundsWon2TextView, roundsWon3TextView, roundsWon4TextView;
    private TextView accuracy1TextView, accuracy2TextView, accuracy3TextView, accuracy4TextView;

    public CustomMapView(Context context) {
        this(context, null, 0);
    }

    public CustomMapView(Context context, AttributeSet attr) {
        this(context, attr, 0);
    }

    public CustomMapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setMapName(String weaponName) {
        mapNameView.setText(weaponName);
    }

    public void setMapImage(int id) {
        mapImageView.setImageResource(id);
    }

    public void setUserOne(String userName) {
        user1TextView.setText(userName);
    }

    public void setUserTwo(String userName) {
        user2TextView.setText(userName);
    }

    public void setUserThree(String userName) {
        user3TextView.setText(userName);
    }

    public void setUserFour(String userName) {
        user4TextView.setText(userName);
    }

    public void setRoundsPlayedOne(String shotsString) {
        roundsPlayed1TextView.setText(shotsString);
    }

    public void setRoundsPlayedTwo(String shotsString) {
        roundsPlayed2TextView.setText(shotsString);
    }

    public void setRoundsPlayedThree(String shotsString) {
        roundsPlayed3TextView.setText(shotsString);
    }

    public void setRoundsPlayedFour(String shotsString) {
        roundsPlayed4TextView.setText(shotsString);
    }

    public void setRoundsWonOne(String hitString) {
        roundsWon1TextView.setText(hitString);
    }

    public void setRoundsWonTwo(String hitString) {
        roundsWon2TextView.setText(hitString);
    }

    public void setRoundsWonThree(String hitString) {
        roundsWon3TextView.setText(hitString);
    }

    public void setRoundsWonFour(String hitString) {
        roundsWon4TextView.setText(hitString);
    }

    public void setAccuracyOne(String accuracyStr) {
        accuracy1TextView.setText(accuracyStr);
    }

    public void setAccuracyTwo(String accuracyStr) {
        accuracy2TextView.setText(accuracyStr);
    }

    public void setAccuracyThree(String accuracyStr) {
        accuracy3TextView.setText(accuracyStr);
    }

    public void setAccuracyFour(String accuracyStr) {
        accuracy4TextView.setText(accuracyStr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mapImageView = (ImageView) this.findViewById(R.id.mapImage);
        mapNameView = (TextView) this.findViewById(R.id.mapName);
        user1TextView = (TextView) this.findViewById(R.id.user1TextView);
        user2TextView = (TextView) this.findViewById(R.id.user2TextView);
        user3TextView = (TextView) this.findViewById(R.id.user3TextView);
        user4TextView = (TextView) this.findViewById(R.id.user4TextView);
        roundsPlayed1TextView = (TextView) this.findViewById(R.id.player1Shot);
        roundsPlayed2TextView = (TextView) this.findViewById(R.id.player2Shot);
        roundsPlayed3TextView = (TextView) this.findViewById(R.id.player3Shot);
        roundsPlayed4TextView = (TextView) this.findViewById(R.id.player4Shot);
        roundsWon1TextView = (TextView) this.findViewById(R.id.player1Hit);
        roundsWon2TextView = (TextView) this.findViewById(R.id.player2Hit);
        roundsWon3TextView = (TextView) this.findViewById(R.id.player3Hit);
        roundsWon4TextView = (TextView) this.findViewById(R.id.player4Hit);
        accuracy1TextView = (TextView) this.findViewById(R.id.player1Accuracy);
        accuracy2TextView = (TextView) this.findViewById(R.id.player2Accuracy);
        accuracy3TextView = (TextView) this.findViewById(R.id.player3Accuracy);
        accuracy4TextView = (TextView) this.findViewById(R.id.player4Accuracy);
    }
}
