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

import com.csgo.iz.fragments.AboutFragment;
import com.csgo.iz.fragments.FAQFragment;
import com.csgo.iz.fragments.FriendsFragment;
import com.csgo.iz.fragments.MainFragment;
import com.csgo.iz.fragments.ProgressDialogFragment;
import com.csgo.iz.modal.SharedPreferenceModel;
import com.csgo.iz.modal.Utility;
import com.csgo.iz.modal.bean.GlobalData;
import com.csgo.iz.modal.bean.Profile;
import com.csgo.iz.modal.http.threads.GlobalAsync;
import com.csgo.iz.modal.http.threads.GlobalAsync.UserGlobalCallback;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private String steamID;
    private GlobalData dataList;
    private UserGlobalCallback listener = new UserGlobalCallback() {
        @Override
        public void UserGlobalIsAvailable(GlobalData data) {
            boolean isNotPrivate = true;
            if (data == null) {
                Log.v("ISNULL", "Global Data:  " + data);
                isNotPrivate = false;
                setContentView(R.layout.private_fragment);
                boolean isConnected = Utility.isNetworkAvailable(getApplicationContext());
                handleErrorQueries(isConnected);
            }
            if (isNotPrivate) {
                setContentView(R.layout.main_activity_offline);
                setupToolBar();
                setupNavigation();
                generateNavigationHeader(data.personalProfile);
                dataList = data;
                displayFragment(0);
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (toolbar != null) {
            getMenuInflater().inflate(R.menu.main_offline, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

    private void launchQueries() {
        ProgressDialogFragment progressDialogFragment = ProgressDialogFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(progressDialogFragment, "dialogbar").commitAllowingStateLoss();
        new GlobalAsync(listener, progressDialogFragment, steamID, this, false).execute();
    }

    private void handleErrorQueries(boolean isConnected) {
        TextView messageTextView = (TextView) findViewById(R.id.refereshMessage);
        LinearLayout refreshButton = (LinearLayout) findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(getIntent());
            }
        });
        if (!isConnected) {
            messageTextView.setText("No Internet Available, please turn it on to view stats");
        }
    }

    private void generateNavigationHeader(final Profile userProfile) {
        ImageView imageProfile = (ImageView) findViewById(R.id.imageProfile);
        TextView textProfileName = (TextView) findViewById(R.id.nameProfile);
        TextView textProfileLink = (TextView) findViewById(R.id.linkProfile);
        if (textProfileName != null) {
            textProfileName.setText(userProfile.userName);
            String html = "<a href=\"" + userProfile.profileURL + "\"><u>View Profile</u></a>";
            textProfileLink.setText(Html.fromHtml(html));
            textProfileLink.setMovementMethod(LinkMovementMethod.getInstance());
            textProfileLink.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(userProfile.profileURL));
                    startActivity(intent);
                }
            });
            Picasso.with(getApplicationContext()).load(userProfile.profileAvatarURL).into(imageProfile);
        }
    }

    private void setupToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_launcher);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupNavigation() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                switch (menuItem.getItemId()) {
                    case R.id.drawer_home:
                        displayFragment(0);
                        return true;
                    case R.id.drawer_friends:
                        displayFragment(1);
                        return true;
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
                fragment = MainFragment.InstanceOf(dataList.stats, dataList.listOfAchievements,
                        dataList.personalProfile);
                break;
            case 1:
                fragment = FriendsFragment.InstanceOf(dataList.listOfFriends);
                break;
            case 2:
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

    private void launchSupportLink() {
        startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.twitchalerts.com/donate/ismailzd")));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferenceModel prefModel = new SharedPreferenceModel(getApplicationContext());
        steamID = prefModel.loadSharedPreferenceUserID();
        launchQueries();
    }
}
