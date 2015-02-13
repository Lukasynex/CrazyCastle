package com.lukasyno.crazycastle;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Color;

public class BasicScene {
	private BasicScene next;
	private BasicScene previous;
	private static int indexForScenes = 0;
	private ArrayList<PointAndStage> DoorsOnScene = new ArrayList<PointAndStage>();
	private Random generator = new Random();
	public static final int COLOR_SPECTRUM = 16777215;

	public final int ID;
	public int topKey, middleUpKey, middleDownKey, bottomKey, BackgroundType, topDoorPosition,
			middleDoorPosition, bottomDoorPosition;

	public int FloorColor;
	public int DoorColor;
	//getters & setters
	public void setNext(BasicScene nxt){
		next = nxt;
	}
	public void setPrev(BasicScene prv){
		previous= prv;
	}
	public BasicScene getNext(){
		return next;
	}
	public BasicScene getPrev(){
		return previous;
	}
	
	public ArrayList<PointAndStage> getDoorsOnScene(){
		return DoorsOnScene;
	}
	
	public BasicScene(int id) {
		ID = id;
		topKey = middleUpKey = middleDownKey = bottomKey = -1;
	}

	public BasicScene(int id, int FloorColor, int DoorColor) {
		ID = id;
		topKey = middleUpKey = middleDownKey = bottomKey = -1;
		this.FloorColor = FloorColor;
		this.DoorColor = DoorColor;
	}

	private int getRandomColor() {
		int r = generator.nextInt(255);
		int g = generator.nextInt(255);
		int b = generator.nextInt(255);
		return Color.rgb(r, g, b);
	}

	public void setValues(int ScreenWidth) {
		FloorColor = getRandomColor();
		DoorColor = Color.rgb(255 - Color.red(FloorColor),
				255 - Color.green(FloorColor), 255 - Color.blue(FloorColor));
		// FloorColor = Color.WHITE;
		// DoorColor = Color.YELLOW;
		BackgroundType = (indexForScenes) % 5;
		++indexForScenes;
		topDoorPosition = (int) (ScreenWidth / 2
				+ ((generator.nextFloat() - 0.5f) * ScreenWidth / 2) + 1);
		middleDoorPosition = (int) (ScreenWidth / 2
				+ ((generator.nextFloat() - 0.5f) * ScreenWidth / 2) + 1);
		bottomDoorPosition = (int) (ScreenWidth / 2
				+ ((generator.nextFloat() - 0.5f) * ScreenWidth / 2) + 1);

		DoorsOnScene.add(new PointAndStage(topDoorPosition, topDoorPosition
				+ CastleEngine.DoorWidth, 0));
		DoorsOnScene.add(new PointAndStage(middleDoorPosition, middleDoorPosition
				+ CastleEngine.DoorWidth, 1));
		DoorsOnScene.add(new PointAndStage(bottomDoorPosition, bottomDoorPosition
				+ CastleEngine.DoorWidth, 2));

	}

	public boolean isPopulatingDone() {
		return (topKey == -1 || middleUpKey == -1 || middleDownKey == -1 || bottomKey == -1);
	}

	public void setRandomBuddies(int top, int midUp, int midDo, int bot) {
		topKey = top;
		middleUpKey = midUp;
		middleDownKey = midDo;
		
		bottomKey = bot;
		
	}
}
