package com.csgo.iz.compare;

import java.util.ArrayList;

import com.csgo.iz.R;
import com.csgo.iz.modal.Model;
import com.csgo.iz.modal.Utility;
import com.csgo.iz.modal.bean.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CompareFragment extends DialogFragment implements OnClickListener {
	private Context context;
	private CustomCheckableFriendsAdapter adapter;
	private static String TAG_CSGO_NUMBERS_COMPARE = "com.csgo.iz.compare";
	private static String TAG_CSGO_NUMBERS = "com.csgo.iz.listoffriends";
	public static String TAG_CSGO_NUMBERS_COMPAREACTIVITY = "com.csgo.iz.compareactivity";
	public static String TAG_CSGO_NUMBERS_COMPARE_ITEMS = "com.csgo.iz.compareactivityitems";
	private String[] list_limit = { "1/4", "2/4", "3/4", "4/4" };
	private ListView listView;
	private RelativeLayout titleLayout;
	private ArrayList<Profile> listOfFriends;
	private ArrayList<Profile> userList;
	private FrameLayout confirmButton, cancelButton;
	private EditText searchEditText;
	private TextView friendsCount;

	public static CompareFragment InstanceOf(ArrayList<Profile> friendList,ArrayList<Profile> userList) {
		CompareFragment fragment = new CompareFragment();
		Bundle bundle = new Bundle();
		bundle.putSerializable(TAG_CSGO_NUMBERS, friendList);
		bundle.putSerializable(TAG_CSGO_NUMBERS_COMPARE, userList);
		fragment.setArguments(bundle);
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
		View compareView = inflater.inflate(R.layout.compare_fragment, container, false);
		context = getActivity().getApplicationContext();
		listOfFriends = (ArrayList<Profile>) getArguments().getSerializable(TAG_CSGO_NUMBERS);
		userList = (ArrayList<Profile>) getArguments().getSerializable(TAG_CSGO_NUMBERS_COMPARE);
		initWidgets(compareView);

		adapter = new CustomCheckableFriendsAdapter(getActivity(), listOfFriends);
		listView.setAdapter(adapter);

		searchEditText.addTextChangedListener(textFilter);
		listView.setOnItemClickListener(new ListViewListener());
		confirmButton.setOnClickListener(this);
		cancelButton.setOnClickListener(this);
		Utility.setFontForView((ViewGroup) compareView, context);
		// getDialog().setCanceledOnTouchOutside(true);
		return compareView;
	}

	private void initWidgets(View compareView) {
		searchEditText = (EditText) compareView.findViewById(R.id.searchEditText);
		listView = (ListView) compareView.findViewById(R.id.listOfFriendsListView);
		listView.setEmptyView(compareView.findViewById(android.R.id.empty));
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		confirmButton = (FrameLayout) compareView.findViewById(R.id.confirmButton);
		cancelButton = (FrameLayout) compareView.findViewById(R.id.cancelButton);
		friendsCount = (TextView) compareView.findViewById(R.id.friendCountTextView);

	}

	private ArrayList<Profile> getSelectedItems() {
		ArrayList<Profile> codeCountrylist = new ArrayList<Profile>();
		SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
		int checkedItemSize = checkedItems.size();
		for (int i = 0; i < checkedItemSize; i++) {
			int position = checkedItems.keyAt(i);
			if (checkedItems.valueAt(i)) {
				codeCountrylist.add((Profile) adapter.getItem(position));
			}
		}
		return codeCountrylist;
	}

	private class ListViewListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			CustomCheckableFriendsAdapter adapter = (CustomCheckableFriendsAdapter) listView.getAdapter();
			boolean selection = listView.isItemChecked(position);
			int listViewCheckSize = listView.getCheckedItemCount();
			if (selection) {
				updateLimitCount(selection, position);
			} else {
				updateLimitCount(selection, position);
			}
			// getDialog().dismiss();
		}

	}

	public boolean isLimitMet() {
		int listViewCheckSize = listView.getCheckedItemCount()+1;
		Log.v("updateLimitCount", "Items Selected:  "+listViewCheckSize);
		if (listViewCheckSize > list_limit.length) {
			return true;
		} else {
			return false;
		}
	}

	private void updateLimitCount(boolean isChecked, int position) {
		String textLimit = friendsCount.getText().toString().replaceAll("\\s+", "");
		Log.v("updateLimitCount", "Is Limit Met: "+isLimitMet());
		if (isLimitMet()) {
			listView.setItemChecked(position, false);
		} else {
			if (isChecked) {
				for (int i = 0; i < list_limit.length; i++) {
					if (textLimit.equals(list_limit[i]) && !textLimit.equals(list_limit[list_limit.length - 1])) {
						friendsCount.setText(list_limit[i + 1]);
						break;
					} else if (textLimit.equals(list_limit[list_limit.length - 1])) {
						Toast.makeText(context, "You have exceeded the limit", Toast.LENGTH_SHORT).show();
						break;
					}
				}
			} else {
				for (int i = 0; i < list_limit.length; i++) {
					if (textLimit.equals(list_limit[i]) && !textLimit.equals(list_limit[0])) {
						friendsCount.setText(list_limit[i - 1]);
					}
				}
			}
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
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.confirmButton) {
			ArrayList<Profile> listOfSelectedFriends = getSelectedItems();
			int size = listOfSelectedFriends.size();
			if (size != 0) {
				listOfSelectedFriends.addAll(0, userList);
				Intent intent = new Intent(getActivity().getApplicationContext(), CompareActivity.class);
				Model.setToNullCompare();
				ArrayList<String> official_list = generateProfiles(listOfSelectedFriends);
				intent.putExtra(TAG_CSGO_NUMBERS_COMPAREACTIVITY, official_list);
				startActivity(intent);
			}

		}
		if (v.getId() == R.id.cancel_action) {
			this.getDialog().dismiss();
		}
	}
	
	private ArrayList<String> generateProfiles(ArrayList<Profile> listOfProfiles){
		ArrayList<String> list = new ArrayList<String>();
		
		for(Profile user:listOfProfiles){
			Log.v("updateLimitCount", "List Of Profiles "+user.getUserID());
			list.add(user.getUserID());
		}
		return list;
	}
}
