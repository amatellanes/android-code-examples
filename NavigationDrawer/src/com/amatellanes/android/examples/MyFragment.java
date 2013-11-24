package com.amatellanes.android.examples;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyFragment extends Fragment {

	public final static String KEY_TEXT = "key_text";

	private String mText;

	// /**
	// * Create a new instance of MyDialogFragment, providing "text" as an
	// * argument.
	// */
	// static MyFragment newInstance(String text) {
	// MyFragment f = new MyFragment();
	//
	// // Supply text input as an argument.
	// Bundle args = new Bundle();
	// args.putString(KEY_TEXT, text);
	// f.setArguments(args);
	//
	// return f;
	// }

	// @Override
	// public void onCreate(Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);
	// mText = getArguments().getString(KEY_TEXT);
	// }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mText = getArguments().getString(KEY_TEXT);

		View v = inflater.inflate(R.layout.fragment, container, false);
		TextView tv = (TextView) v.findViewById(R.id.tv_fragment);
		tv.setText(mText);

		return v;
	}
}
