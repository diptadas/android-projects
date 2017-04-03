package com.ict.gcmexample;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.ict.util.Content;

public class MainActivity extends Activity implements OnClickListener {

	Button btnRegId, send;
	EditText etRegId;
	GoogleCloudMessaging gcm;
	String regid;
	String PROJECT_NUMBER = "your project number";
	String sendTo = "";
	boolean availableProduct = false;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnRegId = (Button) findViewById(R.id.bGetRegId);
		etRegId = (EditText) findViewById(R.id.etRegId);
		send = (Button) findViewById(R.id.send);
		btnRegId.setOnClickListener(this);

		send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub
				if (!sendTo.equals(""))
				{
					GCMMessageSender gcm = new GCMMessageSender();
					ArrayList<String> arrRegId = new ArrayList<String>();
					arrRegId.add(sendTo);
					arrRegId.add("device id");

					Content content = gcm.createContent("Demo", "This is message body", arrRegId);
					Toast.makeText(getApplicationContext(), content + " " + sendTo, Toast.LENGTH_LONG).show();
					gcm.send(content);

					//new AsyncTaskRunnerSpec().execute();
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Please generate id first", Toast.LENGTH_LONG).show();
				}

			}
		});

	}

	public void getRegId()
	{
		new AsyncTask<Void, Void, String>() {
			@Override
			protected String doInBackground(Void... params)
			{
				String msg = "";
				try
				{
					if (gcm == null)
					{
						gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
					}
					regid = gcm.register(PROJECT_NUMBER);
					msg = regid;
					Log.i("GCM", msg);

				} catch (IOException ex)
				{
					msg = "Error :" + ex.getMessage();

				}
				return msg;
			}

			@Override
			protected void onPostExecute(String msg)
			{

				sendTo = msg;
				etRegId.setText(msg + "\n");

			}
		}.execute(null, null, null);
	}

	@Override
	public void onClick(View v)
	{
		getRegId();

	}

	//--------------------------------------------------------------

	class AsyncTaskRunnerSpec extends AsyncTask<String, String, String>
	{
		ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
		private static final String TAG = "PostFetcher";
		public String SERVER_URL = "http://192.168.43.27/gcm/mydatareciver.php";

		int success = 5;
		String x = "";
		String error = "";
		int statusCode;

		@Override
		protected String doInBackground(String... params)
		{
			// TODO Auto-generated method stub

			try
			{

				List<NameValuePair> pair = new ArrayList<NameValuePair>();

				pair.add(new BasicNameValuePair("email", "souravpalitrana@gmail.com"));
				pair.add(new BasicNameValuePair("device_id", sendTo));
				DefaultHttpClient client = new DefaultHttpClient();
				String paramString = URLEncodedUtils.format(pair, "utf-8");
				SERVER_URL += "?" + paramString;
				HttpGet get = new HttpGet(SERVER_URL);

				//Perform the request and check the status code
				HttpResponse response = client.execute(get);
				StatusLine statusLine = response.getStatusLine();
				statusCode = statusLine.getStatusCode();
				if (statusLine.getStatusCode() == 200)
				{
					HttpEntity entity = response.getEntity();
					InputStream content = entity.getContent();

					//Read the server response and attempt to parse it as JSON
					Reader reader = new InputStreamReader(content);

					//GsonBuilder gsonBuilder = new GsonBuilder();
					//gsonBuilder.setDateFormat("M/d/yy hh:mm a");

					availableProduct = true;

				}

			}

			catch (Exception e)
			{
				error = error + e;
			}

			//------------------------------------------------

			return null;
		}

		protected void onPostExecute(String string)
		{

			progressDialog.dismiss();

			if (availableProduct == true)
			{
				Toast.makeText(getApplicationContext(), "Data inserted", Toast.LENGTH_LONG).show();

			}

			else
			{
				Toast.makeText(getApplicationContext(), "There is no data available or server problem ", Toast.LENGTH_LONG).show();
				//view.setText(error);

			}

		}

		protected void onPreExecute()
		{

			progressDialog.setMessage("Please wait. Loading..");
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

	}

	//-------

	//------------------------------------------------------------

}
