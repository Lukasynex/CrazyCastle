package com.example.epilepsy;

import android.os.Handler;


public class EvilBugs{
	private static boolean isStarted = false;
	private static Handler handler = new Handler();
	private static MainActivity activity;

	private static Runnable stepTimer = new Runnable() { 
		@Override
		public void run() {
			activity.EvilWalk();
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