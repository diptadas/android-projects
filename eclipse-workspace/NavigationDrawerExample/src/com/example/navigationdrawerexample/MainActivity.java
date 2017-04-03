package com.example.navigationdrawerexample;

import java.util.ArrayList;

import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {
	
	DrawerLayout drawerLayout;
	ListView drawerList;
	ActionBarDrawerToggle drawerToggle;
    ArrayList<String> drawerItems=new ArrayList<String>();
	String[] drawerTitles;
	TypedArray drawerIcons;
	MyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
		drawerList=(ListView)findViewById(R.id.left_drawer);
	
	    drawerTitles = getResources().getStringArray(R.array.drawer_items);
	    drawerIcons=getResources().obtainTypedArray(R.array.drawer_icons);
		for(int i=0;i<drawerTitles.length;i++)
			drawerItems.add(drawerTitles[i]);
		
		adapter=new MyAdapter(this, drawerItems, drawerIcons);
		drawerList.setAdapter(adapter);
		
		
		drawerToggle=new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer,R.string.drawer_open,R.string.drawer_close)
		{
			
			@Override
			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				getSupportActionBar().setTitle("Drawer Opened");
			}
			
			@Override
			public void onDrawerClosed(View drawerView) {
				// TODO Auto-generated method stub
				getSupportActionBar().setTitle("Drawer Closed");
			}
			
		};
		
		drawerLayout.setDrawerListener(drawerToggle);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		drawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				// TODO Auto-generated method stub
				selectItem(pos);
			}
		});
		
		
		if (savedInstanceState == null) {
			// on first time display view for first nav item
			selectItem(0);
		}
		
		
	    
	}
	
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
	   super.onPostCreate(savedInstanceState);
	   // Sync the toggle state after onRestoreInstanceState has occurred.
	   drawerToggle.syncState();
	}   
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	   super.onConfigurationChanged(newConfig);
	   drawerToggle.onConfigurationChanged(newConfig);
	}  
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(drawerToggle.onOptionsItemSelected(item)) return true;
		return super.onOptionsItemSelected(item);
	}
	
	
	public void selectItem(int position)
	{
		MyFragment fragment = new MyFragment();
		Bundle args = new Bundle();
		args.putInt("position", position);
		fragment.setArguments(args);
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
	    drawerList.setItemChecked(position, true);
	    drawerLayout.closeDrawer(drawerList);
	}

}
