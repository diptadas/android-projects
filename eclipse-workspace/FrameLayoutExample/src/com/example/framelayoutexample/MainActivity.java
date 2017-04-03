package com.example.framelayoutexample;

import android.app.Activity;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener{
	ImageView img1,img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        img1 = (ImageView)findViewById(R.id.imageView1);
        img2 = (ImageView)findViewById(R.id.imageView2);
        
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		if(arg0.getId()==R.id.imageView1)
		{
			img2.setVisibility(View.VISIBLE);
			img1.setVisibility(View.INVISIBLE);
			return;
		}
		else if(arg0.getId()==R.id.imageView2)
		{
			img1.setVisibility(View.VISIBLE);
			img2.setVisibility(View.INVISIBLE);
			return;
		}
		
	}
    
}
