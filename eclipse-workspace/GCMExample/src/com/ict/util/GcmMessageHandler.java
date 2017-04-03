package com.ict.util;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.ict.gcmexample.R;

public class GcmMessageHandler extends IntentService {
	NotificationManager notificationmanager = null;
	String mes;
	private Handler handler;

	public GcmMessageHandler()
	{
		super("GcmMessageHandler");
	}

	@Override
	public void onCreate()
	{
		// TODO Auto-generated method stub
		super.onCreate();
		handler = new Handler();
	}

	@Override
	protected void onHandleIntent(Intent intent)
	{
		Bundle extras = intent.getExtras();

		GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
		// The getMessageType() intent parameter must be the intent you received
		// in your BroadcastReceiver.
		String messageType = gcm.getMessageType(intent);

		mes = extras.getString("title");
		showToast();

		//----------------------

		RemoteViews remoteviews = new RemoteViews(getPackageName(), R.layout.customnotification);
		NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext())
				.setSmallIcon(R.drawable.ic_launcher)
				.setTicker("You have a notification")
				.setContent(remoteviews);
		remoteviews.setImageViewResource(R.id.imagenotileft, R.drawable.ic_launcher);
		//remoteviews.setTextViewText(R.id.imagenotiright,sdf.format(cal.getTime()));
		remoteviews.setTextViewText(R.id.title, "Content Title");
		remoteviews.setTextViewText(R.id.text, mes);
		notificationmanager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
		//notificationmanager.notify(0,builder.build());			   

		Notification noti = builder.build();

		// PendingIntent i=PendingIntent.getActivity(MainActivity.this, 0,new Intent(MainActivity.this, SecondActivity.class),0);

		/*
		 * Intent switchIntent = new Intent(MainActivity.this,
		 * switchButtonListener.class);
		 * PendingIntent i = PendingIntent.getBroadcast(MainActivity.this, 0,
		 * switchIntent, 0);
		 * remoteviews.setOnClickPendingIntent(R.id.linear, i);
		 */
		noti.flags |= builder.build().FLAG_NO_CLEAR | builder.build().FLAG_ONGOING_EVENT;
		noti.flags |= builder.build().FLAG_AUTO_CANCEL;

		notificationmanager.notify(0, noti);

		//--------------------------

		Log.i("GCM", "Received : (" + messageType + ")  " + extras.getString("title"));

		GcmBroadcastReceiver.completeWakefulIntent(intent);

	}

	public void showToast()
	{
		handler.post(new Runnable() {
			public void run()
			{
				Toast.makeText(getApplicationContext(), mes + "  This is from server   ", Toast.LENGTH_LONG).show();
			}
		});

	}
}