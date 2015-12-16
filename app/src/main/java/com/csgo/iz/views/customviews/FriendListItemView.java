package com.csgo.iz.views.customviews;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csgo.iz.R;
import com.csgo.iz.modal.bean.Profile;
import com.squareup.picasso.Picasso;

public class FriendListItemView extends LinearLayout {

    private ImageView userAvatar;
    private TextView userName;
    private TextView userLastLogin;
    private TextView userProfileState;
    private int privateColour;
    private int publicColour;

    public FriendListItemView(Context context) {
        this(context, null);
    }

    public FriendListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        privateColour = ContextCompat.getColor(getContext(), R.color.drawingcolor);
        publicColour = ContextCompat.getColor(getContext(), R.color.primarycolour);
    }

    public void setProfile(Profile profile) {
        userName.setText(profile.userName);
        userLastLogin.setText(profile.lastLogin);
        Picasso.with(getContext()).load(profile.profileAvatarURL).into(userAvatar);

        if (profile.isPrivate) {
            userProfileState.setText("Private Profile");
            userName.setTextColor(privateColour);
            userLastLogin.setTextColor(privateColour);
        } else {
            userProfileState.setText("");
            userName.setTextColor(publicColour);
            userLastLogin.setTextColor(publicColour);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        userAvatar = (ImageView) findViewById(R.id.friend_avatar);
        userName = (TextView) findViewById(R.id.friend_name);
        userLastLogin = (TextView) findViewById(R.id.friend_login);
        userProfileState = (TextView) findViewById(R.id.friend_status);
    }
}
