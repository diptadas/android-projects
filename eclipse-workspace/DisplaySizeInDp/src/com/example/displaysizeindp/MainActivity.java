package com.example.displaysizeindp;

import android.os.Bundle;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        double statusBarHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        
        TypedValue tv = new TypedValue();
        getApplicationContext().getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true);
        double actionBarHeight = getResources().getDimensionPixelSize(tv.resourceId);
        
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        
        double dens = dm.densityDpi;
        double width_px = dm.widthPixels;
        double width_dp = width_px * 160.0d / dens;
        double height_px = dm.heightPixels;
        double height_dp = height_px * 160.0d / dens;
        double height2_px = dm.heightPixels-statusBarHeight-actionBarHeight;
        double height2_dp = height2_px * 160.0d / dens;
        
        TextView txt1 = (TextView)findViewById(R.id.textView1);
        TextView txt2 = (TextView)findViewById(R.id.textView2);
        TextView txt3 = (TextView)findViewById(R.id.textView3);
        TextView txt4 = (TextView)findViewById(R.id.textView4);
        TextView txt5 = (TextView)findViewById(R.id.textView5);
        TextView txt6 = (TextView)findViewById(R.id.textView6);
        
        double value = 5.3647;
        String result = String.format("%.2f", width_dp);
        
        txt1.setText(result);
        
        txt1.setText("Width:\n  " + String.format("%.2f",width_px) + " px / " + String.format("%.2f",width_dp) + " dp");
        txt2.setText("Height:\n  " + String.format("%.2f",height_px) + " px / " + String.format("%.2f",height_dp) + " dp");
        txt3.setText("Status bar:\n  " + String.format("%.2f",statusBarHeight) + " px");
        txt4.setText("Action bar:\n  " + String.format("%.2f",actionBarHeight) + " px");
        txt5.setText("Height without status and action bar:\n  " + 
                     String.format("%.2f",height2_px)+ " px / " + String.format("%.2f",height2_dp) + " dp");
        txt6.setText("Density:\n  " + String.format("%.2f",dens) + " dpi");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
