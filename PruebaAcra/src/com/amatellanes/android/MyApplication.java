package com.amatellanes.android;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;

import android.app.Application;

@ReportsCrashes(
		formKey = "", 
		formUri = "https://[your-username].cloudant.com/acra-myapp/_design/acra-storage/_update/report", 
		reportType=org.acra.sender.HttpSender.Type.JSON,
		httpMethod=org.acra.sender.HttpSender.Method.PUT,
		formUriBasicAuthLogin = "[your-key]", 
		formUriBasicAuthPassword = "[your-password]")
public class MyApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();

		// The following line triggers the initialization of ACRA
		ACRA.init(this);
	}
}