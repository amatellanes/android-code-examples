package com.amatellanes.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public void onClick(View v) {

		Toast toast;

		Context context = getApplicationContext();
		CharSequence text = "Hello toast!";
		int duration = Toast.LENGTH_SHORT;

		switch (v.getId()) {

		case R.id.btnToastBasic:
			toast = Toast.makeText(context, text, duration);
			toast.show();
			break;

		case R.id.btnToastGravity:
			toast = Toast.makeText(context, text, duration);
			int offsetX = 50;
			int offsetY = 25;
			toast.setGravity(Gravity.RIGHT | Gravity.TOP, offsetX, offsetY);
			toast.show();
			break;

		case R.id.btnToastCustom:
			LayoutInflater inflater = getLayoutInflater();
			View layout = inflater.inflate(R.layout.custom_toast,
					(ViewGroup) findViewById(R.id.toast_layout_root));

			TextView textToast = (TextView) layout
					.findViewById(R.id.text_toast);
			textToast.setText(text);

			toast = new Toast(context);
			toast.setDuration(duration);
			toast.setView(layout);
			toast.show();
			break;

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
