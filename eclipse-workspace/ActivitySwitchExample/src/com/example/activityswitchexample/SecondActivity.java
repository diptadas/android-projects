package com.example.activityswitchexample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_activity);

		// implicit

		//		String value = getIntent().getExtras().getString("key");
		//		Toast.makeText(getApplicationContext(), value, Toast.LENGTH_LONG).show();

		// explicit

		String data = getIntent().getData().toString();
		Toast.makeText(getApplicationContext(), data, Toast.LENGTH_LONG).show();
	}

}
