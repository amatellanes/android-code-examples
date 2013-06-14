package com.amatellanes.android.examples;

import static android.widget.Toast.LENGTH_SHORT;
import static java.lang.System.currentTimeMillis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * 
 * @author amatellanes
 * 
 */
public class MainActivity extends Activity {

	private WebView myWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main);

		this.myWebView = (WebView) this.findViewById(R.id.webView);

		// myWebView.addJavascriptInterface(new WebAppInterface(this),
		// "Android");
		//
		// WebSettings webSettings = myWebView.getSettings();
		// webSettings.setJavaScriptEnabled(true);
		//
		// myWebView.loadUrl("file:///android_asset/example.html");

		// Enable JavaScript WebSettings webSettings =
		WebSettings webSettings = myWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);

		// Provide a WebViewClient for your WebView
		myWebView.setWebViewClient(new MyWebViewClient());

		myWebView.loadUrl("http://amatellanes.wordpress.com/");

	}

	@Override
	public void onBackPressed() {

		// Check if there's history
		if (this.myWebView.canGoBack())
			this.myWebView.goBack();
		else
			super.onBackPressed();

	}

	/**
	 * 
	 * @author amatellanes
	 * 
	 */
	private class MyWebViewClient extends WebViewClient {

		private long loadTime; // Web page loading time

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {

			if (Uri.parse(url).getHost().equals("amatellanes.wordpress.com")) {
				// This is my web site, so do not override; let my WebView load
				// the page
				return false;
			}

			// Otherwise, the link is not for a page on my site, so launch
			// another Activity that handles URLs
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
			startActivity(intent);
			return true;
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);

			// Save start time
			this.loadTime = currentTimeMillis();

			// Show a toast
			Toast.makeText(getApplicationContext(),
					"A page has started loading...", LENGTH_SHORT).show();
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);

			// Calculate load time
			this.loadTime = currentTimeMillis() - this.loadTime;

			// Convert milliseconds to date format
			String time = new SimpleDateFormat("mm:ss:SSS", Locale.getDefault())
					.format(new Date(this.loadTime));

			// Show a toast
			Toast.makeText(getApplicationContext(),
					"Page has finished loading in " + time, LENGTH_SHORT)
					.show();

		}
	}

}
