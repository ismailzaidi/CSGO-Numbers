package com.csgo.iz.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.csgo.iz.R;
import com.csgo.iz.modal.Utility;
import com.csgo.iz.modal.http.threads.GlobalAsync;


/**
 * Created by Yusuf on 08/12/2015.
 */
public class ProgressDialogFragment extends DialogFragment implements GlobalAsync.UpdateUserProgress {
    private ProgressBar progressBar;
    private TextView progressTextView;
    public static ProgressDialogFragment newInstance() {
        ProgressDialogFragment fragment = new ProgressDialogFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);
        View aboutView = inflater.inflate(R.layout.progressbardialog, container, false);
        Log.v("com.iz.", "OnCreateView Called");
        progressBar = (ProgressBar) aboutView.findViewById(R.id.progressBar);
        progressTextView = (TextView) aboutView.findViewById(R.id.progressTextView);
        progressBar.setMax(100);

        Utility.setFontForView((ViewGroup) aboutView);
        return aboutView;
    }

    public void show() {
        this.getDialog().show();
    }

    @Override
    public void updateProgress(int value) {
        Log.v("progress dialog", "Value: " + value);
        progressBar.setProgress(value);
        progressTextView.setText(String.valueOf(value)+"%");
        if(value==-1){
            Toast.makeText(getActivity().getApplicationContext(),"Your profile is private", Toast.LENGTH_LONG).show();
        }
        if (value == 100) {
            this.dismiss();
        }
    }
}

