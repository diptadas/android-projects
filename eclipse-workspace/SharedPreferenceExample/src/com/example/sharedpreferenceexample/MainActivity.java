package com.example.sharedpreferenceexample;

import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	TextView high_score,game_score;
	Button play,reset;
	SharedPreferences pref;
	public static String HIGH_SCORE = "high_score_key";
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //pref = getPreferences(MODE_PRIVATE);
        pref = getSharedPreferences("score", 0);
        
        high_score = (TextView)findViewById(R.id.high_score);
        game_score = (TextView)findViewById(R.id.game_score);
        play = (Button)findViewById(R.id.play);
        reset = (Button)findViewById(R.id.reset);
        
        int high = pref.getInt(HIGH_SCORE, 0);
        
        high_score.setText(high+"");
        
        play.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Random r = new Random();
				int val = r.nextInt(1000);
				
				game_score.setText(val+"");
				
				int high = pref.getInt(HIGH_SCORE, 0);
				
				if(val>high)
				{
					SharedPreferences.Editor editor = pref.edit();
					editor.putInt(HIGH_SCORE, val);
					editor.commit();
					high_score.setText(val+"");
					Toast.makeText(getApplicationContext(),"congratulation high score !!!", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
        
        reset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				SharedPreferences.Editor editor = pref.edit();
				editor.putInt(HIGH_SCORE, 0);
				editor.commit();
				
				high_score.setText("000");
				game_score.setText("000");
				
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
