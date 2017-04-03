/*
 * Dipta Das
 * CUET CSE 11
 * dipta670@gmail.com
 */

package com.example.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.widget.Button;

public class AsyncTaskQuery extends AsyncTask<String, String, String> {

	String url;
	ArrayList<String> columns;
	List<NameValuePair> params;
	Button btnListener;

	public int success;
	public ArrayList<HashMap<String, String>> data_list = new ArrayList<HashMap<String, String>>();

	public AsyncTaskQuery(String url, ArrayList<String> columns, List<NameValuePair> params, Button btnListener)
	{
		this.url = url;
		this.columns = columns;
		this.params = params;
		this.btnListener = btnListener;
		execute();
	}

	@Override
	protected void onPreExecute()
	{
		super.onPreExecute();
	}

	protected String doInBackground(String... args)
	{
		JSONParser jParser = new JSONParser();
		JSONArray data = null;

		try
		{
			JSONObject json = jParser.makeHttpRequest(url, "GET", params);

			success = json.getInt("success");

			if (success == 1)
			{
				data = json.getJSONArray("data");

				for (int i = 0; i < data.length(); i++)
				{
					JSONObject obj = data.getJSONObject(i);

					HashMap<String, String> map = new HashMap<String, String>();

					for (int j = 0; j < columns.size(); j++)
					{
						String columnName = columns.get(j);
						map.put(columnName, obj.getString(columnName));
					}

					data_list.add(map);

				}
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			success = 0;
		}

		return null;
	}

	protected void onPostExecute(String file_url)
	{
		btnListener.performClick();
	}
}
