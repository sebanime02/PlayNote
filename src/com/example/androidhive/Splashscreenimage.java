package com.example.androidhive;

import java.util.Timer;
import java.util.TimerTask;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splashscreenimage extends Activity{

private static final long SPLASH_SCREEN_DELAY = 3000;

@Override
 protected void onCreate(Bundle savedInstanceState) {
 	super.onCreate(savedInstanceState);
 	setContentView(R.layout.splashscreenimage);
     TimerTask task = new TimerTask() 
     {
        @Override
         public void run() {
             Intent irsplash = new Intent().setClass(Splashscreenimage.this,LoginActivity.class);
             startActivity(irsplash);
             finish();	
        }               
     };
     Timer timer = new Timer();
     timer.schedule(task, SPLASH_SCREEN_DELAY);
	
}
}
