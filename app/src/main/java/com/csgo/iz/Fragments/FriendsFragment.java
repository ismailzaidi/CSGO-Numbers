package com.csgo.iz.Fragments;

import java.util.ArrayList;
import java.util.List;

import com.csgo.iz.R;
import com.csgo.iz.Adapters.CustomFriendsAdapter;
import com.csgo.iz.modal.Utility;
import com.csgo.iz.modal.bean.GlobalData;
import com.csgo.iz.modal.bean.Profile;
import com.csgo.iz.modal.http.threads.GlobalAsync;
import com.csgo.iz.modal.http.threads.GlobalAsync.UserGlobalCallback;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;


public class FriendsFragment extends Fragment implements OnItemClickListener {
    private String fragmentTag = "ProgressDialog";
    private ListView friendsListView;
    private static String TAG_CSGO_NUMBERS = "com.csgo.iz.Friends";
    private static final String TAG_ISFRIENDS = "com.csgo.iz.Friends";
    private CustomFriendsAdapter adapter;
    private Context context;
    private EditText searchUserEditText;
    private GlobalData dataList;
    private String steamID;
    private CoordinatorLayout coordinatorLayout;

    public static FriendsFragment InstanceOf(ArrayList<Profile> list) {
        FriendsFragment fragment = new FriendsFragment();
        Bundle bundle = new Bundle();
//		Collections.sort(list);
        bundle.putSerializable(TAG_CSGO_NUMBERS, list);
        fragment.setArguments(bundle);
        return fragment;
    }

    private UserGlobalCallback listener = new UserGlobalCallback() {

        @Override
        public void UserGlobalIsAvailable(GlobalData data) {
            // TODO Auto-generated method stub
            dataList = data;
            if (dataList != null) {
                MainFragment mainFragment = MainFragment.InstanceOf(false, steamID, dataList.getStats(), dataList.getListOfAchievements(),
                        dataList.getPersonalProfile());
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.frame_content, mainFragment, "com.csgo.iz.friendsfragment").addToBackStack(null).commit();
            } else {
                Utility.showSnackBar(context, "User doesn't own CSGO", coordinatorLayout);
            }
        }

    };

    private void launchQueries(String steamID) {
        ProgressDialogFragment fragment = ProgressDialogFragment.newInstance();
        fragment.show(getChildFragmentManager(), fragmentTag);
        GlobalAsync profileThread = new GlobalAsync(listener, fragment, steamID, getActivity(), true);
        profileThread.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = getActivity().getApplicationContext();
        View rootView = inflater.inflate(R.layout.friends_fragment, container, false);
        friendsListView = (ListView) rootView.findViewById(R.id.listOfFriendsListView);
        coordinatorLayout = (CoordinatorLayout) rootView.findViewById(R.id.snackbarCoordinatorLayout);
        friendsListView.setEmptyView(rootView.findViewById(android.R.id.empty));

        List<Profile> listOfFriends = (List<Profile>) getArguments().getSerializable(TAG_CSGO_NUMBERS);

        searchUserEditText = (EditText) rootView.findViewById(R.id.searchEditText);
        searchUserEditText.addTextChangedListener(textFilter);
        setupListView(listOfFriends);

        friendsListView.setOnItemClickListener(this);
        return rootView;
    }

    private void setupListView(List<Profile> listOfFriends) {
        adapter = new CustomFriendsAdapter(listOfFriends);
        friendsListView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Profile profile = (Profile) adapter.getItem(position);
        steamID = profile.getUserID();
        boolean isConnected = Utility.isNetworkAvailable(context);
        if (isConnected) {
            launchQueries(profile.getUserID());
        } else {
            Utility.showSnackBar(context, "No Internet Connection", coordinatorLayout);
        }

    }

    private TextWatcher textFilter = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.v("filterTextwatcher", s.toString());
            adapter.getFilter().filter(s.toString());
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // TODO Auto-generated method stub

        }

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub

        }
    };


}
