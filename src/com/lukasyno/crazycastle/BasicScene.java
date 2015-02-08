package com.lukasyno.crazycastle;

import java.util.Random;

import android.graphics.Color;

public class BasicScene {
	public static final int COLOR_SPECTRUM = 16777215;

	public final int ID;
	public int topKey, middleKey, bottomKey, BackgroundType, topDoorPosition=150,
			middleDoorPosition=100, bottomDoorPosition=200;
	// private int RoomsCount;

	public int FloorColor;
	public int DoorColor;

	enum FLOORMASK {
		TOP, MID, BOT
	}

	public BasicScene(int id) {
		ID = id;
		topKey = middleKey = bottomKey = -1;
	}
	public BasicScene(int id, int FloorColor, int DoorColor){
		ID = id;
		topKey = middleKey = bottomKey = -1;
		this.FloorColor = FloorColor;
		this.DoorColor = DoorColor;
	}

	public void setValues(Random rand, int ScreenWidth) {
//		FloorColor = rand.nextInt(COLOR_SPECTRUM);
//		DoorColor = rand.nextInt(COLOR_SPECTRUM);
		FloorColor = Color.WHITE;
		DoorColor = Color.YELLOW;
		BackgroundType = (DoorColor + FloorColor) % 5;
		topDoorPosition = (int) (ScreenWidth * (rand.nextFloat() - 0.5f) / 2
				+ 1 + ScreenWidth / 2);
		middleDoorPosition = (int) (ScreenWidth * (rand.nextFloat() - 0.5f) / 2
				+ 1 + ScreenWidth / 2);
		bottomDoorPosition = (int) (ScreenWidth * (rand.nextFloat() - 0.5f) / 2
				+ 1 + ScreenWidth / 2);
	}

	public boolean isPopulatingDone() {
		return (topKey == -1 || middleKey == -1 || bottomKey == -1);
	}

	public void setRandomBuddies(int top, int mid, int bot) {
		topKey = top;
		middleKey = mid;
		bottomKey = bot;
	}
}
