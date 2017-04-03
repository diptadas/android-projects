package com.example.navigationdrawerexample;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MyFragment extends Fragment{
	
	int pos;
	String[] titles;
	private TypedArray icons;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	Bundle savedInstanceState) {
	View view = inflater.inflate(R.layout.fragment_layout, null);
	TextView textView = (TextView) view.findViewById(R.id.txtFragment);
	ImageView imgView=(ImageView)view.findViewById(R.id.imgFragment);
	titles = getResources().getStringArray(R.array.drawer_items);
	icons = getResources().obtainTypedArray(R.array.drawer_icons);
	textView.setText(titles[pos]);
	imgView.setImageResource(icons.getResourceId(pos, -1));
	return view;
	}

	
	@Override
	public void setArguments(Bundle args) {
	//string = args.getString(ARG_OS);
		pos=args.getInt("position");
	}

}
