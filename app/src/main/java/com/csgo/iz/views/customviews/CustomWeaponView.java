package com.csgo.iz.views.customviews;

import java.util.ArrayList;

import com.csgo.iz.R;
import com.csgo.iz.modal.bean.Summary;
import com.csgo.iz.modal.bean.Weapon;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class CustomWeaponView extends LinearLayout {
	private Context context;
	private ImageView weaponImageView;
	private TextView weaponNameView;
	/**
	 * Users
	 */
	private TextView user1TextView, user2TextView, user3TextView, user4TextView;

	private TextView shots1TextView, shots2TextView, shots3TextView, shots4TextView;

	private TextView hits1TextView, hits2TextView, hits3TextView, hits4TextView;

	private TextView accuracy1TextView, accuracy2TextView, accuracy3TextView, accuracy4TextView;

	private TextView missedShots1TextView, missedShots2TextView, missedShots3TextView, missedShots4TextView;
	private TextView kills1TextView, kills2TextView, kills3TextView, kills4TextView;

	private static int ID = 0;

	public CustomWeaponView(Context context) {
		this(context, null, 0);
		this.context = context;
	}

	public CustomWeaponView(Context context, AttributeSet attr) {
		this(context, attr, 0);
	}

	public CustomWeaponView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
		weaponImageView = (ImageView) this.findViewById(R.id.weaponImage);
		weaponNameView = (TextView) this.findViewById(R.id.weaponName);
		user1TextView = (TextView) this.findViewById(R.id.user1TextView);
		user2TextView = (TextView) this.findViewById(R.id.user2TextView);
		user3TextView = (TextView) this.findViewById(R.id.user3TextView);
		user4TextView = (TextView) this.findViewById(R.id.user4TextView);
		kills1TextView = (TextView) this.findViewById(R.id.player1Kill);
		kills2TextView = (TextView) this.findViewById(R.id.player2Kill);
		kills3TextView = (TextView) this.findViewById(R.id.player3Kill);
		kills4TextView = (TextView) this.findViewById(R.id.player4Kill);
		shots1TextView = (TextView) this.findViewById(R.id.player1Shot);
		shots2TextView = (TextView) this.findViewById(R.id.player2Shot);
		shots3TextView = (TextView) this.findViewById(R.id.player3Shot);
		shots4TextView = (TextView) this.findViewById(R.id.player4Shot);
		hits1TextView = (TextView) this.findViewById(R.id.player1Hit);
		hits2TextView = (TextView) this.findViewById(R.id.player2Hit);
		hits3TextView = (TextView) this.findViewById(R.id.player3Hit);
		hits4TextView = (TextView) this.findViewById(R.id.player4Hit);
		missedShots1TextView = (TextView) this.findViewById(R.id.player1Missed);
		missedShots2TextView = (TextView) this.findViewById(R.id.player2Missed);
		missedShots3TextView = (TextView) this.findViewById(R.id.player3Missed);
		missedShots4TextView = (TextView) this.findViewById(R.id.player4Missed);
		accuracy1TextView = (TextView) this.findViewById(R.id.player1Accuracy);
		accuracy2TextView = (TextView) this.findViewById(R.id.player2Accuracy);
		accuracy3TextView = (TextView) this.findViewById(R.id.player3Accuracy);
		accuracy4TextView = (TextView) this.findViewById(R.id.player4Accuracy);
		
	}

	public void setWeaponName(String weaponName) {
		weaponNameView.setText(weaponName);
	}

	public void setWeaponImage(int id) {
		weaponImageView.setImageResource(id);
	}

	public void setUserRow(ArrayList<Summary> userlist) {
		for (int i = 0; i < userlist.size(); i++) {
			String userName = userlist.get(i).getUserProfile().getUserName();
		}
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

	public void setShotOne(String shotsString) {
		shots1TextView.setText(shotsString);
	}

	public void setShotTwo(String shotsString) {
		shots2TextView.setText(shotsString);
	}

	public void setShotThree(String shotsString) {
		shots3TextView.setText(shotsString);
	}

	public void setShotFour(String shotsString) {
		shots4TextView.setText(shotsString);
	}

	public void setHitOne(String hitString) {
		hits1TextView.setText(hitString);
	}

	public void setHitTwo(String hitString) {
		hits2TextView.setText(hitString);

	}

	public void setHitThree(String hitString) {
		hits3TextView.setText(hitString);

	}

	public void setHitFour(String hitString) {
		hits4TextView.setText(hitString);

	}

	public void setMissedHitOne(String missedShotsString) {
		missedShots1TextView.setText(missedShotsString);
	}

	public void setMissedHitTwo(String missedShotsString) {
		missedShots2TextView.setText(missedShotsString);

	}

	public void setMissedHitThree(String missedShotsString) {
		missedShots3TextView.setText(missedShotsString);

	}

	public void setMissedHitFour(String missedShotsString) {
		missedShots4TextView.setText(missedShotsString);

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
	public void setKillOne(String killstr) {
		kills1TextView.setText(killstr);
	}
	
	public void setKillTwo(String killstr) {
		kills2TextView.setText(killstr);
	}
	
	public void setKillThree(String killstr) {
		kills3TextView.setText(killstr);
		
	}
	public void setKillFour(String killstr) {
		kills4TextView.setText(killstr);
		
	}
}
