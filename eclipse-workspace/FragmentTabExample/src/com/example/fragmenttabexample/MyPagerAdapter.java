package com.example.fragmenttabexample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {

	public MyPagerAdapter(FragmentManager fm)
	{
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int index)
	{
		// TODO Auto-generated method stub
		if (index == 0) return new FirstFragment();
		else if (index == 1) return new SecondFragment();
		return null;
	}

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return 2;
	}

}
