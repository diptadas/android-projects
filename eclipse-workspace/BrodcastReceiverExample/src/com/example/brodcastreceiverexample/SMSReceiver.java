package com.example.brodcastreceiverexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SMSReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent)
	{
		// TODO Auto-generated method stub
		if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED"))
		{
			Bundle bundle = intent.getExtras();
			Object[] objects = (Object[]) bundle.get("pdus"); // pdus: protocol data units

			SmsMessage[] allMessages = new SmsMessage[objects.length];

			String number = null;
			String message = "";

			for (int i = 0; i < objects.length; i++)
			{
				allMessages[i] = SmsMessage.createFromPdu((byte[]) objects[i]);
				if (number == null) number = allMessages[i].getOriginatingAddress();
				message += allMessages[i].getMessageBody();
			}

			//SmsManager manager = SmsManager.getDefault();
			//manager.sendTextMessage(number, null, "Reply", null, null);

			Intent intent2 = new Intent(context, ShowMessage.class);
			intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent2.putExtra("num", number);
			intent2.putExtra("msg", message);
			context.startActivity(intent2);
		}

	}
}
