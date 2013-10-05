package com.amatellanes.android;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class OneActionButtonDialog extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setTitle("Information")
				.setIcon(
						getResources().getDrawable(
								android.R.drawable.ic_dialog_info))
				.setMessage("Message saved as draft.")
				.setNeutralButton(android.R.string.ok, new OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub

					}
				});

		// Create the AlertDialog object and return it
		return builder.create();
	}
}