package com.lukasyno.crazycastle;

import android.os.Handler;


public class BugsWalks{
	private static boolean isStarted = false;
	private static Handler handler = new Handler();
	private static MainActivity activity;

	private static Runnable stepTimer = new Runnable() { 
		@Override
		public void run() {
			activity.Walk();
			handler.postDelayed(this, 10);
		}
	};
	public static void start(MainActivity view) {
		if(!isStarted) {
			handler.postDelayed(stepTimer, 0);
			isStarted = true;
		}
		activity = view;
	}
}