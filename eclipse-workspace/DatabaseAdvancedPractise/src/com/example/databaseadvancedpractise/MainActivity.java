package com.example.databaseadvancedpractise;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	Button insert,delete,show,showAll,update;
	DataHandler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		insert=(Button)findViewById(R.id.btnInsert);
		delete=(Button)findViewById(R.id.btnDelete);
		show=(Button)findViewById(R.id.btnShow);
		showAll=(Button)findViewById(R.id.btnShowAll);
		update=(Button)findViewById(R.id.btnUpdate);
		
		handler=new DataHandler(this);
		
		insert.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				handler.addContact("Peaal", "017....");
				handler.addContact("Sajid", "018....");
				Toast.makeText(getApplicationContext(), "Data inserted", Toast.LENGTH_LONG).show();
			}
		});
		
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				handler.deleteContact(1);
				Toast.makeText(getApplicationContext(), "Data deleted", Toast.LENGTH_LONG).show();
			}
		});
		
		show.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Cursor cursor=handler.getContact(1);
				if(cursor.getCount()<1)
				{
					Toast.makeText(getApplicationContext(), "Data was not available", Toast.LENGTH_LONG).show();
				}
				else
				{
					String allData="";
					cursor.moveToFirst();
					do
					{
						String name=cursor.getString(cursor.getColumnIndex(handler.CONTACT_NAME));
						String phone=cursor.getString(cursor.getColumnIndex(handler.CONTACT_PHONE));
						allData+=name +" "+phone+"\n";
					}
					while(cursor.moveToNext());
					Toast.makeText(getApplicationContext(), allData, Toast.LENGTH_LONG).show();
				}
			}
		});
		
		
		showAll.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Cursor cursor=handler.getAllContact();
				if(cursor.getCount()<1)
				{
					Toast.makeText(getApplicationContext(), "No data available", Toast.LENGTH_LONG).show();
				}
				else
				{
					String allData="";
					cursor.moveToFirst();
					do
					{
						String name=cursor.getString(cursor.getColumnIndex(handler.CONTACT_NAME));
						String phone=cursor.getString(cursor.getColumnIndex(handler.CONTACT_PHONE));
						allData+=name +" "+phone+"\n";
					}
					while(cursor.moveToNext());
					Toast.makeText(getApplicationContext(), allData, Toast.LENGTH_LONG).show();
				}
			}
		});
		
		
		update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				int success=handler.updateContact(1, "Mamun", "019....");
				if(success>0) Toast.makeText(getApplicationContext(), "Updating successful", Toast.LENGTH_LONG).show();
				else Toast.makeText(getApplicationContext(), "Updating was not possible", Toast.LENGTH_LONG).show();
			}
		});
		
		
		
	}


}
