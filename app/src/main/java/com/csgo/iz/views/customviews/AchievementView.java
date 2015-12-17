package com.csgo.iz.views.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csgo.iz.R;
import com.csgo.iz.modal.Utility;
import com.csgo.iz.modal.bean.Achievement;
import com.squareup.picasso.Picasso;

public class AchievementView extends LinearLayout {
    private ImageView achievementImage;
    private TextView achievementName;
    private TextView achievementDescription;
    private ImageView lockImage;

    public AchievementView(Context context) {
        this(context, null, 0);
    }

    public AchievementView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AchievementView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Utility.setFontForView(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        achievementImage = (ImageView)findViewById(R.id.achievementImage);
        achievementName = (TextView)findViewById(R.id.achievementName);
        achievementDescription = (TextView)findViewById(R.id.achievementDescription);
        lockImage = (ImageView) findViewById(R.id.achievementLock);
    }

    public void displayAchievement(Achievement achievement)
    {
        achievementName.setText(achievement.achievementName);
        achievementDescription.setText(achievement.achievementDescription);
        Picasso.with(getContext()).load(achievement.achievementResID).into(achievementImage);
        lockImage.setImageResource(achievement.isLocked ? R.drawable.lock_1 : R.drawable.lock_0);

    }
}
