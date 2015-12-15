package com.csgo.iz.Fragments;

import com.csgo.iz.R;
import com.csgo.iz.modal.Utility;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FAQFragment extends DialogFragment {
	private String credit1 = "<a href=\"http://twowordbird.com/\">twowordbird</a>";
	private String credit2 = "<a href=\"http://ismailzd.co.uk/\">developer</a>";
	private String credit3 = "<a href=\"http://steamcommunity.com/sharedfiles/filedetails/?id=419404847\">Recoil Master (CSGO Map)</a>";
	private TextView creditOne, creditTwo, creditThree;

	public static FAQFragment newInstance() {
		FAQFragment fragment = new FAQFragment();
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
		View aboutView = inflater.inflate(R.layout.faq_fragment, container, false);
		
		Utility.setFontForView((ViewGroup) aboutView, getActivity().getApplicationContext());
		return aboutView;
	}

	private void setUpLinkForCredit() {
		creditOne.setText(Html.fromHtml(credit1));
		creditOne.setMovementMethod(LinkMovementMethod.getInstance());
		creditTwo.setText(Html.fromHtml(credit2));
		creditTwo.setMovementMethod(LinkMovementMethod.getInstance());
		creditThree.setText(Html.fromHtml(credit3));
		creditThree.setMovementMethod(LinkMovementMethod.getInstance());
	}
}
