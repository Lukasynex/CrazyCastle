package com.lukasyno.crazycastle;

import android.os.Handler;

public class Loop {
	private static boolean isStarted = false;
	private static Handler handler = new Handler();
	private static MainActivity activity;
	public static boolean isActive = true;

	private static Runnable stepTimer = new Runnable() {
		@Override
		public void run() {
			if (isActive) {
				activity.LoopActions();
				handler.postDelayed(this, 10);
			}
		}

	};

	public static void start(MainActivity view) {
		if (!isStarted) {
			handler.postDelayed(stepTimer, 0);
			isStarted = true;
		}
		activity = view;
	}
}