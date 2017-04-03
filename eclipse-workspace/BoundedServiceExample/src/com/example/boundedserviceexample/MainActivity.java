package com.example.boundedserviceexample;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boundedserviceexample.TheService.MyLocalBinder;

public class MainActivity extends Activity {

	Button btnBind, btnUnbind, btnTime;
	TextView txt;

	TheService myService;

	public static boolean isBound = false;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnBind = (Button) findViewById(R.id.button1);
		btnUnbind = (Button) findViewById(R.id.button3);
		btnTime = (Button) findViewById(R.id.button2);

		txt = (TextView) findViewById(R.id.textView1);

		btnBind.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, TheService.class);
				bindService(intent, myConnection, Context.BIND_AUTO_CREATE);

			}
		});

		btnUnbind.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				if (isBound) unbindService(myConnection);
				else
					Toast.makeText(getApplicationContext(), "Service not bunded", Toast.LENGTH_LONG).show();

			}
		});

		btnTime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub
				if (isBound)
				{
					String time = myService.getTime();
					txt.setText(time);
				}
				else
				{
					txt.setText("Service not bunded");
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

	protected ServiceConnection myConnection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name)
		{
			// TODO Auto-generated method stub
			myService = null;
			isBound = false;
			txt.setText("Service not bounded");

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service)
		{
			// TODO Auto-generated method stub
			MyLocalBinder binder = (MyLocalBinder) service;
			myService = binder.getService();
			isBound = true;

		}
	};

}
