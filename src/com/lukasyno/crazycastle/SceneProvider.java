package com.lukasyno.crazycastle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SceneProvider {
	private final int ROOMS_COUNT;
	private int ScreenWidth;
	private Random generator = new Random();
	private List<Integer> PadlockList = new ArrayList<Integer>();
	private BasicScene[] AllRooms = null;
	private BasicScene currentScene = null;

	public SceneProvider(int rooms, int screenDim) {
		ROOMS_COUNT = rooms;
		ScreenWidth = screenDim;
		AllRooms = new BasicScene[ROOMS_COUNT];
		fillPadlockSet();
		onPopulateCastle();
		setCurrentScene(0);
	}

	public BasicScene getCurrentScene() {
		return currentScene;
	}

	public void setCurrentScene(int id) {
		currentScene = AllRooms[id];
	}

	private void fillPadlockSet() {
		PadlockList = new ArrayList<Integer>();
		for (int j = 0; j < 3 * ROOMS_COUNT; j++) {
			PadlockList.add(j);
		}
		Collections.shuffle(PadlockList, generator);
	}

	private int randomColor() {
		return generator.nextInt();
	}

	private void onPopulateCastle() {
		for (int i = 0; i < ROOMS_COUNT; i++) {
			AllRooms[i] = new BasicScene(i, randomColor(), randomColor());
			AllRooms[i].setRandomBuddies((Integer) PadlockList.get(3 * i),
					(Integer) PadlockList.get(3 * i + 1),
					(Integer) PadlockList.get(3 * i + 2));
			AllRooms[i].setValues(ScreenWidth);
		}
	}

}
