package com.example.savedownloadedimage;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity {

	String url = "https://looksok.files.wordpress.com/2011/12/me.jpg";

	String folderName = ".com.example.savedownloadedimage"; //hidden folder
	String imageName = "me.jpg";

	ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		imageView = (ImageView) findViewById(R.id.imageView1);

		if (checkifImageExists(folderName, imageName))
		{
			File file = getImage("/" + folderName, imageName);
			String path = file.getAbsolutePath();

			if (path != null)
			{
				Bitmap bitmap = BitmapFactory.decodeFile(path);
				imageView.setImageBitmap(bitmap);
			}
		} else
		{
			new GetImages(url, imageView, imageName).execute();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public static String saveToSdCard(Bitmap bitmap, String folderName, String imageName)
	{

		String stored = null;

		File sdcard = Environment.getExternalStorageDirectory();

		File folder = new File(sdcard.getAbsoluteFile(), folderName);
		folder.mkdir();

		File file = new File(folder.getAbsoluteFile(), imageName);

		if (file.exists()) return stored;

		try
		{
			FileOutputStream out = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
			stored = "success";
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return stored;
	}

	public static File getImage(String folderName, String imageName)
	{
		File mediaImage = null;

		try
		{
			String root = Environment.getExternalStorageDirectory().toString();
			File myDir = new File(root);

			if (!myDir.exists()) return null;

			mediaImage = new File(myDir.getPath() + "/" + folderName + "/" + imageName);

		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mediaImage;
	}

	public static boolean checkifImageExists(String folderName, String imageName)
	{
		Bitmap bitmap = null;

		File file = getImage(folderName, imageName);
		String path = file.getAbsolutePath();

		if (path != null)
			bitmap = BitmapFactory.decodeFile(path);

		if (bitmap == null || bitmap.equals("")) return false;

		return true;
	}

	private class GetImages extends AsyncTask<Object, Object, Object> {

		private String requestUrl, imageName;
		private Bitmap bitmap;

		private GetImages(String requestUrl, ImageView view, String imageName)
		{
			this.requestUrl = requestUrl;
			this.imageName = imageName;
		}

		@Override
		protected Object doInBackground(Object... objects)
		{
			try
			{
				URL url = new URL(requestUrl);
				URLConnection conn = url.openConnection();
				bitmap = BitmapFactory.decodeStream(conn.getInputStream());
			} catch (Exception ex)
			{
			}
			return null;
		}

		@Override
		protected void onPostExecute(Object o)
		{
			if (!checkifImageExists(folderName, imageName))
			{
				imageView.setImageBitmap(bitmap);
				saveToSdCard(bitmap, folderName, imageName);
			}
		}
	}

}
