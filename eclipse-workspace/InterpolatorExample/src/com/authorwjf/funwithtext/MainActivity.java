package com.authorwjf.funwithtext;

import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.Activity;
import android.graphics.Point;

public class MainActivity extends Activity implements OnItemSelectedListener {
	
	private static final long DELAY = 1000;
	private static final long DURATION = 500;
	private static final String PACKAGE = "android.view.animation.";
	
	private TextView mTextView1;
	
	private float getScreenHeight() {
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		return (float) size.y;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTextView1 = (TextView) findViewById(R.id.text_view);
		((Spinner)findViewById(R.id.spinner)).setOnItemSelectedListener(this);
	}


	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
		String interpolatorName = (String) parent.getAdapter().getItem(pos);
		mTextView1.setTranslationY(getScreenHeight());
		mTextView1.setVisibility(View.VISIBLE);
		try {
			Interpolator interpolator = (Interpolator) Class.forName(PACKAGE+interpolatorName).newInstance();
			mTextView1.animate()
				.setInterpolator(interpolator)
				.setDuration(DURATION)
				.setStartDelay(DELAY)
				.translationYBy(-getScreenHeight())
				.start();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}


	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
	}

}
