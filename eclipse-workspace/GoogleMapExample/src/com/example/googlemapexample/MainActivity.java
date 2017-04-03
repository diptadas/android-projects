package com.example.googlemapexample;

import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button showMap, distance, address, internet, provider;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		showMap = (Button) findViewById(R.id.button1);
		distance = (Button) findViewById(R.id.button2);
		address = (Button) findViewById(R.id.button3);
		internet = (Button) findViewById(R.id.button4);
		provider = (Button) findViewById(R.id.button5);

		showMap.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub
				Intent in = new Intent(MainActivity.this, MapActivity.class);
				startActivity(in);
			}
		});

		distance.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub

				float[] results = new float[1];
				double lat1 = 22.368025;
				double lon1 = 91.849106;
				double lat2 = 22.351451;
				double lon2 = 91.852068;

				Location.distanceBetween(lat1, lon1, lat2, lon2, results); // unit
																			// meter

				Toast.makeText(getApplicationContext(), "Distance: " + results[0] / 1000 + " km", Toast.LENGTH_LONG).show();
			}
		});

		address.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub

				Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
				List<Address> addresses = null;
				Address addr = null;

				try
				{
					addresses = geocoder.getFromLocation(22.368025, 91.849106, 2); // no
																					// of
																					// address:
																					// 2
					if (addresses.size() > 0)
					{
						addr = addresses.get(0);
						String info = addr.getAddressLine(0); // street
						info += ", " + addr.getLocality();
						info += ", " + addr.getCountryName();

						Toast.makeText(getApplicationContext(), info, Toast.LENGTH_LONG).show();
					} else
						Toast.makeText(getApplicationContext(), "Address not found", Toast.LENGTH_LONG).show();

				} catch (Exception e)
				{
					Toast.makeText(getApplicationContext(), "Address not found", Toast.LENGTH_LONG).show();
				}

			}
		});

		internet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub

				boolean internetStatus = isConnectedToInternet();
				if (internetStatus)
				{
					Toast.makeText(getApplicationContext(), "Internet Connection ON", Toast.LENGTH_LONG).show();
				} else
				{
					Intent intent = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
					// For Android 4.0 and earlier
					ComponentName cName = new ComponentName("com.android.phone", "com.android.phone.Settings");
					intent.setComponent(cName);
					startActivity(intent);
				}

			}
		});

		provider.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub

				LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
				boolean statusOfGPS = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
				boolean statusOfNetwork = manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
				if (statusOfGPS) Toast.makeText(getApplicationContext(), "GPS provider ON", Toast.LENGTH_LONG).show();
				if (statusOfNetwork) Toast.makeText(getApplicationContext(), "Network provider ON", Toast.LENGTH_LONG).show();
				if (!statusOfGPS || !statusOfNetwork)
				{
					Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
					startActivity(intent);
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

	public boolean isConnectedToInternet()
	{
		ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null)
		{
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null)
			{
				for (int i = 0; i < info.length; i++)
				{
					if (info[i].getState() == NetworkInfo.State.CONNECTED) return true;
				}
			}
		}
		return false;
	}

}
