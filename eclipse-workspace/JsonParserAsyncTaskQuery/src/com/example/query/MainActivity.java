/*
 * Dipta Das
 * CUET CSE 11
 * dipta670@gmail.com
 */

package com.example.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.example.jsondbquery.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	String url = "http://192.168.78.1/example_php/top_chart_list.php";

	ArrayList<String> columns = new ArrayList<String>(Arrays.asList("type", "rank", "name"));
	List<NameValuePair> params = new ArrayList<NameValuePair>();

	ListView listView;
	Button btnListener; // fake button to listen when AsycTask is completed

	AsyncTaskQuery objAsyncTaskQuery;

	int success;
	ArrayList<HashMap<String, String>> data_list;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.listView1);
		btnListener = new Button(this);

		params.add(new BasicNameValuePair("type", "1"));

		objAsyncTaskQuery = new AsyncTaskQuery(url, columns, params, btnListener);

		btnListener.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub

				success = objAsyncTaskQuery.success;
				data_list = objAsyncTaskQuery.data_list;

				if (success == 1)
				{
					ArrayList<String> list = new ArrayList<String>();

					for (int i = 0; i < data_list.size(); i++)
					{
						String value = data_list.get(i).get("name");
						list.add(value);
					}

					ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, list);
					listView.setAdapter(adapter);
				}
				else
				{
					Toast.makeText(MainActivity.this, "Error Connecting Server", Toast.LENGTH_LONG).show();
				}
			}
		});

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3)
			{
				// TODO Auto-generated method stub

				Toast.makeText(MainActivity.this, data_list.get(pos).get("name"), Toast.LENGTH_LONG).show();
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
