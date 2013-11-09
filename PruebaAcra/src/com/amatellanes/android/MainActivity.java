package com.amatellanes.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@SuppressWarnings("null")
	public void reportError(View view) {
		// This code will throw an exception
		TextView textView = null;
		textView.setText("I Am The Danger");
	}
}
