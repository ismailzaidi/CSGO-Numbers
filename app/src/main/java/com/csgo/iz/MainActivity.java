package com.csgo.iz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.csgo.iz.Fragments.AboutFragment;
import com.csgo.iz.Fragments.FAQFragment;
import com.csgo.iz.Fragments.FriendsFragment;
import com.csgo.iz.Fragments.MainFragment;
import com.csgo.iz.Fragments.ProgressDialogFragment;
import com.csgo.iz.compare.CompareFragment;
import com.csgo.iz.modal.Model;
import com.csgo.iz.modal.SharedPreferenceModel;
import com.csgo.iz.modal.Utility;
import com.csgo.iz.modal.bean.GlobalData;
import com.csgo.iz.modal.bean.Profile;
import com.csgo.iz.modal.http.threads.GlobalAsync;
import com.csgo.iz.modal.http.threads.GlobalAsync.UserGlobalCallback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String fragmentTag = "ProgressDialog";
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    public Model model;
    private ArrayList<Profile> listOfFriends;
    private Utility utils;
    private SharedPreferenceModel prefModel;
    private String steamID;
    private ImageView imageProfile;
    private TextView textProfileName;
    private TextView textProfileLink;
    private Profile userProfile;
    private GlobalData dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        utils = new Utility();
        prefModel = new SharedPreferenceModel(getApplicationContext());
        steamID = prefModel.loadSharedPreferenceUserID();
        launchQueries();
    }
    private UserGlobalCallback listener = new UserGlobalCallback() {

        @Override
        public void UserGlobalIsAvailable(GlobalData data) {
            boolean isNotPrivate = true;
            if(data==null){
                Log.v("ISNULL","Global Data:  " + data);
                isNotPrivate = false;
                setContentView(R.layout.private_fragment);
                boolean isConnected = Utility.isNetworkAvailable(getApplicationContext());
                handleErrorQueries(isConnected);
            }
            if(isNotPrivate){
                setContentView(R.layout.main_activity_offline);
                setupToolBar();
                setupNavigation();
                generateNavigationHeader(data.getPersonalProfile());
                dataList = data;
                displayFragment(0);
            }
        }
    };
    private void launchQueries() {
        ProgressDialogFragment fragment = ProgressDialogFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(fragment, "dialogbar");
        transaction.commitAllowingStateLoss();
        GlobalAsync profileThread = new GlobalAsync(listener, fragment, steamID, MainActivity.this, false);
        profileThread.execute();
    }
    private void handleErrorQueries(boolean isConnected){
        TextView messageTextView = (TextView) findViewById(R.id.refereshMessage);
        LinearLayout refreshButton = (LinearLayout) findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(getIntent());
            }
        });
        if(!isConnected){
            messageTextView.setText("No Internet Available, please turn it on to view stats");
        }


    }
    private void generateNavigationHeader(final Profile userProfile) {
        imageProfile = (ImageView) findViewById(R.id.imageProfile);
        textProfileName = (TextView) findViewById(R.id.nameProfile);
        textProfileLink = (TextView) findViewById(R.id.linkProfile);
        if (textProfileName != null) {
            textProfileName.setText(userProfile.getUserName());
            String html = "<a href=\"" + userProfile.getProfileURL() + "\"><u>View Profile</u></a>";
            textProfileLink.setText(Html.fromHtml(html));
            textProfileLink.setMovementMethod(LinkMovementMethod.getInstance());
            textProfileLink.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(userProfile.getProfileURL()));
                    startActivity(intent);
                }
            });
            Picasso.with(getApplicationContext()).load(userProfile.getProfileAvatarURL()).into(imageProfile);
        }
    }

    private void setupToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_launcher);
        actionBar.setDisplayHomeAsUpEnabled(true);

    }


    private void setupNavigation() {

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                // Snackbar.make(, menuItem.getTitle() + " pressed",
                // Snackbar.LENGTH_LONG).show();

                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                switch (menuItem.getItemId()) {
                    case R.id.drawer_home:
                        displayFragment(0);
                        return true;
                    case R.id.drawer_friends:
                        displayFragment(1);
                        return true;
                    // case R.id.drawer_compare:
                    // displayFragment(2);
                    // return true;
                    case R.id.drawer_about:
                        displayFragment(3);
                        return true;
                    case R.id.drawer_faq:
                        displayFragment(4);
                        return true;
                    case R.id.drawer_support:
                        launchSupportLink();
                        return true;
                }
                return true;
            }
        });
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {

                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void displayFragment(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = MainFragment.InstanceOf(false, steamID, dataList.getStats(), dataList.getListOfAchievements(),
                        dataList.getPersonalProfile());
                break;
            case 1:
                fragment = FriendsFragment.InstanceOf(dataList.getListOfFriends());
                break;
            case 2:
                FragmentManager fmCompare = getSupportFragmentManager();
                ArrayList<Profile> singleUser = new ArrayList<Profile>();
                singleUser.add(userProfile);
                CompareFragment compareFrgament = CompareFragment.InstanceOf(listOfFriends, singleUser);
                compareFrgament.show(fmCompare, "com.aboutfragment");
                break;
            case 3:
                FragmentManager fmAbout = getSupportFragmentManager();
                AboutFragment aboutFragment = AboutFragment.newInstance();
                aboutFragment.setCancelable(true);
                aboutFragment.show(fmAbout, "com.aboutfragment");
                break;
            case 4:
                FragmentManager faqAbout = getSupportFragmentManager();
                FAQFragment faqFragment = FAQFragment.newInstance();
                faqFragment.setCancelable(true);
                faqFragment.show(faqAbout, "com.faqfragment");
                break;
        }

        if (fragment != null) {
            FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_content, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (toolbar != null) {
            getMenuInflater().inflate(R.menu.main_offline, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (toolbar != null) {
            int id = item.getItemId();
            if (id == R.id.action_settings) {
                return true;
            }
            if (id == android.R.id.home) {
                Toast.makeText(getApplicationContext(), "It has been Pressed", Toast.LENGTH_SHORT).show();
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }


    private void launchSupportLink() {
        String URL = "https://www.twitchalerts.com/donate/ismailzd";
        Intent donationIntent = new Intent(Intent.ACTION_VIEW);
        donationIntent.setData(Uri.parse(URL));
        startActivity(donationIntent);
    }

}
