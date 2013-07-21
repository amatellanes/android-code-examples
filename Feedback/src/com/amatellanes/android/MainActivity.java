package com.amatellanes.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.ibFeedback:

			// Call the method which will send a feedback.
			this.sendFeedback();

			break;

		default:
			break;
		}

	}

	/**
	 * Report an issue, suggest a feature, or send feedback.
	 */
	private void sendFeedback() {

		// Checks if the device is connected to the Internet.
		if (isDeviceConnected()) {

			// Set the action to be performed
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);

			// E-mail addresses that should be delivered to.
			sendIntent.putExtra(Intent.EXTRA_EMAIL,
					new String[] { "feedback@your-company.com" });

			// Set the subject line of a message
			sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Send app feedback");

			// Set the data type of the message
			sendIntent.setType("plain/text");
			startActivity(Intent.createChooser(sendIntent,
					"Report an issue, suggest a feature, or send feedback"));

		} else

			Toast.makeText(getApplicationContext(),
					"Your device is not connected to the Internet",
					Toast.LENGTH_LONG).show();
	}

	/**
	 * Checks if the device is connected to the Internet.
	 * 
	 * @param context
	 *            application context
	 * @return if the device is connected true, otherwise false.
	 */
	private boolean isDeviceConnected() {

		final ConnectivityManager connectManager = (ConnectivityManager) getApplicationContext()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		return (connectManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
				.getState() == NetworkInfo.State.CONNECTED || connectManager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED);
	}

}
