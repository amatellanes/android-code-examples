package com.amatellanes.android.examples;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView listView;
	private WebView webView;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Display a indeterminate progress bar on title bar
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

		this.setContentView(R.layout.main);

		this.listView = (ListView) findViewById(R.id.listView);
		this.webView = (WebView) findViewById(R.id.webView);

		List<Item> items = new ArrayList<Item>();
		items.add(new Item(R.drawable.following, "Following",
				"http://www.imdb.com/title/tt0154506/"));
		items.add(new Item(R.drawable.memento, "Memento",
				"http://www.imdb.com/title/tt0209144/"));
		items.add(new Item(R.drawable.batman_begins, "Batman Begins",
				"http://www.imdb.com/title/tt0372784/"));
		items.add(new Item(R.drawable.the_prestige, "The Prestige",
				"http://www.imdb.com/title/tt0482571/"));
		items.add(new Item(R.drawable.the_dark_knight, "The Dark Knight",
				"http://www.imdb.com/title/tt0468569/"));
		items.add(new Item(R.drawable.inception, "Inception",
				"http://www.imdb.com/title/tt1375666/"));
		items.add(new Item(R.drawable.the_dark_knight_rises,
				"The Dark Knight Rises", "http://www.imdb.com/title/tt1345836/"));

		// Sets the data behind this ListView
		this.listView.setAdapter(new ItemAdapter(this, items));

		// Register a callback to be invoked when an item in this AdapterView
		// has been clicked
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long arg) {

				// Sets the visibility of the indeterminate progress bar in the
				// title
				setProgressBarIndeterminateVisibility(true);
				// Show progress dialog
				progressDialog = ProgressDialog.show(MainActivity.this,
						"ProgressDialog", "Loading!");

				// Tells JavaScript to open windows automatically.
				webView.getSettings().setJavaScriptEnabled(true);
				// Sets our custom WebViewClient.
				webView.setWebViewClient(new myWebClient());
				// Loads the given URL
				Item item = (Item) listView.getAdapter().getItem(position);
				webView.loadUrl(item.getUrl());
			}
		});

	}

	private class myWebClient extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// Load the given URL on our WebView.
			view.loadUrl(url);
			return true;
		}

		@Override
		public void onPageFinished(WebView view, String url) {

			// When the page has finished loading, hide progress dialog and
			// progress bar in the title.
			super.onPageFinished(view, url);
			setProgressBarIndeterminateVisibility(false);
			progressDialog.dismiss();
		}
	}
}
