package com.example.actionbarexample;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	Button btnShow, btnHide, btnChange;
	ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnShow = (Button) findViewById(R.id.button1);
		btnHide = (Button) findViewById(R.id.button2);
		btnChange = (Button) findViewById(R.id.button3);

		actionBar = getSupportActionBar();

		btnShow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub

				actionBar.show();

			}
		});

		btnHide.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub

				actionBar.hide();

			}
		});

		btnChange.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				actionBar.setIcon(R.drawable.call);
				actionBar.setTitle("NewActionBar");
				actionBar.setSubtitle("Subtitle");

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

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// TODO Auto-generated method stub
		switch (item.getItemId())
		{
		case R.id.call:
			Toast.makeText(getApplicationContext(), "Call item selected", Toast.LENGTH_SHORT).show();
			return true;

		case R.id.mail:
			Toast.makeText(getApplicationContext(), "Mail item selected", Toast.LENGTH_SHORT).show();
			return true;

		case R.id.send:
			Toast.makeText(getApplicationContext(), "Send item selected", Toast.LENGTH_SHORT).show();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
