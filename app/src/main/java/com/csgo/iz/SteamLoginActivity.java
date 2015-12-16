package com.csgo.iz;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.csgo.iz.modal.APICall;
import com.csgo.iz.modal.SharedPreferenceModel;
import com.csgo.iz.modal.bean.Profile;
import com.csgo.iz.modal.http.Steam64IdFromVanityUrl;

/**
 * Created by Yusuf on 15/12/2015.
 */
public class SteamLoginActivity extends AppCompatActivity {
    private WebView webView;
    private SharedPreferenceModel sharedModel;
    private boolean isLaunch = true;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_webview);
        webView = (WebView) findViewById(R.id.userAuthenticationView);
        sharedModel = new SharedPreferenceModel(getApplicationContext());
        WebSettings webSettings = webView.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url,
                                      Bitmap favicon) {
                setTitle(url);
                Uri Url = Uri.parse(url);

                if (Url.getAuthority().equals(APICall.REALM_PARAM.toLowerCase())) {
                    // That means that authentication is finished and the url contains user's id.
                    webView.stopLoading();

                    // Extracts user id.
                    Uri userAccountUrl = Uri.parse(Url.getQueryParameter("openid.identity"));
                    String userId = userAccountUrl.getLastPathSegment();
                    new Steam64IdFromVanityUrl(userId).getSteam64IDIsExistAsync(new Steam64IdFromVanityUrl.UserIDCheckerCallBack() {
                        @Override
                        public void UserIDIsExist(Profile userID) {
                            if (userID != null) {
                                Log.v("MAIN_ACTIVITY_LOGIN", "LoginActivity: " + userID.getUserID());
                                launchIntent(userID.getUserID());
                            } else {
                                Toast.makeText(getApplicationContext(),"Profile is Private Or User Doesn't Own Game . Try again !", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    });
                }

            }
        });
        String url = "https://steamcommunity.com/openid/login?" +
                "openid.claimed_id=http://specs.openid.net/auth/2.0/identifier_select&" +
                "openid.identity=http://specs.openid.net/auth/2.0/identifier_select&" +
                "openid.mode=checkid_setup&" +
                "openid.ns=http://specs.openid.net/auth/2.0&" +
                "openid.realm=https://" + APICall.REALM_PARAM + "&" +
                "openid.return_to=https://" + APICall.REALM_PARAM + "/signin/";
        webView.loadUrl(url);
    }

    public void launchIntent(String steamID) {
        Intent intent = new Intent(this, MainActivity.class);
        sharedModel.saveSharedUserID(steamID);
        intent.putExtra(LoginActivity.INTENT_KEY, steamID);
        if (isLaunch) {// Disables multiple activities from running
            isLaunch = false;
            startActivity(intent);
            finish();

        }
    }
}
