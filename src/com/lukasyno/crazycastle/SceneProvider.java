package com.lukasyno.crazycastle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import android.content.Loader.ForceLoadContentObserver;

public class SceneProvider {
	public final int ROOMS_COUNT;
	private final int ScreenWidth = CastleEngine.ScreenWidth;
	private Random generator = new Random();

	private List<Integer> PadlockList = new ArrayList<Integer>();

	private BasicScene[] AllRooms;
	private BasicScene currentScene;
	private BasicScene previousScene;

	public SceneProvider(int rooms) {
		ROOMS_COUNT = rooms;
		AllRooms = new BasicScene[ROOMS_COUNT];
		fillPadlockSet();
		onPopulateCastle();
		setCurrentScene(0);
		setPreviousScene(0);
	}

	public ArrayList<PointAndStage> getCurrentDoorPosition() {
		return currentScene.getDoorsOnScene();
	}

	public BasicScene getCurrentScene() {
		return currentScene;
	}

	public BasicScene getPreviousScene() {
		return previousScene;
	}

	public void setPreviousScene(int id) {
		previousScene = AllRooms[id];
	}

	public void setCurrentScene(int id) {
		previousScene = currentScene;
		currentScene = AllRooms[id];
	}

	private void fillPadlockSet() {
		PadlockList = new ArrayList<Integer>();
		// PadlockList.add(9);
		// PadlockList.add(4);
		// PadlockList.add(7);
		// PadlockList.add(13);
		//
		// PadlockList.add(3);
		// PadlockList.add(5);
		// PadlockList.add(2);
		// PadlockList.add(15);
		//
		// PadlockList.add(8);
		// PadlockList.add(0);
		// PadlockList.add(11);
		// PadlockList.add(12);
		//
		// PadlockList.add(1);
		// PadlockList.add(6);
		// PadlockList.add(10);
		// PadlockList.add(14);

		// for (int j = 0; j < 4 * ROOMS_COUNT; j++) {
		// PadlockList.add(j);
		// }
		// Collections.shuffle(PadlockList, generator);
	}

	private int randomColor() {
		return generator.nextInt();
	}

	private void onPopulateCastle() {
		for (int i = 0; i < ROOMS_COUNT; i++) {
			AllRooms[i] = new BasicScene(i, randomColor(), randomColor());
			
			AllRooms[i].setValues(ScreenWidth);
		}
	}

}
