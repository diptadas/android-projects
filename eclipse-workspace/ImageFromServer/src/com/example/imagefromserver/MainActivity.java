package com.example.imagefromserver;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends Activity {

	ImageView img;
	ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		img = (ImageView) findViewById(R.id.imageView1);

		ImageLoader loader = new ImageLoader();
		loader.execute();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	class ImageLoader extends AsyncTask<String, String, String>
	{

		@Override
		protected String doInBackground(String... arg0)
		{
			// TODO Auto-generated method stub
			Picasso.with(MainActivity.this).load("http://mobicon-test.webege.com/cuet.png").into(img);
			return null;
		}

		@Override
		protected void onPreExecute()
		{
			// TODO Auto-generated method stub

			dialog = new ProgressDialog(MainActivity.this);
			dialog.setMessage("Image Loading.....");
			dialog.setCancelable(false);
			dialog.show();

		}

		@Override
		protected void onPostExecute(String result)
		{
			// TODO Auto-generated method stub

			dialog.dismiss();

		}

	}

}
