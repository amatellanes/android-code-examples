package com.amatellanes.android.examples;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;
import android.widget.TextView.BufferType;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main);

		/*
		 * Usando código HTML para dar formato al texto escrito en una vista
		 * TextView.
		 */
		TextView textView1 = (TextView) findViewById(R.id.textView1);
		textView1
				.setText(Html
						.fromHtml("<h2>Lorem ipsum</h2> ad his scripta blandit partiendo, eum fastidii <b>accumsan euripidis in</b>, eum liber hendrerit an."));

		/*
		 * Usando Spans para dar formato al texto escrito en una vista TextView.
		 */
		TextView textView2 = (TextView) findViewById(R.id.textView2);

		SpannableStringBuilder ssb1 = new SpannableStringBuilder(
				"Lorem ipsum ad his scripta blandit partiendo, eum fastidii accumsan euripidis in, eum liber hendrerit an.");
		StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
		StyleSpan italicSpan = new StyleSpan(Typeface.ITALIC);
		AbsoluteSizeSpan headerSpan = new AbsoluteSizeSpan(100);
		ssb1.setSpan(boldSpan, 0, 5, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		ssb1.setSpan(headerSpan, 35, 44, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		ssb1.setSpan(italicSpan, 50, 58, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

		textView2.setText(ssb1, BufferType.SPANNABLE);

		/*
		 * Usando Spans para añadir imágenes al texto escrito en una vista
		 * TextView.
		 */
		TextView textView3 = (TextView) findViewById(R.id.textView3);

		SpannableStringBuilder ssb2 = new SpannableStringBuilder(
				"Esto es un emoticono ");
		Bitmap image = BitmapFactory.decodeResource(getResources(),
				R.drawable.emoticon);
		ImageSpan imageSpan = new ImageSpan(this, image);
		ssb2.setSpan(imageSpan, 20, 21, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

		textView3.setText(ssb2, BufferType.SPANNABLE);

		/*
		 * Usando Spans para añadir una URL al texto escrito en una vista
		 * TextView.
		 */
		TextView textView4 = (TextView) findViewById(R.id.textView4);
		SpannableStringBuilder ssb3 = new SpannableStringBuilder(
				"Visita mi blog http://amatellanes.wordpress.com/");
		URLSpan urlSpan = new URLSpan("http://amatellanes.wordpress.com/") {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(getURL()));
				startActivity(intent);
			}
		};
		ssb3.setSpan(urlSpan, 15, ssb3.length(),
				Spannable.SPAN_INCLUSIVE_INCLUSIVE);

		textView4.setText(ssb3, BufferType.SPANNABLE);
		// textView4.setMovementMethod(LinkMovementMethod.getInstance());

		Linkify.addLinks(textView4, Linkify.WEB_URLS);

	}
}
