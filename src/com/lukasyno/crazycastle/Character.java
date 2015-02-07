package com.lukasyno.crazycastle;

import com.lukasyno.crazycastle.Character.GESTURE;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

public class Character {
	private Activity context= null;
	private double x;
	private double y;
	private double center_X;
	private double center_Y;
	public boolean DirectionLeft = true;
	private boolean animationStopped = true;
	private ImageView CharacterRotateLeft;
	public ImageView CurrentCharacterEntity;
	private ImageView CharacterRotateRight;// = (ImageView)findViewById(R.id.Bunny_Mirror);
	private AnimationDrawable CharacterLeftAnimation;
	private AnimationDrawable CharacterRightAnimation;
	public GESTURE WalkStatus = GESTURE.STOP;
	
	private void UpdateCenterPosition(){
		center_X = x + CharacterRotateLeft.getWidth()/2.0;
		center_Y = y + CharacterRotateLeft.getHeight()/2.0;
	}
	public enum GESTURE {
		LEFTWALK, RIGHTWALK, STOP, DEATH, TRANSPARENT
	};

	private void stopAnimation() {
		CharacterLeftAnimation.stop();
		CharacterRightAnimation.stop();
		animationStopped = true;
	}
	public void setWalkStatus(GESTURE g){
		WalkStatus = g;
	}
	private void startAnimation() {
		CharacterLeftAnimation = (AnimationDrawable)CharacterRotateLeft.getDrawable();
		CharacterLeftAnimation.start();
		
		CharacterRightAnimation = (AnimationDrawable)CharacterRotateRight.getDrawable();
		CharacterRightAnimation.start();
		animationStopped = false;
	}
	public static final int DEATH_ZONE = 10000;
	private static final int STEP = 10;
	private static final float STEP_DX = 1.5f;
	
	private static final int RUN = 20;
	
	
	public Character(Activity context){
		this.context = context;
		CharacterRotateLeft = (ImageView)context.findViewById(R.id.Bunny);
		CharacterRotateRight = (ImageView)context.findViewById(R.id.Bunny_Mirror);
		CurrentCharacterEntity = CharacterRotateLeft;
		CharacterRotateRight.setY(DEATH_ZONE);
	}
	public void setPosition(double dx, double dy){
		x = dx; y = dy;
	}
	public void walk(GESTURE type){
		switch(type){
		case LEFTWALK:{
			CurrentCharacterEntity.setX(CurrentCharacterEntity.getX()-STEP_DX);
			if(animationStopped)
				startAnimation();
			break;
		}
		case RIGHTWALK:{
			CurrentCharacterEntity.setX(CurrentCharacterEntity.getX()+STEP_DX);
			if(animationStopped)
				startAnimation();
			break;
		}
		case STOP:{
			stopAnimation();
			break;
		}
		default:{
			break;
		}
		
		}
	}
	public void setAnimation(GESTURE type){
		switch(type){
		case LEFTWALK :{
			CharacterRotateLeft.setX((float)x);
			CharacterRotateLeft.setY((float)y);
			CharacterRotateRight.setX(DEATH_ZONE);
			CharacterRotateRight.setY(DEATH_ZONE);
			DirectionLeft = true;
			break;
		}
		case RIGHTWALK :{
			CharacterRotateRight.setX((float)x);
			CharacterRotateRight.setY((float)y);
			CharacterRotateLeft.setX(DEATH_ZONE);
			CharacterRotateLeft.setY(DEATH_ZONE);
			DirectionLeft = false;
			break;
		}
		case STOP :{
			break;
		}
		
		}
	}
}
