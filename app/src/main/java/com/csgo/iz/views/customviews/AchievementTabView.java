package com.csgo.iz.views.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csgo.iz.R;
import com.csgo.iz.modal.bean.Achievement;

import java.util.List;

public class AchievementTabView extends LinearLayout {
    private TextView achievementTitle;
    private TextView achievementProgress;

    public AchievementTabView(Context context) {
        this(context, null);
    }

    public AchievementTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void displayTabForAchievements(String tabTitle, List<Achievement> achievements) {
        int lockedCount = 0;
        for (Achievement achievement : achievements) {
            if (achievement.isLocked) {
                lockedCount++;
            }
        }

        int size = achievements.size();
        int inComplete = achievements.size() - lockedCount;
        String progress = "(" + inComplete + "/" + size + ")";
        achievementTitle.setText(tabTitle);
        achievementProgress.setText(progress);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        achievementTitle = (TextView) findViewById(R.id.achievement_title);
        achievementProgress = (TextView) findViewById(R.id.achievement_progress);
    }
}
