package com.lukasyno.crazycastle;

import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

public class CarrotManager {
	private static final int WTF_OFFSET_FOR_CARROT = 60;
	// TODO:
	// change 30 to offset!
	private final MainActivity context;
	private ImageView carrot;
	private boolean Visibility = false;
	// public BasicScene currentScene;
	public int currentCarrotIndex;

	public Point[] SceneVisiblePoints;
	public int CollectedCarrots = 0;

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
		TextView view = (TextView) context.findViewById(R.id.collectedCarrots);
		view.setBackgroundColor(Color.YELLOW);
	}

	public float AbsDiff(float x, float y) {
		return (x > y) ? (x - y) : (y - x);
	}

	public void setProperCarrot(int currentScene) {
		int i = 0;
		for (i = 0; i < SceneVisiblePoints.length; i++) {
			carrot.setVisibility(ImageView.INVISIBLE);
			if (currentScene == i) {
				carrot.setX(SceneVisiblePoints[i].x);
				carrot.setY(SceneVisiblePoints[i].y);
				carrot.setVisibility(ImageView.VISIBLE);
				Visibility = true;
				break;
			}
		}
	}

	public void DetachCarrot(int i) {
		++CollectedCarrots;
		carrot.setVisibility(ImageView.INVISIBLE);
		SceneVisiblePoints[i].x = WTF_OFFSET_FOR_CARROT
				+ context.generator.nextInt(CastleEngine.ScreenWidth
						- WTF_OFFSET_FOR_CARROT);
		SceneVisiblePoints[i].y = getRandomStage();
		Visibility = false;
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

	public void setVisibility(boolean b) {
		if (b)
			carrot.setVisibility(ImageView.VISIBLE);
		else
			carrot.setVisibility(ImageView.INVISIBLE);
	}

	public void update() {
		TextView view = (TextView) context.findViewById(R.id.collectedCarrots);
		view.setText("" + CollectedCarrots);

	}

	public boolean isVisible() {

		return Visibility;
	}
}
