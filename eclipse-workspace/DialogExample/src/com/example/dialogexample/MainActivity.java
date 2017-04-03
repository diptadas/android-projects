package com.example.dialogexample;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button btnSimple, btnItem, btnMulti, btnCustom;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnSimple = (Button) findViewById(R.id.button1);
		btnItem = (Button) findViewById(R.id.button2);
		btnMulti = (Button) findViewById(R.id.button3);
		btnCustom = (Button) findViewById(R.id.button4);

		btnSimple.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub

				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

				builder.setTitle("A Simple Dialog");
				builder.setIcon(R.drawable.ic_launcher);
				builder.setMessage("Do you want to exit?");
				builder.setCancelable(false);

				builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						// TODO Auto-generated method stub

						Toast.makeText(getApplicationContext(), "Yes Button Clicked", Toast.LENGTH_SHORT).show();

					}
				});

				builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						// TODO Auto-generated method stub

						Toast.makeText(getApplicationContext(), "No Button Clicked", Toast.LENGTH_SHORT).show();

					}
				});

				AlertDialog simpleDialog = builder.create();
				simpleDialog.show();
			}
		});

		btnItem.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub

				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

				builder.setTitle("A Item Dialog");
				builder.setIcon(R.drawable.ic_launcher);
				builder.setCancelable(false);

				final String[] items = { "Red", "Green", "Blue" };

				builder.setItems(items, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int pos)
					{
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), items[pos] + " is clicked", Toast.LENGTH_SHORT).show();
					}
				});

				builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						// TODO Auto-generated method stub

						Toast.makeText(getApplicationContext(), "Dialog is closed", Toast.LENGTH_SHORT).show();
					}
				});

				AlertDialog itemDialog = builder.create();
				itemDialog.show();
			}
		});

		btnMulti.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub

				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

				builder.setTitle("A Multichoice Item Dialog");
				builder.setIcon(R.drawable.ic_launcher);
				builder.setCancelable(false);

				final String[] items = { "Red", "Green", "Blue", "White" };
				boolean[] selected = { false, true, true, false }; // initially Green,Blue selected

				final ArrayList<String> all = new ArrayList<String>();
				all.add(items[1]);
				all.add(items[2]);

				// builder.setMultiChoiceItems(items, null, listener);
				// null: initially no item selected

				builder.setMultiChoiceItems(items, selected, new OnMultiChoiceClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int pos, boolean checked)
					{
						// TODO Auto-generated method stub

						if (checked) all.add(items[pos]);
						else
							all.remove(items[pos]);

					}
				});

				builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1)
					{
						// TODO Auto-generated method stub

						String data = "Selected items: ";
						for (int i = 0; i < all.size(); i++)
						{
							data += all.get(i) + " ";
						}

						Toast.makeText(getApplicationContext(), data, Toast.LENGTH_LONG).show();

					}
				});

				AlertDialog multiDialog = builder.create();
				multiDialog.show();

			}
		});

		btnCustom.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub

				LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
				View view = inflater.inflate(R.layout.custom_dialog_layout, null);

				Button btnClose = (Button) view.findViewById(R.id.btnClose);

				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setView(view);
				builder.setCancelable(false);

				final AlertDialog customDialog = builder.create();

				btnClose.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0)
					{
						// TODO Auto-generated method stub
						customDialog.cancel();
						Toast.makeText(getApplicationContext(), "Dialog Canceled", Toast.LENGTH_SHORT).show();
					}
				});

				customDialog.show();
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
