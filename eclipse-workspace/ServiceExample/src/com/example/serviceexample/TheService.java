package com.example.serviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class TheService extends Service {

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
	}

	@Override
	public IBinder onBind(Intent intent)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
