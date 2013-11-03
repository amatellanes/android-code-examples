package com.amatellanes.android;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.inject.Inject;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActivity {

	@InjectView(R.id.et_name) private EditText etName;
	@InjectView(R.id.img_user) private ImageView imgUser;
	@InjectView(R.id.text_name) private TextView textName;

	@InjectResource(R.string.hello) private String hello;
	@InjectResource(R.drawable.ic_user) private Drawable image;

	@Inject private Vibrator vibrator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public void sayHello(View view) {

		vibrator.vibrate(500);// Vibrate for 500 milliseconds

		String name = etName.getText().toString();
		textName.setText(String.format(hello, name));

		imgUser.setImageDrawable(image);
	}
}
