package com.amatellanes.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class TwoActionButtonsDialog extends DialogFragment {

	private DialogListener listener;

	public interface DialogListener {
		public void onDialogPositiveClick(DialogFragment dialog);

		public void onDialogNegativeClick(DialogFragment dialog);
	}

	// Override the Fragment.onAttach() method to instantiate the
	// NoticeDialogListener
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// Verify that the host activity implements the callback interface
		try {
			// Instantiate the NoticeDialogListener so we can send events to the
			// host
			listener = (DialogListener) activity;
		} catch (ClassCastException e) {
			// The activity doesn't implement the interface, throw exception
			throw new ClassCastException(activity.toString()
					+ " must implement DialogListener");
		}
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setTitle("Information")
				.setIcon(
						getResources().getDrawable(
								android.R.drawable.ic_dialog_info))
				.setMessage("Are you sure?")
				.setPositiveButton(android.R.string.yes,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								listener.onDialogPositiveClick(TwoActionButtonsDialog.this);
							}
						})
				.setNegativeButton(android.R.string.no,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								listener.onDialogNegativeClick(TwoActionButtonsDialog.this);
							}
						});

		// Create the AlertDialog object and return it
		return builder.create();
	}

	// @Override
	// public Dialog onCreateDialog(Bundle savedInstanceState) {
	//
	// // Use the Builder class for convenient dialog construction
	// AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	//
	// builder.setMessage("Are you sure?")
	// .setPositiveButton(android.R.string.yes,
	// new DialogInterface.OnClickListener() {
	// public void onClick(DialogInterface dialog, int id) {
	// Toast.makeText(getActivity(), "Yes",
	// Toast.LENGTH_SHORT).show();
	// }
	// })
	// .setNegativeButton(android.R.string.no,
	// new DialogInterface.OnClickListener() {
	// public void onClick(DialogInterface dialog, int id) {
	// Toast.makeText(getActivity(), "No",
	// Toast.LENGTH_SHORT).show();
	// }
	// });
	//
	// // Create the AlertDialog object and return it
	// return builder.create();
	// }
}
