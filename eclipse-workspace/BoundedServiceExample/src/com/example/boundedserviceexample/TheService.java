package com.example.boundedserviceexample;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class TheService extends Service {

	public final IBinder myBinder = new MyLocalBinder();

	@Override
	public void onCreate()
	{
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), "Service created", Toast.LENGTH_LONG).show();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), "Service started", Toast.LENGTH_LONG).show();
		return START_NOT_STICKY;
	}

	@Override
	public void onDestroy()
	{
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), "Service destroyed", Toast.LENGTH_LONG).show();
		MainActivity.isBound = false;
	}

	@Override
	public IBinder onBind(Intent intent)
	{
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), "Service Binded", Toast.LENGTH_LONG).show();
		return myBinder;
	}

	public String getTime()
	{
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
		String time = sdf.format(c.getTime());
		return time;
	}

	public class MyLocalBinder extends Binder
	{
		public TheService getService()
		{
			return TheService.this;
		}
	}

}
