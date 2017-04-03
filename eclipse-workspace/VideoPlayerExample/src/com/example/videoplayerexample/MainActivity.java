package com.example.videoplayerexample;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.Window;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {
	
	VideoView video;
	MediaController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        
        video=(VideoView)findViewById(R.id.videoView1);
        controller=new MediaController(MainActivity.this);
        video.setMediaController(controller);
        //video.setVideoURI(Uri.parse("android.resource://com.example.videoplayerexample/raw/video"));
        video.setVideoPath(Environment.getExternalStorageDirectory()+"/video.mp4");
        video.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
