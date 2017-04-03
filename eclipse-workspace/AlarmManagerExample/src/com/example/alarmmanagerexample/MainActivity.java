package com.example.alarmmanagerexample;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity {

	TimePicker timePicker;
	Button setAlarm, removeAlarm;
	String action = "com.example.alarmmanagerexample.ALARM";
	PendingIntent pending;
	AlarmManager manager;
	int id;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		timePicker = (TimePicker) findViewById(R.id.timePicker1);
		setAlarm = (Button) findViewById(R.id.button1);
		removeAlarm = (Button) findViewById(R.id.button2);

		manager = (AlarmManager) getSystemService(ALARM_SERVICE);
		id = 0;

		Intent intent = new Intent(action);

		pending = PendingIntent.getBroadcast(getApplicationContext(), id, intent, PendingIntent.FLAG_UPDATE_CURRENT);

		setAlarm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub
				int minute = timePicker.getCurrentMinute();
				int hour = timePicker.getCurrentHour(); //24-hr formate

				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.HOUR_OF_DAY, hour);
				calendar.set(Calendar.MINUTE, minute);
				calendar.set(Calendar.SECOND, 0);

				manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending);

				Toast.makeText(getApplicationContext(), "Alarm set", Toast.LENGTH_SHORT).show();

			}
		});

		removeAlarm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub
				manager.cancel(pending);
				Toast.makeText(getApplicationContext(), "Alarm cancel", Toast.LENGTH_SHORT).show();

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
