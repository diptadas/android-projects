package com.example.alarmmanagerexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

	String action = "com.example.alarmmanagerexample.ALARM";

	@Override
	public void onReceive(Context context, Intent intent)
	{
		// TODO Auto-generated method stub
		if (intent.getAction().equals(action))
		{
			Intent intent2 = new Intent(context, PopupClass.class);
			intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent2);

		}

	}

}
