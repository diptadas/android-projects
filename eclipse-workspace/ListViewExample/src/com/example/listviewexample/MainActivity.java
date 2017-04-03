package com.example.listviewexample;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {

	ListView list;
	ArrayList<String> data;
	ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		list = (ListView) findViewById(R.id.listView1);
		data = new ArrayList<String>();

		data.add("CUET");
		data.add("BUET");
		data.add("KUET");
		data.add("RUET");

		adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, data);

		list.setAdapter(adapter);
		list.setOnItemClickListener(this);

		//		list.performItemClick(
		//				list.getAdapter().getView(1, null, null),
		//				1,
		//				list.getAdapter().getItemId(1));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		Toast.makeText(getApplicationContext(), (String) parent.getItemAtPosition(position), Toast.LENGTH_LONG).show();

	}

}
