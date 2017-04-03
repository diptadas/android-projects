package com.example.simpledatabaseexample;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	Button insert,show;
	String tableName="student";
	SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        insert = (Button)findViewById(R.id.button1);
        show = (Button)findViewById(R.id.button2);
        pref = getSharedPreferences("Database", 0);
        
        String status = pref.getString("load", "no");
        
        if(status.equals("no")) //Database not created
        {
        	createDatabase();
        	
        	SharedPreferences.Editor editor = pref.edit();
        	editor.putString("load", "yes");
        	editor.commit();
        }
        
        insert.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
		    	SQLiteDatabase db = openOrCreateDatabase("MyDB", MODE_PRIVATE, null);
		    	String insertSQL = "INSERT INTO " + tableName + " VALUES(1, 'Name1', '016...');";
		    	db.execSQL(insertSQL);
		    	insertSQL = "INSERT INTO " + tableName + " VALUES(2, 'Name2', '018...');";
		    	db.execSQL(insertSQL);
		    	db.close();
		    	
		    	Toast.makeText(getApplicationContext(), "Data inserted", Toast.LENGTH_LONG).show();
				
			}
		});
        
        show.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				SQLiteDatabase db = openOrCreateDatabase("MyDB", MODE_PRIVATE, null);
				String querySQL = "SELECT * FROM " + tableName + ";";
				
				Cursor cursor = db.rawQuery(querySQL, null);
				if(cursor.getCount()<1) Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_LONG).show();
				else
				{
					String result="";
					
					cursor.moveToFirst();
					do
					{
						String name = cursor.getString(cursor.getColumnIndex("name"));
						String phone = cursor.getString(cursor.getColumnIndex("phone"));
						
						result += name + " " + phone + "\n";
					}
					while(cursor.moveToNext());
					
					Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
				}
				
				db.close();
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void createDatabase()
    {
    	SQLiteDatabase db = openOrCreateDatabase("MyDB", MODE_PRIVATE, null);
    	String createSQL = "CREATE TABLE IF NOT EXISTS " + tableName + " (id INTEGER, name VARCHAR, phone VARCHAR);";
    	db.execSQL(createSQL);
    	db.close();
    	
    	Toast.makeText(getApplicationContext(), "Database created", Toast.LENGTH_LONG).show();
    }
    
}
