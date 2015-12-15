package com.csgo.iz.views.customviews;

import java.util.ArrayList;

import com.csgo.iz.R;
import com.csgo.iz.modal.bean.Summary;
import com.csgo.iz.modal.bean.Weapon;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class CustomAchievementView extends LinearLayout {
	private Context context;
	private ImageView achievementImageView;
	private TextView achievementTextViewName, achievementTextViewDescription;
	/**
	 * Users
	 */
	private TextView user1TextView, user2TextView, user3TextView, user4TextView;
	private ImageView lock1ImageView, lock2ImageView, lock3ImageView, lock4ImageView;
	private static int ID = 0;

	public CustomAchievementView(Context context) {
		this(context, null, 0);
		this.context = context;
	}

	public CustomAchievementView(Context context, AttributeSet attr) {
		this(context, attr, 0);
	}

	public CustomAchievementView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
		achievementImageView = (ImageView) this.findViewById(R.id.achievementImage);
		achievementTextViewName = (TextView) this.findViewById(R.id.achievementName);
		achievementTextViewDescription = (TextView) this.findViewById(R.id.achievementDescription);
		user1TextView = (TextView) this.findViewById(R.id.user1);
		user2TextView = (TextView) this.findViewById(R.id.user2);
		user3TextView = (TextView) this.findViewById(R.id.user3);
		user4TextView = (TextView) this.findViewById(R.id.user4);
		lock1ImageView = (ImageView) this.findViewById(R.id.userLock1);
		lock2ImageView = (ImageView) this.findViewById(R.id.userLock2);
		lock3ImageView = (ImageView) this.findViewById(R.id.userLock3);
		lock4ImageView = (ImageView) this.findViewById(R.id.userLock4);

	}

	public void setAchievementName(String achievementName) {
		achievementTextViewName.setText(achievementName);
	}

	public void setAchievementDescription(String achievementDescription) {
		achievementTextViewDescription.setText(achievementDescription);
	}

	public void setAchievementImage(Drawable achievement_image) {
		achievementImageView.setImageDrawable(achievement_image);
	}

	public void setUserLockOneImage(Drawable lock_id) {
		lock1ImageView.setImageDrawable(lock_id);
	}

	public void setUserLockTwoImage(Drawable lock_id) {
		lock2ImageView.setImageDrawable(lock_id);
	}

	public void setUserLockThreeImage(Drawable lock_id) {
		lock3ImageView.setImageDrawable(lock_id);
	}

	public void setUserLockFourImage(Drawable lock_id) {
		lock4ImageView.setImageDrawable(lock_id);
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

}
