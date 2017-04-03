package com.example.animationexample;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	ImageView img;
	AnimationDrawable animationControler;
	int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        state=-1;
        
        img = (ImageView)findViewById(R.id.imageView1);
        img.setImageResource(R.drawable.frameanimation);
        
        animationControler = (AnimationDrawable)img.getDrawable();
        
        img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
             state*=-1;
             
             if(state==1) animationControler.start();
             else animationControler.stop();
				
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
