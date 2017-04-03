package com.example.accountmanagerexample;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TextView txt = (TextView) findViewById(R.id.textView1);

		txt.setText(getEmail(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	static String getEmail(Context context)
	{
		AccountManager accountManager = AccountManager.get(context);
		Account account = getAccount(accountManager);
		if (account == null)
		{
			return null;
		} else
		{
			return account.name;
		}
	}

	private static Account getAccount(AccountManager accountManager)
	{
		Account[] accounts = accountManager.getAccountsByType("com.google");
		Account account;
		if (accounts.length > 0)
		{
			account = accounts[0];
		} else
		{
			account = null;
		}
		return account;
	}

}
