package com.example.accelerometerexample;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements SensorEventListener {

	TextView txtX, txtY, txtZ;
	SensorManager manager;
	Sensor sensor;
	long lastTime;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txtX = (TextView) findViewById(R.id.textView1);
		txtY = (TextView) findViewById(R.id.textView2);
		txtZ = (TextView) findViewById(R.id.textView3);

		manager = (SensorManager) getSystemService(SENSOR_SERVICE);
		sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		if (sensor == null)
		{
			Toast.makeText(getApplicationContext(), "Accelerometer Sensor Not Available", Toast.LENGTH_LONG).show();
			finish();
		}

		lastTime = System.currentTimeMillis();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onPause()
	{
		// TODO Auto-generated method stub
		super.onPause();
		manager.unregisterListener(this);
	}

	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
		manager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event)
	{
		// TODO Auto-generated method stub
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
		{
			long currentTime = System.currentTimeMillis();
			if (currentTime - lastTime >= 1000)
			{
				lastTime = currentTime;

				float xValue = event.values[0];
				float yValue = event.values[1];
				float zValue = event.values[2];

				txtX.setText("X-axis: " + xValue);
				txtY.setText("Y-axis: " + yValue);
				txtZ.setText("Z-axis: " + zValue);
			}
		}

	}

}
