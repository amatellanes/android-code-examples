package com.amatellanes.android.examples;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * 
 * @author amatellanes
 * 
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main);

		WebView myWebView = (WebView) this.findViewById(R.id.webView);

		// Bind a new interface between your JavaScript and Android code
		myWebView.addJavascriptInterface(new WebAppInterface(this), "Android");

		// Enable JavaScript
		WebSettings webSettings = myWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);

		// Load HTML page
		myWebView.loadUrl("file:///android_asset/example.html");

	}

}
