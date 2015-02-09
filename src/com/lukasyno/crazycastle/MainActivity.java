package com.lukasyno.crazycastle;

import java.util.ArrayList;
import java.util.Random;

import com.lukasyno.crazycastle.Character.GESTURE;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


/**
 * TODO: castle engine fix door moving around fix set proper floor/door values
 * fix walking through door, set Bugs in proper position to do so -> door
 * coordinates as field in every scene
 * 
 * @author lukasz
 * 
 */
public class MainActivity extends Activity {
	/**
	 * custom classes for game!!!1
	 */
	public ArrayList<Pair<Integer,Integer> > track = new ArrayList<Pair<Integer,Integer> >();
	public Pair<Integer,Integer> temporaryPair = new Pair<Integer, Integer>(0,0);
	public int indexForTrack = 0;
	public CastleEngine CrazyCastle;
	public SceneProvider sceneProvider;
	Character character;

	// private final int ROOMS_COUNT = 4;
	// private int ScreenWidth = 480;
	// private List<Integer> PadlockList = new ArrayList<Integer>();
	// private BasicScene[] AllRooms = new BasicScene[ROOMS_COUNT];
	// private BasicScene currentScene;

	Random generator = new Random();
	int BunnyStep = 20;
	int HEIGHT = 1000;
	boolean DirectionLeft = false;
	boolean DirectionLeftForEvil = false;
	int CollectedCarrots = 0;
	int CurrentStage = 0;
	int time = 0;
	int PreviousStage = 0;
	float X0, Y0;
	int FingerSize = 100;

	//animation stuff
//	final AnimatorSet mAnimationSet = new AnimatorSet();

	Animation fade;
	
	@SuppressLint("UseValueOf")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		for (int i = 0; i < 11; i++) {
			track.add(Pair.create(i%3, i+1));
		}
		track.add(Pair.create(2, 0));
		track.add(Pair.create(1, 0));
		
		
		
		sceneProvider = new SceneProvider(4);

		CrazyCastle = (CastleEngine) findViewById(R.id.castle_view);
		CrazyCastle.setBackground(getResources().getDrawable(R.drawable.wall));
		CrazyCastle.setScene(sceneProvider.getCurrentScene());
		View myView = (View)findViewById(R.id.mainLayout);

//		ObjectAnimator fadeOut = ObjectAnimator.ofFloat(myView, "alpha",  1f, .3f);
//		fadeOut.setDuration(500);
//		ObjectAnimator black = ObjectAnimator.ofFloat(myView, "alpha", .3f,.3f);
//		fadeOut.setDuration(500);
//		ObjectAnimator fadeIn = ObjectAnimator.ofFloat(myView, "alpha", .3f, 1f);
//		fadeIn.setDuration(500);
//
//		mAnimationSet.play(fadeIn).after(black).after(fadeOut);
//
//		mAnimationSet.addListener(new AnimatorListenerAdapter() {
//		    @Override
//		    public void onAnimationEnd(Animator animation) {
//		        super.onAnimationEnd(animation);
//		        //mAnimationSet.start();
//		    }
//		});
		
		fade = AnimationUtils.loadAnimation(this, R.anim.fade_out);
	     

