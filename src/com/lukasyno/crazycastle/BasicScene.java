package com.lukasyno.crazycastle;

import java.util.Random;

import android.graphics.Color;

public class BasicScene {
	private Random generator = new Random();
	public static final int COLOR_SPECTRUM = 16777215;

	public final int ID;
	public int topKey, middleKey, bottomKey, BackgroundType, topDoorPosition,
			middleDoorPosition, bottomDoorPosition;
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
	private int getRandomColor(){
		int r = generator.nextInt(255);
		int g = generator.nextInt(255);
		int b = generator.nextInt(255);
		return Color.rgb(r,g,b);
	}

	public void setValues(int ScreenWidth) {
		FloorColor = getRandomColor();
		DoorColor = Color.rgb(255-Color.red(FloorColor), 
				255-Color.green(FloorColor), 255-Color.blue(FloorColor));
//		FloorColor = Color.WHITE;
//		DoorColor = Color.YELLOW;
		BackgroundType = (DoorColor + FloorColor) % 5;
		
		topDoorPosition = (int) (ScreenWidth/2 + ((generator.nextFloat()-0.5f)*ScreenWidth/2) + 1);
		middleDoorPosition = (int) (ScreenWidth/2 + ((generator.nextFloat()-0.5f)*ScreenWidth/2) + 1);
		bottomDoorPosition = (int) (ScreenWidth/2 + ((generator.nextFloat()-0.5f)*ScreenWidth/2) + 1);
//		topDoorPosition = 10 + generator.nextInt(ScreenWidth-20);
//		middleDoorPosition = 10 + generator.nextInt(ScreenWidth-20);//(int) (ScreenWidth/2 + ((generator.nextFloat()-0.5f)*ScreenWidth/2) + 1);
//		bottomDoorPosition = 10 + generator.nextInt(ScreenWidth-20);//(int) (ScreenWidth/2 + ((generator.nextFloat()-0.5f)*ScreenWidth/2) + 1);
	
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
