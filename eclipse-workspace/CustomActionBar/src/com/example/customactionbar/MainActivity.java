package com.example.customactionbar;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	ActionBar actionBar;
	ImageButton imgCall, imgMail, imgSend;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
		View view = inflater.inflate(R.layout.custom_layout, null);

		imgCall = (ImageButton) view.findViewById(R.id.imgCall);
		imgMail = (ImageButton) view.findViewById(R.id.imgMail);
		imgSend = (ImageButton) view.findViewById(R.id.imgSend);

		imgCall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Call Button selected", Toast.LENGTH_SHORT).show();

			}
		});

		imgMail.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Mail Button selected", Toast.LENGTH_SHORT).show();

			}
		});

		imgSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Send Button selected", Toast.LENGTH_SHORT).show();

			}
		});

		actionBar = getSupportActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setCustomView(view, new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));

	}

}
