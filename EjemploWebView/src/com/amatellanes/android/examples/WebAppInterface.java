package com.amatellanes.android.examples;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.webkit.JavascriptInterface;

/**
 * 
 * @author amatellanes
 * 
 */
public class WebAppInterface {

	Context context;

	/**
	 * Instantiate the interface and set the context
	 * 
	 * @param context
	 */
	public WebAppInterface(Context context) {
		this.context = context;
	}

	/**
	 * Show a dialog from the web page.
	 * 
	 * @param message
	 *            message of the dialog
	 */
	@JavascriptInterface
	public void showDialog(String message) {

		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
		builder.setMessage(message).setNeutralButton("OK",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.dismiss();
					}
				});
		// Create the AlertDialog object and return it
		builder.create().show();
	}
}
