package com.example.customlistexample;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	ListView list;
	ArrayList<String> data;
	CustomAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		list = (ListView) findViewById(R.id.listView1);
		data = new ArrayList<String>();

		data.add("Dhaka");
		data.add("Dhaka");
		data.add("Dhaka");
		data.add("Dhaka");
		data.add("Dhaka");
		data.add("Chittagong");
		data.add("Chittagong");
		data.add("Chittagong");
		data.add("Chittagong");
		data.add("Chittagong");

		adapter = new CustomAdapter(getApplicationContext(), data);
		list.setAdapter(adapter);

		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id)
			{
				// TODO Auto-generated method stub
				String selectedItem = list.getItemAtPosition(position).toString();
				Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_SHORT).show();

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
