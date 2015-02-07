package com.example.epilepsy;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ImageView;


public class MainActivity extends Activity {
	/**
	 * custom classes for game!!!1
	 */
	CastleEngine CrazyCastle;
	Character character;
	
	
	Random generator = new Random();
	int BunnyStep = 20;
	int HEIGHT=1000;
	boolean DirectionLeft = false;
	boolean DirectionLeftForEvil = false;
	int CollectedCarrots =  0;
	int CurrentStage = 0;
	int time=0;
	int PreviousStage = 0;
	float X0,Y0;
	int FingerSize = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		CrazyCastle = (CastleEngine)findViewById(R.id.castle_view);
		character = new Character(this);
		
//		ImageView bugsL = (ImageView)findViewById(R.id.Bunny);
//		ImageView bugsR = (ImageView)findViewById(R.id.Bunny_Mirror);
//		ImageView carrot = (ImageView)findViewById(R.id.Carrot);
//		ImageView EvilR = (ImageView)findViewById(R.id.Evil_Mirror);
//		ImageView EvilL = (ImageView)findViewById(R.id.Evil);
//		EvilR.setY(1000);
//		EvilL.setY(1000);
//		
//		carrot.setX(200);
//		carrot.setY(321);
//		getX0(bugsL.getX());
//		setMirrorProperly(bugsR, bugsL);
//		AnimationDrawable animR = (AnimationDrawable)bugsL.getDrawable();
//		animR.start();
//		AnimationDrawable animL = (AnimationDrawable)bugsR.getDrawable();
//		animL.start();
//		
//		StopBugs.start(this);
//		EvilBugs.start(this);
	}
    
    
    //thread for stopping bugs
    public void StopWalk(){
		ImageView AnimationLeft = (ImageView)findViewById(R.id.Bunny_Mirror);
		ImageView AnimationRight = (ImageView)findViewById(R.id.Bunny);
		ImageView ref = (DirectionLeft) ? AnimationLeft:AnimationRight;
		ImageView carrot = (ImageView)findViewById(R.id.Carrot);

		++time;
    	if(time>50)
    	{
    	AnimationDrawable animR = (AnimationDrawable)AnimationRight.getDrawable();
			animR.stop();
			AnimationDrawable animL = (AnimationDrawable)AnimationLeft.getDrawable();
			animL.stop();
    	}
    	if(AbsDiff(ref.getX(),carrot.getX()) < 20 &&
  		   AbsDiff(ref.getY(),carrot.getY()) < 100){
    		++CollectedCarrots;
    		float dx = CrazyCastle.ScreenWidth/2;
    		dx += (generator.nextFloat()-0.5f)*(2*CrazyCastle.ScreenWidth/3);
    		carrot.setX(dx);
			boolean RandomStage = generator.nextBoolean();
    		if(carrot.getY()==121){
    			if(RandomStage)
    				carrot.setY(321);
    			else
    				carrot.setY(500);
    		}
    		else if(carrot.getY()==500){
    			if(RandomStage)
    				carrot.setY(121);
    			else
    				carrot.setY(321);
    		}
    		else{
    			if(RandomStage)
    				carrot.setY(500);
    			else
    				carrot.setY(121);
    		}
    	}
    }
   
    public float AbsDiff(float x,float y){
    	return (x>y) ? (x-y) : (y-x);
    }
    public void setMirrorProperly(ImageView invisible, ImageView visible){
    	invisible.setY(visible.getY()-HEIGHT);
    	invisible.setX(visible.getX());
	}
    @Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		if(event.getActionMasked() == MotionEvent.ACTION_DOWN) {
			time=0;
			ImageView AnimationLeft = (ImageView)findViewById(R.id.Bunny_Mirror);
			ImageView AnimationRight = (ImageView)findViewById(R.id.Bunny);
			ImageView ref = (DirectionLeft)? AnimationLeft: AnimationRight;
			ImageView refMirror = (!DirectionLeft)? AnimationLeft: AnimationRight;
			
			//variables needed to detect door
			ArrayList<PointAndStage> list = CrazyCastle.DoorsCoordinates;
			PointAndStage pt = list.get(CurrentStage);
			float FH = CrazyCastle.Floor_Height;
			float Y = event.getY() - FingerSize;
			//if we want to go downstairs to stage 1:
			if(CurrentStage==0 &&
			   ref.getX()>pt.DoorStart && 
			   ref.getX() < pt.DoorEnd &&
			   6*FH < Y){
				CurrentStage=1;
				PreviousStage = 0;
			    pt=list.get(CurrentStage);
			    ref.setX(pt.DoorStart);
			    ref.setY((11-3)*FH);
			    setMirrorProperly(refMirror,ref);
			}
			//if we want to go downstairs to stage 2:
			else if(CurrentStage==1 &&
					   ref.getX()>pt.DoorStart && 
					   ref.getX() < pt.DoorEnd &&
					   12*FH<Y){
				CurrentStage=2;
				PreviousStage = 1;
			    pt=list.get(CurrentStage);
			    ref.setX(pt.DoorStart);
			    ref.setY((17-3)*FH);
			    setMirrorProperly(refMirror,ref);
			    }

			//if we want to go upstairs to stage 0:
			else if(CurrentStage==1 &&
					   ref.getX()>pt.DoorStart && 
					   ref.getX() < pt.DoorEnd &&
					   6*FH>Y){
				CurrentStage=0;
				PreviousStage = 1;
			    pt=list.get(CurrentStage);
			    ref.setX(pt.DoorStart);
			    ref.setY((5-3)*FH);
			    setMirrorProperly(refMirror,ref);
			}

			//if we want to go upstairs to stage 1:
			else if(CurrentStage==2 &&
					   ref.getX()>pt.DoorStart && 
					   ref.getX() < pt.DoorEnd &&
					   12*FH>Y){
				CurrentStage=1;
				PreviousStage = 2;
			    pt=list.get(CurrentStage);
			    ref.setX(pt.DoorStart);
			    ref.setY((11-3)*FH);
			    setMirrorProperly(refMirror,ref);
			}
			if(AnimationRight.getX()>event.getX()){
				if(DirectionLeft){ //if left, then left, right is invisible on the screen
					AnimationLeft.setX(AnimationLeft.getX()-BunnyStep);
					AnimationRight.setX(AnimationLeft.getX());
					AnimationLeft.setY(AnimationLeft.getY());
					AnimationRight.setY(AnimationLeft.getY()+HEIGHT);
				}
				else{//if right then left:
					AnimationRight.setX(AnimationLeft.getX()-BunnyStep);
					AnimationLeft.setX(AnimationRight.getX());
					AnimationRight.setY(AnimationRight.getY()+HEIGHT);
					AnimationLeft.setY(AnimationRight.getY()-HEIGHT);
				}
				DirectionLeft=true;
			}
			if(AnimationRight.getX()<event.getX()){
				if(!DirectionLeft){ //if right then right, left is invisible on the screen
					AnimationRight.setX(AnimationRight.getX()+BunnyStep);
					AnimationLeft.setX(AnimationRight.getX());
					
					AnimationRight.setY(AnimationRight.getY());
					AnimationLeft.setY(AnimationRight.getY()+HEIGHT);
				}
				else{//if left then right
					AnimationRight.setX(AnimationLeft.getX()+BunnyStep);
					AnimationLeft.setX(AnimationRight.getX());
					
					AnimationRight.setY(AnimationRight.getY()-HEIGHT);
					AnimationLeft.setY(AnimationRight.getY()+HEIGHT);
				}	
				DirectionLeft=false;
			}
			AnimationDrawable animR = (AnimationDrawable)AnimationRight.getDrawable();
			animR.start();
			AnimationDrawable animL = (AnimationDrawable)AnimationLeft.getDrawable();
			animL.start();
		}return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.restart) {
			RestartGame();
			return true;
			}
		if(id == R.id.action_settings){
			//TODO: Options if needed
		}
		
		return super.onOptionsItemSelected(item);
	}
    public void RestartGame(){
    	ImageView bugsL = (ImageView)findViewById(R.id.Bunny);
    	ImageView bugsR = (ImageView)findViewById(R.id.Bunny_Mirror);
    	bugsL.setX(X0);
    	bugsL.setY(2*CrazyCastle.Floor_Height);
		setMirrorProperly(bugsR, bugsL);
		AnimationDrawable animR = (AnimationDrawable)bugsL.getDrawable();
		animR.start();
		AnimationDrawable animL = (AnimationDrawable)bugsR.getDrawable();
		animL.start();
    }
	public void getX0(float x){ X0=x; }
	public void EvilWalk(){
		if(CollectedCarrots>3){
		ImageView evilRIGHT =  (ImageView)findViewById(R.id.Evil_Mirror);
		ImageView evilLEFT =  (ImageView)findViewById(R.id.Evil);
		ImageView ref1 =(ImageView)findViewById(R.id.Bunny_Mirror);
		ImageView ref2 =(ImageView)findViewById(R.id.Bunny);
		ImageView ref = (DirectionLeft)? ref1: ref2;
		//TODO:create  Bugs death and add change the anim when he 
		//TODO: collides with Evil Rabbit. Also bitmap failgame could be added
		
		
		if(DirectionLeftForEvil){
			evilLEFT.setX(evilLEFT.getX()+1.5f);
			evilRIGHT.setX(evilLEFT.getX());
			evilLEFT.setY((17-3)*CrazyCastle.Floor_Height);
			evilRIGHT.setY(evilLEFT.getY()+HEIGHT);
			if(evilLEFT.getX()>CrazyCastle.ScreenWidth-30)
				DirectionLeftForEvil=false;
		}
		else if(!DirectionLeftForEvil){
			evilLEFT.setX(evilLEFT.getX()-1.5f);
			evilRIGHT.setX(evilLEFT.getX());
			evilRIGHT.setY((17-3)*CrazyCastle.Floor_Height);
			evilLEFT.setY(evilLEFT.getY()+HEIGHT);
			if(evilLEFT.getX()<0)
				DirectionLeftForEvil=true;
		}
		AnimationDrawable animL = (AnimationDrawable)evilRIGHT.getDrawable();
		animL.start();
		AnimationDrawable animR = (AnimationDrawable)evilLEFT.getDrawable();
		animR.start();
		
		}
	}
}
class Point {
    float x, y;
   // public Point(){}
    public Point(float dx, float dy){
    	x=dx;y=dy;
    }
    @Override
    public String toString() {
        return x + ", " + y;
    }
}


