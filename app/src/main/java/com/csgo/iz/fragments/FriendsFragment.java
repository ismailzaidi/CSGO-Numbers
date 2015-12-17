package com.csgo.iz.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

import com.csgo.iz.R;
import com.csgo.iz.adapters.listadapters.FriendsAdapter;
import com.csgo.iz.modal.Utility;
import com.csgo.iz.modal.bean.GlobalData;
import com.csgo.iz.modal.bean.Profile;
import com.csgo.iz.modal.http.threads.GlobalAsync;
import com.csgo.iz.modal.http.threads.GlobalAsync.UserGlobalCallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FriendsFragment extends Fragment implements OnItemClickListener {

    private static String TAG_CSGO_NUMBERS = "com.csgo.iz.Friends";
    private ListView friendsListView;
    private FriendsAdapter adapter;
    private Context context;
    private CoordinatorLayout coordinatorLayout;
    private UserGlobalCallback listener = new UserGlobalCallback() {
        @Override
        public void UserGlobalIsAvailable(GlobalData data) {
            GlobalData dataList = data;
            if (dataList != null) {
                MainFragment mainFragment = MainFragment.InstanceOf(dataList.stats, dataList.listOfAchievements,
                        dataList.personalProfile);
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.frame_content, mainFragment, "com.csgo.iz.friendsfragment").addToBackStack(null).commit();
            } else {
                Utility.showSnackBar(context, "User doesn't own CSGO", coordinatorLayout);
            }
        }
    };
    private TextWatcher textFilter = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            adapter.getFilter().filter(s.toString());
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    public static FriendsFragment InstanceOf(List<Profile> list) {
        FriendsFragment fragment = new FriendsFragment();
        Bundle bundle = new Bundle();
        Collections.sort(list);
        bundle.putParcelableArrayList(TAG_CSGO_NUMBERS, new ArrayList<Parcelable>(list));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = getActivity().getApplicationContext();
        View rootView = inflater.inflate(R.layout.friends_fragment, container, false);
        friendsListView = (ListView) rootView.findViewById(R.id.listOfFriendsListView);
        coordinatorLayout = (CoordinatorLayout) rootView.findViewById(R.id.snackbarCoordinatorLayout);
        friendsListView.setEmptyView(rootView.findViewById(android.R.id.empty));

        List<Profile> listOfFriends = getArguments().getParcelableArrayList(TAG_CSGO_NUMBERS);

        EditText searchUserEditText = (EditText) rootView.findViewById(R.id.searchEditText);
        searchUserEditText.addTextChangedListener(textFilter);
        setupListView(listOfFriends);

        friendsListView.setOnItemClickListener(this);
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Profile profile = (Profile) adapter.getItem(position);
        boolean isConnected = Utility.isNetworkAvailable(context);
        if (isConnected) {
            launchQueries(profile.userID);
        } else {
            Utility.showSnackBar(context, "No Internet Connection", coordinatorLayout);
        }
    }

    private void launchQueries(String steamID) {
        ProgressDialogFragment fragment = ProgressDialogFragment.newInstance();
        String fragmentTag = "ProgressDialog";
        fragment.show(getChildFragmentManager(), fragmentTag);
        GlobalAsync profileThread = new GlobalAsync(listener, fragment, steamID, getActivity(), true);
        profileThread.execute();
    }

    private void setupListView(List<Profile> listOfFriends) {
        adapter = new FriendsAdapter(listOfFriends);
        friendsListView.setAdapter(adapter);
    }
}
