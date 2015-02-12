package com.lukasyno.crazycastle;

import java.security.GeneralSecurityException;

import android.util.Pair;
import android.widget.ImageView;

public class CarrotManager {
	// TODO:
	// change 30 to offset!
	private final MainActivity context;
	private ImageView carrot;
	//public BasicScene currentScene;
	public int currentCarrotIndex;

	public Point[] SceneVisiblePoints;
	private int CollectedCarrots = 0;

	public float AbsDiff(float x, float y) {
		return (x > y) ? (x - y) : (y - x);
	}

	public void setProperCarrot(int currentScene) {
		int i = 0;
		for (i = 0; i < SceneVisiblePoints.length; i++) {
			carrot.setVisibility(ImageView.INVISIBLE);
			if (currentScene == i){
				carrot.setX(SceneVisiblePoints[i].x);
				carrot.setY(SceneVisiblePoints[i].y);
				carrot.setVisibility(ImageView.VISIBLE);
				break;
			}
		}
	}

	public void DetachCarrot(int i) {
		++CollectedCarrots ;
		carrot.setVisibility(ImageView.INVISIBLE);
		SceneVisiblePoints[i].x = 30+context.generator.nextInt(CastleEngine.ScreenWidth-30);
		SceneVisiblePoints[i].y = getRandomStage();
	}

	private final int ROOMS_COUNT;

	public boolean CollidesWith(ImageView collider) {
		return (AbsDiff(collider.getX(), carrot.getX()) < 20 && AbsDiff(
				collider.getY(), carrot.getY()) < 150);
	}

	private float getRandomStage() {
		int i = context.generator.nextInt(3);
		switch (i) {
		case 0:
			return 321;
		case 1:
			return 500;
		default:
			return 121;
		}
	}

	@SuppressWarnings("unchecked")
	public CarrotManager(MainActivity context) {
		this.context = context;
		carrot = (ImageView) context.findViewById(R.id.Carrot);
		carrot.setX(30 + context.generator
				.nextInt(CastleEngine.ScreenWidth - 30));
		carrot.setY(500);
		this.ROOMS_COUNT = context.sceneProvider.ROOMS_COUNT;
		SceneVisiblePoints = new Point[ROOMS_COUNT];
		for (int i = 0; i < SceneVisiblePoints.length; i++) {
			SceneVisiblePoints[i] = new Point(
					context.generator.nextInt(CastleEngine.ScreenWidth),
					getRandomStage());
		}
	}

	public void setVisibility(boolean b) {
		if (b)
			carrot.setVisibility(ImageView.VISIBLE);
		else
			carrot.setVisibility(ImageView.INVISIBLE);
	}

	public void setCarrot(int stage) {
		switch (stage) {
		case 0: {
			break;
		}
		case 1: {
			break;
		}
		case 2: {
			break;
		}

		}
	}

}
