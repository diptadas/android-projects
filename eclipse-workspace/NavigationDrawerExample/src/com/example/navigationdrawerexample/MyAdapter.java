package com.example.navigationdrawerexample;

import java.util.ArrayList;
import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyAdapter extends ArrayAdapter<String>{

	ArrayList <String> words;
    TypedArray  icons;
	private Activity context;
	
	
	public MyAdapter(Activity context, ArrayList<String> words,TypedArray icons) {
		super(context, R.layout.list_item, words);
		// TODO Auto-generated constructor stub
		this.context=context;
		this.words=words;
		this.icons=icons;
	}
	
	
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView==null)
		{
			LayoutInflater inflater= context.getLayoutInflater(); 
			convertView= inflater.inflate(R.layout.list_item, parent,false);
		}	 
			 
		 TextView text = (TextView)convertView.findViewById(R.id.txtItem);				 
		 ImageView img = (ImageView)convertView.findViewById(R.id.imgItem);
		 
		 text.setText(words.get(position));
		 img.setImageResource(icons.getResourceId(position, -1));
		 	
		return convertView;
	}
	
	

}
