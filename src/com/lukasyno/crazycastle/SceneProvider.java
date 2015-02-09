package com.lukasyno.crazycastle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SceneProvider {
	private final int ROOMS_COUNT;//=4;
	private final int ScreenWidth = CastleEngine.ScreenWidth;
	private Random generator = new Random();
	private List<Integer> PadlockList = new ArrayList<Integer>();
	private BasicScene[] AllRooms;
	private BasicScene currentScene;

	public SceneProvider(int rooms) {
		ROOMS_COUNT = rooms;
		AllRooms = new BasicScene[ROOMS_COUNT];
		fillPadlockSet();
		onPopulateCastle();
		setCurrentScene(0);
	}
	public ArrayList<PointAndStage> getCurrentDoorPosition(){
		return currentScene.getDoorsOnScene();
	}
	public BasicScene getCurrentScene() {
		return currentScene;
	}

	public void setCurrentScene(int id) {
		currentScene = AllRooms[id];
	}

	private void fillPadlockSet() {
		PadlockList = new ArrayList<Integer>();
		//why 4 * ROOMS_COUNT? well, we have 4 states to go through the door:
		// we can go from stage:
		//(0 to 1), (1 to 0), (1 to 2), (2 to 1)
		
		for (int j = 1; j < 4 * ROOMS_COUNT-1; j++) {
			PadlockList.add(j);
		}
		PadlockList.add(0);
//		PadlockList.add(8);
//		PadlockList.add(7);
//		PadlockList.add(7);
//		PadlockList.add(4);
//
//		PadlockList.add(10);
//		PadlockList.add(1);
//		PadlockList.add(1);
//		PadlockList.add(12);
//		
//		PadlockList.add(2);
//		PadlockList.add(11);
//		PadlockList.add(11);
//		PadlockList.add(5);
//		
//		PadlockList.add(6);
//		PadlockList.add(9);
//		PadlockList.add(9);
//		PadlockList.add(3);
		
		
//		Collections.shuffle(PadlockList, generator);
	}

	private int randomColor() {
		return generator.nextInt();
	}

	private void onPopulateCastle() {
		for (int i = 0; i < ROOMS_COUNT; i++) {
			AllRooms[i] = new BasicScene(i, randomColor(), randomColor());
			AllRooms[i].setRandomBuddies((Integer) PadlockList.get(3 * i),
					(Integer) PadlockList.get(3 * i + 1),
					(Integer) PadlockList.get(3 * i + 2), (Integer) PadlockList.get(3 * i + 3));
			AllRooms[i].setValues(ScreenWidth);
		}
	}

}
