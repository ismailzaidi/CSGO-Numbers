package com.csgo.iz.views.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomTextHeader extends TextView {
	private Context context;
	public CustomTextHeader(Context context) {
		this(context, null, 0);
		this.context = context;
	}

	public CustomTextHeader(Context context, AttributeSet attr) {
		this(context, attr, 0);
	}

	public CustomTextHeader(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		this.context = context;
		Typeface font = Typeface.createFromAsset(context.getAssets(), "Roboto-Bold.ttf");
		setTypeface(font);
	}
}
