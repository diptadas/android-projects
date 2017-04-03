package com.example.endscrollview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	ScrollViewExt scrollView;
	TextView txt;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		scrollView = (ScrollViewExt) findViewById(R.id.scrollView1);
		txt = (TextView) findViewById(R.id.textView1);

		scrollView.setScrollViewListener(new ScrollViewListener() {

			@Override
			public void onScrollChanged(ScrollViewExt scrollView, int x, int y, int oldx, int oldy)
			{
				// TODO Auto-generated method stub
				View view = (View) scrollView.getChildAt(scrollView.getChildCount() - 1);
				int diff = (view.getBottom() - (scrollView.getHeight() + scrollView.getScrollY()));

				// if diff is zero, then the bottom has been reached
				if (diff == 0)
				{
					// do stuff
					txt.setText("Scroll Up");
				}
				else
				{
					txt.setText("Scroll Down");
				}

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
