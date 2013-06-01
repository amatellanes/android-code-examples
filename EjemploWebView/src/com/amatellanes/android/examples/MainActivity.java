package com.amatellanes.android.examples;

import android.app.Activity;
import android.os.Bundle;
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
		myWebView.loadUrl("http://amatellanes.wordpress.com/");

	}

}
