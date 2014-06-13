package com.example.wheres_dan_jin;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity {
	TextView timeView;
	ImageButton dan;
	int timePassed = 0;
	Timer time;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		timeView = (TextView) findViewById(R.id.timeView);
		dan = (ImageButton) findViewById(R.id.dan);
		Random r = new Random();
		Display display = getWindowManager().getDefaultDisplay(); 
		time = new Timer();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
		int x = r.nextInt(width - dan.getWidth());
		int y = r.nextInt(height - dan.getHeight());
		dan.setX(x);
		dan.setY(y);
		dan.setOnClickListener(danListener);
		time.schedule(new TimerTask() {
			public void run() {
				Runnable run = new Runnable() {
					public void run() {
						timeView.setText("" + (++timePassed));
					}
				};
				MainActivity.this.runOnUiThread(run);
			}
		}, 0, 1000);
	}
	
	View.OnClickListener danListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this); //Read Update
	        alertDialog.setTitle("Results");
	        alertDialog.setMessage("You found Dan Jin!");
	        alertDialog.setPositiveButton("Continue?", new DialogInterface.OnClickListener() {
	        	public void onClick(DialogInterface dialog, int which) {
		              loadActivity();
		           }
		        });
		    alertDialog.setNegativeButton("Exit?", new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog, int which) {
			          System.exit(0);
			       }
			    });
	        alertDialog.show(); 
	        time = null;
		}
	};
	
	public void loadActivity(){
		setContentView(R.layout.activity_main);
		timeView = (TextView) findViewById(R.id.timeView);
		dan = (ImageButton) findViewById(R.id.dan);
		timePassed = 0;
		Random r = new Random();
		Display display = getWindowManager().getDefaultDisplay(); 
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
		int x = r.nextInt(width - dan.getWidth());
		int y = r.nextInt(height - dan.getHeight());
		dan.setX(x);
		dan.setY(y);
		dan.setOnClickListener(danListener);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
