package com.example.simplebrowser;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	Button go,google;
	WebView web;
	EditText txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        go = (Button)findViewById(R.id.button1);
        google = (Button)findViewById(R.id.button2);
        web = (WebView)findViewById(R.id.webView1);
        txt = (EditText)findViewById(R.id.editText1);
        
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setBuiltInZoomControls(true);
        web.setWebViewClient(new WebViewClient());
        
        web.loadUrl("file:///android_asset/webpage/home.html");
        
        go.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				web.loadUrl(txt.getText().toString());
			}
		});
        google.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				web.loadUrl("http://www.google.com");
				
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
