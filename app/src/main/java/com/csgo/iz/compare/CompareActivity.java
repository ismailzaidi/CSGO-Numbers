package com.csgo.iz.compare;

import java.util.ArrayList;

import com.csgo.iz.MainActivity;
import com.csgo.iz.R;
import com.csgo.iz.fragments.AboutFragment;
import com.csgo.iz.fragments.MainFragment;
import com.csgo.iz.modal.Model;
import com.csgo.iz.modal.Utility;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class CompareActivity extends AppCompatActivity {
	private Toolbar toolbar;
	private Utility utils;
	private ArrayList<String> listOfFriends;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.compare_activity);
		utils = new Utility();
		listOfFriends = (ArrayList<String>) getIntent().getExtras().getSerializable(CompareFragment.TAG_CSGO_NUMBERS_COMPAREACTIVITY);
		setupToolBar();
		displayFragment(0);
		Model.setToNull();
	}
	private void setupToolBar() {
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		this.setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setHomeAsUpIndicator(R.drawable.back_button);
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	private void displayFragment(int position) {
		Fragment fragment = null;

		switch (position) {
		case 0:
			fragment = MainFragment.InstanceOf(true,listOfFriends);
			break;
		case 1:
			break;
		case 2:
			FragmentManager fmAbout = getSupportFragmentManager();
			AboutFragment aboutFragment = AboutFragment.newInstance();
			aboutFragment.show(fmAbout, "com.aboutfragment");
			
			break;
		case 4:
			
			break;
		}
		
		if (fragment != null) {
			FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
			transaction.replace(R.id.frame_content, fragment);
			transaction.commit();
		}

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_offline, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		if (id == android.R.id.home) {
			Toast.makeText(getApplicationContext(), "It has been pressed", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(getApplicationContext(), MainActivity.class);
			startActivity(intent);
			Model.setToNull();
			CompareActivity.this.overridePendingTransition(R.anim.move_left_out_activity, R.anim.move_right_in_activity);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
}
