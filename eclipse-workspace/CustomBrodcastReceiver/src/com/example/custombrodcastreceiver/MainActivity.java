package com.example.custombrodcastreceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.custombroadcastreceiver.R;

public class MainActivity extends Activity {

	TextView txt;
	EditText etxt;
	Button btn;

	CustomReceiver myReceiver;
	String action = "custom event";
	IntentFilter filter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txt = (TextView) findViewById(R.id.textView1);
		etxt = (EditText) findViewById(R.id.editText1);
		btn = (Button) findViewById(R.id.button1);

		myReceiver = new CustomReceiver();
		filter = new IntentFilter(action);

		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub
				Intent intent = new Intent(action);
				String data = etxt.getText().toString();

				if (data.equals(""))
				{
					Toast.makeText(getApplicationContext(), "No input", Toast.LENGTH_LONG).show();
				}
				else
				{
					intent.putExtra("key", data);
					sendBroadcast(intent);
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

	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
		registerReceiver(myReceiver, filter);
	}

	@Override
	protected void onDestroy()
	{
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(myReceiver);
	}

	class CustomReceiver extends BroadcastReceiver
	{

		@Override
		public void onReceive(Context context, Intent intent)
		{
			// TODO Auto-generated method stub
			if (intent.getAction().equals(action))
			{
				String data = intent.getExtras().getString("key");
				txt.setText(data);

			}

		}

	}

}
