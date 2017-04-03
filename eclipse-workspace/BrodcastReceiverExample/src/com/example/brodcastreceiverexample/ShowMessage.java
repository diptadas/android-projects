package com.example.brodcastreceiverexample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowMessage extends Activity {

	TextView txt;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_message);

		txt = (TextView) findViewById(R.id.textView1);

		String number = getIntent().getExtras().getString("num");
		String message = getIntent().getExtras().getString("msg");

		txt.setText("Number: " + number + "\nMessage:\n" + message);

	}
}
