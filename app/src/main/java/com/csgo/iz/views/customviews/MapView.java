package com.csgo.iz.views.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.csgo.iz.R;
import com.csgo.iz.modal.Utility;
import com.csgo.iz.modal.bean.GameMap;

import java.util.Locale;

public class MapView extends FrameLayout {
    private TextView roundsWon;
    private TextView roundsPlayed;
    private TextView winRate;
    private TextView mapName;
    private ViewGroup mapImage;

    public MapView(Context context) {
        this(context, null, 0);
    }

    public MapView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setMap(GameMap gameMap) {
        mapImage.setBackgroundResource(gameMap.mapID);
        mapName.setText(gameMap.mapName.toUpperCase(new Locale("EN")));
        Utility utils = new Utility();
        roundsPlayed.setText(utils.getFormatSorter(gameMap.roundPlayed));
        roundsWon.setText(utils.getFormatSorter(gameMap.roundsWon));
        winRate.setText(utils.getFormatSorter(gameMap.mapAccuracy) + "%");
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mapImage = (ViewGroup) findViewById(R.id.mapImage);
        roundsWon = (TextView) findViewById(R.id.mapRoundsWon);
        roundsPlayed = (TextView) findViewById(R.id.mapRoundsPlayed);
        winRate = (TextView) findViewById(R.id.mapWinRate);
        mapName = (TextView) findViewById(R.id.mapName);

        Utility.setFontForView(this);
    }
}
