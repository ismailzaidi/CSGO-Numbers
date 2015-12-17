package com.csgo.iz.modal;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.csgo.iz.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Utility {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        return netInfo != null && netInfo.isConnectedOrConnecting();

    }

    public static void showSnackBar(Context context, String message, CoordinatorLayout layout) {
        Snackbar snackbar = Snackbar.make(layout, message, Snackbar.LENGTH_LONG).setAction("Got it",
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub

                    }
                });
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(ContextCompat.getColor(context, R.color.primarycolour));
        snackbar.setActionTextColor(ContextCompat.getColor(context, R.color.whitetext));
        snackbar.show();

    }

    public static ProgressDialog generateProgressBar(Context context, String title, String message) {
        ProgressDialog dialog = ProgressDialog.show(context, title, message);
        return dialog;

    }

    public static void setDialogParams(Dialog dialog, Context context) {
        Dialog defaultDialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(defaultDialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(layoutParams);
    }

    public static void setFontForView(ViewGroup viewChildren) {
        Typeface font = Typeface.createFromAsset(viewChildren.getContext().getAssets(), "Roboto-Light.ttf");
        View child;
        for (int i = 0; i < viewChildren.getChildCount(); i++) {
            child = viewChildren.getChildAt(i);
            if (child instanceof ViewGroup) {
                setFontForView((ViewGroup) child);

            } else if (child instanceof TextView) {

                TextView textView = (TextView) child;
//                if (!textView.getTypeface().isBold()) {
//                    textView.setTypeface(font);
//                }

            } else if (child instanceof Button) {
                ((Button) child).setTypeface(font);

            } else if (child instanceof EditText) {
                ((EditText) child).setTypeface(font);

            }

        }
    }

    public static void setFontBoldForView(ViewGroup viewChildren, Context context) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), "Roboto-Bold.ttf");
        View child;
        for (int i = 0; i < viewChildren.getChildCount(); i++) {
            child = viewChildren.getChildAt(i);
            if (child instanceof ViewGroup) {
                setFontForView((ViewGroup) child);

            } else if (child instanceof TextView) {
                ((TextView) child).setTypeface(font);
                ;
            } else if (child instanceof Button) {
                ((Button) child).setTypeface(font);
                ;
            } else if (child instanceof EditText) {
                ((EditText) child).setTypeface(font);
                ;
            }

        }
    }

    public static long convertToHours(String value) {
        return TimeUnit.HOURS.convert(Long.parseLong(value), TimeUnit.MINUTES);
    }

    public static String generateTimeDifference(String login) {
        Date userDate = null;
        Date mobileDate = new Date();
        Calendar calender = Calendar.getInstance();
        long mobileTime = new Date().getTime();
        calender.setTimeInMillis(mobileTime);
        SimpleDateFormat formatMobileTime = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
        String dateStop = formatMobileTime.format(calender.getTime()).toString();
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
        try {
            userDate = format.parse(login);
            mobileDate = format.parse(dateStop);

            //in milliseconds
            long diff = (mobileDate.getTime() - userDate.getTime()) / 1000;
            Log.v("TIME_TEST", "Time Difference: " + diff);
            int days = (int) Math.floor((diff / 86400));
            int hours = (int) Math.floor(((diff - days * 86400)) / (60 * 60));
            int minutes = (int) Math.floor((diff - (days * 86400 + hours * 3600)) / 60);
            if (diff >= 86400) {
                login = (days == 1) ? days + " day ago" : days + " days ago";
            }
            if (diff < 86400 && diff > 3600) {// Hours
                login = (hours == 1) ? hours + " hour ago" : hours + " hours ago";
            }
            if (diff < 3600) {
                login = (minutes == 1) ? minutes + " minute ago" : minutes + " minutes ago";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return login;
    }

    public String[] getAchievementData(Context context, int position) {
        AssetManager assetManager = context.getAssets();
        String data = "";
        ArrayList<String> firstColumn = new ArrayList<String>();
        ArrayList<String> secondColumn = new ArrayList<String>();
        try {
            InputStream inputStream = assetManager.open("achievment_list.csv");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                String[] column = line.split(",");
                data += "<item>" + column[1] + "</item>";
            }
            bufferedReader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.v("com.csgo.utility", data);

        return null;
    }

    public boolean checkIsSteamID(String value) {
        String pattern = "(\\d+){17}";
        if (value.matches(pattern)) {
            return true;
        }
        return false;
    }

    public String getFormatSorter(int num) {
        DecimalFormat format = new DecimalFormat("####,###,###.##");
        return format.format(num);
    }

    public String getFormatSorter(double num) {
        DecimalFormat format = new DecimalFormat("####,###,###.##");
        return format.format(num);
    }


}
