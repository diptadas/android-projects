package com.example.activityswitchexample;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        button = (Button) findViewById(R.id.button1);
        
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
                // explicit intent	
				
//				Intent intent = new Intent(MainActivity.this,SecondActivity.class);
//				intent.putExtra("key", "this is name");
//				startActivity(intent);
				
				// implicit intent - default action
				
//				Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.google.com"));
//				startActivity(intent);
				
				// implicit intent - intent filtering 
				
				Intent intent 	= new Intent("com.example.activityswitchexample.LAUNCH",Uri.parse("http://www.google.com"));
				startActivity(intent);
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
