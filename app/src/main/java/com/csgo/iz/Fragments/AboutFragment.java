package com.csgo.iz.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csgo.iz.R;
import com.csgo.iz.modal.Utility;

public class AboutFragment extends DialogFragment implements View.OnClickListener {
    private String credit1 = "<a href=\"https://github.com/ElFeesho/\">ElFeesho</a>";
    private String credit2 = "<a href=\"https://www.youtube.com/user/stabilishero/\">StabilisHero</a>";
    private String credit3 = "https://github.com/ismailzd/CSGO-Numbers";
    private String credit4 = "http://twitch.tv/ismailzd";
    private String fragdeluxeCredit = "https://www.fragdeluxe.com/#servers";
    private TextView creditOneTextView, creditTwoTextView;
    private FrameLayout developerButton, sourceCodeButton,twitchButton;
    private LinearLayout fragDeluxeButton;

    public static AboutFragment newInstance() {
        AboutFragment fragment = new AboutFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View aboutView = inflater.inflate(R.layout.about_fragment, container, false);
        creditOneTextView = (TextView) aboutView.findViewById(R.id.credit1);
        creditTwoTextView = (TextView) aboutView.findViewById(R.id.credit2);
        developerButton = (FrameLayout) aboutView.findViewById(R.id.developerButton);
        sourceCodeButton = (FrameLayout) aboutView.findViewById(R.id.sourceCodeButton);
        twitchButton = (FrameLayout) aboutView.findViewById(R.id.twitchIcon);
        fragDeluxeButton = (LinearLayout) aboutView.findViewById(R.id.launchFragButton);
//        creditTwoTextView.setOnClickListener(this);
//        creditOneTextView.setOnClickListener(this);
        developerButton.setOnClickListener(this);
        sourceCodeButton.setOnClickListener(this);
        fragDeluxeButton.setOnClickListener(this);
        twitchButton.setOnClickListener(this);
        setUpLinkForCredit();
        Utility.setFontForView((ViewGroup) aboutView);
        return aboutView;
    }

    @Override
    public void onClick(View event) {
        if (event.getId() == R.id.credit1) {
            launchIntent(credit1);
        }
        if (event.getId() == R.id.credit2) {
            launchIntent(credit2);
        }
        if (event.getId() == R.id.developerButton) {
            launchIntent(credit3);
        }
        if (event.getId() == R.id.sourceCodeButton) {
            launchIntent(credit3);
        }
        if (event.getId() == R.id.launchFragButton) {
            launchIntent(fragdeluxeCredit);
        }
        if(event.getId()==R.id.twitchIcon){
            launchIntent(credit4);
        }
    }
    private void launchIntent(String url){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
    private void setUpLinkForCredit() {
        creditOneTextView.setText(Html.fromHtml(credit1));
        creditOneTextView.setMovementMethod(LinkMovementMethod.getInstance());
        creditTwoTextView.setText(Html.fromHtml(credit2));
        creditTwoTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
