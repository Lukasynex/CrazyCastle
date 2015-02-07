package com.example.epilepsy;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

public class Character {
	private Activity context= null;
	private double x;
	private double y;
	private double center_X;
	private double center_Y;
	private boolean DirectionLeft = true;
	private ImageView CharacterRotateLeft;
	private ImageView CharacterRotateRight;// = (ImageView)findViewById(R.id.Bunny_Mirror);
	private AnimationDrawable CharacterLeftAnimation;
	private AnimationDrawable CharacterRightAnimation;
	
	private void UpdateCenterPosition(){
		center_X = x + CharacterRotateLeft.getWidth()/2.0;
		center_Y = y + CharacterRotateLeft.getHeight()/2.0;
	}
	public enum GESTURE {
		LEFTWALK, RIGHTWALK, STOP, DEATH, TRANSPARENT
	};
	private void setupAnimation() {
		CharacterLeftAnimation = (AnimationDrawable)CharacterRotateLeft.getDrawable();
		CharacterLeftAnimation.start();
		
		CharacterRightAnimation = (AnimationDrawable)CharacterRotateRight.getDrawable();
		CharacterRightAnimation.start();
		
	}
	private static final int DEATH_ZONE = 10000;
	private static final int STEP = 10;
	private static final int RUN = 20;
	
	
	public Character(Activity context){
		this.context = context;
		CharacterRotateLeft = (ImageView)context.findViewById(R.id.Bunny);
		CharacterRotateRight = (ImageView)context.findViewById(R.id.Bunny_Mirror);
	}
	public void setPosition(double dx, double dy){
		x = dx; y = dy;
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
