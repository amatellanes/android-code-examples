package com.amatellanes.android;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.Toast;

public class CustomDialog extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		// Get the layout inflater
		LayoutInflater inflater = getActivity().getLayoutInflater();

		// Inflate and set the layout for the dialog
		// Pass null as the parent view because its going in the dialog layout
		builder.setView(inflater.inflate(R.layout.dialog_signin, null))
				// Add action buttons
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Toast.makeText(getActivity(),
										android.R.string.yes,
										Toast.LENGTH_SHORT).show();
							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						Toast.makeText(getActivity(), android.R.string.no,
								Toast.LENGTH_SHORT).show();
					}
				});

		// Create the AlertDialog object and return it
		return builder.create();
	}

	// /**
	// * The system calls this to get the DialogFragment's layout, regardless of
	// * whether it's being displayed as a dialog or an embedded fragment.
	// */
	// @Override
	// public View onCreateView(LayoutInflater inflater, ViewGroup container,
	// Bundle savedInstanceState) {
	// // Inflate the layout to use as dialog or embedded fragment
	// return inflater.inflate(R.layout.dialog_signin, container, false);
	// }
	//
	// /** The system calls this only when creating the layout in a dialog. */
	// @Override
	// public Dialog onCreateDialog(Bundle savedInstanceState) {
	// // The only reason you might override this method when using
	// // onCreateView() is to modify any dialog characteristics. For example,
	// // the dialog includes a title by default, but your custom layout might
	// // not need it. So here you can remove the dialog title, but you must
	// // call the superclass to get the Dialog.
	// Dialog dialog = super.onCreateDialog(savedInstanceState);
	// dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	// return dialog;
	// }

}
