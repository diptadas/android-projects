package com.example.customlistexample;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

	Context context;
	ArrayList<String> data;
	LayoutInflater inflater;
	Typeface customFont;

	public CustomAdapter(Context context, ArrayList<String> data)
	{
		this.context = context;
		this.data = data;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		customFont = Typeface.createFromAsset(context.getAssets(), "font/myfont.TTF");
	}

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return data.get(position) + " " + position;
	}

	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parentGroup)
	{
		// TODO Auto-generated method stub

		ViewHolder holder = null;

		if (convertView == null)
		{
			//Toast.makeText(context, position + " inflated", Toast.LENGTH_SHORT).show();

			convertView = inflater.inflate(R.layout.item, null);
			holder = new ViewHolder();

			holder.txt1 = (TextView) convertView.findViewById(R.id.textView1);
			holder.txt2 = (TextView) convertView.findViewById(R.id.textView2);

			convertView.setTag(holder);
		}

		holder = (ViewHolder) convertView.getTag();

		holder.txt1.setText(data.get(position));
		holder.txt2.setText("" + position);

		holder.txt1.setTypeface(customFont);

		return convertView;
	}

	public static class ViewHolder
	{
		TextView txt1, txt2;
	}
}