	     fade.setAnimationListener(new AnimationListener() {
	         public void onAnimationEnd(Animation animation) 
	         {
	              
	         }

	         public void onAnimationRepeat(Animation arg0) {            
	         }

	         public void onAnimationStart(Animation arg0) {
	         }
	     });
		
		
		
		
		character = new Character(this);
		character.CurrentCharacterEntity.setX(character.CurrentCharacterEntity
				.getX() + 111);
		// character.CurrentCharacterEntity.setX(character.CurrentCharacterEntity.getX()+100);
		// ImageView bugsL = (ImageView)findViewById(R.id.Bunny);
		// ImageView bugsR = (ImageView)findViewById(R.id.Bunny_Mirror);
		ImageView carrot = (ImageView) findViewById(R.id.Carrot);
		carrot.setX(1000);
		ImageView EvilR = (ImageView) findViewById(R.id.Evil_Mirror);
		ImageView EvilL = (ImageView) findViewById(R.id.Evil);
		EvilR.setY(1000);
		EvilL.setY(1000);
		//
		// carrot.setX(200);
		// carrot.setY(321);
		// getX0(bugsL.getX());
		// setMirrorProperly(bugsR, bugsL);
		// AnimationDrawable animR = (AnimationDrawable)bugsL.getDrawable();
		// animR.start();
		// AnimationDrawable animL = (AnimationDrawable)bugsR.getDrawable();
		// animL.start();
		//
		BugsWalks.start(this);
		// EvilBugs.start(this);
	}

	// thread for stopping bugs
	public void Walk() {
		character.walk(character.WalkStatus);
		// ImageView AnimationLeft = (ImageView)findViewById(R.id.Bunny_Mirror);
		// ImageView AnimationRight = (ImageView)findViewById(R.id.Bunny);
		// ImageView ref = (DirectionLeft) ? AnimationLeft:AnimationRight;
		// ImageView carrot = (ImageView)findViewById(R.id.Carrot);

		// ++time;
		// if(time>50)
		// {
		// AnimationDrawable animR =
		// (AnimationDrawable)AnimationRight.getDrawable();
		// animR.stop();
		// AnimationDrawable animL =
		// (AnimationDrawable)AnimationLeft.getDrawable();
		// animL.stop();
		// }
		// if(AbsDiff(ref.getX(),carrot.getX()) < 20 &&
		// AbsDiff(ref.getY(),carrot.getY()) < 100){
		// ++CollectedCarrots;
		// float dx = CrazyCastle.ScreenWidth/2;
		// dx += (generator.nextFloat()-0.5f)*(2*CrazyCastle.ScreenWidth/3);
		// carrot.setX(dx);
		// boolean RandomStage = generator.nextBoolean();
		// if(carrot.getY()==121){
		// if(RandomStage)
		// carrot.setY(321);
		// else
		// carrot.setY(500);
		// }
		// else if(carrot.getY()==500){
		// if(RandomStage)
		// carrot.setY(121);
		// else
		// carrot.setY(321);
		// }
		// else{
		// if(RandomStage)
		// carrot.setY(500);
		// else
		// carrot.setY(121);
		// }
		// }
	}

	public float AbsDiff(float x, float y) {
		return (x > y) ? (x - y) : (y - x);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		if (event.getActionMasked() == MotionEvent.ACTION_UP) {
			character.setWalkStatus(GESTURE.STOP);
		}
		if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
			time = 0;
			ImageView AnimationLeft = (ImageView) findViewById(R.id.Bunny_Mirror);
			ImageView AnimationRight = (ImageView) findViewById(R.id.Bunny);
			ImageView ref = (DirectionLeft) ? AnimationLeft : AnimationRight;
			ImageView refMirror = (!DirectionLeft) ? AnimationLeft
					: AnimationRight;

			// variables needed to detect door
			ArrayList<PointAndStage> list = sceneProvider
					.getCurrentDoorPosition();

			PointAndStage pt = list.get(CurrentStage);
			CrazyCastle.setDoorCoordinates(list);

			float FloorHeight = CastleEngine.Floor_Height;
			float Y = event.getY() - FingerSize;
			// if we want to go downstairs to stage 1:
			if (CurrentStage == 0 && ref.getX() > pt.DoorStart
					&& ref.getX() < pt.DoorEnd && 6 * FloorHeight < Y) {
	

				View lay = (View)findViewById(R.id.mainLayout);
				lay.startAnimation(fade);
//				AlphaAnimation fadein = new AlphaAnimation(0, 1);
//			     //fadein.setFillAfter(true);
//			     fadein.setDuration(500);
//			     View lay = (View)findViewById(R.id.mainLayout);
//			     lay.startAnimation(fadein);

//				mAnimationSet.start();
				CurrentStage = generator.nextInt(3);
				PreviousStage = 0;
				sceneProvider
						.setCurrentScene(generator.nextInt(4));
				
				CrazyCastle.setScene(sceneProvider.getCurrentScene());
				list = sceneProvider.getCurrentDoorPosition();
				pt = list.get(CurrentStage);
				ref.setX(pt.DoorStart);
				ref.setY(Y_Position_Is_Essential(CurrentStage));
				character.setMirrorProperly(refMirror, ref);
			}
			// if we want to go downstairs to stage 2:
			else if (CurrentStage == 1 && ref.getX() > pt.DoorStart
					&& ref.getX() < pt.DoorEnd && 12 * FloorHeight < Y) {
				
				View lay = (View)findViewById(R.id.mainLayout);
				lay.startAnimation(fade);
				
				CurrentStage = generator.nextInt(3);
				PreviousStage = 1;
				sceneProvider
						.setCurrentScene(generator.nextInt(4));
				CrazyCastle.setScene(sceneProvider.getCurrentScene());
				list = sceneProvider.getCurrentDoorPosition();
				pt = list.get(CurrentStage);
				ref.setX(pt.DoorStart);
				// ref.setY((17 - 3) * FloorHeight);
				ref.setY(Y_Position_Is_Essential(CurrentStage));

				// setCharacterNearDoor(1, pt.DoorStart);
				character.setMirrorProperly(refMirror, ref);

			}
			// if we want to go upstairs to stage 0:
			else if (CurrentStage == 1 && ref.getX() > pt.DoorStart
					&& ref.getX() < pt.DoorEnd && 6 * FloorHeight > Y) {
				
				View lay = (View)findViewById(R.id.mainLayout);
				lay.startAnimation(fade);

				CurrentStage = generator.nextInt(3);
				PreviousStage = 1;
				sceneProvider
						.setCurrentScene(generator.nextInt(4));
				
				
				CrazyCastle.setScene(sceneProvider.getCurrentScene());
				list = sceneProvider.getCurrentDoorPosition();
				pt = list.get(CurrentStage);
				ref.setX(pt.DoorStart);
				ref.setY(Y_Position_Is_Essential(CurrentStage));

				character.setMirrorProperly(refMirror, ref);
			}
			// if we want to go upstairs to stage 1:
			else if (CurrentStage == 2 && ref.getX() > pt.DoorStart
					&& ref.getX() < pt.DoorEnd && 12 * FloorHeight > Y) {
				
				View lay = (View)findViewById(R.id.mainLayout);
				lay.startAnimation(fade);
				
				CurrentStage = generator.nextInt(2);
				PreviousStage = 2;
				sceneProvider
						.setCurrentScene(generator.nextInt(4));
				
				
				CrazyCastle.setScene(sceneProvider.getCurrentScene());
				list = sceneProvider.getCurrentDoorPosition();
				pt = list.get(CurrentStage);
				ref.setX(pt.DoorStart);
				ref.setY(Y_Position_Is_Essential(CurrentStage));
				character.setMirrorProperly(refMirror, ref);
				
			}
			// move character left/right
			if (character.CurrentCharacterEntity.getX() > event.getX()) {
				character.setAnimation(GESTURE.RIGHTWALK);
				character.DirectionLeft = true;
				character.setWalkStatus(GESTURE.LEFTWALK);
			} else if (character.CurrentCharacterEntity.getX() < event.getX()) {

				character.setAnimation(GESTURE.LEFTWALK);
				character.DirectionLeft = false;
				character.setWalkStatus(GESTURE.RIGHTWALK);
			}
		}
		return true;
	}

	private int Y_Position_Is_Essential(int stage) {
		return (5 + 6 * stage - 3) * CastleEngine.Floor_Height;
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
		if (id == R.id.action_settings) {
			// TODO: Options if needed
		}

		return super.onOptionsItemSelected(item);
	}

	public void RestartGame() {
		ImageView bugsL = (ImageView) findViewById(R.id.Bunny);
		ImageView bugsR = (ImageView) findViewById(R.id.Bunny_Mirror);
		bugsL.setX(X0);
		bugsL.setY(2 * CastleEngine.Floor_Height);
		character.setMirrorProperly(bugsR, bugsL);
		AnimationDrawable animR = (AnimationDrawable) bugsL.getDrawable();
		animR.start();
		AnimationDrawable animL = (AnimationDrawable) bugsR.getDrawable();
		animL.start();
	}

	public void getX0(float x) {
		X0 = x;
	}

	public void EvilWalk() {
		if (CollectedCarrots > 3) {
			ImageView evilRIGHT = (ImageView) findViewById(R.id.Evil_Mirror);
			ImageView evilLEFT = (ImageView) findViewById(R.id.Evil);
			ImageView ref1 = (ImageView) findViewById(R.id.Bunny_Mirror);
			ImageView ref2 = (ImageView) findViewById(R.id.Bunny);
			ImageView ref = (DirectionLeft) ? ref1 : ref2;
			// TODO:create Bugs death and add change the anim when he
			// TODO: collides with Evil Rabbit. Also bitmap failgame could be
			// added

			if (DirectionLeftForEvil) {
				evilLEFT.setX(evilLEFT.getX() + 1.5f);
				evilRIGHT.setX(evilLEFT.getX());
				evilLEFT.setY((17 - 3) * CastleEngine.Floor_Height);
				evilRIGHT.setY(evilLEFT.getY() + HEIGHT);
				if (evilLEFT.getX() > CastleEngine.ScreenWidth - 30)
					DirectionLeftForEvil = false;
			} else if (!DirectionLeftForEvil) {
				evilLEFT.setX(evilLEFT.getX() - 1.5f);
				evilRIGHT.setX(evilLEFT.getX());
				evilRIGHT.setY((17 - 3) * CastleEngine.Floor_Height);
				evilLEFT.setY(evilLEFT.getY() + HEIGHT);
				if (evilLEFT.getX() < 0)
					DirectionLeftForEvil = true;
			}
			AnimationDrawable animL = (AnimationDrawable) evilRIGHT
					.getDrawable();
			animL.start();
			AnimationDrawable animR = (AnimationDrawable) evilLEFT
					.getDrawable();
			animR.start();

		}
	}
}

class Point {
	float x, y;

	// public Point(){}
	public Point(float dx, float dy) {
		x = dx;
		y = dy;
	}

	@Override
	public String toString() {
		return x + ", " + y;
	}
}
