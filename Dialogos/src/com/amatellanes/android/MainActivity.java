package com.amatellanes.android;

import java.net.MalformedURLException;
import java.net.URL;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements
		TwoActionButtonsDialog.DialogListener {
	// If you're using Android API 11 o lower, your Activity must extend
	// FragmentActivity

	private static final String TAG = "dialog";
	private static final int SIZE_DOWNLOAD = 255;

	private boolean mIsLargeLayout;

	private MyTask task;

	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mIsLargeLayout = getResources().getBoolean(R.bool.large_layout);

	}

	public void showDialog() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		CustomDialog newFragment = new CustomDialog();

		if (mIsLargeLayout) {
			// The device is using a large layout, so show the fragment as a
			// dialog
			newFragment.show(fragmentManager, TAG);
		} else {
			// The device is smaller, so show the fragment fullscreen
			FragmentTransaction transaction = fragmentManager
					.beginTransaction();
			// For a little polish, specify a transition animation
			transaction
					.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			// To make it fullscreen, use the 'content' root view as the
			// container for the fragment, which is always the root view for the
			// activity
			transaction.add(android.R.id.content, newFragment)
					.addToBackStack(null).commit();
		}
	}

	public void showNoActionButton(View v) {
		DialogFragment dialog = new NoActionButtonsDialog();
		dialog.show(getSupportFragmentManager(), TAG);
	}

	public void showOneActionButton(View v) {
		DialogFragment dialog = new OneActionButtonDialog();
		dialog.show(getSupportFragmentManager(), TAG);
	}

	public void showTwoActionButton(View v) {
		DialogFragment dialog = new TwoActionButtonsDialog();
		dialog.show(getSupportFragmentManager(), TAG);
	}

	public void showThreeActionButton(View v) {
		DialogFragment dialog = new ThreeActionButtonsDialog();
		dialog.show(getSupportFragmentManager(), TAG);
	}

	public void showSimpleList(View v) {
		DialogFragment dialog = new SimpleListDialog();
		dialog.show(getSupportFragmentManager(), TAG);
	}

	public void showSingleChoiceList(View v) {
		DialogFragment dialog = new SingleChoiceListDialog();
		dialog.show(getSupportFragmentManager(), TAG);
	}

	public void showMultipleChoiceList(View v) {
		DialogFragment dialog = new MultipleChoiceListDialog();
		dialog.show(getSupportFragmentManager(), TAG);
	}

	public void showCustomDialog(View v) {
		DialogFragment dialog = new CustomDialog();
		dialog.show(getSupportFragmentManager(), TAG);
	}

	public void showTimePickerDialog(View v) {
		DialogFragment dialog = new TimePickerFragment();
		dialog.show(getSupportFragmentManager(), TAG);
	}

	public void showDatePickerDialog(View v) {
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getSupportFragmentManager(), TAG);
	}

	public void showProgressBar(View v) {

		progressDialog = new ProgressDialog(this);
		progressDialog.setMessage("Downloading...");
		progressDialog.setTitle("Progress");
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setCancelable(false);

		progressDialog.setButton(ProgressDialog.BUTTON_NEUTRAL, "Cancel",
				new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						task.cancel(true);
					}
				});

		try {
			URL url = new URL(
					"http://developer.android.com/downloads/design/Android_Design_Icons_20120814.zip");
			task = new MyTask();
			task.execute(url);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	// The dialog fragment receives a reference to this Activity through the
	// Fragment.onAttach() callback, which it uses to call the following methods
	// defined by the NoticeDialogFragment.NoticeDialogListener interface
	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		// User touched the dialog's positive button
		Toast.makeText(getApplicationContext(), android.R.string.yes,
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		// User touched the dialog's negative button
		Toast.makeText(getApplicationContext(), android.R.string.no,
				Toast.LENGTH_SHORT).show();
	}

	private class MyTask extends AsyncTask<URL, Float, Integer> {

		@Override
		protected void onPreExecute() {
			progressDialog.setProgress(0);
			progressDialog.setMax(100);
			progressDialog.show();
		}

		@Override
		protected Integer doInBackground(URL... params) {
			for (int i = 0; i < SIZE_DOWNLOAD; i++) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				publishProgress(i / (float) SIZE_DOWNLOAD);

				if (isCancelled())
					break;
			}

			return SIZE_DOWNLOAD;
		}

		protected void onProgressUpdate(Float... progress) {
			int p = Math.round(100 * progress[0]);
			progressDialog.setProgress(p);
		}

		protected void onPostExecute(Integer result) {
			progressDialog.dismiss();
		}
	}

}
