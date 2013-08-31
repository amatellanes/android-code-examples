package com.amatellanes.android;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class MainActivity extends FragmentActivity {

	private static final long SPLASH_SCREEN_DELAY = 3000;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			// Show the splash screen at the beginning
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new SplashScreenFragment()).commit();
		}

		TimerTask task = new TimerTask() {
			@Override
			public void run() {

				// Replace the splash screen fragment to main fragment and
				// specific animation resources to run for the fragments that
				// are entering and exiting in this transaction.
				getSupportFragmentManager()
						.beginTransaction()
						.setCustomAnimations(R.anim.fragment_slide_left_enter,
								R.anim.fragment_slide_left_exit)
						.replace(R.id.container, new MainFragment()).commit();

			}
		};

		// Simulate a long loading process on application startup.
		Timer timer = new Timer();
		timer.schedule(task, SPLASH_SCREEN_DELAY);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
