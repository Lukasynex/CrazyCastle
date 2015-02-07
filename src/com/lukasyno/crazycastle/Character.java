package com.lukasyno.crazycastle;


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
		center_X = x + CurrentCharacterEntity.getWidth()/2.0;
		center_Y = y + CurrentCharacterEntity.getHeight()/2.0;
	}
	private void UpdatePos(){
		x = CurrentCharacterEntity.getX();
		y = CurrentCharacterEntity.getY();
	}
	public enum GESTURE {
		LEFTWALK, RIGHTWALK, STOP, DEATH, TRANSPARENT
	};

	
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
		setupAnimation();
		setPosition(CharacterRotateLeft.getX(), CharacterRotateLeft.getY());
	}
	private void setPosition(double dx, double dy){
		x = dx; y = dy;
	}
	public void setMirrorProperly(ImageView invisible, ImageView visible){
    	invisible.setY(visible.getY()-DEATH_ZONE);
    	invisible.setX(visible.getX());
	}
	public void walk(GESTURE type){
		UpdatePos();
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
		float dx = CurrentCharacterEntity.getX();
		float dy = CurrentCharacterEntity.getY();
		
		switch(type){
		case LEFTWALK :{
			CharacterRotateLeft.setX((float)dx);
			CharacterRotateLeft.setY((float)dy);
			CharacterRotateRight.setX(DEATH_ZONE);
			CharacterRotateRight.setY(DEATH_ZONE);
			DirectionLeft = true;
			CurrentCharacterEntity = CharacterRotateLeft;
			break;
		}
		case RIGHTWALK :{
			CharacterRotateRight.setX((float)dx);
			CharacterRotateRight.setY((float)dy);
			CharacterRotateLeft.setX(DEATH_ZONE);
			CharacterRotateLeft.setY(DEATH_ZONE);
			DirectionLeft = false;
			CurrentCharacterEntity = CharacterRotateRight;
			break;
		}
		case STOP :{
			break;
		}
		
		}
	}
	private void stopAnimation() {
		CharacterLeftAnimation.stop();
		CharacterRightAnimation.stop();
		animationStopped = true;
	}
	private void setupAnimation(){
		CharacterLeftAnimation = (AnimationDrawable)CharacterRotateLeft.getDrawable();
		CharacterRightAnimation = (AnimationDrawable)CharacterRotateRight.getDrawable();
		startAnimation();
		stopAnimation();
	}
	private void startAnimation() {
		CharacterLeftAnimation.start();
		CharacterRightAnimation.start();
		animationStopped = false;
	}
	public void setWalkStatus(GESTURE f){
		WalkStatus = f;
	}
}
