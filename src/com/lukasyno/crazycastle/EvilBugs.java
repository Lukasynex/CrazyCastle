package com.lukasyno.crazycastle;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.widget.ImageView;

public class EvilBugs {
	private Activity context;
	public boolean isEvilLeft = true;
	private ImageView EvilRotateLeft;
	public ImageView CurrentEvilEntity;
	private ImageView EvilRotateRight;

	private AnimationDrawable EvilLeftAnimation;
	private AnimationDrawable EvilRightAnimation;
	private SceneProvider sceneProvider;

	private static final int STEP = 10;
	private static final float STEP_DX = 1.5f;

	private static final int RUN = 20;
	private static final int WTF_OFFSET = 50;
	private int currentStage = 2;

	public void setCurrentStage(int i) {
		currentStage = Math.abs(i % 3);
	}

	public EvilBugs(Activity context, SceneProvider sceneProvider) {
		this.context = context;
		this.sceneProvider = sceneProvider;
		EvilRotateRight = (ImageView) context.findViewById(R.id.Evil_Mirror);
		EvilRotateLeft = (ImageView) context.findViewById(R.id.Evil);
		EvilRotateRight.setY(Character.DEATH_ZONE);
		EvilRotateRight.setY(Character.DEATH_ZONE);
		setupAnimation();
	}

	private void setupAnimation() {
		EvilLeftAnimation = (AnimationDrawable) EvilRotateRight.getDrawable();
		EvilRightAnimation = (AnimationDrawable) EvilRotateLeft.getDrawable();
		startAnimation();
		stopAnimation();

	}

	public void EvilWalk() {

//		if (true || sceneProvider.getCurrentScene().ID == 2) {
			EvilRotateRight.setVisibility(ImageView.VISIBLE);
			EvilRotateLeft.setVisibility(ImageView.VISIBLE);

			if (isEvilLeft) {
				EvilRotateLeft.setX(EvilRotateLeft.getX() + STEP_DX);
				EvilRotateRight.setX(EvilRotateLeft.getX());
				EvilRotateLeft.setY((5 + 6 * currentStage - 3)
						* CastleEngine.Floor_Height);
				EvilRotateRight.setY(EvilRotateLeft.getY()
						+ Character.DEATH_ZONE);

				if (EvilRotateLeft.getX() > CastleEngine.ScreenWidth
						- WTF_OFFSET)
					isEvilLeft = false;
			} else if (!isEvilLeft) {
				EvilRotateLeft.setX(EvilRotateLeft.getX() - STEP_DX);
				EvilRotateRight.setX(EvilRotateLeft.getX());
				EvilRotateRight.setY((5 + 6 * currentStage - 3)
						* CastleEngine.Floor_Height);
				EvilRotateLeft.setY(EvilRotateLeft.getY()
						+ Character.DEATH_ZONE);
				if (EvilRotateLeft.getX() < 0)
					isEvilLeft = true;
			}
			startAnimation();
//		} else {
//			EvilRotateRight.setVisibility(ImageView.INVISIBLE);
//			EvilRotateLeft.setVisibility(ImageView.INVISIBLE);
//
//		}
	}

	private void stopAnimation() {
		EvilLeftAnimation.stop();
		EvilRightAnimation.stop();
	}

	private void startAnimation() {
		EvilLeftAnimation.start();
		EvilRightAnimation.start();
	}

	public void onRestart() {
	}
}