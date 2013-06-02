package com.amatellanes.android.examples;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.amatellanes.android.examples.transformers.ZoomOutPageTransformer;

/**
 * 
 * @author amatellanes
 * 
 */
public class MainActivity extends FragmentActivity {

	/**
	 * The pager widget, which handles animation and allows swiping horizontally
	 * to access previous and next pages.
	 */
	ViewPager pager = null;

	/**
	 * The pager adapter, which provides the pages to the view pager widget.
	 */
	MyFragmentPagerAdapter pagerAdapter;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		this.setContentView(R.layout.main);

		// Instantiate a ViewPager
		this.pager = (ViewPager) this.findViewById(R.id.pager);
		// Set a custom animation
		this.pager.setPageTransformer(true, new ZoomOutPageTransformer());

		// Create an adapter with the fragments we show on the ViewPager
		MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(
				getSupportFragmentManager());
		adapter.addFragment(ScreenSlidePageFragment.newInstance(getResources()
				.getColor(R.color.android_blue), 1));
		adapter.addFragment(ScreenSlidePageFragment.newInstance(getResources()
				.getColor(R.color.android_purple), 2));
		adapter.addFragment(ScreenSlidePageFragment.newInstance(getResources()
				.getColor(R.color.android_green), 3));
		adapter.addFragment(ScreenSlidePageFragment.newInstance(getResources()
				.getColor(R.color.android_yellow), 4));
		adapter.addFragment(ScreenSlidePageFragment.newInstance(getResources()
				.getColor(R.color.android_red), 5));
		this.pager.setAdapter(adapter);

	}

	@Override
	public void onBackPressed() {

		// Return to previous page when we press back button
		if (this.pager.getCurrentItem() == 0)
			super.onBackPressed();
		else
			this.pager.setCurrentItem(this.pager.getCurrentItem() - 1);

	}
}
