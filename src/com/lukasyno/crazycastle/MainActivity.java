package com.lukasyno.crazycastle;

import java.security.Provider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.lukasyno.crazycastle.CastleEngine.WALL;
import com.lukasyno.crazycastle.Character.GESTURE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * TODO: castle engine fix door moving around fix
 * 
 * @author lukasz
 * 
 */
public class MainActivity extends Activity {
	/**
	 * custom classes for game!!!1
	 */
	public CastleEngine CrazyCastle;
//	public SceneProvider Provider = new SceneProvider(4, 480);
	Character character;

	private final int ROOMS_COUNT = 4;
	private int ScreenWidth;
	private List<Integer> PadlockList = new ArrayList<Integer>();
	private BasicScene[] AllRooms  = new BasicScene[ROOMS_COUNT];
	private BasicScene currentScene;
	
	
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

	@SuppressLint("UseValueOf")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		fillPadlockSet();
		onPopulateCastle();
		
		CrazyCastle = (CastleEngine) findViewById(R.id.castle_view);
		CrazyCastle.setBackground(getResources()
				.getDrawable(R.drawable.wall));
		
		
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

	// public void setMirrorProperly(ImageView invisible, ImageView visible){
	// invisible.setY(visible.getY()-character.DEATH_ZONE);
	// invisible.setX(visible.getX());
	// }
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
			ArrayList<PointAndStage> list = CrazyCastle.DoorsCoordinates;
			// pt helps to detect when bugs is passing the door:
			PointAndStage pt = list.get(CurrentStage);
			float FH = CastleEngine.Floor_Height;
			float Y = event.getY() - FingerSize;
			// if we want to go downstairs to stage 1:
			if (CurrentStage == 0 && ref.getX() > pt.DoorStart
					&& ref.getX() < pt.DoorEnd && 6 * FH < Y) {
				CurrentStage = 1;
				PreviousStage = 0;
				
				//BasicScene sc = new BasicScene(1, Color.BLUE, Color.WHITE);
//				Provider.setCurrentScene(0);
				setCurrentScene(1);
				CrazyCastle.setScene(currentScene);
				
				pt = list.get(CurrentStage);
				ref.setX(pt.DoorStart);
				ref.setY((11 - 3) * FH);
				character.setMirrorProperly(refMirror, ref);
			}
			// if we want to go downstairs to stage 2:
			else if (CurrentStage == 1 && ref.getX() > pt.DoorStart
					&& ref.getX() < pt.DoorEnd && 12 * FH < Y) {
				CurrentStage = 2;
				PreviousStage = 1;
				
				setCurrentScene(2);
				CrazyCastle.setScene(currentScene);
				
				pt = list.get(CurrentStage);
				ref.setX(pt.DoorStart);
				ref.setY((17 - 3) * FH);
				character.setMirrorProperly(refMirror, ref);
			}

			// if we want to go upstairs to stage 0:
			else if (CurrentStage == 1 && ref.getX() > pt.DoorStart
					&& ref.getX() < pt.DoorEnd && 6 * FH > Y) {
				CurrentStage = 0;
				PreviousStage = 1;
				
				setCurrentScene(3);
				CrazyCastle.setScene(currentScene);
				
//				Provider.setCurrentScene(1);
//				CrazyCastle.setScene(Provider.getCurrentScene());
				
				//CrazyCastle.setBackgroundWall(WALL.PRISON);

				pt = list.get(CurrentStage);
				ref.setX(pt.DoorStart);
				ref.setY((5 - 3) * FH);
				character.setMirrorProperly(refMirror, ref);
			}

			// if we want to go upstairs to stage 1:
			else if (CurrentStage == 2 && ref.getX() > pt.DoorStart
					&& ref.getX() < pt.DoorEnd && 12 * FH > Y) {
				CurrentStage = 1;
				PreviousStage = 2;

				setCurrentScene(0);
				CrazyCastle.setScene(currentScene);
								

				//CrazyCastle.setBackgroundWall(WALL.WIZARD);
				
				pt = list.get(CurrentStage);
				ref.setX(pt.DoorStart);
				ref.setY((11 - 3) * FH);
				character.setMirrorProperly(refMirror, ref);
			}
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
		bugsL.setY(2 * CrazyCastle.Floor_Height);
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
				evilLEFT.setY((17 - 3) * CrazyCastle.Floor_Height);
				evilRIGHT.setY(evilLEFT.getY() + HEIGHT);
				if (evilLEFT.getX() > CrazyCastle.ScreenWidth - 30)
					DirectionLeftForEvil = false;
			} else if (!DirectionLeftForEvil) {
				evilLEFT.setX(evilLEFT.getX() - 1.5f);
				evilRIGHT.setX(evilLEFT.getX());
				evilRIGHT.setY((17 - 3) * CrazyCastle.Floor_Height);
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
	public void setCurrentScene(int id){
		currentScene = AllRooms[id];
	}
	private void fillPadlockSet() {
		PadlockList = new ArrayList<Integer>();
		for (int j = 0; j < 3*ROOMS_COUNT; j++) {
			PadlockList.add(j);
		}
		Collections.shuffle(PadlockList, generator);
	}
	private int randomColor(){
		return generator.nextInt();
	}
	private void onPopulateCastle() {
		for (int i = 0; i < ROOMS_COUNT; i++) {
			AllRooms[i] = new BasicScene(i);
			AllRooms[i].setRandomBuddies((Integer) PadlockList.get(3 * i),
					(Integer) PadlockList.get(3 * i + 1),
					(Integer) PadlockList.get(3 * i + 2));
			AllRooms[i].setValues(generator, ScreenWidth);
		}
		currentScene = AllRooms[0];
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
