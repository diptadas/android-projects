package com.dreamers.banglacustomlist;



import java.util.ArrayList;

import com.dibosh.experiments.android.support.customfonthelper.AndroidCustomFontSupport;

import android.app.Activity;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter{

	
	ArrayList <String> countryItem;
	ArrayList <String> champion;
	private Activity context;
	Typeface banglaFont;

	public CustomAdapter(Activity context, ArrayList<String> country,ArrayList<String> champion) {
		
		// TODO Auto-generated constructor stub
		this.context=context;
		this.countryItem=country;
		this.champion=champion;
		banglaFont = Typeface.createFromAsset(context.getAssets(),"font/solaimanlipinormal.ttf");
				
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		  return countryItem.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		 return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
	
		 if (convertView == null) {
		        convertView =LayoutInflater.from(context)
					      .inflate(R.layout.row, parent, false);
		    }
		
		 
		 
		 
		 TextView countryName = (TextView)convertView.findViewById(R.id.country);
		 TextView time=(TextView)convertView.findViewById(R.id.time);
		 ImageView flag = (ImageView)convertView.findViewById(R.id.flag);
	   
		 countryName.setTypeface(banglaFont,Typeface.BOLD);
		 time.setTypeface(banglaFont,Typeface.BOLD);
		 
		 
		 String name=countryItem.get(position);
		 SpannableString convertedName=AndroidCustomFontSupport.getCorrectedBengaliFormat(name,banglaFont, (float) 1);
		 String times=champion.get(position);
		 SpannableString convertedTime=AndroidCustomFontSupport.getCorrectedBengaliFormat(times,banglaFont, (float) 1);
		 
		 countryName.setText(convertedName);
		 time.setText(convertedTime);
				 if(position==0)
		 {
			 
					 flag.setImageResource(R.drawable.argentina);
		 } 
		 
		 else if(position==1)
		 {
			 
			 flag.setImageResource(R.drawable.brazil);
			 
 	 
		 }
		 
		 else if(position==2)
		 {
			 
			 flag.setImageResource(R.drawable.croatia);
			 
 	 
		 }
		 else if(position==3)
		 {
			 
			 flag.setImageResource(R.drawable.denmark);
			 
 	 
		 }
	
		 else if(position==4)
		 {
			 
			 flag.setImageResource(R.drawable.england);
			 
 	 
		 }
		 else if(position==5)
		 {
			 
			 flag.setImageResource(R.drawable.germany);
 	 
		 }
		 else if(position==6)
		 {
			 
			 flag.setImageResource(R.drawable.italy);
 	 
		 }
		 else 
		 {
			 
			 flag.setImageResource(R.drawable.spain);
 	 
		 }
		
	 
		
		return convertView;
	}
	
	
	

}
