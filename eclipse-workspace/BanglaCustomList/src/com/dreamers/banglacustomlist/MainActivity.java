package com.dreamers.banglacustomlist;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
ListView listView;
ArrayList<String>item,champion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView=(ListView)findViewById(R.id.listView1);
		
	     item =new ArrayList<String>();
	 champion=new ArrayList<String>();
		item.add("আর্জেন্টিনা");
		champion.add("২ বার চ্যাম্পিয়ন");
		item.add("ব্রাজিল");
		champion.add("৫ বার চ্যাম্পিয়ন");
		item.add("ক্রোয়েশিয়া");
		champion.add("০ বার চ্যাম্পিয়ন");
		item.add("ডেনমার্ক");
		champion.add("০ বার চ্যাম্পিয়ন");
		item.add("ইংল্যান্ড");
		champion.add("১ বার চ্যাম্পিয়ন");
		item.add("জার্মানী");
		champion.add("৪ বার চ্যাম্পিয়ন");
		item.add("ইতালি");
		champion.add("৪ বার চ্যাম্পিয়ন");
		item.add("স্পেন");
		champion.add("১ বার চ্যাম্পিয়ন");
		
		
		CustomAdapter adapter=new CustomAdapter(this, item,champion);
		listView.setAdapter(adapter);
		
		
		listView.setOnItemClickListener(new OnItemClickListener() {
        	
       	 @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                  long arg3) 
            {
       		
       			
            }
       		
       		
    		
   	});
	}

	

}
